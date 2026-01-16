<script setup  lang="ts">
    import CountCard from '@/components/CountCard.vue';
    import StatusChart from '@/components/dashboardComponents/StatusChart.vue';
    import ActivityLog from '@/components/dashboardComponents/ActivityLog.vue';
    import { useTaskStore } from '@/stores/taskStore';
  
  const taskStore = useTaskStore();
</script>

<template>
    <main class="p-6 max-w-7xl mx-auto">
        <header class="mb-8">
            <h1 class="text-3xl font-bold text-white">Dashboard de Produtividade</h1>
            <p class="text-slate-400">Visão geral do sistema e métricas de execução.</p>
        </header>


        <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
            <CountCard titulo="Total de Tarefas" :calculo="taskStore.totalTasks"/>
            <CountCard titulo="Tarefas concluídas" :calculo="taskStore.completedTasks"/>
            <CountCard titulo="Tarefas pendentes" :calculo="taskStore.pendingTasks"/>
        </div>

        <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
            <StatusChart/>
            <ActivityLog />
        </div>
    </main>
    

</template>