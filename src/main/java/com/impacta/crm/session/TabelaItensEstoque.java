package com.impacta.crm.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.impacta.crm.model.ItemEstoque;
import com.impacta.crm.model.Produto;

public class TabelaItensEstoque {

	private String uuid;
	private List<ItemEstoque> itens = new ArrayList<>();
	
	public TabelaItensEstoque(String uuid) {
		this.uuid = uuid;
	}
	
	public void adicionarItem(Produto produto, Integer quantidade) {
		Optional<ItemEstoque> itemEstoqueOptional = buscarItemPorProduto(produto);
		
		ItemEstoque itemEstoque = null;
		if (itemEstoqueOptional.isPresent()) {
			itemEstoque = itemEstoqueOptional.get();
			itemEstoque.setQuantidade(itemEstoque.getQuantidade() + quantidade);
		} else {
			itemEstoque = new ItemEstoque();
			itemEstoque.setProduto(produto);
			itemEstoque.setQuantidade(quantidade);
			itens.add(0, itemEstoque);
		}
	}
	
	public void alterarQuantidadeItens(Produto produto, Integer quantidade) {
		ItemEstoque itemVenda = buscarItemPorProduto(produto).get();
		itemVenda.setQuantidade(quantidade);
	}
	
	public void excluirItem(Produto produto) {
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getProduto().equals(produto))
				.findAny().getAsInt();
		itens.remove(indice);
	}
	
	public int totalProdutos() {
		return itens.size();
	}
	
	public int getTotalItens() {
		return itens.stream().mapToInt(ItemEstoque::getQuantidade).sum();
	}

	public List<ItemEstoque> getItens() {
		return itens;
	}
	
	private Optional<ItemEstoque> buscarItemPorProduto(Produto produto) {
		return itens.stream()
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
		TabelaItensEstoque other = (TabelaItensEstoque) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
