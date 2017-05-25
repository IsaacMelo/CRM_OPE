Brewer = Brewer || {};

Brewer.DialogoExcluirLogico = (function() {
	
	function DialogoExcluirLogico() {
		this.exclusaoBtn = $('.js-exclusao-btn-logico');
	}
	
	DialogoExcluirLogico.prototype.iniciar = function() {
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
		
		swal({
			title: 'Tem certeza?',
			text: 'Deseja Inativar ' + objeto + ' ?',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, inativar agora!',
			closeOnConfirm: false
		}, onExcluirConfirmado.bind(this, url));
	}
	
	function onExcluirConfirmado(url) {
		$.ajax({
			url: url,
			method: 'DELETE',
			success: onExcluidoSucesso.bind(this),
			error: onErroExcluir.bind(this)
		});
	}
	
	function onExcluidoSucesso() {
		var urlAtual = window.location.href;
		var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
		var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';
		window.location = novaUrl;
	}
	
	function onErroExcluir(e) {
		swal('Oops!', e.responseText, 'error');
	}
	
	return DialogoExcluirLogico;
	
}());

$(function() {
	var dialogoLogico = new Brewer.DialogoExcluirLogico();
	dialogoLogico.iniciar();
});
