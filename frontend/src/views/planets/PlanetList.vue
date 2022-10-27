<template>
  <div class="bg">
    <img class="planet" src="../../assets/planet/1.png" alt="">
    <img class="planet" src="../../assets/planet/2.png" alt="">
    <img class="planet" src="../../assets/planet/3.png" alt="">
    <img class="planet" src="../../assets/planet/4.png" alt="">
    <img class="planet" src="../../assets/planet/5.png" alt="">
    <img class="planet" src="../../assets/planet/6.png" alt="">
    <img class="planet" src="../../assets/planet/7.png" alt="">
    <img class="planet" src="../../assets/planet/8.png" alt="">
    <img class="stars" src="../../assets/planet/stars.png" alt="">
    <img class="stars_line blink_star" src="../../assets/planet/stars-line.png" alt="">
    <img class="teapot" src="../../assets/planet/teapot.png" alt="">
    <h1>행성 목록</h1>      
  </div>
</template>

<script setup>

</script>

<style scoped>
h1{
    text-align: left;
    padding-top:2%;
    padding-left:15%;
    color: white;
}
.blink_star {
  animation: blinker 1s linear infinite;
}

@keyframes blinker {
  50% {
    opacity: 0;
  }
}

.bg{
  background-image: url("../../assets/planet/bg.png");
  height: 100vh;
  width: 100vw;
  display:block;
  z-index: -100;
  position: absolute;
  overflow: hidden;
}
.planet{
  display:block;
  max-width: 15%;
  height: auto;
  position: absolute;
  border-radius: 50%;
  left: 50%;
  top: 50%;
}
.stars{
  display:block;
  max-width:20%;
  height: auto;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-top: -104.72px;
  margin-left : -86.14px;
  filter: grayscale(100%); 
  /* -webkit-filter: grayscale(100%); */
}
.stars_line{
  display:block;
  max-width:20%;
  height: auto;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-top: -94.72px;
  margin-left : -90.34px;
}
.teapot{
  display:none;
  max-width:16%;
  height: auto;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-top: -41px;
  margin-left : -20.34px;
  }


.stars_line:hover + .stars{
    filter:none;
}
.stars:hover{
    filter:none;
}
.stars_line:hover + .teapot{
    display: block;
} 

.planet:nth-child(1) {
  transform: rotate(0deg) translate(340%);
  margin-left: -52.355px;
  margin-top: -49.7px;
  filter: grayscale(100%); 
  /* -webkit-filter: grayscale(100%); */
}
.planet:nth-child(2) {
  transform: rotate(30deg) translate(220%) rotate(-30deg);
  margin-left: -52.35px;
  margin-top: -55.62px;
  filter: grayscale(100%); 
  /* -webkit-filter: grayscale(100%); */
}
.planet:nth-child(3) {
  transform: rotate(90deg) translate(100%) rotate(-90deg);
  margin-left: -52.35px;
  margin-top: -52.35px;
  filter: grayscale(100%); 
  /* -webkit-filter: grayscale(100%); */
}
.planet:nth-child(4) {
  transform: rotate(140deg) translate(150%) rotate(-140deg);
  margin-left: -78.5px;
  margin-top: -78.5px;
  filter: grayscale(100%); 
  /* -webkit-filter: grayscale(100%); */
}
.planet:nth-child(5) {
  transform: rotate(180deg) translate(340%) rotate(-180deg);
  margin-left: -52.35px;
  margin-top: -49.7px;
  filter: grayscale(100%); 
  /* -webkit-filter: grayscale(100%); */
}
.planet:nth-child(6) {
  transform: rotate(215deg) translate(225%) rotate(-215deg);
  margin-left: -52.35px;
  margin-top: -52.35px;
  filter: grayscale(100%); 
  /* -webkit-filter: grayscale(100%); */
}
.planet:nth-child(7) {
  transform: rotate(270deg) translate(175%) rotate(-270deg);
  margin-left: -52.35px;
  margin-top: -48.88px;
  filter: grayscale(100%); 
  /* -webkit-filter: grayscale(100%); */
}
.planet:nth-child(8) {
  transform: rotate(315deg) translate(175%) rotate(-315deg);
  margin-left: -52.35px;
  margin-top: -46.3px;
  filter: grayscale(100%); 
  /* -webkit-filter: grayscale(100%); */
}

.planet:hover {
    -webkit-filter: none;
    filter: none;
}

</style>