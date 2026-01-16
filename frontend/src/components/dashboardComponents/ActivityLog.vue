<script setup lang="ts">
import { useTaskStore } from '@/stores/taskStore'

const taskStore = useTaskStore()

// Função para formatar a hora de forma simples
const formatTime = (date: Date) => {
  return date.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' })
}

// Cores baseadas no tipo de ação
const getActionColor = (type: string) => {
  switch (type) {
    case 'Criou': return 'text-blue-400'
    case 'Excluiu': return 'text-red-400'
    case 'Editou': return 'text-yellow-400'
    case 'Concluiu': return 'text-green-400'
    default: return 'text-slate-400'
  }
}
</script>

<template>
  <div class="bg-slate-800 p-6 rounded-2xl border border-slate-700 shadow-xl flex flex-col h-[350px]">
    <h3 class="text-lg font-bold text-white mb-4">Log de Atividades (Auditoria)</h3>
    
    <div class="flex-1 overflow-y-auto pr-2 space-y-4">
      <div v-if="taskStore.activities.length === 0" class="text-slate-500 text-center py-10 italic">
        Nenhuma atividade registrada ainda.
      </div>

      <div 
        v-for="log in taskStore.activities" 
        :key="log.id"
        class="flex items-center justify-between text-sm border-l-2 border-slate-700 pl-4 py-1"
      >
        <div>
          <span :class="getActionColor(log.type)" class="font-bold mr-2 uppercase text-xs tracking-wider">
            {{ log.type }}
          </span>
          <span class="text-slate-300">"{{ log.taskTitle }}"</span>
        </div>
        <span class="text-slate-500 font-mono text-xs">
          {{ formatTime(log.timestamp) }}
        </span>
      </div>
    </div>
    <div class="flex gap-2">
        <button 
          @click="taskStore.exportActivities('json')"
          class="text-[10px] bg-slate-700 hover:bg-slate-600 text-slate-300 px-2 py-1 rounded font-mono transition-colors"
          title="Exportar JSON"
        >
          JSON
        </button>
        <button 
          @click="taskStore.exportActivities('csv')"
          class="text-[10px] bg-slate-700 hover:bg-slate-600 text-slate-300 px-2 py-1 rounded font-mono transition-colors"
          title="Exportar CSV"
        >
          CSV
        </button>
      </div>
  </div>
</template>