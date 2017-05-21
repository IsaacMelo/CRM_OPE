var Brewer = Brewer || {};

Brewer.ContaCadastroRapido = (function() {
	
	function ContaCadastroRapido() {
		this.modal = $('#modalCadastroRapidoConta');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-conta-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputBanco = $('#banco');
		this.inputAgencia = $('#agencia');
		this.inputConta = $('#conta');
		this.inputPrincipal = $('#principal');
		this.inputId = $('#id');
		this.uuid = $('#uuid');
		this.inputCodigo = $('#codigoConta');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-conta');
		this.tabelaContaBacaria = $('#table-body-conta');
		this.htmlTabelaPesquisa = $('#js-tabela-conta-bancaria').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
	}
	
	ContaCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow(e) {
		
		if(e.relatedTarget.getAttribute('data-param') == 'editar'){
			this.inputBanco.val(e.relatedTarget.getAttribute('data-banco')); 
			this.inputAgencia.val(e.relatedTarget.getAttribute('data-agencia'));
			this.inputConta.val(e.relatedTarget.getAttribute('data-conta'));
			this.inputId.val(e.relatedTarget.getAttribute('data-id'));
			this.inputCodigo.val(e.relatedTarget.getAttribute('data-codigo'));
			if(e.relatedTarget.getAttribute('data-principal') == 'true'){
				$('#principal').prop('checked', true).change();
				this.inputPrincipal.val(true);
			}else{
				$('#principal').prop('checked', false).change();
				this.inputPrincipal.val(false);
			}
			
		}
		
		this.botaoSalvar.attr('data-param', e.relatedTarget.getAttribute('data-param'));
		this.inputBanco.focus();
	}
	
	function onModalClose() {
		this.inputBanco.val('');
		this.inputAgencia.val('');
		this.inputConta.val('');
		this.inputId.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		this.form.find('.form-group.banco').removeClass('has-error');
		this.form.find('.form-group.agencia').removeClass('has-error');
		this.form.find('.form-group.conta').removeClass('has-error');
		if(this.botaoSalvar.attr('data-param') == 'salvar'){
			salvar.call(this);
		}else{
			editar.call(this);
		}
		
	}
	
	function onErroSalvandoConta(obj) {
		var erros = obj.responseJSON;
		this.containerMensagemErro.empty();
		this.containerMensagemErro.removeClass('hidden');
		erros.forEach(function(e){
			this.containerMensagemErro.append('<div><i class="fa  fa-exclamation-circle"></i> ' + e.defaultMessage + '</div>');
			if(e.field == 'banco'){
				this.form.find('.form-group.banco').addClass('has-error');
			}else if(e.field == 'agencia'){
				this.form.find('.form-group.agencia').addClass('has-error');
			}else if (e.field == 'conta'){
				this.form.find('.form-group.conta').addClass('has-error');
			}
				
		}.bind(this));
	}
	
	function onContaSalvo(contas) {
		var html = this.template(contas);
		this.tabelaContaBacaria.html(html);
		this.modal.modal('hide');
		var dialogo = new Brewer.DialogoExcluirConta();
		dialogo.iniciar();
	}
	
	function editar(){
		var nomeBanco = this.inputBanco.val().trim();
		var agencia = this.inputAgencia.val().trim();
		var conta = this.inputConta.val().trim();
		var id = this.inputId.val().trim();
		var uuid = this.uuid.val().trim();
		var principal = this.inputPrincipal.val();
		var codigo = this.inputCodigo.val();
		$.ajax({
			url: this.url,
			method: 'PUT',
			contentType: 'application/json',
			data: JSON.stringify({ banco: nomeBanco, agencia: agencia, conta: conta, id:id, principal:principal, uuid:uuid, codigo:codigo}),
			error: onErroSalvandoConta.bind(this),
			success: onContaSalvo.bind(this)
		});
	}
	
	function salvar(){
		var nomeBanco = this.inputBanco.val().trim();
		var agencia = this.inputAgencia.val().trim();
		var conta = this.inputConta.val().trim();
		var uuid = this.uuid.val().trim();
		var principal = this.inputPrincipal.val();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ banco: nomeBanco, agencia: agencia, conta: conta, principal:principal, uuid: uuid}),
			error: onErroSalvandoConta.bind(this),
			success: onContaSalvo.bind(this)
		});
	}
	
	return ContaCadastroRapido;
	
}());

$(function() {
	var contaCadastroRapido = new Brewer.ContaCadastroRapido();
	contaCadastroRapido.iniciar();
});