<script setup lang="ts">
    import { ref } from 'vue';

    defineProps<{isOpen:boolean}>();
    const emit = defineEmits(['close', 'save'])

    const title = ref('')
    const description = ref('')

    const handleSave = () =>{
        if (!title.value.trim()) return alert('O titulo é obrigatório!')

        emit('save',{title: title.value, description: description.value})

        //limpa formulario após salvar
        title.value =''
        description.value =''
    }


</script>

<template>
  <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/70 backdrop-blur-sm">
    
    <div class="bg-slate-800 w-full max-w-md rounded-2xl border border-slate-700 shadow-2xl overflow-hidden">
      <header class="p-6 border-b border-slate-700 flex justify-between items-center">
        <h2 class="text-xl font-bold text-white">Nova Tarefa</h2>
        <button @click="emit('close')" class="text-slate-400 hover:text-white text-2xl">&times;</button>
      </header>

      <div class="p-6 flex flex-col gap-4">
        <div>
          <label class="block text-sm font-medium text-slate-400 mb-1">Título</label>
          <input 
            v-model="title"
            type="text" 
            placeholder="O que precisa ser feito?"
            class="w-full bg-slate-900 border border-slate-700 rounded-lg p-3 text-white focus:ring-2 focus:ring-blue-500 outline-none"
          >
        </div>

        <div>
          <label class="block text-sm font-medium text-slate-400 mb-1">Descrição</label>
          <textarea 
            v-model="description"
            rows="3"
            placeholder="Detalhes da tarefa..."
            class="w-full bg-slate-900 border border-slate-700 rounded-lg p-3 text-white focus:ring-2 focus:ring-blue-500 outline-none resize-none"
          ></textarea>
        </div>
      </div>

      <footer class="p-6 bg-slate-800/50 flex gap-3 justify-end">
        <button @click="emit('close')" class="px-4 py-2 text-slate-400 hover:text-white font-medium">
          Cancelar
        </button>
        <button @click="handleSave" class="bg-blue-600 hover:bg-blue-500 text-white px-6 py-2 rounded-lg font-bold transition-all">
          Criar Tarefa
        </button>
      </footer>
    </div>
  </div>
</template>