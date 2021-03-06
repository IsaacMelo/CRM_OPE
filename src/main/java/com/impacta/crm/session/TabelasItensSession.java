package com.impacta.crm.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.impacta.crm.model.Produto;
import com.impacta.crm.model.ItemVenda;

@SessionScope
@Component
public class TabelasItensSession {

	private Set<TabelaItensVenda> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, long codigo, Produto produto, int quantidade) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(codigo, produto, quantidade);
		tabelas.add(tabela);
	}

	public void alterarQuantidadeItens(String uuid, Produto produto, Integer quantidade) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItens(produto, quantidade);
	}

	public void excluirItem(String uuid, Produto produto) {
		TabelaItensVenda tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(produto);
	}

	public List<ItemVenda> getItens(String uuid) {
		return buscarTabelaPorUuid(uuid).getItens();
	}
	
	public List<ItemVenda> getItensAlterados(String uuid) {
		return buscarTabelaPorUuid(uuid).getItensAlterados();
	}
	
	public List<ItemVenda> getItensDeletados(String uuid) {
		return buscarTabelaPorUuid(uuid).getItensDeletados();
	}
	
	public Object getValorTotal(String uuid) {
		return buscarTabelaPorUuid(uuid).getValorTotal();
	}
	
	private TabelaItensVenda buscarTabelaPorUuid(String uuid) {
		TabelaItensVenda tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensVenda(uuid));
		return tabela;
	}

	
}
