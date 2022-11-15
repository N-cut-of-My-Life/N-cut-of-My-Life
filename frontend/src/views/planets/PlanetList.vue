<template>
  <div class="help-tip">
    <p>&nbsp;3개 이상의 행성을 다녀오시면 여행을 마치실 수 있습니다!</p>
  </div>
  <video muted autoplay loop playbackRate="1.0">
    <source src="@/assets/intro_video.mp4" type="video/mp4" />
  </video>
  <audio muted loop autoplay volume="0.5">
    <source src="@/assets/audio/daylight.mp3" type="audio/mp3" />
  </audio>
  <div class="other">
    <b-button
      @click="gotoPage($event, { name: 'introfirstpage' })"
      class="button_prev"
      size="sm"
    >
      <strong>&lt;</strong>&nbsp;&nbsp;돌아가기
    </b-button>
  </div>
  <div class="bg">
    <div class="planet"></div>
    <div
      class="planet"
      v-bind:class="{ complete: checkCompletedPlanet(2) }"
      @click="gotoPage($event, { name: 'sadplanet' })"
      v-b-tooltip.hover.top="'훌쩍훌쩍 행성'"
    ></div>
    <div
      class="planet"
      v-bind:class="{ complete: checkCompletedPlanet(1) }"
      @click="gotoPage($event, { name: 'happyplanet' })"
      v-b-tooltip.hover.left="'하하호호 행성'"
    ></div>
    <div
      class="planet"
      v-bind:class="{ complete: checkCompletedPlanet(6) }"
      @click="gotoPage($event, { name: 'musicplanet' })"
      v-b-tooltip.hover.bottom="'둠칫둠칫 행성'"
    ></div>
    <div
      class="planet"
      v-bind:class="{ complete: checkCompletedPlanet(4) }"
      @click="gotoPage($event, { name: 'dreamplanet' })"
      v-b-tooltip.hover.top="'이루지못 행성'"
    ></div>
    <div
      class="planet"
      v-bind:class="{ complete: checkCompletedPlanet(5) }"
      @click="gotoPage($event, { name: 'regretplanet' })"
      v-b-tooltip.hover.bottom="'괜히글 행성'"
    ></div>
    <div
      class="planet"
      v-bind:class="{ complete: checkCompletedPlanet(8) }"
      @click="gotoPage($event, { name: 'preciousplanet' })"
      v-b-tooltip.hover.top="'넌너무소중 행성'"
    ></div>
    <img
      class="planet"
      v-bind:class="{ complete: checkCompletedPlanet(3) }"
      src="../../assets/planet/blink.png"
      alt=""
      @click="gotoPage($event, { name: 'treasureplanet' })"
      v-b-tooltip.hover.right="'반짝반짝 행성'"
    />
    <img class="stars" src="../../assets/planet/stars.png" alt="" />
    <img
      class="stars_line blink_star"
      src="../../assets/planet/stars-line.png"
      alt=""
    />
    <img
      class="teapot"
      v-bind:class="{ complete: checkCompletedPlanet(7) }"
      src="../../assets/planet/teapot.png"
      alt=""
      @click="gotoPage($event, { name: 'genieplanet' })"
      v-b-tooltip.hover.top="'지니 행성'"
    />
  </div>
  <div class="last">
    <b-button
      class="button_2"
      v-show="getMinimumConditionsMet() === true"
      @click="finishTravel"
    >
      <div class="wave">
        <span style="--i: 1">여</span>
        <span style="--i: 2">행</span>
        <span style="--i: 3">을</span>
        <span style="--i: 4">&nbsp;</span>
        <span style="--i: 5">마</span>
        <span style="--i: 6">칠</span>
        <span style="--i: 7">래</span>
        <span style="--i: 8">요</span>
        <span style="--i: 9">!</span>
      </div>
    </b-button>
  </div>
</template>

<script>
import { useMusicStore } from "@/store/music";
import { usePlanetStore } from "@/store/planet";
export default {
  methods: {
    gotoPage(event, link) {
      if (event.target.classList.contains("complete")) {
        return;
      }
      this.$router.push(link);
    },
    getMinimumConditionsMet() {
      return usePlanetStore().minimumConditionsMet;
    },
    finishTravel() {
      usePlanetStore().finishTravel();
      this.$router.push({ name: "lastword" });
    },
    checkCompletedPlanet(planetId) {
      let completed = false;
      usePlanetStore().articleRequest.answers.forEach((answer) => {
        if (answer.questionId == planetId) {
          completed = true;
        }
      });
      return completed;
    },
  },
  mounted() {
    useMusicStore().isSoundActive();
  },
};
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

body {
  margin: 0;
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

.last {
  position: absolute;
  bottom: 5%;
  right: 3%;
  margin: auto;
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
  display: inline-block;
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
  filter: grayscale(100%);
  -webkit-filter: grayscale(100%);
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
  display: block;
  max-width: 16%;
  height: auto;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-top: -81px;
  margin-left: -20.34px;
  cursor: pointer;
  opacity: 0;
}

.teapot:hover {
  opacity: 1;
}

.stars:hover {
  filter: none;
  -webkit-filter: none;
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
  filter: grayscale(35%);
  -webkit-filter: grayscale(35%);
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
  margin-left: 40px;
  margin-top: -250px;
  filter: grayscale(35%);
  -webkit-filter: grayscale(35%);
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
  margin-left: 20px;
  margin-top: -136px;
  filter: grayscale(35%);
  -webkit-filter: grayscale(35%);
  animation: rotation_3 20s infinite linear;
  cursor: pointer;
}

.planet:nth-child(4) {
  max-width: 20%;
  height: 256px;
  width: 256px;
  margin-left: 60px;
  margin-top: -170px;
  filter: grayscale(35%);
  -webkit-filter: grayscale(35%);
  animation: rotation_4 20s infinite linear;
  cursor: pointer;
  background: rgb(20, 20, 20, 0) url("../../assets/planet/music.png") repeat-x
    0% 0%;
  background-size: 256px 256px;
  max-height: auto;
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
  filter: grayscale(35%);
  -webkit-filter: grayscale(35%);
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
  filter: grayscale(35%);
  -webkit-filter: grayscale(35%);
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
  filter: grayscale(35%);
  -webkit-filter: grayscale(35%);
  animation: rotation_7 20s infinite linear;
  cursor: pointer;
}

.planet:nth-child(8) {
  max-width: 15%;
  height: auto;
  margin-left: 50px;
  margin-top: -0px;
  filter: grayscale(35%);
  -webkit-filter: grayscale(35%);
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

.complete {
  filter: grayscale(100%) !important;
  -webkit-filter: grayscale(100%) !important;
  pointer-events: none;
}

/* .complete:hover {
  filter: none;
  -webkit-filter: none;
} */

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
    transform: scale(0.6) rotate(0deg) translate(400%) rotate(30deg);
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

.button_2 {
  border-radius: 0.8vw;
  /* border-color: #81c6e8; */
  background-color: #827397;
  position: relative;
  margin: 300px auto 0;
  transition: all 0.3s ease-in-out 0s;
}

.button_2::before {
  content: "";
  border-radius: 1000px;
  min-width: calc(190px + 12px);
  min-height: calc(60px + 12px);
  box-shadow: 0 0 60px #ffffff;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  opacity: 0.5;
  transition: all 0.3s ease-in-out 0s;
  animation: ring 1.5s infinite;
}

.button_2:hover,
.button_2:focus {
  color: #313133;
  transform: translateY(-6px);
}

.button_2:hover::before,
.button_2:focus::before {
  opacity: 1;
}

.button_2:hover::after,
.button_2:focus::after {
  animation: none;
  display: none;
}

@keyframes ring {
  0% {
    width: fit-content;
    height: fit-content;
    opacity: 1;
  }

  100% {
    width: fit-content;
    height: fit-content;
    opacity: 0;
  }
}

.wave {
  position: relative;
  /* -webkit-box-reflect: below -1px linear-gradient(transparent, #FFFFFF); */
}

.wave span {
  position: relative;
  display: inline-block;
  color: #ffffff;
  font-size: 1.2vw;
  text-transform: uppercase;
  animation: wave 3s infinite;
  animation-delay: calc(0.1s * var(--i));
}

@keyframes wave {
  0%,
  40%,
  100% {
    transform: translateY(0);
  }

  20% {
    transform: translateY(-3px);
  }
}
</style>
<style>
.help-tip {
  position: absolute;
  top: 2.5%;
  right: 1%;
  text-align: center;
  background-color: #334756;
  border-radius: 50%;
  width: 2vw;
  height: 2vw;
  font-size: 1.1vw;
  line-height: 2.1vw;
  cursor: default;
  padding-left: 2px;
}

.help-tip:before {
  content: "?";
  font-weight: bold;
  color: #fff;
}

.help-tip:hover p {
  display: block;
  transform-origin: 100% 0%;
  -webkit-animation: fadeIn 0.3s ease-in-out;
  animation: fadeIn 0.3s ease-in-out;
}

.help-tip p {
  display: none;
  text-align: left;
  background-color: #1e2021;
  width: 24vw;
  position: absolute;
  border-radius: 1vw;
  box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.2);
  right: -4px;
  color: #fff;
  font-size: 1vw;
  line-height: 1.4;
  padding: 0.1vw;
}

.help-tip p:before {
  position: absolute;
  content: "";
  width: 0;
  height: 0;
  border: 6px solid transparent;
  border-bottom-color: #1e2021;
  left: 20%;
  top: 12px;
}

.help-tip p:after {
  width: 100%;
  height: 40px;
  content: "";
  position: absolute;
  top: -40px;
  left: 0;
}

@-webkit-keyframes fadeIn {
  0% {
    opacity: 0;
    transform: scale(0.6);
  }

  100% {
    opacity: 100%;
    transform: scale(1);
  }
}

@keyframes fadeIn {
  0% {
    opacity: 0;
  }

  100% {
    opacity: 100%;
  }
}
</style>
