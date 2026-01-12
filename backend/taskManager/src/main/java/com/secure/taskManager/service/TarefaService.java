package com.secure.taskManager.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.secure.taskManager.dtos.DadosCadastroTarefa;
import com.secure.taskManager.entidades.Papel;
import com.secure.taskManager.entidades.Tarefa;
import com.secure.taskManager.entidades.Usuario;
import com.secure.taskManager.repository.TarefaRepository;
import com.secure.taskManager.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    // MÉTODO SEGURO: Lista tarefas baseada no papel
    public List<Tarefa> listarTarefasSeguras() {
        Usuario usuarioLogado = getUsuarioLogado();

        if (usuarioLogado.getPapel() == Papel.ADMIN) {
            // Se for ADMIN, vê tudo
            return tarefaRepository.findAll();
        } else {
            // Se for USER, vê apenas as suas
            return tarefaRepository.findByUsuario(usuarioLogado);
        }
    }
    
    

    public Tarefa criarTarefa(DadosCadastroTarefa dados) {
        Usuario usuarioLogado = getUsuarioLogado();
        Usuario donoDaTarefa = usuarioLogado;

        // Lógica de Atribuição:
        // Se for ADMIN e ele mandou um ID de outro usuário, atribuímos a esse outro usuário.
        if (usuarioLogado.getPapel() == Papel.ADMIN && dados.idUsuarioParaAtribuir() != null) {
            donoDaTarefa = usuarioRepository.findById(dados.idUsuarioParaAtribuir())
                    .orElseThrow(() -> new RuntimeException("Usuário alvo não encontrado"));
        }

        Tarefa novaTarefa = new Tarefa(dados.titulo(), dados.descricao(), donoDaTarefa);
        return tarefaRepository.save(novaTarefa);
    }

    // Método auxiliar para pegar o usuário que está no SecurityContext (vindo do Token JWT)
    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}