package br.com.righi.agencia.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.righi.agencia.api.entities.Cliente;
import br.com.righi.agencia.api.forms.ClienteForm;
import br.com.righi.agencia.api.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public void cadastrarCliente(ClienteForm formularioCliente) {
		Cliente clienteExistente = null;
		clienteExistente = repository.findByPessoaDocumentoCpf(formularioCliente.getCpf());
		
		if(clienteExistente != null) {
			
		}
		
	}
	
}
