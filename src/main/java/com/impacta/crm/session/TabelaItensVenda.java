package com.impacta.crm.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.impacta.crm.model.Produto;
import com.impacta.crm.model.ItemVenda;

class TabelaItensVenda {

	private String uuid;
	private List<ItemVenda> itens = new ArrayList<>();
	private List<ItemVenda> itensAlterados = new ArrayList<>();
	private List<ItemVenda> itensDeletados = new ArrayList<>();
	
	public TabelaItensVenda(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(long codigo, Produto produto, Integer quantidade) {
		Optional<ItemVenda> itemVendaOptional = buscarItemPorProduto(produto);
		
		ItemVenda itemVenda = null;
		if (itemVendaOptional.isPresent()) {
			itemVenda = itemVendaOptional.get();
			itemVenda.setQuantidade(itemVenda.getQuantidade() + quantidade);
		} else {
			itemVenda = new ItemVenda();
			if(codigo != 0) itemVenda.setCodigo(codigo);
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(quantidade);
			itemVenda.setValorUnitario(produto.getValor());
			itemVenda.setComissao(itemVenda.getComissao());
			itens.add(0, itemVenda);
		}
	}
	
	private void adicionarItemAlterado(Produto produto, ItemVenda itemVenda) {
		Optional<ItemVenda> itemAlteradoVendaOptional = buscarItemAlteradoPorProduto(produto);
		
		if (!itemAlteradoVendaOptional.isPresent()) {
			ItemVenda itemVendaAlterado = new ItemVenda();
			itemVendaAlterado.setCodigo(itemVenda.getCodigo());
			itemVendaAlterado.setProduto(itemVenda.getProduto());
			itemVendaAlterado.setQuantidade(itemVenda.getQuantidade());
			itemVendaAlterado.setValorUnitario(itemVenda.getValorUnitario());
			itemVendaAlterado.setComissao(itemVenda.getComissao());
			itemVendaAlterado.setVenda(itemVenda.getVenda());
			itemVendaAlterado.setProduto(itemVenda.getProduto());
			itensAlterados.add(0, itemVendaAlterado);
		}
	}
	
	private void adicionarItemDeletado(ItemVenda itemVenda) {
		ItemVenda itemVendaDeletado = new ItemVenda();
		itemVendaDeletado.setCodigo(itemVenda.getCodigo());
		itemVendaDeletado.setProduto(itemVenda.getProduto());
		itemVendaDeletado.setQuantidade(itemVenda.getQuantidade());
		itemVendaDeletado.setValorUnitario(itemVenda.getValorUnitario());
		itemVendaDeletado.setComissao(itemVenda.getComissao());
		itemVendaDeletado.setVenda(itemVenda.getVenda());
		itemVendaDeletado.setProduto(itemVenda.getProduto());
		itensDeletados.add(0, itemVendaDeletado);
	}
	
	public void alterarQuantidadeItens(Produto produto, Integer quantidade) {
		ItemVenda itemVenda = buscarItemPorProduto(produto).get();
		adicionarItemAlterado(produto, itemVenda);		
		itemVenda.setQuantidade(quantidade);
	}
	
	public void excluirItem(Produto produto) {
		ItemVenda itemVenda = buscarItemPorProduto(produto).get();
		adicionarItemDeletado(itemVenda);
		
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getProduto().equals(produto))
				.findAny().getAsInt();
		itens.remove(indice);
	}
	
	public int total() {
		return itens.size();
	}

	public List<ItemVenda> getItens() {
		return itens;
	}
	
	public List<ItemVenda> getItensAlterados() {
		return itensAlterados;
	}
	
	public List<ItemVenda> getItensDeletados() {
		return itensDeletados;
	}
	
	private Optional<ItemVenda> buscarItemPorProduto(Produto produto) {
		return itens.stream()
				.filter(i -> i.getProduto().equals(produto))
				.findAny();
	}
	
	private Optional<ItemVenda> buscarItemAlteradoPorProduto(Produto produto) {
		return itensAlterados.stream()
				.filter(i -> i.getProduto().equals(produto))
				.findAny();
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		TabelaItensVenda other = (TabelaItensVenda) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
