package com.impacta.crm.controller.validator;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.impacta.crm.model.ItemVenda;
import com.impacta.crm.model.Produto;
import com.impacta.crm.model.Venda;
import com.impacta.crm.model.StatusVenda;;

@Component
public class VendaItemValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Venda.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Venda venda = (Venda) target;
		validarStoqueItens(errors, venda);
	}

	
	private void validarStoqueItens(Errors errors, Venda venda) {
		
		for (ItemVenda item : venda.getItens()) {
			Produto produto = item.getProduto();
			Optional<ItemVenda> itemAlteradoOptional = buscarItemPorProduto(venda.getItensAlterados(),produto);
			int quantidade = 0;
			
			if(itemAlteradoOptional.isPresent() || item.isNovo()){

				//Caso exista item alterado, estão pega o valor da diferença 
				if(itemAlteradoOptional.isPresent() && !item.isNovo() && venda.getStatus() == StatusVenda.EMITIDA){
					ItemVenda ItemAlterado = itemAlteradoOptional.get();
					quantidade = produto.getQuantidadeEstoque() - (item.getQuantidade() - ItemAlterado.getQuantidade());
				}else{
					quantidade = produto.getQuantidadeEstoque() - item.getQuantidade();	
				}
				
				if(quantidade < 0){
					errors.reject("", "Produto "+produto.getNome()+" sem saldo no estoque ("+produto.getQuantidadeEstoque()+")");
				}	
			}
		}
		
	}

	private Optional<ItemVenda> buscarItemPorProduto(List<ItemVenda> item,Produto produto){
		return item.stream()
				.filter(i -> i.getProduto().equals(produto))
				.findAny();
	}	

}
