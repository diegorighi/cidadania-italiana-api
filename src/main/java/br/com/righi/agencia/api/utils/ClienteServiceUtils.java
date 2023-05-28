package br.com.righi.agencia.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;

@Component
@PropertySource("classpath:mensagens.properties")
public class ClienteServiceUtils {
	
	@Autowired
	private InsereClienteMensagem mensagemInsert;
	
	@Autowired
	private AlteraSenhaMensagem mensagemUpdate;

	public void criaRetornoInserirCliente(ClienteMensagemDTO cliente) {
		mensagemInsert.build(cliente);
	}
	
	public void criaRetornoAlterarSenha(ClienteMensagemDTO cliente) {
		mensagemUpdate.build(cliente);
	}
	
}
