package br.com.righi.agencia.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.righi.agencia.api.dto.ClienteDTO;
import br.com.righi.agencia.api.forms.ClienteForm;

@Component
@PropertySource("classpath:mensagens.properties")
public class ClienteServiceUtils {
	
	@Value("${mensagem.retorno.api.sucesso}")
	private String mensagemSucesso;
	
	@Value("${mensagem.retorno.api.usuario.existente}")
	private String mensagemUsuarioExistente;
	
	public ClienteDTO criaRetorno(ClienteForm formularioCliente, Boolean sucesso) {
		ClienteDTO mensagemRetorno = 
				new ClienteDTO(this.mensagemUsuarioExistente, formularioCliente.getPrimeiroNome(), 
						formularioCliente.getSegundoNome(), formularioCliente.getSobrenome(), 
						formularioCliente.getEmail(), sucesso);
		return mensagemRetorno;
	}
	
	public String mensagemSucesso() {
		return this.mensagemSucesso;
	}

}
