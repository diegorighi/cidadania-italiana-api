package br.com.righi.agencia.api.exceptions;

import java.io.FileNotFoundException;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

@Getter
@Configuration
@PropertySource("classpath:mensagens.properties")
public class ClienteSenhaForaPadroes extends FileNotFoundException {

	private static final long serialVersionUID = 1952871856648127367L;

	private String mensagem = "A senha informada deve conter entre 8 e 16 caracteres ";
	private String cpf = null;
	
	@Deprecated
	public ClienteSenhaForaPadroes(){}
	
	public ClienteSenhaForaPadroes(String cpf, String senha) {
		this.cpf = cpf;
		this.mensagem = this.getMensagem() + "para o cpf: "+cpf+" com valores: "+senha;
	}
}
