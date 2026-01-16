<script setup lang="ts">
    import TaskCard from '@/components/TaskCard.vue';  
    import { useTaskStore } from '@/stores/taskStore';
    import TaskModal from '@/components/TaskModal.vue';
    import TaskModalEdit from '@/components/TaskModalEdit.vue';
    import { ref } from 'vue';
    import type { Task } from '@/types/Task'; // Importando o tipo
    
    const taskStore = useTaskStore();
    const isModalOpen = ref(false);
    const isModalEditOpen = ref(false);
    const taskForEdit = ref<Task | null>(null);// Armazena a tarefa que clicamos

    const handleAddTask = (data: { title: string, description: string }) => {
        taskStore.addTask(data.title, data.description);
        isModalOpen.value = false; // Fecha o modal após salvar
    };

    const handleDelete = (id:number) => {
        taskStore.deleteTask(id)
    }

    const OpenModalEdit = (task:Task) =>{
        taskForEdit.value = task;
        isModalEditOpen.value = true

    }

    const handleComplete = (task:Task) =>{
        taskForEdit.value = task;
        taskStore.completeTask(task.id)

    }

    const handleEditSave = (data:{ id:number, title: string, description: string , status: Task['status']}) => {
        taskStore.editTask(data.id, data.title,data.description,data.status);
        isModalEditOpen.value = false //fecha
    }



</script>

<template >
    <main class="max-w-4xl mx-auto p-6">
        <header class="mb-8 flex justify-between items-center">
            <div>
                <h1 class="text-3xl font-bold text-white">Minhas Tarefas</h1>
                <p class="text-slate-400">Gerencie suas atividades com segurança</p>
            </div>
            <button @click="isModalOpen = true" class="bg-blue-600 hover:bg-blue-500 text-white px-4 py-2 rounded-lg font-semibold trasition-all">
              + Nova Tarefa  
            </button>
        </header>

        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <TaskCard v-for="item in taskStore.tasks" :key="item.id" :task="item" @excluir="handleDelete" @editar="OpenModalEdit"  @complete="handleComplete"/>
        </div>

        <TaskModal 
            :isOpen="isModalOpen" 
            @close="isModalOpen = false" 
            @save="handleAddTask"
         />
         <TaskModalEdit
            :isOpen="isModalEditOpen" 
            :task="taskForEdit"
            @close="isModalEditOpen = false" 
            @edit="handleEditSave"
        />

    </main>
</template>