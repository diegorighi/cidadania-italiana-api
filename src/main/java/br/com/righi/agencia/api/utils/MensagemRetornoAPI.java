package br.com.righi.agencia.api.utils;

public abstract class MensagemRetornoAPI {

	protected MensagemRetornoAPI proximaMensagem;
	
	public MensagemRetornoAPI(MensagemRetornoAPI proximaMensagem) {
		this.proximaMensagem = proximaMensagem;
	}
	
}
