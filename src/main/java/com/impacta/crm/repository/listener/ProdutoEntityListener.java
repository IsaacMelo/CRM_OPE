package com.impacta.crm.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.stereotype.Component;

import com.impacta.crm.model.Produto;
import com.impacta.crm.storage.FotoStorage;

@Component
public class ProdutoEntityListener {

//	@Autowired
//	private FotoStorage fotoStorage;

	@PostLoad
	public void postLoad(final Produto produto) {
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		produto.setUrlFoto(FotoStorage.URL + produto.getFotoOuMock());
		produto.setUrlThumbnailFoto(FotoStorage.URL + FotoStorage.THUMBNAIL_PREFIX + produto.getFotoOuMock());
	}

}
