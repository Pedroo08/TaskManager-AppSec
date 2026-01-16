export interface Task {
  id: number
  title: string
  description: string
  status: 'pending' | 'completed' // Tipagem literal para evitar erros de digitação
}

export interface Activity {
  id: number
  type: 'Criou' | 'Excluiu' | 'Editou' | 'Concluiu'
  taskTitle: string
  timestamp: Date
}
