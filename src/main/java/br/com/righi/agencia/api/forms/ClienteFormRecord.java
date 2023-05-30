package br.com.righi.agencia.api.forms;

import jakarta.annotation.Nonnull;

public record ClienteFormRecord(
		@Nonnull String primeiroNome,
	    String segundoNome,
	    @Nonnull String sobrenome,
	    @Nonnull String dataNascimento,
	    @Nonnull String cpf,
	    @Nonnull String logradouro,
	    @Nonnull int numeroLogradouro,
	    @Nonnull String complemento,
	    @Nonnull String cidade,
	    @Nonnull String uf,
	    @Nonnull String cep,
	    @Nonnull String email,
	    @Nonnull String celular,
	    @Nonnull String login,
	    @Nonnull String senha
		) {

}
