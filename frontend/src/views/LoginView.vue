<script setup lang="ts">
    import { useRouter } from 'vue-router';
     const router = useRouter();
     

     const handleLogin = () => {
        // Nota de AppSec: No futuro, aqui faremos a chamada para a API 
        // e verificaremos as credenciais antes de navegar.
        console.log("Login realizado com sucesso!");
  
        // Navega para a rota com o nome 'tasks' definida no index.ts
        router.push('/');

     }

</script>

<template >
    <main class="max-w-4xl mx-auto p-6">
        <header class="mb-8 flex justify-between items-center">
            <div>
                <h1 class="text-3xl font-bold text-white">Login</h1>
            </div>
        </header>

        <div class="max-w-2xl mx-auto p-6 flex flex-col gap-3 ">
            <label>Email:</label>
            <input type="text"  class="  border-2  rounded-md" >
            <Label>Senha:</Label>
            <input type="password"  class="  border-2  rounded-md" >
             <button @click="handleLogin" class="bg-blue-600 hover:bg-blue-500 text-white px-4 py-2 rounded-lg font-semibold trasition-all">
              Entrar
            </button>
        </div>
    </main>
</template>