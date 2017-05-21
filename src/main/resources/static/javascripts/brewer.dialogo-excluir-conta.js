Brewer = Brewer || {};

Brewer.DialogoExcluirConta = (function() {
	
	function DialogoExcluirConta() {
		this.uuid = $('#uuid').val();
		this.exclusaoBtn = $('.js-exclusao-conta-btn');
		this.tabelaContaBacaria = $('#table-body-conta');
		this.htmlTabelaPesquisa = $('#js-tabela-conta-bancaria').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
	}
	
	DialogoExcluirConta.prototype.iniciar = function() {
		this.exclusaoBtn.on('click', onExcluirClicado.bind(this));
	}
	
	function onExcluirClicado(event) {
		event.preventDefault();
		var objeto = $(event.currentTarget).data('objeto');
		var url = $(event.currentTarget).data('url');
		var id = $(event.currentTarget).data('id');
		console.log(id);
		swal({
			title: 'Tem certeza?',
			text: 'Excluir "' + objeto + '" ? Você não poderá recuperar depois.',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, excluir agora!',
			closeOnConfirm: true
		}, onExcluirConfirmado.bind(this, url,id));
	}
	
	function onExcluirConfirmado(url,id) {
		$.ajax({
			url: url+'/'+this.uuid+'/'+id+'/',
			method: 'DELETE',
			success: onExcluidoSucesso.bind(this),
			error: onErroExcluir.bind(this)
		});		
		
	}
	
	function onExcluidoSucesso(contas) {
		var html = this.template(contas);
		this.tabelaContaBacaria.html(html);
		setTimeout(function(){
			swal('Pronto!', 'Excluído com sucesso!', 'success');
		},100)
		var dialogo = new Brewer.DialogoExcluirConta();
		dialogo.iniciar();	
	}
	
	function onErroExcluir(e) {
		swal('Oops!', e.responseText, 'error');
	}
	
	return DialogoExcluirConta;
	
}());

$(function() {
	var dialogo = new Brewer.DialogoExcluirConta();
	dialogo.iniciar();
});