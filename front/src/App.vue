<template>
  <div class="block w-full box-border text-center bg-gradient-to-r from-blue-600 to-blue-500 text-white text-6xl font-bold py-16 [text-shadow:0px_0px_30px_var(--tw-shadow-color)] shadow-[rgba(255,255,255,0.25)]">
    Pokédex Spring
  </div>
  
  <div class="py-16 px-[5%]">
    <p v-if="account.username">
      Bienvenue <strong>{{ account.username }}</strong> ! Vous pouvez créer ou modifier des Pokémon.
      <div class="my-2">
        <button class="block btn my-2" @click="newPokemon">Créer un Pokémon</button>
        <button class="btn" @click="logOut">Se déconnecter</button>
      </div>
    </p>
    <p v-else>
      Vous n'êtes pas connecté. Connectez-vous pour pouvoir créer ou modifier des Pokémon.
      <div class="my-2">
        <button class="btn mr-2" @click="signInModalShown = !signInModalShown">S'inscrire</button>
        <button class="btn" @click="logInModalShown = !logInModalShown">Se connecter</button>
      </div>
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

  <ModalSignIn v-if="signInModalShown" :hide-modal="() => signInModalShown = false" :sign-in="signIn"></ModalSignIn>
  <ModalLogIn v-if="logInModalShown" :hide-modal="() => logInModalShown = false" :log-in="logIn"></ModalLogIn>
  <ModalPokemonView v-if="pokemonViewModalShown" :pokemon="pokemon" :hide-modal="() => pokemonViewModalShown = false"></ModalPokemonView>
  <ModalPokemonEdit v-if="pokemonEditModalShown" :pokemon="pokemon" :account="account" :pokemon-mode="pokemonMode" :hide-modal="editFinished"></ModalPokemonEdit>
</template>

<script>
import ModalPokemonView from './components/ModalPokemonView.vue';
import ModalPokemonEdit from './components/ModalPokemonEdit.vue';
import ModalLogIn from './components/ModalLogIn.vue';
import ModalSignIn from './components/ModalSignIn.vue';
import Close from 'vue-material-design-icons/Close.vue';
import Eye from 'vue-material-design-icons/Eye.vue';
import Pencil from 'vue-material-design-icons/Pencil.vue';
import Delete from 'vue-material-design-icons/Delete.vue';

export default {
  components: {
    ModalPokemonView,
    ModalPokemonEdit,
    ModalLogIn,
    ModalSignIn,
    Close,
    Eye,
    Pencil,
    Delete
  },
  props: ['pokemon'],
  data() {
    return {
      pokemons: [],
      pokemon: {},
      pokemonMode: 'new',
      account: {
        username: null,
        password: null
      },
      pokemonViewModalShown: false,
      pokemonEditModalShown: false,
      logInModalShown: false,
      signInModalShown: false
    };
  },
  methods: {
    async logIn(username, password) {
      const res = await fetch(`http://localhost:8080/login`, {
        method: 'POST',
        headers: {
          'Accept': '*/*',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({'username': username, 'password': password})
      });
      if (res.status == 200 || res.status == 201) {
        this.account.username = username;
        this.account.password = password;
        
        this.logInModalShown = false;
      } else {
        alert("Erreur : Le nom d'utilisateur ou le mot de passe est incorrect. Veuillez réessayez.");
      }
    },
    async logOut() {
      this.account = {
        username: null,
        password: null
      };
    },
    async signIn(username, password) {
      const res = await fetch(`http://localhost:8080/users/new`, {
        method: 'POST',
        headers: {
          'Accept': '*/*',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({'username': username, 'password': password})
      });

      if (res.status == 200 || res.status == 201) {
        this.account.username = username;
        this.account.password = password;
        
        this.signInModalShown = false;
      } else {
        alert("Erreur : Ce nom d'utilisateur est déjà utilisé. Veuillez réessayez.");
      }
    },
    async viewPokemons() {
      const res = await fetch(`http://localhost:8080/pokemons`);
      const data = await res.json();
      console.log(data);

      this.pokemons = data;
    },
    async viewPokemon(pokemon) {
      const res = await fetch(`http://localhost:8080/pokemons/${pokemon.id}`);
      const data = await res.json();
      
      this.pokemon = data;
      this.pokemonViewModalShown = true;
    },
    async newPokemon() {
      this.pokemon = {
        id: '',
        name: '',
        types: ['', ''],
        hp: 1,
        attack: 1,
        defense: 1,
        specialAttack: 1,
        specialDefense: 1,
        speed: 1
      };

      this.pokemonMode = 'new';
      this.pokemonEditModalShown = true;
    },
    async editPokemon(pokemon) {
      const res = await fetch(`http://localhost:8080/pokemons/${pokemon.id}`);
      const data = await res.json();
      
      this.pokemon = data;
      this.pokemonMode = 'edit';
      this.pokemonEditModalShown = true;
    },
    editFinished() {
      this.pokemonEditModalShown = false;
      this.viewPokemons();
    },
    async deletePokemon(pokemon) {
      if (confirm(`Êtes-vous sûr de supprimer le Pokémon ${pokemon.name} ? Cette action est irréversible !`)) {
        const res = await fetch(`http://localhost:8080/pokemons/${pokemon.id}/delete`, {
          method: 'DELETE',
          headers: {
            'Accept': '*/*',
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + btoa(this.account.username + ":" + this.account.password)
          }
        });

        if (res.status == 200 || res.status == 201) {
          alert(`${pokemon.name} a été supprimé avec succès.`);
        } else {
          alert(`Erreur : ${pokemon.name} n'a pas pu être supprimé. Veuillez réessayez.`);
        }

        await this.viewPokemons();
      }
    }
  },
  mounted() {
    this.viewPokemons();
  }
}
</script>