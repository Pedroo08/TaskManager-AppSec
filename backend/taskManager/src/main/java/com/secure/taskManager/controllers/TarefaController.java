package com.secure.taskManager.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secure.taskManager.dtos.DadosCadastroTarefa;
import com.secure.taskManager.entidades.Tarefa;
import com.secure.taskManager.service.TarefaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {
	
	private final TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<Tarefa>> listar() {
        // O service decide se retorna tudo ou só as do usuário
        return ResponseEntity.ok(tarefaService.listarTarefasSeguras());
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody DadosCadastroTarefa dados) {
        return ResponseEntity.ok(tarefaService.criarTarefa(dados));
    }

}
