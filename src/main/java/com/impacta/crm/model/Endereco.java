package com.impacta.crm.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Logradouro é obrigatório")
	private String logradouro;
	
	@NotBlank(message = "Número é obrigatório")
	private String numero;
	
	private String complemento;
	
	@NotBlank(message = "CEP é obrigatório")
	private String cep;
	
	@NotBlank(message = "Cidade é obrigatório")
	private String cidade;
	
	@NotBlank(message = "Estado é obrigatório")
	private String estado;
	
	@NotBlank(message = "Logradouro é obrigatório")
	private String bairro;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	
	
}
