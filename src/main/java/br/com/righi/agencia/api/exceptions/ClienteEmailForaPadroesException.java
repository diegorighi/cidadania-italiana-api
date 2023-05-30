package br.com.righi.agencia.api.exceptions;

import java.io.FileNotFoundException;

import br.com.righi.agencia.api.forms.ClienteEmailFormRecord;
import lombok.Getter;

@Getter
public class ClienteEmailForaPadroesException extends FileNotFoundException {

	private static final long serialVersionUID = 6550621450115593168L;

	private String mensagem = "O email informado não é válido ";
	private String cpf = null;
	
	public ClienteEmailForaPadroesException(ClienteEmailFormRecord formulario) {
		this.cpf = formulario.cpf();
		this.mensagem = this.mensagem + "para o cpf: "+formulario.cpf()+" com valores: "+formulario.email();
	}
}
