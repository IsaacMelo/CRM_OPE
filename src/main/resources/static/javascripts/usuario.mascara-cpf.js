var Brewer = Brewer || {};

Brewer.MascaraCpf = (function() {
	
	function MascaraCpf() {
		this.inputCpf = $('#cpf');
	}
	
	MascaraCpf.prototype.iniciar = function() {
		aplicarMascara.call(this);
	}
	
	
	function aplicarMascara() {
		this.inputCpf.mask("000.000.000-00");
	}
	
	return MascaraCpf;
	
}());

$(function() {
	var mascaraCpf = new Brewer.MascaraCpf();
	mascaraCpf.iniciar();
});