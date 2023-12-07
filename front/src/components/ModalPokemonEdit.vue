<template>
  <div class="fixed top-0 left-0 w-full h-full bg-black opacity-70 z-10" @click="hideModal"></div>
  <div class="fixed z-20 rounded-lg overflow-hidden top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 shadow-xl">
    <div class="px-6 py-3 bg-gradient-to-br from-blue-500 to-blue-600 text-white text-lg">
      Créer un Pokémon
      <close class="cursor-pointer float-right" :size="26" @click="hideModal" />
    </div>
    <div class="p-6 bg-slate-100">
      <!-- Form content -->
      <input class="block input-text mb-4" type="text" placeholder="Name" v-model="pokemon.name" ref="name">

      <template v-for="n in [0, 1]">
        <select class="block select mb-4" v-model="pokemon.types[n]">
          <option value="" selected>--- Sélectionner un type ---</option>
          <option v-for="t in types" :value="t">{{ t }}</option>
        </select>
      </template>

      <h4 class="font-bold text-lg mb-4">Statistiques</h4>

      <div v-for="stat in baseStats" class="mb-2">
        <div class="inline-block w-36">{{ stat.replace('_', ' ').toUpperCase() }}</div>
        <input class="input-text small inline-block mx-2 w-20" type="number" min="1" step="1" max="255" v-model="pokemon[stat]">
        <input class="inline-block w-40" type="range" min="1" step="1" max="255" v-model="pokemon[stat]">
      </div>

      <button class="block btn mt-4" @click="createPokemon">Créer le Pokémon</button>
    </div>
  </div>
</template>

<script>
import Close from 'vue-material-design-icons/Close.vue';

export default {
  components: {
    Close
  },
  data() {
    return {
      types: ['NORMAL', 'FIRE', 'WATER', 'PLANT'],
      baseStats: ['hp', 'attack', 'defense', 'specialAttack', 'specialDefense', 'speed']
    }
  },
  props: ['pokemon', 'hideModal'],
  methods: {
    async createPokemon() {
      const data = JSON.stringify(this.pokemon);
      this.resetPokemon();

      await fetch(`http://localhost:8080/pokemons/new`, { method: 'POST', body: data });
      alert("Pokemon créé !");
      
      this.hideModal();
    },
    resetPokemon() {
      this.pokemon = {
        name: '',
        types: ['', ''],
        hp: 1,
        attack: 1,
        defense: 1,
        specialAttack: 1,
        specialDefense: 1,
        speed: 1
      };
    },
    checkShortcut(e) {
      if (e.key === "Escape") {
        this.hideModal();
      }
    }
  },
  mounted() {
    this.$refs.name.focus();
    
    window.addEventListener('keydown', this.checkShortcut);
  },
  unmounted() {
    window.removeEventListener('keydown', this.checkShortcut);
  }
}
</script>