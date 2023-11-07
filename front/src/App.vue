<template>
  <div class="block w-full text-center bg-gradient-to-r from-blue-600 to-blue-500 text-white text-6xl font-bold py-16 [text-shadow:0px_0px_30px_var(--tw-shadow-color)] shadow-[rgba(255,255,255,0.25)]">
    Pokédex Spring
  </div>
  
  <div class="py-16 px-[5%]">
    <!-- <button class="btn mr-2" @click="logInModalShown = !logInModalShown">Log In</button> -->
    <button class="btn" @click="pokemonModalShown = !pokemonModalShown">New Pokémon</button>

    <table class="w-full table-auto text-left overflow-hidden rounded-md shadow mt-6">
      <thead class="bg-gradient-to-r from-blue-600 to-blue-500 text-white">
        <tr class="text-lg">
          <th class="px-8 py-4 w-40"></th>
          <th class="px-8 py-4">ID</th>
          <th class="px-8 py-4">Name</th>
          <th class="px-8 py-4">Type</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="pokemon in pokemons" class="bg-white even:bg-slate-50 hover:bg-slate-100 cursor-pointer">
          <td class="px-8 py-2">
            <img :src="`https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png`" class="m-auto">
          </td>
          <td class="px-8 py-4">
            #{{ pokemon.id }}
          </td>
          <td class="px-8 py-4">
            {{ pokemon.name }}
          </td>
          <td class="px-8 py-4">
            TYPE1 TYPE2
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <ModalLogIn :shown="logInModalShown" :hide-modal="() => logInModalShown = false"></ModalLogIn>
  <ModalPokemon :shown="pokemonModalShown" :hide-modal="() => pokemonModalShown = false"></ModalPokemon>
</template>

<script>
import ModalPokemon from './components/ModalPokemon.vue';
import ModalLogIn from './components/ModalLogIn.vue';
import Close from 'vue-material-design-icons/Close.vue';

export default {
  components: {
    ModalPokemon,
    ModalLogIn,
    Close
  },
  props: ['pokemon'],
  data() {
    return {
      pokemons: [
        {id: 132, name: 'Metamorph'},
        {id: 25, name: 'Pikachu'},
        {id: 384, name: 'Rayquaza'},
        {id: 722, name: 'Brindibou'},
      ],
      pokemonModalShown: false,
      logInModalShown: false,
    };
  }
}
</script>