package com.secure.taskManager.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.secure.taskManager.entidades.Usuario;

@Service
public class ServicoToken {

	// Pegamos o "segredo" do application.properties para não deixar hardcoded no código
    @Value("${api.security.token.secret}")
    private String secret;
	
	public String gerarToken(Usuario usuario) {
		try {
			// Define o algoritmo de criptografia (HMAC256) usando a senha secreta
			Algorithm algoritimo = Algorithm.HMAC256(secret);
			
			return JWT.create()
					.withIssuer("Meu Sistema")// Quem emitiu o token (nossa API)
					.withSubject(usuario.getLogin())// Quem é o dono do token (Login)
					.withExpiresAt(dataExpiracao())// Validade do token
					.sign(algoritimo);// Assina digitalmente
			}catch(JWTCreationException exception) {
				throw new RuntimeException("Erro ao gerar token JWT", exception);
			}
		
	}
	
	public String validarToken(String tokenJWT) {
		try {
			Algorithm algoritmo = Algorithm.HMAC256(secret);
			
			return JWT.require(algoritmo)
					.withIssuer("Meu Sistema") // Verifica se foi nossa API mesmo que emitiu
                    .build()
                    .verify(tokenJWT) // Descriptografa e valida validade/assinatura
                    .getSubject(); // Retorna o login que estava dentro do token
                    
        } catch (JWTVerificationException exception) {
            // Se o token for inválido ou expirado, retorna vazio (o filtro vai barrar depois)
            return "";
        }
	}
	
	
	
	// Define que o token expira em 2 horas (Zona Timezone Brasil -03:00)
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
	
}
