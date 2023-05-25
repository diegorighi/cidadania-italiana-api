package br.com.righi.agencia.api.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.righi.agencia.api.forms.ClienteForm;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	public void cadastrar(@RequestBody ClienteForm formularioCliente) {
		
	}
	
}
