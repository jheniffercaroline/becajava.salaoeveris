package br.salaoeveris.app.response;

import java.util.List;

public class ListServicoResponse extends BaseResponse {

	private List<ServicoResponse> Servicos;

	public List<ServicoResponse> getServicos() {
		return Servicos;
	}

	public void setServicos(List<ServicoResponse> servicos) {
		Servicos = servicos;
	}

}