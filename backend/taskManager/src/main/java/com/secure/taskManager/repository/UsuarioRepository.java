package com.secure.taskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.secure.taskManager.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	    // Método especial para o Spring Security encontrar o usuário pelo login
	    UserDetails findByLogin(String login);
}


