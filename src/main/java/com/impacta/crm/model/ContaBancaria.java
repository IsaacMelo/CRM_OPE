package com.impacta.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "conta_bancaria")
public class ContaBancaria implements Serializable, Comparable<ContaBancaria>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotBlank(message = "Banco é obrigatório")
	@Column(name = "banco")
	private String banco;
	
	@NotBlank(message = "Agência é obrigatória")
	@Column(name = "agencia")
	private String agencia;
	
	@NotBlank(message = "Conta é obrigatória")
	@Column(name = "conta")
	private String conta;
	
	@Column(name = "principal")
	private boolean principal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_usuario", updatable = true)
	@JsonIgnore
	private Usuario usuario;
	
	@Transient
	private String id;
	
	@Transient
	private String uuid;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String toString(){
		return (this.banco+this.agencia+this.conta);
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	@Override
	public int compareTo(ContaBancaria conta) {
			if(this.principal){
				if(!conta.principal){
					return -1;
				}
			}
			if(conta.principal){
				if(!this.principal){
					return 1;
				}
			}
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		ContaBancaria other = (ContaBancaria) obj;
		boolean resposta = false;
		
		if (codigo == null) {
			if (other.codigo != null)
				resposta =  false;
		} else if (!codigo.equals(other.codigo)){
			resposta = false;
		} else{
			resposta = true;
		}
		
		if(banco.equals(other.banco) && !resposta){
			if(agencia.equals(other.agencia)){
				if(conta.equals(other.conta)){
					resposta = true;
				}
			}
		}		
	
		return resposta;
	}

	
	
	
	

	
}
