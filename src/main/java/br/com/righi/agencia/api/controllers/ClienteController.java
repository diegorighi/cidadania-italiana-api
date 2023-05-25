package br.com.righi.agencia.api.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.righi.agencia.api.dto.ClienteDTO;
import br.com.righi.agencia.api.forms.ClienteForm;
import br.com.righi.agencia.api.services.ClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@PostMapping
	public ResponseEntity<ClienteDTO> cadastrar(@RequestBody ClienteForm formularioCliente, UriComponentsBuilder uriBuilder) {
		ClienteDTO retorno = service.cadastrarCliente(formularioCliente);
		URI uri = uriBuilder.path("/cliente/{cpf}").buildAndExpand(formularioCliente.getCpf()).toUri();
		
		if(retorno.getSucesso()) {
			return ResponseEntity.created(uri).body(retorno);
		}else{
			return ResponseEntity.status(HttpStatus.IM_USED).body(retorno);
		}
	}
	
}
