package br.com.righi.agencia.api.forms;

import jakarta.annotation.Nonnull;

public record ClienteEmailFormRecord(
		@Nonnull String cpf,
		@Nonnull String email
		) {

}
