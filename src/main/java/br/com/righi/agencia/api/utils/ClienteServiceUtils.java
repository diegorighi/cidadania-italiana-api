package br.com.righi.agencia.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;
import br.com.righi.agencia.api.entities.Cliente;
import br.com.righi.agencia.api.forms.ClienteEnderecoFormRecord;

@Component
@PropertySource("classpath:mensagens.properties")
public class ClienteServiceUtils {
	
	@Autowired
	private InsereClienteMensagem mensagemInsert;
	
	@Autowired
	private AlteraSenhaMensagem mensagemSenhaUpdate;
	
	@Autowired
	private AlteraEnderecoMensagem mensagemEnderecoUpdate;

	public void criaRetornoInserirCliente(ClienteMensagemDTO cliente) {
		mensagemInsert.build(cliente);
	}
	
	public void criaRetornoAlterarSenha(ClienteMensagemDTO cliente) {
		mensagemSenhaUpdate.build(cliente);
	}
	
	public void criaRetornoAlteraEndereco(ClienteMensagemDTO cliente) {
		mensagemEnderecoUpdate.build(cliente);
	}
	
	public void bindEndereco(Cliente mongoDbCliente, ClienteEnderecoFormRecord enderecoForm) {
		mongoDbCliente.getEndereco().setLogradouro(enderecoForm.logradouro());
		mongoDbCliente.getEndereco().setNumeroLogradouro(enderecoForm.numeroLogradouro());
		mongoDbCliente.getEndereco().setComplemento(enderecoForm.complemento());
		mongoDbCliente.getEndereco().setCidade(enderecoForm.cidade());
		mongoDbCliente.getEndereco().setUf(enderecoForm.uf());
		mongoDbCliente.getEndereco().setCep(enderecoForm.cep());
	}
	
}
