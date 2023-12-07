<template>
  <div class="block w-full box-border text-center bg-gradient-to-r from-blue-600 to-blue-500 text-white text-6xl font-bold py-16 [text-shadow:0px_0px_30px_var(--tw-shadow-color)] shadow-[rgba(255,255,255,0.25)]">
    Pokédex Spring
  </div>
  
  <div class="py-16 px-[5%]">
    <p v-if="account.username">
      Bienvenue <strong>{{ account.username }}</strong> ! Vous pouvez créer ou modifier des Pokémon.
      <button class="block btn my-2" @click="pokemonEditModalShown = !pokemonEditModalShown">Créer un Pokémon</button>
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
            <button class="btn-icon bg-blue-500 mr-1" @click="viewPokemon(pokemon)">
              <Eye :size="18" />
            </button>
            <button v-show="account.username" class="btn-icon bg-orange-500 mr-1" @click="editPokemon(pokemon)">
              <Pencil :size="18" />
            </button>
            <button v-show="account.username" class="btn-icon bg-red-500" @click="deletePokemon(pokemon)">
              <Delete :size="18" />
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <ModalLogIn v-if="logInModalShown" :hide-modal="() => logInModalShown = false" :log-in="logIn"></ModalLogIn>
  <ModalPokemonView v-if="pokemonViewModalShown" :pokemon="pokemon" :hide-modal="() => pokemonViewModalShown = false"></ModalPokemonView>
  <ModalPokemonEdit v-if="pokemonEditModalShown" :pokemon="pokemon" :hide-modal="() => pokemonEditModalShown = false"></ModalPokemonEdit>
</template>

<script>
import ModalPokemonView from './components/ModalPokemonView.vue';
import ModalPokemonEdit from './components/ModalPokemonEdit.vue';
import ModalLogIn from './components/ModalLogIn.vue';
import Close from 'vue-material-design-icons/Close.vue';
import Eye from 'vue-material-design-icons/Eye.vue';
import Pencil from 'vue-material-design-icons/Pencil.vue';
import Delete from 'vue-material-design-icons/Delete.vue';

export default {
  components: {
    ModalPokemonView,
    ModalPokemonEdit,
    ModalLogIn,
    Close,
    Eye,
    Pencil,
    Delete
  },
  props: ['pokemon'],
  data() {
    return {
      pokemons: [],
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
      account: {
        username: null,
        password: null
      },
      pokemonViewModalShown: false,
      pokemonEditModalShown: false,
      logInModalShown: false,
    };
  },
  methods: {
    logIn(username, password) {
      this.account.username = username;
      this.account.password = password;
      this.logInModalShown = false;
    },
    async viewPokemon(pokemon) {
      this.pokemon = pokemon;
      this.pokemonViewModalShown = true;

      const res = await fetch(`http://localhost:8080/pokemons/${pokemon.id}`);
      const data = await res.json();
      
      this.pokemon = data;
    },
    editPokemon(pokemon) {
      console.log(pokemon);

      this.pokemon = pokemon;
      this.pokemonEditModalShown = true;
    },
    deletePokemon(pokemon) {
      if (confirm(`Êtes-vous sûr de supprimer le Pokémon ${pokemon.name} ? Cette action est irréversible !`)) {
        console.log(pokemon);
      }
    }
  },
  async mounted() {
    const res = await fetch(`http://localhost:8080/pokemons`);
    const data = await res.json();
    console.log(data);

    this.pokemons = data;
  }
}
</script>