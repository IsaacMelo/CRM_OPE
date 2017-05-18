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
		int qtdMinEstoque = 0;
		int qtdEstoque = 0;
		int qtdTotalVenda = 0;
		
		for (ItemVenda item : venda.getItens()) {
			Produto produto = item.getProduto();
			
			//Verifca se o produto esta com o controle de estoque ativo
			if(produto.getEstoqueAtivo()){
				
				qtdMinEstoque 	= produto.getQuantidadeMinima();
				qtdEstoque 		= produto.getQuantidadeEstoque();
				qtdTotalVenda   = qtdEstoque - qtdMinEstoque;
				
				Optional<ItemVenda> itemAlteradoOptional = buscarItemPorProduto(venda.getItensAlterados(),produto);
				
				if(itemAlteradoOptional.isPresent() || item.isNovo() || venda.getStatus() == StatusVenda.ORCAMENTO){

					//Caso exista item alterado, estão pega o valor da diferença 
					if(itemAlteradoOptional.isPresent() && !item.isNovo() && venda.getStatus() == StatusVenda.EMITIDA){
						ItemVenda ItemAlterado = itemAlteradoOptional.get();
						qtdTotalVenda = qtdTotalVenda - (item.getQuantidade() - ItemAlterado.getQuantidade());
					}else{
						qtdTotalVenda = qtdTotalVenda - item.getQuantidade();
					}
					
					if(qtdTotalVenda < 0){
						errors.reject("", "Produto "+produto.getNome()+" com saldo minimo no estoque ("+produto.getQuantidadeMinima()+")");
					}	
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
