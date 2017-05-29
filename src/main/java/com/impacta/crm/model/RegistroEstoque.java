package com.impacta.crm.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "registro_estoque")
@DynamicUpdate
public class RegistroEstoque implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

	@Column(name = "total_produtos")
	private Integer totalProdutos = 0;
	
	@Column(name = "total_itens")
	private Integer totalItens = 0;

	@NotNull(message = "Usuario é obrigatório")
	@ManyToOne
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;

	@NotNull(message = "Tipo de registro é obrigatório")
	@Enumerated(EnumType.STRING)
	private Registro registro;
	
	@OneToMany(mappedBy = "registro", targetEntity = ItemEstoque.class, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}, orphanRemoval = true)
	private List<ItemEstoque> itens = new ArrayList<>();
	
	@Transient
	private String uuid;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(Integer totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public Integer getTotalItens() {
		return totalItens;
	}

	public void setTotalItens(Integer totalItens) {
		this.totalItens = totalItens;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public List<ItemEstoque> getItens() {
		return itens;
	}

	public void setItens(List<ItemEstoque> itens) {
		this.itens = itens;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void adicionarItens(List<ItemEstoque> itens) {
		this.itens = itens;
		this.itens.forEach(i -> i.setRegistro(this));
	}
	
	public boolean isNovo() {
		return codigo == null;
	}
	
	public Long getDiasCriacao() {
		LocalDate inicio = dataCriacao != null ? dataCriacao.toLocalDate() : LocalDate.now();
		return ChronoUnit.DAYS.between(inicio, LocalDate.now());
	}
	
	public boolean isSalvarPermitido() {
		return !registro.equals(Registro.ENTRADA) 
				&& !registro.equals(Registro.SAIDA);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistroEstoque other = (RegistroEstoque) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
}
