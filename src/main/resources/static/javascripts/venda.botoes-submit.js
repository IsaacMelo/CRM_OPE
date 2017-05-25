Brewer = Brewer || {};

Brewer.BotaoSubmit = (function() {
	
	function BotaoSubmit() {
		this.submitBtn = $('.js-submit-btn');
		this.formulario = $('.js-formulario-principal');
	}

	BotaoSubmit.prototype.iniciar = function() {
		this.submitBtn.on('click', onSubmit.bind(this));
	}
	
	function onSubmit(evento) {
		evento.preventDefault();
		
		var botaoClicado = $(evento.target);
		var acao = botaoClicado.data('acao');
		
		var acaoInput = $('<input type="hidden">');
		acaoInput.attr('name', acao);
		
		this.formulario.append(acaoInput);
		this.formulario.submit();
	}
	
	return BotaoSubmit
	
}());

Brewer.BotaoCancelar = (function() {
	
	function BotaoCancelar() {
		this.submitBtn = $('.js-cancelar-btn');
		this.formulario = $('.js-formulario-principal');
	}
	
	BotaoCancelar.prototype.iniciar = function() {
		this.submitBtn.on('click', onSubmit.bind(this));
	}
	
	function onSubmit(evento) {
		evento.preventDefault();
		var botaoClicado = $(evento.target);
		var objeto = botaoClicado.data('objeto');
		
		swal({
			title: 'Tem certeza?',
			text: 'Cancelar venda ' + objeto + ' ? Você não poderá alterá-la depois.',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, cancelar agora!',
			closeOnConfirm: false
		}, onCancelarConfirmado.bind(this));
		
	}
	
	function onCancelarConfirmado(){		
		var acaoInput = $('<input type="hidden">');
		acaoInput.attr('name', 'cancelar');
		this.formulario.append(acaoInput);
		this.formulario.submit();
	}
	
	return BotaoCancelar
	
}());

$(function() {
	
	var botaoSubmit = new Brewer.BotaoSubmit();
	botaoSubmit.iniciar();
	var botaoCancelar = new Brewer.BotaoCancelar();
	botaoCancelar.iniciar();
	
});