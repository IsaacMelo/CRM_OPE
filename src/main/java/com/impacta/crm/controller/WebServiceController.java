package com.impacta.crm.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

@Controller
@RequestMapping("/webservice")
public class WebServiceController {

	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ViaCEPEndereco pesquisaDeEndereco(@RequestParam(name = "cep", defaultValue = "") String cep) throws IOException{
		ViaCEPClient client = new ViaCEPClient();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {	}
		return client.getEndereco(cep);		
	}
}
