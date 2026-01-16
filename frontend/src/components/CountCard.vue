<script setup lang="ts">
    
    defineProps<{
        titulo: string;
        calculo?: number;
    }>();
    

</script>

<template>
  <div class="bg-slate-800 p-6 rounded-2xl border border-slate-700 hover:border-blue-500 transition-all shadow-lg group">
    <div class="flex flex-col items-center gap-2">
      <h3 class="text-base font-medium text-slate-400 uppercase tracking-wider group-hover:text-slate-300 transition-colors"> {{ titulo }} </h3>

       <p class=" text-5xl font-black text-white tabular-nums tracking-tight">
          {{ calculo ?? 0  }}
      </p>
      <div class="w-12 h-1 bg-blue-600 rounded-full mt-2 group-hover:w-20 transition-all duration-300"></div>
    </div>
    
  </div>
</template> 

<style scoped>
/* Tabular-nums garante que o número não "dance" se o valor mudar */
.tabular-nums {
  font-variant-numeric: tabular-nums;
}
</style>