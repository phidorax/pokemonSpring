<template>
  <div class="fixed top-0 left-0 w-full h-full bg-black opacity-70 z-10" @click="hideModal"></div>
  <div class="fixed min-w-[300px] w-9/12 z-20 rounded-lg overflow-hidden top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 shadow-xl" @keydown.esc="hideModal">
    <div class="px-6 py-3 bg-gradient-to-br from-blue-500 to-blue-600 text-white text-lg">
      Voir le Pokémon {{ pokemon.name }}
      <close class="cursor-pointer float-right" :size="26" @click="hideModal" />
    </div>
    <div class="p-6 bg-slate-100">
      <!-- Form content -->
      {{ pokemon.name }} (#{{ pokemon.id }})

      <span v-for="typ in pokemon.types">
        <img :src="`/img/types/${typ}.png`">
      </span>

      <h4 class="font-bold text-lg my-2">Statistiques</h4>

      <div v-for="(stat, n) in baseStats" class="mb-2">
        <div class="flex px-1">
          <div class="flex-1">
            {{ stats[n] }}
          </div>
          {{ pokemon[stat] }}
        </div>
        <div class="block w-full h-7 rounded-full bg-gradient-to-r from-blue-500 to-gray-300" :style="{ '--tw-gradient-from-position': `${Math.round(parseInt(pokemon[stat])/255*100)}%`, '--tw-gradient-to-position': `${Math.round(parseInt(pokemon[stat])/255*100)}%` }"></div>
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
      stats: ['PV', 'Attaque', 'Défense', 'Attaque spéciale', 'Défense spéciale', 'Vitesse'],
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