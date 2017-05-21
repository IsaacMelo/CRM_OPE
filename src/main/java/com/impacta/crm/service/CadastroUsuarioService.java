package com.impacta.crm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.impacta.crm.model.ContaBancaria;
import com.impacta.crm.model.Usuario;
import com.impacta.crm.repository.ContasBancarias;
import com.impacta.crm.repository.Usuarios;
import com.impacta.crm.service.exception.ContaObrigatoriaUsuarioException;
import com.impacta.crm.service.exception.ContaPrincipalUsuarioException;
import com.impacta.crm.service.exception.EmailUsuarioJaCadastradoException;
import com.impacta.crm.service.exception.SenhaObrigatoriaUsuarioException;
import com.impacta.crm.session.TabelaContaBancariaSession;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private ContasBancarias contasBancarias;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TabelaContaBancariaSession tabelaContas;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findByEmail(usuario.getEmail());
		
		if(usuario.getContas().isEmpty()){
			throw new ContaObrigatoriaUsuarioException("Usuário deve ter ao menos uma conta");
		}
		
		if(existePrincipal(usuario) == null){
			throw new ContaPrincipalUsuarioException("Usuário deve ter uma conta principal");
		}
	
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		}
		
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		}
		
		if (usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		} else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		if (!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}
		
		usuarios.save(usuario);
	}

	private ContaBancaria existePrincipal(Usuario usuario) {
		return usuario.getContas().stream().filter(c-> c.isPrincipal()).findAny().orElse(null);
	}

	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario statusUsuario) {
		statusUsuario.executar(codigos, usuarios);
	}

	@Transactional
	public void excluirConta(String uuid, String id) {
		ContaBancaria contaExclusao = tabelaContas.get(uuid, id);
		if(contaExclusao.getCodigo() != null){
			contasBancarias.delete(contaExclusao);
		}
		
	}
	
}
