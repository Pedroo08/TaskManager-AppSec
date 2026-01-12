package com.secure.taskManager.dtos;

public record DadosCadastroTarefa(String titulo, 
	    String descricao,
	    Long idUsuarioParaAtribuir // Opcional: Só Admin deve usar
	    ) {}
