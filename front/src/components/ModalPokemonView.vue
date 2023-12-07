<template>
  <div class="fixed top-0 left-0 w-full h-full bg-black opacity-70 z-10" @click="hideModal"></div>
  <div class="fixed z-20 rounded-lg overflow-hidden top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 shadow-xl" @keydown.esc="hideModal">
    <div class="px-6 py-3 bg-gradient-to-br from-blue-500 to-blue-600 text-white text-lg">
      Voir un Pokémon
      <close class="cursor-pointer float-right" :size="26" @click="hideModal" />
    </div>
    <div class="p-6 bg-slate-100">
      <!-- Form content -->
      {{ pokemon.name }}

      <span v-for="typ in pokemon.types">
        <img :src="`/img/types/${typ}.png`">
      </span>

      <h4 class="font-bold text-lg mb-4">Statistiques</h4>

      <div v-for="stat in baseStats" class="mb-2">
        <div class="inline-block w-36">{{ stat.replace('_', ' ').toUpperCase() }}</div>
        {{ pokemon[stat] }}
        <div class="block w-full h-7 rounded-full bg-gradient-to-r from-blue-500 to-gray-400" :style="{ '--tw-gradient-from-position': `${Math.round(parseInt(pokemon[stat])/255*100)}%`, '--tw-gradient-to-position': `${Math.round(parseInt(pokemon[stat])/255*100)}%` }"></div>
      </div>
    </div>
  </div>
</template>

<script>
import Close from 'vue-material-design-icons/Close.vue';

export default {
  components: {
    Close
  },
  props: ['pokemon', 'hideModal'],
  data() {
    return {
      baseStats: ['hp', 'attack', 'defense', 'specialAttack', 'specialDefense', 'speed']
    };
  },
  methods: {
    checkShortcut(e) {
      if (e.key === "Escape") {
        this.hideModal();
      }
    }
  },
  mounted() {
    window.addEventListener('keydown', this.checkShortcut);
  },
  unmounted() {
    window.removeEventListener('keydown', this.checkShortcut);
  }
}
</script>