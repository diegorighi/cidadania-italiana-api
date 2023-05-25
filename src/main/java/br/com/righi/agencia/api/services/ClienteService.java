package br.com.righi.agencia.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import br.com.righi.agencia.api.dto.ClienteDTO;
import br.com.righi.agencia.api.entities.Cliente;
import br.com.righi.agencia.api.forms.ClienteForm;
import br.com.righi.agencia.api.handlers.ClienteHandler;
import br.com.righi.agencia.api.repositories.ClienteRepository;

@Service
@PropertySource("classpath:mensagens.properties")
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Value("${mensagem.retorno.api.sucesso}")
	private String mensagemSucesso;
	
	@Value("${mensagem.retorno.api.usuario.existente}")
	private String mensagemUsuarioExistente;
	
	public ClienteDTO cadastrarCliente(ClienteForm formularioCliente) {
		Boolean sucesso = false;
		Optional<Cliente> clienteExistente = null;
		
		clienteExistente = repository.findClienteByCpf(formularioCliente.getCpf());
		
		//Se não existir usuário na base: inclua
		if(clienteExistente.isEmpty()) {
			ClienteHandler handler = new ClienteHandler();
			Cliente novoCliente = handler.formToEntity(formularioCliente);
			repository.save(novoCliente);
			sucesso = true;
		}
		
		//Prepara mensagem de retorno
		ClienteDTO mensagemRetorno = criaRetorno(formularioCliente, sucesso);
		if(sucesso) {
			mensagemRetorno.setMensagemStatus(this.mensagemSucesso);
		}
		
		return mensagemRetorno;
	}

	private ClienteDTO criaRetorno(ClienteForm formularioCliente, Boolean sucesso) {
		ClienteDTO mensagemRetorno = 
				new ClienteDTO(this.mensagemUsuarioExistente, formularioCliente.getPrimeiroNome(), 
						formularioCliente.getSegundoNome(), formularioCliente.getSobrenome(), 
						formularioCliente.getEmail(), sucesso);
		return mensagemRetorno;
	}
	
}

	