var Brewer = Brewer || {};

Brewer.CampoCEP = (function(){
	
	function CampoCEP(){
		this.inputCep = $('#cep');
		this.inputLogradouro = $('#logradouro');
		this.inputBairro = $('#bairro');
		this.inputCidade = $('#cidade');
		this.inputEstado = $('#estado');
		this.imgLoading = $('.js-img-loading');
	}
	
	CampoCEP.prototype.iniciar = function(){
		reset.call(this);
		this.inputCep.on('change',buscarCEP.bind(this));
	}
	
	function buscarCEP(){
		if(this.inputCep.val()){
			var resposta = $.ajax({
				url: this.inputCep.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'cep': this.inputCep.val().replace('.','').replace('-','') }, 
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(render.bind(this));
		}else{
			reset.call(this);
		}
	}
	
	function render(data){
		if(data){
			this.inputLogradouro.val(data.logradouro);
			this.inputBairro.val(data.bairro);
			this.inputCidade.val(data.localidade);
			this.inputEstado.val(data.uf);
			this.inputLogradouro.attr('disabled', false);
			this.inputBairro.attr('disabled', false);
			this.inputCidade.attr('disabled', false);
			this.inputEstado.attr('disabled', false);
		}else{
			reset.call(this);
		}
		
	}
	
	function iniciarRequisicao(){
		reset.call(this);
		this.imgLoading.show();
	}
	
	function finalizarRequisicao(){
		this.imgLoading.hide();
	}
	
	function reset(){
		this.inputLogradouro.val('');
		this.inputBairro.val('');
		this.inputCidade.val('');
		this.inputEstado.val('');
		this.inputLogradouro.attr('disabled', 'disabled');
		this.inputBairro.attr('disabled', 'disabled');
		this.inputCidade.attr('disabled', 'disabled');
		this.inputEstado.attr('disabled', 'disabled');
	}
	
	return CampoCEP;
	
}());

$(function() {
	
	var consultaCep = new Brewer.CampoCEP();
	consultaCep.iniciar();
	
	/*var comboEstado = new Brewer.ComboEstado();
	comboEstado.iniciar();
	
	var comboCidade = new Brewer.ComboCidade(comboEstado);
	comboCidade.iniciar();*/
	

	
});

