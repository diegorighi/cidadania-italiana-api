package br.com.righi.agencia.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Cidadania Italiana API", version = "1.0", description = "Documentacao para integracao com a API"))
public class ApiCidadaniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCidadaniaApplication.class, args);
	}

}
