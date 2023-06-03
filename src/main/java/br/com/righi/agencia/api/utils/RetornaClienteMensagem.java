package br.com.righi.agencia.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;
import br.com.righi.agencia.api.dto.RetornoMensagem;

@Component
@PropertySource("classpath:mensagens.properties")
public class RetornaClienteMensagem implements RetornoMensagem<ClienteMensagemDTO> {

	@Value("${mensagem.cliente.existente.ok}")
	private String mensagemClienteExisteOK;
	
	@Value("${mensagem.cliente.existente.nok}")
	private String mensagemClienteExisteNOK;
	
	@Override
	public void build(ClienteMensagemDTO statusRetorno) {
		if(statusRetorno.getReturnStatus()) {
			statusRetorno.setMensagemStatus(this.mensagemClienteExisteOK);
		}else {
			statusRetorno.setMensagemStatus(this.mensagemClienteExisteNOK);
		}
		
	}

}
