package com.impacta.crm.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.impacta.crm.model.ContaBancaria;
import com.impacta.crm.service.exception.ContaBancariaJaCadastradaException;

@SessionScope
@Component
public class TabelaContaBancariaSession {

	private Set<TabelaContaBancaria> tabelas = new HashSet<>();

	public void adicionarConta(String uuid, ContaBancaria conta) throws ContaBancariaJaCadastradaException {
		TabelaContaBancaria tabela = buscarTabelaPorUiid(uuid);
		tabela.adicionarConta(conta);
		tabelas.add(tabela);
	}
	
	public void editarConta(String uuid, ContaBancaria conta) throws ContaBancariaJaCadastradaException {
		TabelaContaBancaria tabela = buscarTabelaPorUiid(uuid);
		tabela.editarConta(conta);
	}

	public void excluirConta(String uuid, String id) {
		TabelaContaBancaria tabela = buscarTabelaPorUiid(uuid);
		tabela.excluirConta(id);
	}

	public List<ContaBancaria> getContas(String uuid) {
		return buscarTabelaPorUiid(uuid).getContas();
	}
	
	public ContaBancaria get(String uuid, String id){
		return buscarTabelaPorUiid(uuid).get(id);
	}
	
	public void adiconarTabela(List<ContaBancaria> contas, String uuid){
		TabelaContaBancaria tabela = new TabelaContaBancaria(uuid);
		tabela.setContas(contas);
		if(!tabela.getContas().isEmpty()){
			tabela.getContas().forEach(c-> c.setId(c.getCodigo() != null ? c.getCodigo().toString(): c.getId()));
		}	
		tabelas.add(tabela);
		
	}
	
	private TabelaContaBancaria buscarTabelaPorUiid(String uuid) {
		return tabelas.stream()
				.filter(t-> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaContaBancaria(uuid));
	}
	
}
