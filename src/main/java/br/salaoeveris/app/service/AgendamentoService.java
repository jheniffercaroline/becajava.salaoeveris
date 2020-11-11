package br.salaoeveris.app.service;

import org.springframework.stereotype.Service;

import br.salaoeveris.app.model.Agendamento;
import br.salaoeveris.app.model.Cliente;
import br.salaoeveris.app.model.Servico;
import br.salaoeveris.app.repository.AgendamentoRepository;
import br.salaoeveris.app.request.AgendamentoRequest;
import br.salaoeveris.app.response.BaseResponse;

@Service
public class AgendamentoService {

	final AgendamentoRepository _repository;

	public AgendamentoService(AgendamentoRepository repository) {
		_repository = repository;

	}

	public BaseResponse inserir(AgendamentoRequest agendamentoRequest) {

		BaseResponse response = new BaseResponse();
		
		Agendamento agendamento = new Agendamento();
		
		response.StatusCode = 400;

		if (agendamentoRequest.getDataHora() == null) {
			response.Message = "Data do Agendamento não inserida.";
			return response;
		} else if (agendamentoRequest.getIdCliente() == null || agendamentoRequest.getIdCliente() == 0) {
			response.Message = "Cliente não inserido";
			return response;
		} else if (agendamentoRequest.getIdServico() == null || agendamentoRequest.getIdServico() == 0) {
			response.Message = "Serviço não Inserido";
			return response;
		}

		Cliente cliente = new Cliente();
		cliente.setId(agendamentoRequest.getIdCliente());
		agendamento.setCliente(cliente);
		
		Servico servico = new Servico();
		servico.setId(agendamentoRequest.getIdServico());
		agendamento.setServico(servico);
		
		agendamento.setDataHora(agendamentoRequest.getDataHora());

		_repository.save(agendamento);
		response.StatusCode = 201;
		response.Message = "Parabéns você concluiu seu agendamnto com sucesso.";

		return response;

	}

}