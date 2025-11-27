package com.secure.taskManager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secure.taskManager.dtos.DadosLoginDTO;
import com.secure.taskManager.dtos.DadosRegistroDTO;
import com.secure.taskManager.dtos.DadosTokenJWT;
import com.secure.taskManager.entidades.Papel;
import com.secure.taskManager.entidades.Usuario;
import com.secure.taskManager.repository.UsuarioRepository;
import com.secure.taskManager.service.ServicoAutenticacao;
import com.secure.taskManager.service.ServicoToken;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {
	

    final private AuthenticationManager manager; // O "porteiro" que sabe validar credenciais
	
	
    final private UsuarioRepository usuarioRepository;
	
	
    final private ServicoToken servicoToken; // O nosso gerador de crachás
    
    final private ServicoAutenticacao servicoAutenticacao;

    
    final private PasswordEncoder passwordEncoder;
    
    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLoginDTO dados) {
        // 1. Encapsula login e senha num objeto que o Spring Security entende
        var tokenDeAutenticacao = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        // 2. Pede para o Spring validar (ele bate no banco e checa o hash automaticamente)
        // Se a senha estiver errada, ele lança um erro e o método para aqui.
        var autenticacao = manager.authenticate(tokenDeAutenticacao);

        // 3. Se chegou aqui, é porque deu certo. Vamos gerar o Token.
        // (Usuario) autenticacao.getPrincipal() pega o objeto Usuario que foi carregado do banco
        var tokenJWT = servicoToken.gerarToken((Usuario) autenticacao.getPrincipal());

        // 4. Devolve o token num JSON
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/registro")
    public ResponseEntity registrar(@RequestBody DadosRegistroDTO dados) {
        if (usuarioRepository.findByLogin(dados.login()) != null) {
            return ResponseEntity.badRequest().body("Login já existe.");
        }
        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        Papel papel = (dados.papel() == Papel.ADMIN) ? Papel.ADMIN : Papel.USER;
        Usuario novoUsuario = new Usuario(dados.login(), senhaCriptografada, papel);
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
	
	
}
