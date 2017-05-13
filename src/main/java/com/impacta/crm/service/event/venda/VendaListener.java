package com.impacta.crm.service.event.venda;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.impacta.crm.model.Produto;
import com.impacta.crm.model.ItemVenda;
import com.impacta.crm.repository.Produtos;
import com.impacta.crm.model.StatusVenda;

@Component
public class VendaListener {

	@Autowired
	private Produtos produtos;
	
	@EventListener
	public void vendaEmitida(VendaEvent vendaEvent) {
		
		//Verifica se é um venda nova ou era um orçamento
		if(vendaEvent.getVenda().getStatus() != StatusVenda.ORCAMENTO){
			//Percorre todos os item cancelados
			for (ItemVenda itemDeletado : vendaEvent.getVenda().getItensDeletados()) {
				Produto produto = produtos.findOne(itemDeletado.getProduto().getCodigo());
				Optional<ItemVenda> itemAlteradoOptional = buscarItemPorProduto(vendaEvent.getVenda().getItensAlterados(),produto);
				
				//verifica se o item foi alterado e se não é um item novo que foi cancelado
				if(itemAlteradoOptional.isPresent() && !itemDeletado.isNovo()){
					ItemVenda ItemAlterado = itemAlteradoOptional.get();
					produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + ItemAlterado.getQuantidade());
					produtos.save(produto);		
				}else if(!itemDeletado.isNovo()){ //Caso não seja um item novo mais foi cancelado.
					produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + itemDeletado.getQuantidade());
					produtos.save(produto);
				}
			}
			
			//Percorre todos os item da venda
			for (ItemVenda item : vendaEvent.getVenda().getItens()) {
				Produto produto = produtos.findOne(item.getProduto().getCodigo());
				Optional<ItemVenda> itemAlteradoOptional = buscarItemPorProduto(vendaEvent.getVenda().getItensAlterados(),produto);
				int quantidade = 0;
				
				//Caso exista item alterado, estão pega o valor da diferença 
				if(itemAlteradoOptional.isPresent()){
					ItemVenda ItemAlterado = itemAlteradoOptional.get();
					quantidade = item.getQuantidade() - ItemAlterado.getQuantidade();
				}
				
				//Se for um item novo
				if(item.isNovo()){
					//só pode baixar itens novos quando a venda esta com o Status EMITIDA
					if(vendaEvent.getVenda().getStatus() != StatusVenda.CANCELADA){
						produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
						produtos.save(produto);
					}

				}else if(itemAlteradoOptional.isPresent()){//Caso o item tenha sido alterado
					if(vendaEvent.getVenda().getStatus() != StatusVenda.CANCELADA){//se for item alterado e a venda com Status EMITIDA
						produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
					}else{//Itens já baixado no estoque, mais teve alteração na venda e a venda foi cancelada;
						ItemVenda ItemAlterado = itemAlteradoOptional.get();
						produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + ItemAlterado.getQuantidade());
					}
					produtos.save(produto);
					
				}else if (vendaEvent.getVenda().getStatus() == StatusVenda.CANCELADA){ //Ultimo caso, não teve alteração e a venda foi cancelada
						produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + item.getQuantidade());
						produtos.save(produto);
				}
			}
		}else{ // Uma venda nova, ignora todas as alterações feita na venda.
			for (ItemVenda item : vendaEvent.getVenda().getItens()) {
				Produto produto = produtos.findOne(item.getProduto().getCodigo());
				produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
				produtos.save(produto);
			}
		}
	
	}
	
	private Optional<ItemVenda> buscarItemPorProduto(List<ItemVenda> item,Produto produto){
		return item.stream()
				.filter(i -> i.getProduto().equals(produto))
				.findAny();
	}	
}
