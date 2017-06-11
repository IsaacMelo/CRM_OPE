Brewer.Produto = (function() {
	
	function Produto() {

		this.valorSugeridoBox = $('.js-valor-sugerido-box');
		this.valorCompraInput = $('#valorCompra');
		this.margemProdutoInput = $('#margemProduto');
		this.estoqueInput = $('#estoque');
		this.codigoInput = $('#codigo');
		
		this.valorCompra = this.valorCompraInput.data('valor');
		this.margemProduto = this.margemProdutoInput.data('valor');
		
		if(this.codigoInput.val()){
			this.estoqueInput.prop("readonly", true);
		}
		
	}
	
	Produto.prototype.iniciar = function() {
		this.valorCompraInput.on('keyup', onValorSugeridoAlterado.bind(this));
	}
	
	function onValorSugeridoAlterado(evento) {
		this.valorCompra = Brewer.recuperarValor($(evento.target).val());
		onValoresAlterados.call(this);
	}
	
	function onValoresAlterados() {
		this.valorSugerido = ((this.valorCompra/100)*this.margemProduto)+this.valorCompra;
		this.valorSugeridoBox.html(Brewer.formatarMoeda(this.valorSugerido));
	}
	
	return Produto;
	
}());

$(function() {
	var produto = new Brewer.Produto();
	produto.iniciar();
	
});