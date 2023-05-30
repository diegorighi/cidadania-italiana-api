package br.com.righi.agencia.api.forms;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;

public record ClienteSenhaFormRecord(
		String cpf,
		@Nonnull @Size(min=8, max=16)
		String senha
		) {

}
