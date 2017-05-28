var Brewer = Brewer || {};

Brewer.GraficoVendaPorMes = (function() {
	
	function GraficoVendaPorMes() {
		this.ctx = $('#graficoVendasPorMes')[0].getContext('2d');
	}
	
	GraficoVendaPorMes.prototype.iniciar = function() {
		$.ajax({
			url: 'vendas/totalPorMes',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(vendaMes) {
		var meses = [];
		var valores = [];
		vendaMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var graficoVendasPorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Vendas por mês',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                data: valores
		    	}]
		    },
		});
	}
	
	return GraficoVendaPorMes;
	
}());

Brewer.GraficoVendaPorOrigem = (function() {
	
	function GraficoVendaPorOrigem() {
		this.ctx = $('#graficoVendasPorOrigem')[0].getContext('2d');
	}
	
	GraficoVendaPorOrigem.prototype.iniciar = function() {
		$.ajax({
			url: 'vendas/porOrigem',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(vendaOrigem) {
		var meses = [];
		var cervejasNacionais = [];
		var cervejasInternacionais = [];
		
		vendaOrigem.forEach(function(obj) {
			meses.unshift(obj.mes);
			cervejasNacionais.unshift(obj.totalNacional);
			cervejasInternacionais.unshift(obj.totalInternacional)
		});
		
		var graficoVendasPorOrigem = new Chart(this.ctx, {
		    type: 'bar',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Nacional',
		    		backgroundColor: "rgba(220,220,220,0.5)",
	                data: cervejasNacionais
		    	},
		    	{
		    		label: 'Internacional',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                data: cervejasInternacionais
		    	}]
		    },
		});
	}
	
	return GraficoVendaPorOrigem;
	
}());

Brewer.GraficoVendaPorCategoria = (function() {
	
	function GraficoVendaPorCategoria() {
		this.ctx = $('#graficoVendasPorCategoria')[0].getContext('2d');
	}
	
	GraficoVendaPorCategoria.prototype.iniciar = function() {
		$.ajax({
			url: 'vendas/porCategoria',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(vendaCategoria) {
		//função que retorna um número hexadecimal aleatório entre 0 e 255 (FF):
		function Hx() { return parseInt((Math.random() * 255)).toString(16); }

		//Função para retornar o código completo da cor, com 3 números aleatórios:
		function CorAleat() { return "#" + Hx() + Hx() + Hx(); }

		//Testando a função:
		var nova_cor = CorAleat();
		
		
		var nome = [];
		var quantidade = [];
		var cor = [];

		vendaCategoria.forEach(function(obj) {
			nome.unshift(obj.nome);
			quantidade.unshift(obj.quantidade);
			cor = CorAleat();
		});
		
		
		var data = {
			    labels: nome,
			    datasets: [
			        {
			            data: quantidade,
			            backgroundColor: [
			                "#FF6384",
			                "#36A2EB",
			                "#FFCE56",
			                "#9ACD32",
			                "#CD5C5C",
			                "#FF8C00",
			                "#EE82EE",
			                "#CDC8B1",
			                "#836FFF",
			                "#63B8FF",
			                "#97FFFF"
			            ]
			        }]
			};
		
		var graficoVendasPorOrigem = new Chart(this.ctx, {
		    type: 'doughnut',
		    data: data,
		    options: {
		        animation:{
		            animateScale:true
		        }
		    }

		});
	}
	
	return GraficoVendaPorCategoria;
	
}());


$(function() {
	var graficoVendaPorMes = new Brewer.GraficoVendaPorMes();
	graficoVendaPorMes.iniciar();
	
	//var graficoVendaPorOrigem = new Brewer.GraficoVendaPorOrigem();
	//graficoVendaPorOrigem.iniciar();
	
	var graficoVendaPorCategoria = new Brewer.GraficoVendaPorCategoria();
	graficoVendaPorCategoria.iniciar();
});
