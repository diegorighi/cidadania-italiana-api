package br.com.righi.agencia.api.forms;

import java.time.LocalDate;

import jakarta.annotation.Nonnull;
import lombok.Getter;

@Getter
public class ClienteForm {

	@Nonnull private String primeiroNome = null;
    private String segundoNome = null;
    @Nonnull private String sobrenome = null;
    @Nonnull private LocalDate dataNascimento = null;
    @Nonnull private String cpf = null;
    @Nonnull private String logradouro = null;
    @Nonnull private int numeroLogradouro = 0;
    @Nonnull private String complemento = null;
    @Nonnull private String cidade = null;
    @Nonnull private String uf = null;
    @Nonnull private String cep = null;
    @Nonnull private String email = null;
    @Nonnull private String celular = null;
    @Nonnull private String login = null;
    @Nonnull private String senha = null;
    
	public ClienteForm(String primeiroNome, String segundoNome, String sobrenome, LocalDate dataNascimento, String cpf,
			String logradouro, int numeroLogradouro, String complemento, String cidade, String uf, String cep,
			String email, String celular, String login, String senha) {
		this.primeiroNome = primeiroNome;
		this.segundoNome = segundoNome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.logradouro = logradouro;
		this.numeroLogradouro = numeroLogradouro;
		this.complemento = complemento;
		this.cidade = cidade;
		this.uf = uf;
		this.cep = cep;
		this.email = email;
		this.celular = celular;
		this.login = login;
		this.senha = senha;
	}
    
}
