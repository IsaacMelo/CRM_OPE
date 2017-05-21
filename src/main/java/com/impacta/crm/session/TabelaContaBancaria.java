package com.impacta.crm.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.impacta.crm.model.ContaBancaria;
import com.impacta.crm.service.exception.ContaBancariaJaCadastradaException;

class TabelaContaBancaria {
	
	private List<ContaBancaria> contas = new ArrayList<>();

	private String uuid;
	
	public TabelaContaBancaria(String uuid){
		this.uuid = uuid;
	}
	
	@Autowired
	public void adicionarConta(ContaBancaria conta){
		
		if(!this.contas.contains(conta)){
			verificarPrincipal(conta);
			conta.setId(conta.toString().replaceAll(" ", ""));
			this.contas.add(conta);
		}else{
			throw new ContaBancariaJaCadastradaException("Conta já cadastrada");
		}
		
	}
	
	public void editarConta(ContaBancaria conta){
			verificarPrincipal(conta);
			int index = IntStream.range(0, contas.size()).filter(i-> contas.get(i).getId().equals(conta.getId())).findAny().getAsInt();
			contas.remove(index);
			contas.add(conta);
	}
	
	public void excluirConta(String id) {
		int index = IntStream.range(0, contas.size()).filter(i-> contas.get(i).getId().equals(id)).findAny().getAsInt();
		contas.remove(index);
		
	}
	
	public ContaBancaria get(String id) {
		int index = IntStream.range(0, contas.size()).filter(i-> contas.get(i).getId().equals(id)).findAny().getAsInt();
		return contas.get(index);
	}
	
	private void verificarPrincipal(ContaBancaria conta) {
		if(conta.isPrincipal()){
			contas.forEach(c -> {
				if(c.isPrincipal()){
					c.setPrincipal(false);
				}
			});
		}
	}
	
	public int size(){
		return this.contas.size();
	}


	public List<ContaBancaria> getContas() {
		Collections.sort(this.contas);
		return this.contas;
	}
	
	public void setContas(List<ContaBancaria> contas){
		this.contas = contas;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
		TabelaContaBancaria other = (TabelaContaBancaria) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	

	
	
}
