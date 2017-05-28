Brewer.Estoque = (function() {
	
	function Estoque(tabelaItens) {
		this.tabelaItens = tabelaItens;
		this.totalItens = $('.js-total-itens-box');
		this.totalProdutos = $('.js-total-produtos-box');
		this.tipoRegistro = $('.js-registro-box');
		this.valorTotalItens = this.tabelaItens.totalItens();
		this.valorTotalProdutos = this.tabelaItens.totalProdutos();
		
	}
	
	Estoque.prototype.iniciar = function() {
		this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));

		onValoresAlterados.call(this);
	}
	
	function onTabelaItensAtualizada(evento,tabelaItens) {
		this.valorTotalItens = $(tabelaItens).data('total-itens') == null ? 0 : $(tabelaItens).data('total-itens');
		this.valorTotalProdutos = $(tabelaItens).data('total-produtos') == null ? 0 : $(tabelaItens).data('total-produtos');
		onValoresAlterados.call(this);
	}
	
	function onValoresAlterados() {
		this.totalItens.text(this.valorTotalItens);
		this.totalProdutos.text(this.valorTotalProdutos);
	}
	
	return Estoque;
	
}());

$(function() {
	
	var autocompleteEstoque = new Brewer.AutocompleteEstoque();
	autocompleteEstoque.iniciar();
	
	var tabelaItensEstoque = new Brewer.TabelaItensEstoque(autocompleteEstoque);
	tabelaItensEstoque.iniciar();
	
	var estoque = new Brewer.Estoque(tabelaItensEstoque);
	estoque.iniciar();
	
});