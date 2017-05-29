package com.impacta.crm.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.impacta.crm.model.ItemEstoque;
import com.impacta.crm.model.Produto;


@SessionScope
@Component
public class TabelaItensEstoqueSession {

	private Set<TabelaItensEstoque> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Produto produto, int quantidade) {
		TabelaItensEstoque tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(produto, quantidade);
		tabelas.add(tabela);
	}

	public void alterarQuantidadeItens(String uuid, Produto produto, Integer quantidade) {
		TabelaItensEstoque tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItens(produto, quantidade);
	}

	public void excluirItem(String uuid, Produto produto) {
		TabelaItensEstoque tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(produto);
	}
	
	public int getTotalProdutos(String uuid){
		return buscarTabelaPorUuid(uuid).totalProdutos();
	}
	
	public int getTotalItens(String uuid){
		return buscarTabelaPorUuid(uuid).getTotalItens();
	}
	
	public List<ItemEstoque> getItens(String uuid) {
		return buscarTabelaPorUuid(uuid).getItens();
	}
	
	private TabelaItensEstoque buscarTabelaPorUuid(String uuid) {
		TabelaItensEstoque tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensEstoque(uuid));
		return tabela;
	}
}
