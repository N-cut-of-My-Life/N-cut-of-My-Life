<template>
  <div class="title">행성 목록</div>
  <video muted autoplay loop playbackRate="1.0">
    <source src="@/assets/intro_video.mp4" type="video/mp4">
  </video>
  <audio loop autoplay volume="0.5">
    <source src="@/assets/audio/daylight.mp3" type="audio/mp3">
  </audio>
  <div class="other">
        <b-button @click="gotoPage({ name: 'introfirstpage' })" class="button_prev" size="sm">
            <strong>&lt;</strong>&nbsp;&nbsp;돌아가기
        </b-button>
    </div>
  <div class="bg">
    <div class="planet"></div>
    <div class="planet" @click="gotoPage({ name: 'sadplanet'})"></div>
    <div class="planet" @click="gotoPage({ name: 'happyplanet'})"></div>
    <img class="planet" src="../../assets/planet/music.png" alt="" @click="gotoPage({ name: 'musicplanet' })" />
    <div class="planet" @click="gotoPage({ name: 'dreamplanet'})"></div>
    <div class="planet" @click="gotoPage({ name: 'regretplanet'})"></div>
    <div class="planet" @click="gotoPage({ name: 'preciousplanet'})"></div>
    <img class="planet" src="../../assets/planet/blink.png" alt="" @click="gotoPage({ name: 'treasureplanet' })" />
    <img class="stars" src="../../assets/planet/stars.png" alt="">
    <img class="stars_line blink_star" src="../../assets/planet/stars-line.png" alt="">
    <img class="teapot" src="../../assets/planet/teapot.png" alt="" @click="gotoPage({ name: 'genieplanet' })" />
  </div>
</template>

<script>
export default {
  methods: {
    gotoPage(link) {
      this.$router.push(link)
    }
  }
}
</script>

<style scoped>
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

.title {
  padding-right: 1%;
  padding-top: 1%;
  color: aliceblue;
  font-size: 1.5vw;
  font-weight: 400;
  text-align: right;
}

.blink_star {
  animation: blinker 1s linear infinite;
}

.button_prev {
    background-color: #ffffff;
    color: #141414;
    border-radius: 0.8vw;
    border-color: #ffffff;
}

.other {
    position: absolute;
    left: 1%;
    top: 2.5%;
    margin: auto;
}

/* .bg{
  background-image: url("../../assets/planet/bg.png");
  height: 100vh;
  width: 100vw;
  display:block;
  z-index: -100;
  position: absolute;
  overflow: hidden;
} */
.planet {
  display: block;
  position: absolute;
  border-radius: 50%;
  left: 50%;
  top: 50%;
}

.stars {
  display: block;
  max-width: 20%;
  height: auto;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-top: -144.72px;
  margin-left: -86.14px;
  filter: grayscale(75%);
  -webkit-filter: grayscale(75%);
}

.stars_line {
  display: block;
  max-width: 20%;
  height: auto;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-top: -134.72px;
  margin-left: -90.34px;
}

.teapot {
  display:block;
  max-width: 16%;
  height: auto;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-top: -81px;
  margin-left: -20.34px;
  cursor: pointer;
}


.stars_line:hover+.stars {
  filter: none;
  -webkit-filter: none;
}

.stars:hover {
  filter: none;
  -webkit-filter: none;
}

.stars_line:hover+.teapot {
  display: block;
}

.planet:nth-child(1) {
  display: none;
  width: 256px;
  height: 256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/dearme.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: -128px;
  margin-top: -128px;
  filter: grayscale(75%);
  -webkit-filter: grayscale(75%);
  animation: rotation_1 20s infinite linear;
  cursor: pointer;
}

.planet:nth-child(2) {
  width: 256px;
  height: 256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/weep.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: 0px;
  margin-top: -250px;
  filter: grayscale(75%);
  -webkit-filter: grayscale(75%);
  animation: rotation_2 20s infinite linear;
  cursor: pointer;
}

.planet:nth-child(3) {
  width: 256px;
  height: 256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/haho.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: -50px;
  margin-top: -128px;
  filter: grayscale(75%);
  -webkit-filter: grayscale(75%);
  animation: rotation_3 20s infinite linear;
  cursor: pointer;
}

.planet:nth-child(4) {
  max-width: 15%;
  height: auto;
  margin-left: 10px;
  margin-top: -150px;
  filter: grayscale(75%);
  -webkit-filter: grayscale(75%);
  animation: rotation_4 20s infinite linear;
  cursor: pointer;
}

.planet:nth-child(5) {
  width: 256px;
  height: 256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/didnt.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: -100px;
  margin-top: 100px;
  filter: grayscale(75%);
  -webkit-filter: grayscale(75%);
  animation: rotation_5 20s infinite linear;
  cursor: pointer;
}

.planet:nth-child(6) {
  width: 256px;
  height: 256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/dearyou.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: -188px;
  margin-top: -128px;
  filter: grayscale(75%);
  -webkit-filter: grayscale(75%);
  animation: rotation_6 20s infinite linear;
  cursor: pointer;
}

.planet:nth-child(7) {
  width: 256px;
  height: 256px;
  max-width: 20%;
  max-height: auto;
  background: #666 url("../../assets/planet/forget.png") repeat-x 0% 0%;
  background-size: 512px 256px;
  margin-left: -128px;
  margin-top: -128px;
  filter: grayscale(75%);
  -webkit-filter: grayscale(75%);
  animation: rotation_7 20s infinite linear;
  cursor: pointer;
}

.planet:nth-child(8) {
  max-width: 15%;
  height: auto;
  margin-left: 50px;
  margin-top: -46.3px;
  filter: grayscale(75%);
  -webkit-filter: grayscale(75%);
  animation: rotation_8 20s infinite linear;
  cursor: pointer;
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
    background-position: 0 0;
  }

  to {
    transform: scale(0.6) rotate(0deg) translate(400%)rotate(30deg);
    background-position: -511px 0;
  }
}

@keyframes rotation_2 {
  from {
    transform: scale(0.65) rotate(30deg) translate(320%) rotate(0deg);
    background-position: 0 0;
  }

  to {
    transform: scale(0.65) rotate(30deg) translate(320%) rotate(0deg);
    background-position: -511px 0;
  }
}

@keyframes rotation_3 {
  from {
    transform: scale(0.8) rotate(70deg) translate(130%) rotate(0deg);
    background-position: 0 0;
  }

  to {
    transform: scale(0.8) rotate(70deg) translate(130%) rotate(0deg);
    background-position: -511px 0;
  }
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
    background-position: 0 0;
  }

  to {
    transform: scale(0.5) rotate(180deg) translate(500%) rotate(15deg);
    background-position: -511px 0;
  }
}

@keyframes rotation_6 {
  from {
    transform: scale(1) rotate(200deg) translate(200%) rotate(0deg);
    background-position: 0 0;
  }

  to {
    transform: scale(1) rotate(200deg) translate(200%) rotate(0deg);
    background-position: -511px 0;
  }
}

@keyframes rotation_7 {
  from {
    transform: scale(0.7) rotate(250deg) translate(155%) rotate(0deg);
    background-position: 0 0;
  }

  to {
    transform: scale(0.7) rotate(250deg) translate(155%) rotate(0deg);
    background-position: -511px 0;
  }
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