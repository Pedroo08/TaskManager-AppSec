package com.secure.taskManager.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.secure.taskManager.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicoAutenticacao implements UserDetailsService{
	
	
   final  private UsuarioRepository repository;
   
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // O AuthenticationManager chama este método automaticamente
       // Ele busca no banco pelo login e retorna o objeto UserDetails (seu Usuario)
       return repository.findByLogin(username);
   }

}
