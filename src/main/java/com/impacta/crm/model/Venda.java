package com.impacta.crm.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "venda")
@DynamicUpdate
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

	@Column(name = "valor_frete")
	private BigDecimal valorFrete;

	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;

	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	@Column(name = "valor_comissao")
	private BigDecimal valorComissao = BigDecimal.ZERO;

	private String observacao;

	@Column(name = "data_hora_entrega")
	private LocalDateTime dataHoraEntrega;

	@ManyToOne
	@JoinColumn(name = "codigo_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;

	@Enumerated(EnumType.STRING)
	private StatusVenda status = StatusVenda.ORCAMENTO;
	
	@NotNull(message = "A forma de pagamento é obrigatório")
	@ManyToOne
	@JoinColumn(name = "codigo_pagamento")
	private FormaPagamento formaPagamento;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemVenda> itens = new ArrayList<>();
	
	@Transient
	private List<ItemVenda> itensAlterados = new ArrayList<>();
	
	@Transient
	private List<ItemVenda> itensDeletados = new ArrayList<>();
	
	@Transient
	private String uuid;
	
	@Transient
	private BigDecimal baseComissao;
	
	@Transient
	private BigDecimal descontoMax;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Transient
	private LocalDate dataEntrega;

	@DateTimeFormat(pattern = "HH:mm")
	@Transient
	private LocalTime horarioEntrega;

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

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorComissao() {
		return valorComissao;
	}

	public void setValorComissao(BigDecimal valorComissao) {
		this.valorComissao = valorComissao;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public LocalDateTime getDataHoraEntrega() {
		return dataHoraEntrega;
	}

	public void setDataHoraEntrega(LocalDateTime dataHoraEntrega) {
		this.dataHoraEntrega = dataHoraEntrega;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public BigDecimal getBaseComissao() {
		return baseComissao;
	}

	public void setBaseComissao(BigDecimal baseComissao) {
		this.baseComissao = baseComissao;
	}
	
	public BigDecimal getDescontoMax() {
		return descontoMax;
	}

	public void setDescontoMax(BigDecimal descontoMax) {
		this.descontoMax = descontoMax;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalTime getHorarioEntrega() {
		return horarioEntrega;
	}

	public void setHorarioEntrega(LocalTime horarioEntrega) {
		this.horarioEntrega = horarioEntrega;
	}

	public void adicionarItens(List<ItemVenda> itens) {
		this.itens = itens;
		this.itens.forEach(i -> i.setVenda(this));
	}
	
	public void adicionarItensAlterados(List<ItemVenda> itensAlterados) {
		this.itensAlterados = itensAlterados;
		this.itensAlterados.forEach(i -> i.setVenda(this));
	}
	
	public void adicionarItensDeletados(List<ItemVenda> itensDeletados) {
		this.itensDeletados = itensDeletados;
		this.itensDeletados.forEach(i -> i.setVenda(this));
	}
	
	public List<ItemVenda> getItensAlterados() {
		return itensAlterados;
	}
	
	public List<ItemVenda> getItensDeletados() {
		return itensDeletados;
	}

	public void setItensAlterados(List<ItemVenda> itensAlterados) {
		this.itensAlterados = itensAlterados;
	}
	
	public void setItensDeletados(List<ItemVenda> itensDeletados) {
		this.itensDeletados = itensDeletados;
	}
	
	public boolean isNova() {
		return codigo == null;
	}
	
	public BigDecimal getValorTotalItens() {
		return getItens().stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void calcularValorTotal() {
		this.valorTotal = calcularValorTotal(getValorTotalItens(), getValorFrete(), getValorDesconto());
	}
	
	public void calcularValorComissao() {
		this.valorComissao = calcularValorComissao(getValorTotal(), getBaseComissao());
	}
	
	public Long getDiasCriacao() {
		LocalDate inicio = dataCriacao != null ? dataCriacao.toLocalDate() : LocalDate.now();
		return ChronoUnit.DAYS.between(inicio, LocalDate.now());
	}
	
	public boolean isSalvarPermitido() {
		return !status.equals(StatusVenda.CANCELADA) 
				&& !status.equals(StatusVenda.FINALIZADA) 
				&& !status.equals(StatusVenda.FATURADA)
				&& !status.equals(StatusVenda.TRANSPORTE);
	}
	
	public boolean isCancelarPermitido() {
		return !status.equals(StatusVenda.ORCAMENTO) && codigo != null;
	}
	
	public boolean isEmitirPermitido() {
		return status.equals(StatusVenda.ORCAMENTO);
	}
	
	public boolean isSalvarProibido() {
		return !isSalvarPermitido();
	}
	
	private BigDecimal calcularValorTotal(BigDecimal valorTotalItens, BigDecimal valorFrete, BigDecimal valorDesconto) {
		BigDecimal valorTotal = valorTotalItens
				.add(Optional.ofNullable(valorFrete).orElse(BigDecimal.ZERO))
				.subtract(Optional.ofNullable(valorDesconto).orElse(BigDecimal.ZERO));
		return valorTotal;
	}
	
	private BigDecimal calcularValorComissao(BigDecimal valorTotal, BigDecimal pct) {
		return valorTotal
				.multiply(pct)
				.divide(new BigDecimal(100));
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
		Venda other = (Venda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
