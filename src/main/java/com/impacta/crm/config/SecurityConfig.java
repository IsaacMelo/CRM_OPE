package com.impacta.crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.impacta.crm.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**")
			.antMatchers("/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/clientes/**").hasRole("CADASTRAR_CLIENTES")
				.antMatchers("/fornecedores/**").hasRole("CADASTRAR_FORNECEDORES")
				.antMatchers("/produtos/**").hasRole("CADASTRAR_PRODUTOS")
				.antMatchers("/categorias/**").hasRole("CADASTRAR_CATEGORIAS")
				.antMatchers("/usuarios").hasRole("VISUALIZAR_USUARIOS")
				.antMatchers("/usuarios/novo").hasRole("CADASTRAR_USUARIOS")
				.antMatchers("/vendas").hasRole("CADASTRAR_VENDAS")
				.antMatchers("/vendas/nova").hasRole("CADASTRAR_VENDAS")
				.antMatchers("/vendas/faturadas").hasRole("VENDAS_FATURADAS")
				.antMatchers("/parametros").hasRole("CADASTRAR_PARAMETROS")
				.antMatchers("/relatorios/comissaoVendedor").hasRole("RELATORIO_COMISSOES")
				.antMatchers("/relatorios/vendasEmitidas").hasRole("RELATORIO_VENDAS_EMITIDAS")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403")
				.and()
			.sessionManagement()
				.invalidSessionUrl("/login");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
