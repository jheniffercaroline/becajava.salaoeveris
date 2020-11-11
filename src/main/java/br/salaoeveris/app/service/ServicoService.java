package br.salaoeveris.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.salaoeveris.app.model.Servico;
import br.salaoeveris.app.repository.ServicoRepository;
import br.salaoeveris.app.request.ServicoRequest;
import br.salaoeveris.app.response.BaseResponse;
import br.salaoeveris.app.response.ListServicoResponse;
import br.salaoeveris.app.response.ServicoResponse;

@Service
public class ServicoService {

	final ServicoRepository _repository;

	@Autowired
	private ServicoService(ServicoRepository repository) {
		_repository = repository;
	}

	public BaseResponse inserir(ServicoRequest servicoRequest) {
		Servico servico = new Servico();
		BaseResponse base = new ServicoResponse();
		base.StatusCode = 400;

		if (servicoRequest.getNome() == "" || servicoRequest.getNome() == null) {
			base.Message = "Servico não encontrado ! ";
			return base;

		}
		servico.setNome(servicoRequest.getNome());
		servico.setValor(servicoRequest.getValor());

		_repository.save(servico);

		base.StatusCode = 201;
		base.Message = "Servico cadastrado com sucesso";
		return base;

	}

	public ServicoResponse obter(Long id) {
		Optional<Servico> servico = _repository.findById(id);
		ServicoResponse response = new ServicoResponse();
		response.StatusCode = 400;

		if (servico.get().getId() == 0) {
			response.Message = "Serviço não localizado!";
			return response;
		}

		if (servico.get().getNome() == "") {
			response.Message = "Nome de serviço não localizado";
			return response;
		}

		if (servico.get().getValor() == 0) {
			response.Message = "Valor de serviço não localizado!";
			return response;
		}

		response.setId(id);
		response.setNome(servico.get().getNome());
		response.setValor(servico.get().getValor());
		response.StatusCode = 200;
		response.Message = "Serviço obtido com sucesso!";
		return response;
	}

	public ListServicoResponse listar() {

		List<Servico> lista = _repository.findAll();

		List<ServicoResponse> servicoResponse = new ArrayList<ServicoResponse>();

		for (Servico servico : lista) {
			ServicoResponse listServicoResponse = new ServicoResponse();

			listServicoResponse.setId(servico.getId());
			listServicoResponse.setNome(servico.getNome());
			listServicoResponse.setValor(servico.getValor());

			servicoResponse.add(listServicoResponse);

		}
		ListServicoResponse response = new ListServicoResponse();

		response.setServicos(servicoResponse);
		response.StatusCode = 200;
		response.Message = "Servicos obtidos com sucesso.";

		return response;

	}
}