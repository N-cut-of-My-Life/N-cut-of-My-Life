<template>
  <PageLoader />
  <div
    class="mute"
    @click="musicStore.toggleMute()"
    v-if="route.path !== '/' && route.path !== '/traveldiary'"
  >
    <svg
      v-if="musicStore.isMute === false"
      xmlns="http://www.w3.org/2000/svg"
      width="30"
      height="30"
      fill="currentColor"
      class="bi bi-volume-up-fill"
      viewBox="0 0 16 16"
    >
      <path
        d="M11.536 14.01A8.473 8.473 0 0 0 14.026 8a8.473 8.473 0 0 0-2.49-6.01l-.708.707A7.476 7.476 0 0 1 13.025 8c0 2.071-.84 3.946-2.197 5.303l.708.707z"
      />
      <path
        d="M10.121 12.596A6.48 6.48 0 0 0 12.025 8a6.48 6.48 0 0 0-1.904-4.596l-.707.707A5.483 5.483 0 0 1 11.025 8a5.483 5.483 0 0 1-1.61 3.89l.706.706z"
      />
      <path
        d="M8.707 11.182A4.486 4.486 0 0 0 10.025 8a4.486 4.486 0 0 0-1.318-3.182L8 5.525A3.489 3.489 0 0 1 9.025 8 3.49 3.49 0 0 1 8 10.475l.707.707zM6.717 3.55A.5.5 0 0 1 7 4v8a.5.5 0 0 1-.812.39L3.825 10.5H1.5A.5.5 0 0 1 1 10V6a.5.5 0 0 1 .5-.5h2.325l2.363-1.89a.5.5 0 0 1 .529-.06z"
      />
    </svg>
    <svg
      v-else
      xmlns="http://www.w3.org/2000/svg"
      width="30"
      height="30"
      fill="currentColor"
      class="bi bi-volume-mute-fill"
      viewBox="0 0 16 16"
    >
      <path
        d="M6.717 3.55A.5.5 0 0 1 7 4v8a.5.5 0 0 1-.812.39L3.825 10.5H1.5A.5.5 0 0 1 1 10V6a.5.5 0 0 1 .5-.5h2.325l2.363-1.89a.5.5 0 0 1 .529-.06zm7.137 2.096a.5.5 0 0 1 0 .708L12.207 8l1.647 1.646a.5.5 0 0 1-.708.708L11.5 8.707l-1.646 1.647a.5.5 0 0 1-.708-.708L10.793 8 9.146 6.354a.5.5 0 1 1 .708-.708L11.5 7.293l1.646-1.647a.5.5 0 0 1 .708 0z"
      />
    </svg>
    <span
      v-if="route.path == '/introfirstpage'"
      style="margin-left: 8px; color: #ffffffa4"
      >BGM과 함께라면 더욱 뜻깊은 여행을 하실 수 있어요!!</span
    >
  </div>
  <router-view />
</template>

<script setup>
import PageLoader from "@/load/PageLoader.vue";
import { useMusicStore } from "@/store/music";
import { watch } from "vue";
import { useRoute } from "vue-router";

const musicStore = useMusicStore();
const route = useRoute();
watch(
  () => musicStore.isMute,
  function () {
    musicStore.isSoundActive();
  }
);
</script>

<style>
#app {
  font-family: "NanumBarunGothic", sans-serif !important;
  -webkit-touch-callout: none; /* iOS Safari */
  -webkit-user-select: none; /* Safari */
  -moz-user-select: none; /* Old versions of Firefox */
  -ms-user-select: none; /* Internet Explorer/Edge */
  user-select: none;
}

.v-application {
  font-family: "NanumBarunGothic", sans-serif !important;
}

@font-face {
  font-family: "NanumBarunGothic";
  src: url("@/fonts/NanumBarunGothic.ttf") format("truetype");
}

body {
  margin: 0;
}

.mute {
  position: fixed;
  left: 1%;
  bottom: 2.5%;
  margin: auto;
  color: white;
  cursor: pointer;
  z-index: 1;
}
</style>
