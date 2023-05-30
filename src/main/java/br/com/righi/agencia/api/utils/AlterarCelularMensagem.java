package br.com.righi.agencia.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;
import br.com.righi.agencia.api.dto.RetornoMensagem;

@Component
@PropertySource("classpath:mensagens.properties")
public class AlterarCelularMensagem implements RetornoMensagem {

	@Value("${mensagem.cliente.contato.celular.alterado.ok}")
	private String mensagemAlterarCelularOK;
	
	@Value("${mensagem.cliente.contato.celular.alterado.ok}")
	private String mensagemAlterarCelularNOK;
	
	@Override
	public void build(ClienteMensagemDTO statusRetorno) {
		if(statusRetorno.getReturnStatus()) {
			statusRetorno.setMensagemStatus(this.mensagemAlterarCelularOK);
		}else {
			statusRetorno.setMensagemStatus(this.mensagemAlterarCelularNOK);
		}
	}

}
