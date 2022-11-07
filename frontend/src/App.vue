<template>
  <PageLoader />
  <router-view/>
</template>

<script>
import PageLoader from "@/load/PageLoader.vue";

export default {
  components: {
    PageLoader,
  },
};
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
</style>
