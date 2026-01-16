<script setup lang="ts">
import { RouterView, useRoute } from 'vue-router'
import Sidebar from './components/Sidebar.vue';
import { computed, ref } from 'vue';

const route = useRoute();
const isSidebarOpen = ref(true); // Estado para abrir/fechar

const showSidebar = computed(() => route.name !== 'login');

const toggleSidebar = () => {
  isSidebarOpen.value = !isSidebarOpen.value;
};
</script>

<template>
  <div class="flex h-screen bg-slate-900 text-slate-100 overflow-hidden">
    
    <Sidebar v-if="showSidebar" :isOpen="isSidebarOpen" />

    <div class="flex-1 flex flex-col min-w-0">
      
      <header v-if="showSidebar" class="p-6 border-b border-slate-800 flex items-center justify-between bg-slate-900/50 backdrop-blur-md">
        <div class="flex items-center gap-4">
          <button @click="toggleSidebar" class="p-2 hover:bg-slate-800 rounded-lg transition-colors">
            <span class="text-xl">{{ isSidebarOpen ? '⬅️' : '➡️' }}</span>
          </button>
          
          <h1 class="text-xl font-black text-blue-500 uppercase tracking-tighter">
            TaskManager <span class="text-slate-500 text-sm font-mono">AppSec</span>
          </h1>
        </div>
        
        <div class="text-sm text-slate-400 font-medium">
          Olá, Usuário
        </div>
      </header>

      <div class="flex-1 overflow-y-auto">
        <RouterView />
      </div>
      
    </div>
  </div>
</template>