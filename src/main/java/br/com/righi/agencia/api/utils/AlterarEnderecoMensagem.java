package br.com.righi.agencia.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;
import br.com.righi.agencia.api.dto.RetornoMensagem;

@Component
@PropertySource("classpath:mensagens.properties")
public class AlterarEnderecoMensagem implements RetornoMensagem {

	@Value("${mensagem.cliente.endereco.alterado.ok}")
	private String mensagemAlterarEnderecoOK;
	
	@Value("${mensagem.cliente.endereco.alterado.nok}")
	private String mensagemAlterarEnderecoNOK;
	
	@Override
	public void build(ClienteMensagemDTO statusRetorno) {
		if(statusRetorno.getReturnStatus()) {
			statusRetorno.setMensagemStatus(this.mensagemAlterarEnderecoOK);
		}else {
			statusRetorno.setMensagemStatus(this.mensagemAlterarEnderecoNOK);
		}
	}

}
