package br.salaoeveris.app.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AgendamentoRequest {

	private LocalDateTime dataHora;
	private Long idCliente;
	private Long idServico;

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

}
