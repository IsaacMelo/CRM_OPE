package com.impacta.crm.controller.validator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.impacta.crm.model.Venda;

@Component
public class VendaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Venda.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "cliente.codigo", "", "Selecione um cliente na pesquisa rápida");
		ValidationUtils.rejectIfEmpty(errors, "formaPagamento.codigo", "", "Selecione a forma de pagamento");
		
		Venda venda = (Venda) target;
		validarSeInformouApenasHorarioEntrega(errors, venda);
		validarSeInformouItens(errors, venda);
		validarValorTotalNegativo(errors, venda);
		validarValorMaxDesconto(errors, venda);
	}

	private void validarValorMaxDesconto(Errors errors, Venda venda) {
		
		BigDecimal totalSemDesc = venda.getValorTotalItens()
				.add(Optional.ofNullable(venda.getValorFrete()).orElse(BigDecimal.ZERO));
		
		BigDecimal valorMaxDesc = totalSemDesc
				.multiply(Optional.ofNullable(venda.getDescontoMax()).orElse(BigDecimal.ZERO))
				.divide(new BigDecimal(100));
		
		BigDecimal valorDesc = Optional.ofNullable(venda.getValorDesconto()).orElse(BigDecimal.ZERO)
				.subtract(Optional.ofNullable(valorMaxDesc).orElse(BigDecimal.ZERO));
		
		if (valorDesc.compareTo(BigDecimal.ZERO) > 0) {
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			String valorMaxDescNF = nf.format (valorMaxDesc);
			errors.rejectValue("valorDesconto", "", "Desconto máximo permitido na venda "+valorMaxDescNF);
		}
		
	}

	private void validarValorTotalNegativo(Errors errors, Venda venda) {
		if (venda.getValorTotal().compareTo(BigDecimal.ZERO) < 0) {
			errors.reject("", "Valor total não pode ser negativo");
		}
	}

	private void validarSeInformouItens(Errors errors, Venda venda) {
		if (venda.getItens().isEmpty()) {
			errors.reject("", "Adicione pelo menos um produto na venda");
		}
	}
	
	private void validarSeInformouApenasHorarioEntrega(Errors errors, Venda venda) {
		if (venda.getHorarioEntrega() != null && venda.getDataEntrega() == null) {
			errors.rejectValue("dataEntrega", "", "Informe uma data da entrega para um horário");
		}
	}
	
}
