<template>
  <div class="fixed top-0 left-0 w-full h-full bg-black opacity-70 z-10" @click="hideModal"></div>
  <div class="fixed z-20 rounded-lg overflow-hidden top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 shadow-xl">
    <div class="px-6 py-3 bg-gradient-to-br from-blue-500 to-blue-600 text-white text-lg">
      Connexion
      <close class="cursor-pointer float-right" :size="26" @click="hideModal" />
    </div>
    <div class="p-6 bg-slate-100">
      <form action="#" method="POST" @submit.prevent="logIn(account.username, account.password)">
        <input class="block input-text mb-4" type="text" placeholder="Nom d'utilisateur" v-model="account.username">
        <input class="block input-text mb-4" type="password" placeholder="Mot de passe" v-model="account.password">
        
        <input type="submit" class="block btn mt-4 cursor-pointer" value="Se connecter">
      </form>
    </div>
  </div>
</template>

<script>
import Close from 'vue-material-design-icons/Close.vue';

export default {
  components: {
    Close
  },
  props: ['hideModal', 'logIn'],
  data() {
    return {
      account: {
        username: '',
        password: ''
      }
    }
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