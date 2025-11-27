package com.secure.taskManager.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.secure.taskManager.repository.UsuarioRepository;
import com.secure.taskManager.service.ServicoToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FiltroDeSeguranca extends OncePerRequestFilter {

    final private ServicoToken servicoToken;
    final private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        
        // DEBUG: Mostra informações da requisição
        System.out.println("=== FILTRO DE SEGURANÇA ===");
        System.out.println("Request URI: " + requestURI);
        System.out.println("Method: " + request.getMethod());

        // 1. Se for rota pública, passa direto sem verificar token
        if (isRotaPublica(request)) {
            System.out.println("✅ Rota pública - passando direto");
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Tenta recuperar o token do cabeçalho (apenas para rotas protegidas)
        var token = recuperarToken(request);
        
        if (token == null) {
            System.out.println("❌ Token não encontrado para rota protegida");
            // Não define autenticação - o Spring Security bloqueará depois
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Se o token existir, valida ele
        System.out.println("🔐 Validando token...");
        var login = servicoToken.validarToken(token);
        
        if (login.isEmpty()) {
            System.out.println("❌ Token inválido ou expirado");
            filterChain.doFilter(request, response);
            return;
        }

        // 4. Busca o usuário no banco
        System.out.println("👤 Buscando usuário: " + login);
        UserDetails usuario = usuarioRepository.findByLogin(login);
        
        if (usuario == null) {
            System.out.println("❌ Usuário não encontrado no banco: " + login);
            filterChain.doFilter(request, response);
            return;
        }

        // 5. Cria a autenticação
        System.out.println("✅ Autenticando usuário: " + login);
        var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(autenticacao);

        // 6. Continua a requisição
        filterChain.doFilter(request, response);
        
        System.out.println("=== FILTRO FINALIZADO ===");
    }

    // Método para identificar rotas públicas
    private boolean isRotaPublica(HttpServletRequest request) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        
        // Rotas públicas
        if (method.equals("POST") && uri.equals("/auth/login")) return true;
        if (method.equals("POST") && uri.equals("/auth/registro")) return true;
        if (method.equals("GET") && uri.equals("/tarefas")) return true;
        if (uri.startsWith("/h2-console")) return true;
        
        return false;
    }

    // Método auxiliar para limpar o prefixo "Bearer "
    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            System.out.println("📭 Header Authorization não encontrado");
            return null;
        }
        if (!authHeader.startsWith("Bearer ")) {
            System.out.println("⚠️  Header Authorization não começa com 'Bearer '");
            return null;
        }
        
        String token = authHeader.replace("Bearer ", "");
        System.out.println("🔑 Token recuperado: " + (token.length() > 10 ? token.substring(0, 10) + "..." : token));
        return token;
    }
}