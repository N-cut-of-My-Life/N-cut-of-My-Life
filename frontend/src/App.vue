<template>
  <PageLoader />
  <div class="mute" @click="musicStore.toggleMute()">Mute/Unmute</div>
  <router-view />
</template>

<script setup>
import PageLoader from "@/load/PageLoader.vue";
import { useMusicStore } from '@/store/index'
import { watch } from 'vue'
const musicStore = useMusicStore()

 watch(() => musicStore.isMute, function() {
  musicStore.isSoundActive()
})
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
    color: white;
    cursor: pointer;
}
</style>
