<template>
  <PageLoader />
  <!-- <a class="mute" href="" @click="store.toggleMute">Mute/Unmute</a> -->
  <router-view />
</template>

<script setup>
import PageLoader from "@/load/PageLoader.vue";
// import { useMusicStore } from '@/store/index.js'
// const store = useMusicStore()


  // methods: {
  //   toggleMute() {
  //     var myAudio = document.getElementById('myaudios');
  //     myAudio.muted = !myAudio.muted;
  //   }
  // }

</script>

<style>
#app {
  font-family: 'NanumBarunGothic', sans-serif !important;
}

.v-application {
  font-family: 'NanumBarunGothic', sans-serif !important;
}

@font-face {
  font-family: 'NanumBarunGothic';
  src: url('@/fonts/NanumBarunGothic.ttf') format('truetype');
}

body {
  margin: 0;
}

.mute{
    position: absolute;
    left: 2%;
    bottom: 2%;
    margin: auto;
}
</style>
