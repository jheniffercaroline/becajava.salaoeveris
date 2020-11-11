package br.salaoeveris.app.request;

import java.time.LocalDate;

public class AgendamentoRequest {
	private LocalDate dataHora;

	public LocalDate getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDate dataHora) {
		this.dataHora = dataHora;
	}

}
