Brewer = Brewer || {};

Brewer.DialogoExcluir = (function() {
	
	function DialogoExcluir() {
		this.exclusaoBtn = $('.js-exclusao-btn-permanente');
	}
	
	DialogoExcluir.prototype.iniciar = function() {
		this.exclusaoBtn.on('click', onExcluirClicado.bind(this));
		if (window.location.search.indexOf('excluido') > -1) {
			window.history.pushState('','',window.location.href.replace('?excluido',''));
			window.history.pushState('','',window.location.href.replace('&excluido',''));
			swal('Pronto!', 'Excluído com sucesso!', 'success');			
		}
	}
	
	function onExcluirClicado(evento) {
		evento.preventDefault();
		var botaoClicado = $(evento.currentTarget);
		var url = botaoClicado.data('url');
		var objeto = botaoClicado.data('objeto');
		var redirect = botaoClicado.data('redirect');
		
		swal({
			title: 'Tem certeza?',
			text: 'Excluir ' + objeto + ' ? Você não poderá recuperar depois.',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, excluir agora!',
			closeOnConfirm: false
		}, onExcluirConfirmado.bind(this, url, redirect));
	}
	
	function onExcluirConfirmado(url,redirect) {
		$.ajax({
			url: url,
			method: 'DELETE',
			success: onExcluidoSucesso.bind(this, redirect),
			error: onErroExcluir.bind(this)
		});
	}
	
	function onExcluidoSucesso(redirect) {
		var urlAtual = redirect;
		var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
		var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';
		window.location = novaUrl;
	}
	
	function onErroExcluir(e) {
		swal('Oops!', e.responseText, 'error');
	}
	
	return DialogoExcluir;
	
}());

$(function() {
	var dialogo = new Brewer.DialogoExcluir();
	dialogo.iniciar();
});
