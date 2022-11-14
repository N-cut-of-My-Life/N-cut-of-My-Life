<template>
  <div class="login-btn">
    <router-link
      :to="{ name: 'introfirstpage' }"
      @click="refreshToken"
      class="nav-link"
      style="text-decoration: none; color: inherit"
    >
      &nbsp;카카오 로그인
    </router-link>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { useAccountStore } from "@/store/account";

const route = useRoute();
const code = route.query.code;
const accountStore = useAccountStore();

accountStore.kakaoLogin(code);
const refreshToken = accountStore.refreshToken;
</script>

<style></style>
