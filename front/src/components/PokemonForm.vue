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

  <button class="block btn mt-4" @click="console.log(pokemon)">Create a Pokémon</button>
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
        special_attack: 1,
        special_defense: 1,
        speed: 1
      },
      types: ['NORMAL', 'FIRE', 'WATER', 'PLANT'],
      baseStats: ['hp', 'attack', 'defense', 'special_attack', 'special_defense', 'speed']
    }
  },
  mounted() {
    
  }
}
</script>