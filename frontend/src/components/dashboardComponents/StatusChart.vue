<script setup lang="ts">
    import { Doughnut } from 'vue-chartjs';
    import { Chart as ChartJS, Title,Tooltip, Legend,ArcElement,CategoryScale } from 'chart.js';
    import { useTaskStore } from '@/stores/taskStore';

    // Registramos os componentes necessários do Chart.js
    ChartJS.register(Title,Tooltip,Legend,ArcElement,CategoryScale)

    const taskStore =useTaskStore()

    //Configurações visuais do gráfico
    const chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'bottom' as const,
                labels: { color: '#94a3b8', font: { size: 14 } }
            }
        }
    }
</script>

<template>
    <div class="bg-slate-800 p-6 rounded-2xl border border-slate-700 shadow-xl h-[350px]">
    <h3 class="text-lg font-bold text-white mb-4">Distribuição de Tarefas</h3>
    <div class="h-64">
      <Doughnut :data="taskStore.chartData" :options="chartOptions" />
    </div>
  </div>

</template>