<template>
  <div v-show="shown" class="fixed top-0 left-0 w-full h-full bg-black opacity-70 z-10" @click="hideModal"></div>
  <div v-show="shown" class="fixed z-20 rounded-lg overflow-hidden top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 shadow-xl">
    <div class="px-6 py-3 bg-gradient-to-br from-blue-500 to-blue-600 text-white text-lg">
      Log In
      <close class="cursor-pointer float-right" :size="26" @click="hideModal" />
    </div>
    <div class="p-6 bg-slate-100">
      <input class="block input-text mb-4" type="text" placeholder="Username" v-model="account.username">
      <input class="block input-text mb-4" type="password" placeholder="Password" v-model="account.password">
      
      <button class="block btn mt-4" @click="console.log(account)">Log In</button>
    </div>
  </div>
</template>

<script>
import Close from 'vue-material-design-icons/Close.vue';

export default {
  components: {
    Close
  },
  props: ['shown', 'hideModal'],
  data() {
    return {
      account: {
        username: '',
        password: ''
      }
    }
  }
}
</script>