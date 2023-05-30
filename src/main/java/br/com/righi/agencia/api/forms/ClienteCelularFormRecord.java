package br.com.righi.agencia.api.forms;

import jakarta.annotation.Nonnull;

public record ClienteCelularFormRecord(
		@Nonnull String cpf,
		@Nonnull String celular
		) {

}
