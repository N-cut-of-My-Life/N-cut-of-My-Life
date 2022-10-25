<template>
  <video muted autoplay loop playbackRate="1.0">
      <source src="../assets/space.mp4" type="video/mp4">
  </video>

  <div class="sentence" >
    <p>실험텍스트1</p>
    <p>실험텍스트2</p>
    <p>실험텍스트3</p>
  </div>
</template>

<script setup>



</script>

<style scoped>
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
.sentence {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  height: 25%;
  margin: auto;
  text-align: center;
  color:aliceblue;
  font-size: 3rem;
}

.sentence p {
  margin: 0;
}
</style>