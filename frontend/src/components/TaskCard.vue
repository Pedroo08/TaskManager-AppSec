<script setup  lang="ts">
// Definimos a interface para garantir o tipo dos dados (Bom para AppSec/Qualidade)
interface Task{
    id:number;
    title: string;
    description:string;
    status: 'pending'| 'completed';

}

// Definindo as props que o componente vai receber
 defineProps<{task:Task}>();

</script>

<template>
    <div class="bg-slate-800 p-4 rounded-xl border border-slate-700 hover:border-blue-500 transition-colors shadow-sm">
    <div class="flex justify-between items-start mb-2">
      <h3 class="text-lg font-semibold text-slate-100">{{ task.title }}</h3>
      <span 
        :class="task.status === 'completed' ? 'bg-green-900 text-green-300' : 'bg-yellow-900 text-yellow-300'"
        class="text-xs px-2 py-1 rounded-full font-medium"
      >
        {{ task.status }}
      </span>
    </div>
    <p class="text-slate-400 text-sm leading-relaxed">
      {{ task.description }}
    </p>
    <div class="mt-4 flex gap-2">
      <button class="text-xs text-blue-400 hover:text-blue-300 font-medium">Editar</button>
      <button class="text-xs text-red-400 hover:text-red-300 font-medium">Excluir</button>
    </div>
  </div>

</template>