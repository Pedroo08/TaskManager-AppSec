import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import type { Task, Activity } from '@/types/Task'

export const useTaskStore = defineStore('task', () => {
  const activities = ref<Activity[]>([]) // Novo estado para logs
  // Estado: A lista de tarefas agora mora aqui
  const tasks = ref<Task[]>([
    {
      id: 1,
      title: 'Configurar Ambiente',
      description: 'Instalar Vue e Tailwind.',
      status: 'completed',
    },
    { id: 2, title: 'Implementar Auth', description: 'Criar login seguro.', status: 'pending' },
    { id: 3, title: 'Sanitização', description: 'Prevenir XSS.', status: 'pending' },
    {
      id: 4,
      title: 'Gráficos',
      description: 'Adicionar gráficos com base nos valores.',
      status: 'pending',
    },
  ])

  // Função interna para registrar logs
  const addLog = (type: Activity['type'], taskTitle: string) => {
    activities.value.unshift({
      // unshift adiciona no início do array
      id: Date.now(),
      type,
      taskTitle,
      timestamp: new Date(),
    })
  }

  // Getters: Cálculos automáticos baseados nas tarefas
  const totalTasks = computed(() => tasks.value.length)
  const completedTasks = computed(() => tasks.value.filter((t) => t.status === 'completed').length)
  const pendingTasks = computed(() => tasks.value.filter((t) => t.status === 'pending').length)

  // Ação para adicionar tarefa
  const addTask = (title: string, description: string) => {
    const newTask: Task = {
      id: Date.now(), //// Gerador de ID simples para treino
      title,
      description,
      status: 'pending' as const,
    }

    tasks.value.push(newTask)
    addLog('Criou', title) // Registra a ação
  }

  const deleteTask = (id: number) => {
    const task = tasks.value.find((t) => t.id === id)
    if (task) {
      addLog('Excluiu', task.title)
      tasks.value = tasks.value.filter((t) => t.id !== id)
    }
  }

  const editTask = (id: number, title: string, description: string, status: Task['status']) => {
    const task = tasks.value.find((task) => task.id === id)

    if (task) {
      task.title = title
      task.description = description
      task.status = status
      addLog('Editou', title)
    }
  }

  const completeTask = (id: number) => {
    const task = tasks.value.find((task) => task.id === id)

    if (task) {
      task.status = task.status === 'pending' ? 'completed' : 'pending'
      addLog('Concluiu', task.title)
    }
  }

  // Para o Dashboard
  const chartData = computed(() => ({
    labels: ['Concluídas', 'Pendentes'],
    datasets: [
      {
        backgroundColor: ['#22c55e', '#eab308'], // Verde-500 e Amarelo-500 do Tailwind
        data: [completedTasks.value, pendingTasks.value], // Usa os getters existentes
      },
    ],
  }))

  const exportActivities = (format: 'json' | 'csv') => {
    if (activities.value.length === 0) {
      alert('Não há atividades para exportar.')
      return
    }

    let content = ''
    let contentType = ''
    let fileName = `audit_report_${Date.now()}`

    if (format === 'json') {
      // Exportação JSON: Simples e direta
      content = JSON.stringify(activities.value, null, 2)
      contentType = 'application/json'
      fileName += '.json'
    } else {
      // Exportação CSV: Precisamos mapear os cabeçalhos e as linhas
      const headers = ['ID', 'Tipo', 'Tarefa', 'Data/Hora']
      const rows = activities.value.map((act) => [
        act.id,
        act.type,
        act.taskTitle,
        act.timestamp.toLocaleString('pt-BR'),
      ])

      // Unimos os cabeçalhos e as linhas com vírgulas e quebras de linha
      content = [headers, ...rows].map((e) => e.join(',')).join('\n')
      contentType = 'text/csv'
      fileName += '.csv'
    }

    // Lógica de download do navegador
    const blob = new Blob([content], { type: contentType })
    const url = URL.createObjectURL(blob)
    const link = document.createElement('a')

    link.href = url
    link.download = fileName
    link.click()

    // Limpeza de memória
    URL.revokeObjectURL(url)
  }

  return {
    tasks,
    totalTasks,
    completedTasks,
    pendingTasks,
    addTask,
    deleteTask,
    editTask,
    completeTask,
    chartData,
    activities,
    exportActivities,
  }
})
