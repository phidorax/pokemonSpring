<template>
  <div class="block w-full box-border text-center bg-gradient-to-r from-blue-600 to-blue-500 text-white text-6xl font-bold py-16 [text-shadow:0px_0px_30px_var(--tw-shadow-color)] shadow-[rgba(255,255,255,0.25)]">
    Pokédex Spring
  </div>
  
  <div class="py-16 px-[5%]">
    <p v-if="account.username">
      Bienvenue <strong>{{ account.username }}</strong> ! Vous pouvez créer ou modifier des Pokémon.
      <button class="block btn my-2" @click="pokemonModalShown = !pokemonModalShown">Créer un Pokémon</button>
    </p>
    <p v-else>
      Vous n'êtes pas connecté. Connectez-vous pour pouvoir créer ou modifier des Pokémon.
      <button class="block btn my-2" @click="logInModalShown = !logInModalShown">Se connecter</button>
    </p>

    <table class="w-full table-auto text-left overflow-hidden rounded-md shadow mt-6">
      <thead class="bg-gradient-to-r from-blue-600 to-blue-500 text-white">
        <tr class="text-lg">
          <th class="px-8 py-4 w-40"></th>
          <th class="px-8 py-4">ID</th>
          <th class="px-8 py-4">Nom</th>
          <th class="px-8 py-4">Type</th>
          <th class="px-8 py-4 w-48">Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="pokemon in pokemons" class="bg-white even:bg-slate-50">
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
          <td class="px-8 py-4 text-center">
            <button class="btn-icon bg-blue-500 mr-1">
              <Eye size="18" />
            </button>
            <button class="btn-icon bg-orange-500 mr-1">
              <Pencil size="18" />
            </button>
            <button class="btn-icon bg-red-500">
              <Delete size="18" />
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <ModalLogIn :shown="logInModalShown" :hide-modal="() => logInModalShown = false" :log-in="logIn"></ModalLogIn>
  <ModalPokemon :shown="pokemonModalShown" :hide-modal="() => pokemonModalShown = false"></ModalPokemon>
</template>

<script>
import ModalPokemon from './components/ModalPokemon.vue';
import ModalLogIn from './components/ModalLogIn.vue';
import Close from 'vue-material-design-icons/Close.vue';
import Eye from 'vue-material-design-icons/Eye.vue';
import Pencil from 'vue-material-design-icons/Pencil.vue';
import Delete from 'vue-material-design-icons/Delete.vue';

export default {
  components: {
    ModalPokemon,
    ModalLogIn,
    Close,
    Eye,
    Pencil,
    Delete
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
      account: {
        username: null,
        password: null
      },
      pokemonModalShown: false,
      logInModalShown: false,
    };
  },
  methods: {
    logIn(username, password) {
      this.account.username = username;
      this.account.password = password;
      this.logInModalShown = false;
    }
  }
}
</script>