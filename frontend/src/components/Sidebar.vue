<script setup lang="ts">
import { RouterLink } from 'vue-router';

    defineProps<{ isOpen:boolean}>();

</script>

<template>
  <aside 
    :class="isOpen ? 'w-64' : 'w-0 overflow-hidden opacity-0'"
    class="bg-slate-800 border-r border-slate-700 flex flex-col transition-all duration-300 ease-in-out h-full"
  >
    <nav class="flex-1 px-4 py-8 flex flex-col gap-2">
      <RouterLink 
        to="/dashboard" 
        class="flex items-center gap-3 p-3 rounded-lg hover:bg-slate-700 transition-colors text-slate-300 hover:text-white"
        active-class="bg-blue-600! text-white!"
      >
        <span class="text-xl">📊</span> 
        <span class="font-medium">Dashboard</span>
      </RouterLink>

      <RouterLink 
        to="/" 
        class="flex items-center gap-3 p-3 rounded-lg hover:bg-slate-700 transition-colors text-slate-300 hover:text-white"
        active-class="bg-blue-600! text-white!"
      >
        <span class="text-xl">📝</span> 
        <span class="font-medium">Tarefas</span>
      </RouterLink>
    </nav>

    <div class="p-4 border-t border-slate-700">
      <button class="w-full flex items-center gap-3 p-3 rounded-lg text-red-400 hover:bg-red-900/20 transition-colors">
        <span>🚪</span> Sair
      </button>
    </div>
  </aside>
</template>