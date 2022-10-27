<template>
  <video muted autoplay loop playbackRate="1.0">
    <source src="../assets/intro_video.mp4" type="video/mp4">
  </video>
  <h1>인트로 드가자</h1>
</template>

<script setup>

</script>

<style>

body {
  margin: 0;
}

video {
  position: fixed;
  right: 0;
  bottom: 0;
  min-width: 100%;
  min-height: 100%;
  width: auto;
  height: auto;
  z-index: -100;
  background-size: cover;
}

h1{
    color:#ffffff;
}
</style>