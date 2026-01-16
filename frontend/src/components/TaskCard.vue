<script setup  lang="ts">
import type { Task } from '@/types/Task';


// Definindo as props que o componente vai receber
 const props = defineProps<{task:Task}>();

 const emit = defineEmits(['editar','excluir','complete'])

  const handleDelete = (id:number) =>{
    emit('excluir',id)
  }

  const OpenEdit = () =>{
    emit('editar',props.task)
  }

   const complete = () =>{
    emit('complete',props.task)
  }

</script>

<template>
    <div class="bg-slate-800 p-4 rounded-xl border border-slate-700 hover:border-blue-500 transition-colors shadow-sm">
    <div class="flex justify-between items-start mb-2">
      <h3 class="text-lg font-semibold text-slate-100">{{ task.title }}</h3>
      <span 
        :class="task.status === 'completed' ? 'bg-green-900 text-green-300' : 'bg-yellow-900 text-yellow-300'"
        class="text-xs px-2 py-1 rounded-full font-medium"
        @click="complete"
      >
        {{ task.status }}
      </span>
    </div>
    <p class="text-slate-400 text-sm leading-relaxed">
      {{ task.description }}
    </p>
    <div class="mt-4 flex gap-2">
      <button class="text-xs text-blue-400 hover:text-blue-300 font-medium"  @click="OpenEdit">Editar</button>
      <button class="text-xs text-red-400 hover:text-red-300 font-medium" @click="handleDelete(task.id)">Excluir</button>
    </div>
  </div>

</template>