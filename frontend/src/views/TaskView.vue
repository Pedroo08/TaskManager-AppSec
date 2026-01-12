<script setup lang="ts">
    import {ref} from 'vue';
    import TaskCard from '@/components/TaskCard.vue';  

    const tasks =ref([

        { 
            id: 1, 
            title: 'Configurar Ambiente', 
            description: 'Instalar Vue, Tailwind e configurar PostCSS.', 
            status: 'completed' 
        },
        { 
            id: 2, 
            title: 'Implementar Auth', 
            description: 'Criar fluxo de login com proteção contra Session Fixation.', 
            status: 'pending' 
        },
        { 
            id: 3, 
            title: 'Sanitização de Inputs', 
            description: 'Garantir que nenhum dado de usuário cause XSS no TaskCard.', 
            status: 'pending' 
        }
    ]);

    const handleCriarTarefa  = () =>{

        console.log("tarefa criada")

    }
</script>

<template >
    <main class="max-w-4xl mx-auto p-6">
        <header class="mb-8 flex justify-between items-center">
            <div>
                <h1 class="text-3xl font-bold text-white">Minhas Tarefas</h1>
                <p class="text-slate-400">Gerencie suas atividades com segurança</p>
            </div>
            <button @click="handleCriarTarefa" class="bg-blue-600 hover:bg-blue-500 text-white px-4 py-2 rounded-lg font-semibold trasition-all">
              + Nova Tarefa  
            </button>
        </header>

        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <TaskCard v-for="item in tasks" :key="item.id" :task="item" />
        </div>
    </main>
</template>