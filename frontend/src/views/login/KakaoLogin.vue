<template>
  <video muted autoplay loop playbackRate="1.0">
    <source src="@/assets/intro_video.mp4" type="video/mp4" />
  </video>
</template>

<script setup>
import { useRoute } from "vue-router";
import { useAccountStore } from "@/store/account";

const route = useRoute();
const code = route.query.code;
const accountStore = useAccountStore();

accountStore.kakaoLogin(code);
</script>

<style></style>
