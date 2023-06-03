package br.com.righi.agencia.api.dto;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mensagens.properties")
public interface RetornoMensagem<T> {

	public void build(T retorno);

}
