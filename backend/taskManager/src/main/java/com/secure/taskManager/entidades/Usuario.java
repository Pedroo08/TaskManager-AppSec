package com.secure.taskManager.entidades;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name ="usuarios")
@Entity(name ="usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails{ // <--- O SEGREDO ESTÁ AQUI
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha; // Será salva criptografada (BCrypt)
	
    @Enumerated(EnumType.STRING)
    private Papel papel; // Enum que criaremos abaixo (ADMIN ou USER)
    
    @Override
    public Collection <? extends GrantedAuthority> getAuthorities(){
    	if( this.papel == Papel.ADMIN) {
    		return List.of(
    				new SimpleGrantedAuthority("ROLE_ADMIN"),
    				new SimpleGrantedAuthority("ROLE_USER")
    		);}
    	
    	return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    	
    }
    
    
    @Override
    public String getPassword() {
        return senha; // O Spring usa isso para bater com o banco
    }

    @Override
    public String getUsername() {
        return login; // O identificador único
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

	public Usuario(String login, String senhaCriptografada, Papel papel) {
		this.login = login;
		this.senha = senhaCriptografada;
		this.papel = papel;
	}
    
}
