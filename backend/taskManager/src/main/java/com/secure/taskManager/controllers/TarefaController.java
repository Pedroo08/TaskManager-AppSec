package com.secure.taskManager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
	
	@PostMapping
    public String criarTarefa() { 
		
		return "Tarefa criada!"; 
    }

	
	@GetMapping
	public String teste() {
		return "OK";
	}

}
