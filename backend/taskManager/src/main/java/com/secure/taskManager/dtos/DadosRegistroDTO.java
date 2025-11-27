package com.secure.taskManager.dtos;

import com.secure.taskManager.entidades.Papel;

public record DadosRegistroDTO(String login, String senha,  Papel papel) {

}
