package com.secure.taskManager.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class ConfiguracaoSeguranca {
	
	
	final private FiltroDeSeguranca filtroDeSeguranca;
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
		return httpSecurity
				
				.csrf(csrf -> csrf.disable())// Desabilita proteção contra CSRF (padrão para APIs REST)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// Indica que não guardaremos sessão
				.authorizeHttpRequests(authorize -> authorize
						// Rotas Públicas (Login e Registro)
						.requestMatchers(HttpMethod.POST,"/auth/registro").permitAll()
						.requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
						.requestMatchers(HttpMethod.GET,"/tarefas").permitAll()
						.requestMatchers("/h2-console/**").permitAll()
						
						// Rotas de Admin
						.requestMatchers(HttpMethod.POST,"/tarefas").hasRole("ADMIN")// Exemplo: Só admin cria tarefas (por enquanto)
						
						// Qualquer outra rota precisa estar autenticada
						.anyRequest().authenticated()
						)//fim da autorizações
				.headers(headers -> headers.frameOptions().disable())
				.addFilterBefore(filtroDeSeguranca, UsernamePasswordAuthenticationFilter.class)// <-- ADICIONAREMOS O JWT AQUI DEPOIS
				.build();
		}
	
	
	

}
