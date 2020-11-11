package br.salaoeveris.app.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.salaoeveris.app.model.Cliente;
import br.salaoeveris.app.repository.ClienteRepository;
import br.salaoeveris.app.request.ClienteRequest;
import br.salaoeveris.app.response.BaseResponse;
import br.salaoeveris.app.response.ClienteResponse;
import br.salaoeveris.app.response.ListClienteResponse;

@Service
public class ClienteService {

	final ClienteRepository _repository;

	@Autowired
	private ClienteService(ClienteRepository repository) {
		_repository = repository;
	}

	public BaseResponse inserir(ClienteRequest clientRequest) {
		Cliente cliente = new Cliente();
		BaseResponse base = new BaseResponse();
		base.StatusCode = 400;

		if (clientRequest.getNome() == "" || clientRequest.getNome() == null) {
			base.Message = "Nome do cliente não informado!";
			return base;
		}
		if (clientRequest.getCpf() == "" || clientRequest.getCpf() == null) {
			base.Message = "CPF do cliente não informado!";
			return base;
		}
		if (clientRequest.getEndereco() == "" || clientRequest.getEndereco() == null) {
			base.Message = "Endereço do cliente não informado!";
			return base;
		}
		if (clientRequest.getTelefone() == "" || clientRequest.getTelefone() == null) {
			base.Message = "Telefone do cliente não informado!";
			return base;
		}

		cliente.setNome(clientRequest.getNome());
		cliente.setCpf(clientRequest.getCpf());
		cliente.setEndereco(clientRequest.getEndereco());
		cliente.setTelefone(clientRequest.getTelefone());

		_repository.save(cliente);

		base.StatusCode = 201;
		base.Message = "Cliente inserido com sucesso!";
		return base;

	}

	public ClienteResponse obter(Long id) {

		Optional<Cliente> cliente = _repository.findById(id);
		ClienteResponse response = new ClienteResponse();

		if (cliente.isEmpty()) {
			response.Message = "Cliente não encontrado";
			response.StatusCode = 400;
			return response;
		}

		response.setId(cliente.get().getId());
		response.setNome(cliente.get().getNome());
		response.setEndereco(cliente.get().getEndereco());
		response.setTelefone(cliente.get().getTelefone());
		response.Message = "Cliente obtido com sucesso!";
		response.StatusCode = 200;

		return response;

	}

	public ListClienteResponse listar() {

		List<Cliente> lista = _repository.findAll();

		List<ClienteResponse> clienteResponse = new ArrayList<ClienteResponse>();

		for (Cliente cliente : lista) {
			ClienteResponse clienteResponseList = new ClienteResponse();

			clienteResponseList.setNome(cliente.getNome());
			clienteResponseList.setEndereco(cliente.getEndereco());
			clienteResponseList.setTelefone(cliente.getTelefone());
			clienteResponseList.setId(cliente.getId());

			clienteResponse.add(clienteResponseList);

		}

		ListClienteResponse response = new ListClienteResponse();
		response.setClientes(clienteResponse);
		response.StatusCode = 200;
		response.Message = "Clientes obtidos com sucesso";

		return response;

	}

}
