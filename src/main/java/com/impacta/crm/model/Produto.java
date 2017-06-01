package com.impacta.crm.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.util.StringUtils;

import com.impacta.crm.repository.listener.ProdutoEntityListener;
import com.impacta.crm.validation.SKU;

@EntityListeners(ProdutoEntityListener.class)
@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@SKU
	@NotBlank
	private String sku;

	@NotBlank
	private String nome;

	@NotBlank(message = "A descrição é obrigatória")
	@Size(max = 50, message = "O tamanho da descrição deve estar entre 1 e 50")
	private String descricao;

	@NumberFormat(pattern = "#,##0.00")
	@NotNull(message = "Valor de venda é obrigatório")
	@DecimalMin(value = "0.50", message = "O valor do produto deve ser maior que R$0,50")
	@DecimalMax(value = "9999999.99", message = "O valor do produto deve ser menor que R$9.999.999,99")
	private BigDecimal valor;
	
	@NumberFormat(pattern = "#,##0.00")
	@NotNull(message = "Valor de compra é obrigatório")
	@DecimalMin(value = "0.10", message = "O valor de compra do produto deve ser maior que R$0,10")
	@DecimalMax(value = "9999999.99", message = "O valor de compra produto deve ser menor que R$9.999.999,99")
	@Column(name = "valor_compra")
	private BigDecimal valorCompra;

	@NumberFormat(pattern = "#,##0.00")
	@NotNull(message = "A comissão é obrigatória")
	@DecimalMax(value = "100.0", message = "A comissão deve ser igual ou menor que 100")
	private BigDecimal comissao;

	@NumberFormat(pattern = "#,##0")
	@NotNull(message = "A quantidade em estoque é obrigatória")
	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque;
	
	@NumberFormat(pattern = "#,##0")
	@NotNull(message = "A quantidade minima em estoque é obrigatória")
	@Column(name = "quantidade_minima")
	private Integer quantidadeMinima;

	@NotNull(message = "O categoria é obrigatório")
	@ManyToOne
	@JoinColumn(name = "codigo_categoria")
	private Categoria categoria;
	
	@NotNull(message = "O fornecedor é obrigatório")
	@ManyToOne
	@JoinColumn(name = "codigo_fornecedor")
	private Fornecedor fornecedor;

	private String foto;

	@Column(name = "content_type")
	private String contentType;
	
	private Boolean ativo;

	@Column(name = "estoque_ativo")
	private Boolean estoqueAtivo;

	@Transient
	private boolean novaFoto;

	@Transient
	private String urlFoto;

	@Transient
	private String urlThumbnailFoto;

	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		sku = sku.toUpperCase();
	}
	
	public Produto(){
		this.ativo = true;
		this.estoqueAtivo = true;
	}
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Integer getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(Integer quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Boolean getEstoqueAtivo() {
		return estoqueAtivo;
	}

	public void setEstoqueAtivo(Boolean estoqueAtivo) {
		this.estoqueAtivo = estoqueAtivo;
	}

	public String getFotoOuMock() {
		return !StringUtils.isEmpty(foto) ? foto : "produto-mock.png";
	}

	public boolean temFoto() {
		return !StringUtils.isEmpty(this.foto);
	}

	public boolean isNova() {
		return codigo == null;
	}

	public boolean isNovaFoto() {
		return novaFoto;
	}

	public void setNovaFoto(boolean novaFoto) {
		this.novaFoto = novaFoto;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public String getUrlThumbnailFoto() {
		return urlThumbnailFoto;
	}

	public void setUrlThumbnailFoto(String urlThumbnailFoto) {
		this.urlThumbnailFoto = urlThumbnailFoto;
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
