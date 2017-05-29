package com.impacta.crm.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SegurancaController {

	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) {		
		if (user != null) {
			if(user.getAuthorities().contains("ROLE_VISUALIZAR_DASHBOARD")){
				return "redirect:/";
			}
			if(user.getAuthorities().contains("ROLE_CADASTRAR_VENDAS")){
				return "redirect:/vendas";
			}
			if(user.getAuthorities().contains("ROLE_CADASTRAR_PRODUTOS")){
				return "redirect:/produtos";
			}
			if(user.getAuthorities().contains("ROLE_VENDAS_FATURADAS")){
				return "redirect:/vendas/faturadas";
			}
		}
		
		return "Login";
	}
	
	@GetMapping("/403")
	public String acessoNegado() {
		return "403";
	}
	
}
