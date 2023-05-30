package br.com.righi.agencia.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.righi.agencia.api.dto.ClienteMensagemDTO;
import br.com.righi.agencia.api.entities.Cliente;
import br.com.righi.agencia.api.exceptions.ClienteEmailForaPadroesException;
import br.com.righi.agencia.api.exceptions.ClienteSenhaForaPadroesException;
import br.com.righi.agencia.api.forms.ClienteCelularFormRecord;
import br.com.righi.agencia.api.forms.ClienteEmailFormRecord;
import br.com.righi.agencia.api.forms.ClienteEnderecoFormRecord;
import br.com.righi.agencia.api.forms.ClienteFormRecord;
import br.com.righi.agencia.api.forms.ClienteSenhaFormRecord;
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
	public ClienteMensagemDTO cadastrarCliente(ClienteFormRecord formularioCliente) {
		long startTime, endTime, totalTime = 0;
		ClienteMensagemDTO retornoAPI = new ClienteMensagemDTO();
		startTime = System.currentTimeMillis();
		
		log.info("###################################################");
		log.info("[PRIMARY SERVICE] Iniciando processamento");
		
		//Se não existir usuário na base: inclua, altere status de sucesso da inclusao para TRUE
		if(!clienteExisteBase(formularioCliente.cpf())) {
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
	private void incluirCliente(ClienteFormRecord formularioCliente) {
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
	public ClienteMensagemDTO alterarSenhaCredencial(ClienteSenhaFormRecord formulario) {
		long startTime, endTime, totalTime = 0;
		startTime = System.currentTimeMillis();
		ClienteMensagemDTO retornoClienteMensagem = new ClienteMensagemDTO();
		
		log.info("###################################################");
		log.info("[PRIMARY SERVICE] Iniciando processamento");
		
		try {
			
			if(formulario.senha().length() < 8 || formulario.senha().length() > 16) {
				throw new ClienteSenhaForaPadroesException(formulario.cpf(), formulario.senha());
			}
			
			if(clienteExisteBase(formulario.cpf())) {
				Optional<Cliente> clienteExistente = retornaClientePorCpf(formulario.cpf());
				Cliente mongoDbCliente = clienteExistente.get();
				
				//Faz encriptação da senha
				utils.bindSenha(formulario, mongoDbCliente);
				repository.save(mongoDbCliente);
				
				retornoClienteMensagem.setReturnStatus(Boolean.TRUE);
			}
			
			utils.criaRetornoAlterarSenha(retornoClienteMensagem);
			
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			log.info(String.format("[PRIMARY SERVICE] Tempo total de processamento: %d ms", totalTime));
			log.info("###################################################");
			
		}catch(ClienteSenhaForaPadroesException csfpe) {
			retornoClienteMensagem.setMensagemStatus(csfpe.getMensagem());
			retornoClienteMensagem.setReturnStatus(Boolean.FALSE);
			log.info(String.format("[PRIMARY SERVICE] "+csfpe.getMensagem()));
			log.info("###################################################");
		}
		
		return retornoClienteMensagem;
	}
	
	public ClienteMensagemDTO alterarEnderecoCliente(ClienteEnderecoFormRecord enderecoForm) {
		long startTime, endTime, totalTime = 0;
		startTime = System.currentTimeMillis();
		ClienteMensagemDTO retornoClienteMensagem = new ClienteMensagemDTO();
		log.info("###################################################");
		log.info("[PRIMARY SERVICE] Iniciando processamento");
		
		
		if(clienteExisteBase(enderecoForm.cpf())) {
			Optional<Cliente> clienteExistente = retornaClientePorCpf(enderecoForm.cpf());
			Cliente mongoDbCliente = clienteExistente.get();
			
			utils.bindEndereco(mongoDbCliente, enderecoForm);
			repository.save(mongoDbCliente);
			retornoClienteMensagem.setReturnStatus(Boolean.TRUE);
		}
			utils.criaRetornoAlteraEndereco(retornoClienteMensagem);
			
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			log.info(String.format("[PRIMARY SERVICE] Tempo total de processamento: %d ms", totalTime));
			log.info("###################################################");
		
		return retornoClienteMensagem;
	}
	
	public ClienteMensagemDTO alterarEmailCliente(ClienteEmailFormRecord emailForm) {
		long startTime, endTime, totalTime = 0;
		startTime = System.currentTimeMillis();
		ClienteMensagemDTO retornoClienteMensagem = new ClienteMensagemDTO();
		log.info("###################################################");
		log.info("[PRIMARY SERVICE] Iniciando processamento");
		
		try {
			if(Boolean.FALSE == utils.emailValido(emailForm.email())) {
				throw new ClienteEmailForaPadroesException(emailForm);
			}
			
			if(clienteExisteBase(emailForm.cpf())) {
				Optional<Cliente> clienteExistente = retornaClientePorCpf(emailForm.cpf());
				Cliente mongoDbCliente = clienteExistente.get();
				utils.bindEmail(mongoDbCliente, emailForm);
				repository.save(mongoDbCliente);
				retornoClienteMensagem.setReturnStatus(Boolean.TRUE);
			}
			utils.criaRetornoAlteraEmail(retornoClienteMensagem);
			
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			log.info(String.format("[PRIMARY SERVICE] Tempo total de processamento: %d ms", totalTime));
			log.info("###################################################");
		}catch(ClienteEmailForaPadroesException cefpe) {
			retornoClienteMensagem.setMensagemStatus(cefpe.getMensagem());
			retornoClienteMensagem.setReturnStatus(Boolean.FALSE);
			log.info(String.format("[PRIMARY SERVICE] "+cefpe.getMensagem()));
			log.info("###################################################");
		}
		
		return retornoClienteMensagem;
	}
	
	public ClienteMensagemDTO alterarCelularCliente(ClienteCelularFormRecord celularForm) {
		long startTime, endTime, totalTime = 0;
		startTime = System.currentTimeMillis();
		ClienteMensagemDTO retornoClienteMensagem = new ClienteMensagemDTO();
		log.info("###################################################");
		log.info("[PRIMARY SERVICE] Iniciando processamento");
		
		if(clienteExisteBase(celularForm.cpf())) {
			Optional<Cliente> clienteExistente = retornaClientePorCpf(celularForm.cpf());
			Cliente mongoDbCliente = clienteExistente.get();
			utils.bindCelular(mongoDbCliente, celularForm);
			retornoClienteMensagem.setReturnStatus(Boolean.TRUE);
		}
		utils.criaRetornoAlteraEmail(retornoClienteMensagem);
		
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		log.info(String.format("[PRIMARY SERVICE] Tempo total de processamento: %d ms", totalTime));
		log.info("###################################################");
		
		return retornoClienteMensagem;
	}
	
}

	