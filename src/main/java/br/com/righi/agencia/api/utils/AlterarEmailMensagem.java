package br.com.righi.agencia.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;
import br.com.righi.agencia.api.dto.RetornoMensagem;

@Component
@PropertySource("classpath:mensagens.properties")
public class AlterarEmailMensagem implements RetornoMensagem<ClienteMensagemDTO> {

	@Value("${mensagem.cliente.contato.email.alterado.ok}")
	private String mensagemAlterarEmailOK;
	
	@Value("${mensagem.cliente.contato.email.alterado.nok}")
	private String mensagemAlterarEmailNOK;
	
	@Override
	public void build(ClienteMensagemDTO statusRetorno) {
		if(statusRetorno.getReturnStatus()) {
			statusRetorno.setMensagemStatus(this.mensagemAlterarEmailOK);
		}else {
			statusRetorno.setMensagemStatus(this.mensagemAlterarEmailNOK);
		}
	}

}
