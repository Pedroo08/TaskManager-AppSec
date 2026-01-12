<script setup lang="ts">
  import {RouterView} from 'vue-router'
</script>

<template>

  <div class="min-h-screen bg-slate-900 text-slate-100">
      
      <nav class="p-6 border-b border-slate-800">
        <h1 class="text-xl font-black text-blue-500 uppercase tracking-tighter">
          TaskManager <span class="text-slate-500 text-sm font-mono">AppSec</span>
        </h1>
      </nav>

      <RouterView />
      
    </div>


</template>

<style scoped></style>
