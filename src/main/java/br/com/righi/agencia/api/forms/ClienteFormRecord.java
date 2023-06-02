package br.com.righi.agencia.api.forms;

import jakarta.annotation.Nonnull;

public record ClienteFormRecord(
		@Nonnull String pessoaPrimeiroNome,
	    String pessoaSegundoNome,
	    @Nonnull String pessoaSobrenome,
	    @Nonnull String pessoaDataNascimento,
	    @Nonnull String documentoCpf,
	    @Nonnull String enderecoLogradouro,
	    @Nonnull int enderecoNumeroLogradouro,
	    @Nonnull String enderecoComplemento,
	    @Nonnull String enderecoCidade,
	    @Nonnull String enderecoUf,
	    @Nonnull String enderecoCep,
	    @Nonnull String contatoEmail,
	    @Nonnull String contatoCelular,
	    @Nonnull String credencialLogin,
	    @Nonnull String credencialSenha) {
	
		@Deprecated
		public ClienteFormRecord() {
			this(null, null, null, null, null, null, 0, 
					null, null, null, null, null, null, null, null);
		} 

}
