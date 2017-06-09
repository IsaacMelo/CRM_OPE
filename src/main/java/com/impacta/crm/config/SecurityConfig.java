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
@ComponentScan(basePackageClasses = {AppUserDetailsService.class, PasswordEncoder.class})
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
				.antMatchers("/clientes/**").hasRole("MANTER_CLIENTES")
				.antMatchers("/fornecedores/**").hasRole("MANTER_FORNECEDORES")
				.antMatchers("/produtos/{\\+d}").hasRole("MANTER_PRODUTOS")
				.antMatchers("/categorias/**").hasRole("MANTER_CATEGORIAS")
				.antMatchers("/usuarios").hasRole("MANTER_USUARIOS")
				.antMatchers("/usuarios/novo").hasRole("MANTER_USUARIOS")
				.antMatchers("/usuarios/{\\+d}").hasRole("MANTER_USUARIOS")
				.antMatchers("/vendas").hasRole("MANTER_VENDAS")
				.antMatchers("/vendas/nova").hasRole("MANTER_VENDAS")
				.antMatchers("/vendas/faturadas").hasRole("MANTER_VENDAS_FATURADAS")
				.antMatchers("/parametros").hasRole("MANTER_PARAMETROS")
				.antMatchers("/relatorios/**").hasRole("RELATORIOS")
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
