package br.com.righi.agencia.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;
import br.com.righi.agencia.api.dto.RetornoMensagem;

@Component
@PropertySource("classpath:mensagens.properties")
public class InsereClienteMensagem implements RetornoMensagem<ClienteMensagemDTO> {
	
	@Value("${mensagem.cliente.inserir.ok}")
	private String mensagemInsertOK;
	
	@Value("${mensagem.cliente.inserir.nok}")
	private String mensagemInsertNOK;
	
	@Override
	public void build(ClienteMensagemDTO statusRetorno) {
		if(statusRetorno.getReturnStatus()) {
			statusRetorno.setMensagemStatus(this.mensagemInsertOK);
		}else {
			statusRetorno.setMensagemStatus(this.mensagemInsertNOK);
		}
	}

}
