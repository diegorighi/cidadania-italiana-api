package br.com.righi.agencia.api.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mindrot.jbcrypt.BCrypt;

import br.com.righi.agencia.api.entities.Cliente;
import br.com.righi.agencia.api.entities.Contato;
import br.com.righi.agencia.api.entities.Credencial;
import br.com.righi.agencia.api.entities.Documento;
import br.com.righi.agencia.api.entities.Endereco;
import br.com.righi.agencia.api.entities.Pessoa;
import br.com.righi.agencia.api.forms.ClienteForm;

public class ClienteHandler implements EntityHandler<Cliente, ClienteForm> {

	@Override
	public Cliente formToEntity(ClienteForm form) {
		
		Pessoa novaPessoa = geraPessoa(form);
		Documento novoDocumento = geraDocumento(form);
		Endereco novoEndereco = geraEndereco(form);
		Contato novoContato = geraContato(form);
		Credencial novaCredencial = geraCredencial(form);
		
		return new Cliente(novaPessoa, novoDocumento, novoEndereco, novoContato, novaCredencial);
	}


	
	
	
	
	
	
	
	
	
	

	private Credencial geraCredencial(ClienteForm form) {
		String password = BCrypt.hashpw(form.getSenha(), BCrypt.gensalt());		
		return new Credencial(form.getLogin(), password);
	}
	
	private Contato geraContato(ClienteForm form) {
		return new Contato(form.getEmail(), form.getCelular());
	}

	private Endereco geraEndereco(ClienteForm form) {
		return new Endereco(form.getLogradouro(), form.getNumeroLogradouro(), form.getComplemento(), form.getCidade(), form.getUf(), form.getCep());
	}

	private Documento geraDocumento(ClienteForm form) {
		return new Documento(form.getCpf().replaceAll(".", "").replaceAll("-", ""));
	}

	private Pessoa geraPessoa(ClienteForm form) {
		String formatoData = "dd/MM/yyyy";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatoData);
		LocalDate data = LocalDate.parse(form.getDataNascimento(), formatter);
		
		return new Pessoa(form.getPrimeiroNome(), form.getSegundoNome(), form.getSobrenome(), data);
	}


}
