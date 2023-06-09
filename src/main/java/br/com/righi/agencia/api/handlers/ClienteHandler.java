package br.com.righi.agencia.api.handlers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.righi.agencia.api.entities.Cliente;
import br.com.righi.agencia.api.entities.Contato;
import br.com.righi.agencia.api.entities.Credencial;
import br.com.righi.agencia.api.entities.Documento;
import br.com.righi.agencia.api.entities.Endereco;
import br.com.righi.agencia.api.entities.Pessoa;
import br.com.righi.agencia.api.forms.ClienteFormRecord;

@Component
@PropertySource("classpath:mensagens.properties")
public class ClienteHandler implements EntityHandler<Cliente, ClienteFormRecord> {
	
	@Value("${localdate.formato}")
	private String formatoData;
	
	@Override
	public Cliente formToEntity(ClienteFormRecord form) {
		
		Pessoa novaPessoa = geraPessoa(form);
		Documento novoDocumento = geraDocumento(form);
		Endereco novoEndereco = geraEndereco(form);
		Contato novoContato = geraContato(form);
		Credencial novaCredencial = geraCredencial(form);
		
		return new Cliente(novaPessoa, novoDocumento, novoEndereco, novoContato, novaCredencial);
		
		
	}


	
	
	
	
	
	// NÃO UTILIZAR ESTES MÉTODOS PRIVADOS. 
	// Utilize apenas os métodos públicos em caso de nova implementação

	/**
	 * Wrapper que retorna uma credencial já encriptada
	 * @param form
	 * @return Credencial.class
	 */
	private Credencial geraCredencial(ClienteFormRecord form) {
		String password = BCrypt.hashpw(form.credencialSenha(), BCrypt.gensalt());		
		return new Credencial(form.credencialLogin(), password);
	}
	
	/**
	 * Wrapper que retorna um contato
	 * @param form
	 * @return Contato.class
	 */
	private Contato geraContato(ClienteFormRecord form) {
		return new Contato(form.contatoEmail(), form.contatoCelular());
	}

	/**
	 * Wrapper que retorna um endereco
	 * @param form
	 * @return Endereco.class
	 */
	private Endereco geraEndereco(ClienteFormRecord form) {
		return new Endereco(form.enderecoLogradouro(), form.enderecoNumeroLogradouro(), 
				form.enderecoComplemento(), form.enderecoCidade(), 
				form.enderecoUf(), form.enderecoCep());
	}

	/**
	 * Wrapper que retorna um documento
	 * @param form
	 * @return Documento.class
	 */
	private Documento geraDocumento(ClienteFormRecord form) {
		return new Documento(form.documentoCpf().replaceAll("[^0-9]", ""));
	}

	/**
	 * Wrapper que retorna uma pessoa
	 * @param form
	 * @return Pessoa.class
	 */
	private Pessoa geraPessoa(ClienteFormRecord form) {
		return new Pessoa(form.pessoaPrimeiroNome(), form.pessoaSegundoNome(), 
				form.pessoaSobrenome(), form.pessoaDataNascimento());
	}


}
