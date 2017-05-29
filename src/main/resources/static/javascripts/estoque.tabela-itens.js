Brewer.TabelaItensEstoque = (function() {
	
	function TabelaItensEstoque(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaCervejasContainer = $('.js-tabela-cervejas-container');
		this.uuid = $('#uuid').val();
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	TabelaItensEstoque.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
		bindQuantidade.call(this);
		bindTabelaItem.call(this);
		bindTipoRegistro.call(this);
	}
	
	TabelaItensEstoque.prototype.totalProdutos = function() {
		return this.tabelaCervejasContainer.data('produtos');
	}
	
	TabelaItensEstoque.prototype.totalItens = function() {
		return this.tabelaCervejasContainer.data('itens');
	}
	
	function onItemSelecionado(evento, item) {
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codigoCerveja: item.codigo,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onItemAtualizadoNoServidor(html) {
		this.tabelaCervejasContainer.html(html);
		
		bindQuantidade.call(this);
		bindTipoRegistro.call(this);
		
		var tabelaItem = bindTabelaItem.call(this); 
		this.emitter.trigger('tabela-itens-atualizada', tabelaItem);
	}
	
	function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val();
		
		if (quantidade <= 0) {
			input.val(1);
			quantidade = 1;
		}
		
		var codigoCerveja = input.data('codigo-cerveja');
		
		var resposta = $.ajax({
			url: 'item/' + codigoCerveja,
			method: 'PUT',
			data: {
				quantidade: quantidade,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onDoubleClick(evento) {
		$(this).toggleClass('solicitando-exclusao');
	}
	
	function onExclusaoItemClick(evento) {
		var codigoCerveja = $(evento.target).data('codigo-cerveja');
		var resposta = $.ajax({
			url: 'item/' + this.uuid + '/' + codigoCerveja,
			method: 'DELETE'
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onChangeTipoRegistro(inputTipoRegistro){
		var boxRegistro = $('.js-registro-box');
		if(inputTipoRegistro.val() == 'ENTRADA'){
			boxRegistro.text('Entrada');
		}else if(inputTipoRegistro.val() == 'SAIDA'){
			boxRegistro.text('Saída');
		} else{
			boxRegistro.text('Nenhum Tipo');
		}		
	}
	
	function bindQuantidade() {
		var quantidadeItemInput = $('.js-tabela-cerveja-quantidade-item');
		quantidadeItemInput.on('change', onQuantidadeItemAlterado.bind(this));
		quantidadeItemInput.maskNumber({ integer: true, thousands: '' });
	}
	
	function bindTabelaItem() {
		var tabelaItem = $('.js-tabela-item');
		tabelaItem.on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
		return tabelaItem;
	}
	
	function bindTipoRegistro(){
		var inputTipoRegistro = $('#tipoRegistro');
		inputTipoRegistro.on('change', onChangeTipoRegistro.bind(this,inputTipoRegistro));
	}
	
	
	return TabelaItensEstoque;
	
}());