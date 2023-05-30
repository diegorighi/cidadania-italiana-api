package br.com.righi.agencia.api.exceptions;

import java.io.FileNotFoundException;

import lombok.Getter;

@Getter
public class ClienteSenhaForaPadroesException extends FileNotFoundException {

	private static final long serialVersionUID = 1952871856648127367L;

	private String mensagem = "A senha informada deve conter entre 8 e 16 caracteres ";
	private String cpf = null;
	
	@Deprecated
	public ClienteSenhaForaPadroesException(){}
	
	public ClienteSenhaForaPadroesException(String cpf, String senha) {
		this.cpf = cpf;
		this.mensagem = this.getMensagem() + "para o cpf: "+cpf+" com valores: "+senha;
	}
}
