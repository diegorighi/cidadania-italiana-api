package br.com.righi.agencia.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;
import br.com.righi.agencia.api.dto.RetornoMensagem;

@Component
@PropertySource("classpath:mensagens.properties")
public class AlterarSenhaMensagem implements RetornoMensagem<ClienteMensagemDTO> {

	@Value("${mensagem.cliente.credencial.senha.alterada.ok}")
	private String mensagemAlterarSenhaOK;
	
	@Value("${mensagem.cliente.credencial.senha.alterada.nok}")
	private String mensagemAlterarSenhaNOK;
	
	@Override
	public void build(ClienteMensagemDTO statusRetorno) {
		if(statusRetorno.getReturnStatus()) {
			statusRetorno.setMensagemStatus(this.mensagemAlterarSenhaOK);
		}else {
			statusRetorno.setMensagemStatus(this.mensagemAlterarSenhaNOK);
		}
		
	}

}
