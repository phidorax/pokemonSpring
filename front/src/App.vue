<template>
  <ModalLogIn :shown="logInModalShown" :hide-modal="() => logInModalShown = false"></ModalLogIn>
  <ModalPokemon :shown="pokemonModalShown" :hide-modal="() => pokemonModalShown = false"></ModalPokemon>
  
  <button class="btn mr-2" @click="logInModalShown = !logInModalShown">Log In</button>
  <button class="btn" @click="pokemonModalShown = !pokemonModalShown">Create a Pokémon</button>
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
      pokemonModalShown: false,
      logInModalShown: false,
    };
  }
}
</script>