package com.secure.taskManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.taskManager.entidades.Tarefa;
import com.secure.taskManager.entidades.Usuario;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	
	List<Tarefa> findByUsuario(Usuario usuario);
}
