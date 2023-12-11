<template>
  <div class="fixed top-0 left-0 w-full h-full bg-black opacity-70 z-10" @click="hideModal"></div>
  <div class="fixed z-20 rounded-lg overflow-hidden top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 shadow-xl">
    <div class="px-6 py-3 bg-gradient-to-br from-blue-500 to-blue-600 text-white text-lg">
      {{ pokemonMode == 'new' ? 'Créer' : 'Modifier' }} un Pokémon
      <close class="cursor-pointer float-right" :size="26" @click="hideModal" />
    </div>
    <div class="p-6 bg-slate-100">
      <!-- Form content -->
      <input class="block input-text mb-4" type="number" placeholder="ID" v-model="pokemon.id" :disabled="pokemonMode == 'edit'">
      <input class="block input-text mb-4" type="text" placeholder="Name" v-model="pokemon.name" ref="name">

      <template v-for="n in [0, 1]">
        <select class="block select mb-4" v-model="pokemon.types[n]">
          <option value="" selected disabled>--- Sélectionner un type ---</option>
          <option v-for="t in types" :value="t">{{ t.toUpperCase() }}</option>
        </select>
      </template>

      <h4 class="font-bold text-lg mb-4">Statistiques</h4>

      <div v-for="stat in baseStats" class="mb-2">
        <div class="inline-block w-36">{{ stat.replace('_', ' ').toUpperCase() }}</div>
        <input class="input-text small inline-block mx-2 w-20" type="number" min="1" step="1" max="255" v-model="pokemon[stat]">
        <input class="inline-block w-40" type="range" min="1" step="1" max="255" v-model="pokemon[stat]">
      </div>

      <button class="block btn mt-4" @click="createPokemon">{{ pokemonMode == 'new' ? 'Créer' : 'Modifier' }} le Pokémon</button>
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
      types: [],
      baseStats: ['hp', 'attack', 'defense', 'specialAttack', 'specialDefense', 'speed']
    }
  },
  props: ['pokemon', 'account', 'pokemonMode', 'hideModal'],
  methods: {
    async createPokemon() {
      if (this.pokemonMode == 'new') {
        const res = await fetch(`http://localhost:8080/pokemons/new`, {
          method: 'POST',
          headers: {
            'Accept': '*/*',
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + btoa(this.account.username + ":" + this.account.password)
          },
          body: JSON.stringify(this.pokemon)
        });

        alert("Pokemon créé !");
      } else {
        const res = await fetch(`http://localhost:8080/pokemons/${this.pokemon.id}/edit`, {
          method: 'POST',
          headers: {
            'Accept': '*/*',
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + btoa(this.account.username + ":" + this.account.password)
          },
          body: JSON.stringify(this.pokemon)
        });
        
        alert("Pokemon modifié !");
      }

      this.hideModal();
    },
    checkShortcut(e) {
      if (e.key === "Escape") {
        this.hideModal();
      }
    }
  },
  async mounted() {
    this.$refs.name.focus();

    window.addEventListener('keydown', this.checkShortcut);

    const res = await fetch(`http://localhost:8080/types`);
    const data = await res.json();
    
    this.types = data;
  },
  unmounted() {
    window.removeEventListener('keydown', this.checkShortcut);
  }
}
</script>