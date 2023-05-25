package br.com.righi.agencia.api.handlers;

public interface EntityHandler<T, K> {

	public T formToEntity(K form);
}
