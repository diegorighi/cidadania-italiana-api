package br.com.righi.agencia.api.forms;

import jakarta.annotation.Nonnull;

public record ClienteEnderecoFormRecord(
		@Nonnull String cpf,
		@Nonnull String logradouro,
		@Nonnull int numeroLogradouro,
		@Nonnull String complemento,
		@Nonnull String cidade,
		@Nonnull String uf,
		@Nonnull String cep
	    ) {

}
