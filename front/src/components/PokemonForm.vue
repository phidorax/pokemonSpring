<template>
  <input class="block input-text mb-4" type="text" placeholder="Name" v-model="pokemon.name">
  <template v-for="n in [0, 1]">
    <select class="block select mb-4" v-model="pokemon.types[n]">
      <option value="" selected>--- Select a type ---</option>
      <option v-for="t in types" :value="t">{{ t }}</option>
    </select>
  </template>

  <h4 class="font-bold text-lg mb-4">Base Stats</h4>

  <div v-for="stat in baseStats" class="mb-2">
    <div class="inline-block w-36">{{ stat.replace('_', ' ').toUpperCase() }}</div>
    <input class="input-text small inline-block mx-2 w-20" type="number" min="1" step="1" max="255" v-model="pokemon[stat]">
    <input class="inline-block w-40" type="range" min="1" step="1" max="255" v-model="pokemon[stat]">
  </div>

  <button class="block btn mt-4" @click="createPokemon">Create a Pokémon</button>
</template>

<script>
export default {
  data() {
    return {
      pokemon: {
        name: '',
        types: ['', ''],
        hp: 1,
        attack: 1,
        defense: 1,
        specialAttack: 1,
        specialDefense: 1,
        speed: 1
      },
      types: ['NORMAL', 'FIRE', 'WATER', 'PLANT'],
      baseStats: ['hp', 'attack', 'defense', 'specialAttack', 'specialDefense', 'speed']
    }
  },
  methods: {
    async createPokemon() {
      const data = JSON.stringify(this.pokemon);
      this.resetPokemon();

      await fetch(`http://localhost:8080/pokemons/new`, { method: 'POST', body: data });
      alert("Pokemon créé !");
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
    }
  }
}
</script>