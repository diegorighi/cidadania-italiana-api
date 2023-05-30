package br.com.righi.agencia.api.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;
import br.com.righi.agencia.api.entities.Cliente;
import br.com.righi.agencia.api.exceptions.ClienteSenhaForaPadroes;
import br.com.righi.agencia.api.forms.ClienteForm;
import br.com.righi.agencia.api.forms.ClienteFormSenha;
import br.com.righi.agencia.api.handlers.ClienteHandler;
import br.com.righi.agencia.api.repositories.ClienteRepository;
import br.com.righi.agencia.api.utils.ClienteServiceUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ClienteServiceUtils utils;
	
	@Transactional
	public ClienteMensagemDTO cadastrarCliente(ClienteForm formularioCliente) {
		long startTime, endTime, totalTime = 0;
		ClienteMensagemDTO retornoAPI = new ClienteMensagemDTO();
		startTime = System.currentTimeMillis();
		
		log.info("###################################################");
		log.info("[PRIMARY SERVICE] Iniciando processamento");
		
		//Se não existir usuário na base: inclua, altere status de sucesso da inclusao para TRUE
		if(!clienteExisteBase(formularioCliente.getCpf())) {
			incluirCliente(formularioCliente);
			retornoAPI.setReturnStatus(Boolean.TRUE);
		}
		
		utils.criaRetornoInserirCliente(retornoAPI);
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		
		log.info(String.format("[PRIMARY SERVICE] Tempo total de processamento: %d ms", totalTime));
		log.info("###################################################");
		
		return retornoAPI;
	}
	
	private Boolean clienteExisteBase(String cpf) {
		if(repository.findDocumentoCpf(cpf.replaceAll("[^0-9]", "")).isPresent()) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}

	@CacheEvict("clienteCache")
	private void incluirCliente(ClienteForm formularioCliente) {
		log.info("[PRIMARY SERVICE] Limpando cache armazenado");
		log.info("[PRIMARY SERVICE] Iniciando persistencia no MongoDB");
		ClienteHandler handler = new ClienteHandler();
		Cliente novoCliente = handler.formToEntity(formularioCliente);
		repository.save(novoCliente);
	}
	
	@Cacheable("clienteCache")
	public Optional<Cliente> retornaClientePorCpf(String cpf) {
		long startTime, endTime, totalTime = 0;
		log.info("###################################################");
		log.info("[SECONDARY SERVICE] Iniciando pesquisa no MongoDB");
		startTime = System.currentTimeMillis();
		
		Optional<Cliente> retornoBanco = repository.findDocumentoCpf(cpf.replaceAll("[^0-9]", ""));
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		
		log.info(String.format("[SECONDARY SERVICE] Tempo total de verificação de registro: %d ms", totalTime));
		log.info("###################################################");
		
		return retornoBanco;
	}

	public Page<Cliente> retornarListaClientes(Pageable pageable){
		return repository.findAll(pageable);
	}

	@Transactional
	@CacheEvict("clienteCache")
	public ClienteMensagemDTO alterarSenhaCredencial(ClienteFormSenha formulario) {
		long startTime, endTime, totalTime = 0;
		startTime = System.currentTimeMillis();
		ClienteMensagemDTO retornoClienteMensagem = new ClienteMensagemDTO();
		
		log.info("###################################################");
		log.info("[PRIMARY SERVICE] Iniciando processamento");
		
		try {
			
			if(formulario.getSenha().length() < 8 || formulario.getSenha().length() > 16) {
				throw new ClienteSenhaForaPadroes(formulario.getCpf(), formulario.getSenha());
			}
			
			if(clienteExisteBase(formulario.getCpf())) {
				Optional<Cliente> clienteExistente = retornaClientePorCpf(formulario.getCpf());
				Cliente mongoDbCliente = clienteExistente.get();
				
				//Faz encriptação da senha
				String novaSenha = BCrypt.hashpw(formulario.getSenha(), BCrypt.gensalt());
				mongoDbCliente.getCredencial().setSenha(novaSenha);
				repository.save(clienteExistente.get());
				
				retornoClienteMensagem.setReturnStatus(Boolean.TRUE);
			}
			
			utils.criaRetornoAlterarSenha(retornoClienteMensagem);
			
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			log.info(String.format("[PRIMARY SERVICE] Tempo total de processamento: %d ms", totalTime));
			log.info("###################################################");
			
		}catch(ClienteSenhaForaPadroes user) {
			retornoClienteMensagem.setMensagemStatus(user.getMensagem());
			retornoClienteMensagem.setReturnStatus(Boolean.FALSE);
			log.info(String.format("[PRIMARY SERVICE] "+user.getMensagem()));
			log.info("###################################################");
		}
		
		return retornoClienteMensagem;
	}
	
	
	
}

	