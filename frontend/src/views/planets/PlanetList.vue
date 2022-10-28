<template>
  <div class="bg">
    <div class="planet"></div>
    <div class="planet"></div>
    <div class="planet"></div>
    <img class="planet" src="../../assets/planet/music.png" alt="" @click="gotoPage({name:'musicplanet'})"/>
    <div class="planet"></div>
    <div class="planet"></div>
    <div class="planet"></div>
    <img class="planet" src="../../assets/planet/blink.png" alt="">
    <img class="stars" src="../../assets/planet/stars.png" alt="">
    <img class="stars_line blink_star" src="../../assets/planet/stars-line.png" alt="">
    <img class="teapot" src="../../assets/planet/teapot.png" alt="">
    <h1>행성 목록</h1>      
  </div>
</template>

<script>
export default{
  methods:{
    gotoPage(link){
      this.$router.push(link)
    }
  }
}
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
  -webkit-filter: grayscale(100%);
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
  cursor:pointer;
  }


.stars_line:hover + .stars{
  filter:none;
  -webkit-filter: none;
}
.stars:hover {
  filter:none;
  -webkit-filter: none;
}
.stars_line:hover + .teapot{
  display: block;
}

.planet:nth-child(1) {
  width:256px;
  height:256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/dearme.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: -128px;
  margin-top: -128px;
  filter: grayscale(100%); 
  -webkit-filter: grayscale(100%);
  animation: rotation_1 20s infinite linear;
  cursor:pointer;
}

.planet:nth-child(2) {
  width:256px;
  height:256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/weep.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: -128px;
  margin-top: -128px;
  filter: grayscale(100%); 
  -webkit-filter: grayscale(100%);
  animation: rotation_2 20s infinite linear;
  cursor:pointer;
}
.planet:nth-child(3) {
  width:256px;
  height:256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/haho.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: -128px;
  margin-top: -128px;
  filter: grayscale(100%); 
  -webkit-filter: grayscale(100%);
  animation: rotation_3 20s infinite linear;
  cursor:pointer;
}
.planet:nth-child(4) {
  max-width: 15%;
  height: auto;
  margin-left: -78.5px;
  margin-top: -78.5px;
  filter: grayscale(100%); 
  -webkit-filter: grayscale(100%);
  animation: rotation_4 20s infinite linear;
  cursor:pointer;
}
.planet:nth-child(5) {
  width:256px;
  height:256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/didnt.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: -128px;
  margin-top: -128px;
  filter: grayscale(100%); 
  -webkit-filter: grayscale(100%);
  animation: rotation_5 20s infinite linear;
  cursor:pointer;
}
.planet:nth-child(6) {
  width:256px;
  height:256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/dearyou.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: -128px;
  margin-top: -128px;
  filter: grayscale(100%); 
  -webkit-filter: grayscale(100%);
  animation: rotation_6 20s infinite linear;
  cursor:pointer;
}
.planet:nth-child(7) {
  width:256px;
  height:256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/forget.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: -128px;
  margin-top: -128px;
  filter: grayscale(100%); 
  -webkit-filter: grayscale(100%);
  animation: rotation_7 20s infinite linear;
  cursor:pointer;
}
.planet:nth-child(8) {
  max-width: 15%;
  height: auto;
  margin-left: -52.35px;
  margin-top: -46.3px;
  filter: grayscale(100%); 
  -webkit-filter: grayscale(100%);
  animation: rotation_8 20s infinite linear;
  cursor:pointer;
}

.planet:nth-child(1):hover {
   -webkit-filter: none;
    filter: none;
    box-shadow: 0 0 20px yellow;
}
.planet:nth-child(2):hover {
   -webkit-filter: none;
    filter: none;
    box-shadow: 0 0 20px yellow;
}
.planet:nth-child(3):hover {
   -webkit-filter: none;
    filter: none;
    box-shadow: 0 0 20px yellow;
}
.planet:nth-child(4):hover {
   -webkit-filter: none;
    filter: none;
}
.planet:nth-child(5):hover {
   -webkit-filter: none;
    filter: none;
    box-shadow: 0 0 20px yellow;
}
.planet:nth-child(6):hover {
   -webkit-filter: none;
    filter: none;
    box-shadow: 0 0 20px yellow;
}
.planet:nth-child(7):hover {
   -webkit-filter: none;
    filter: none;
    box-shadow: 0 0 20px yellow;
}
.planet:nth-child(8):hover {
   -webkit-filter: none;
    filter: none;
}


@keyframes blinker {
  50% {
    opacity: 0;
  }
}

@keyframes rotation_1 {
  from {
    transform: scale(0.6) rotate(0deg) translate(400%) rotate(30deg);
    background-position: 0 0;}

  to {
    transform: scale(0.6) rotate(0deg) translate(400%)rotate(30deg);
    background-position: -511px 0; } 
}
@keyframes rotation_2 {
  from {
    transform: scale(0.65) rotate(30deg) translate(320%) rotate(0deg);
    background-position: 0 0; }

  to {
    transform: scale(0.65) rotate(30deg) translate(320%) rotate(0deg);
    background-position: -511px 0; } 
}
@keyframes rotation_3 {
  from {
    transform: scale(0.8) rotate(70deg) translate(130%) rotate(0deg);
    background-position: 0 0; }

  to {
    transform: scale(0.8) rotate(70deg) translate(130%) rotate(0deg);
    background-position: -511px 0; } 
}
@keyframes rotation_4 {
  from {
    transform: scale(1) rotate(160deg) translate(200%) rotate(0deg);
  }
  to {
    transform: scale(1) rotate(160deg) translate(200%) rotate(359deg);
  }
}
@keyframes rotation_5 {
  from {
    transform: scale(0.5) rotate(180deg) translate(500%) rotate(15deg);
    background-position: 0 0; }

  to {
    transform: scale(0.5) rotate(180deg) translate(500%) rotate(15deg);
    background-position: -511px 0; } 
}
@keyframes rotation_6 {
  from {
    transform: scale(1) rotate(200deg) translate(200%) rotate(0deg);
    background-position: 0 0; }

  to {
    transform: scale(1) rotate(200deg) translate(200%) rotate(0deg);
    background-position: -511px 0; } 
}
@keyframes rotation_7 {
  from {
    transform: scale(0.7) rotate(250deg) translate(155%) rotate(0deg);
    background-position: 0 0; }

  to {
    transform: scale(0.7) rotate(250deg) translate(155%) rotate(0deg);
    background-position: -511px 0; } 
}
@keyframes rotation_8 {
  from {
    transform: scale(1) rotate(315deg) translate(175%) rotate(0deg);
  }
  to {
    transform: scale(1) rotate(315deg) translate(175%) rotate(359deg);
  }
}
</style>