<template>
  <music-modal @complete="complete"></music-modal>
  <div class="jumbotron">
    <div class="title">둠칫둠칫 행성</div>
  </div>
  <audio
    id="myaudios"
    loop
    autoplay
    :src="audios[currentAudio]"
    volume="0.2"
  ></audio>
  <!-- <a href="javascript:void(0);" @click="toggleMute()">Mute/Unmute</a> -->
  <img
    class="bubble"
    :src="images[currentImage]"
    alt=""
    v-show="!elementVisible_2"
  />
  <div class="other">
    <b-button
      @click="gotoPage({ name: 'planetlist' })"
      class="button_prev"
      size="sm"
    >
      <strong>&lt;</strong>&nbsp;&nbsp;다른 행성 가기 🪐
    </b-button>
  </div>
  <div class="jump">
    <b-row>
      <b-col>
        <b-button
          @click="previousImage()"
          class="button"
          size="sm"
          :disabled="currentImage === 0"
        >
          뒤로
        </b-button>
      </b-col>
      <b-col>
        <b-button
          @click="nextImage()"
          class="button"
          size="sm"
          :disabled="currentImage === images.length - 1"
        >
          다음
        </b-button>
      </b-col>
    </b-row>
  </div>
  <div v-if="currentImage === images.length - 1" class="last">
    <b-button
      v-show="elementVisible && !elementVisible_5"
      class="button_2"
      size="md"
      href="#openModal-about"
      @click="useMusicStore().songs = []"
    >
      <div class="wave">
        <span style="--i: 1">음</span>
        <span style="--i: 2">악</span>
        <span style="--i: 3">&nbsp;</span>
        <span style="--i: 4">검</span>
        <span style="--i: 5">색</span>
        <span style="--i: 6">하</span>
        <span style="--i: 7">러</span>
        <span style="--i: 8">&nbsp;</span>
        <span style="--i: 9">가</span>
        <span style="--i: 10">기</span>
        <span style="--i: 11">!</span>
      </div>
    </b-button>
  </div>
  <div class="last_2">
    <b-button
      v-show="elementVisible_4"
      class="button_3"
      size="md"
      @click="endthisPlanet()"
    >
      <div class="wave">
        <span style="--i: 1">둠</span>
        <span style="--i: 2">칫</span>
        <span style="--i: 3">둠</span>
        <span style="--i: 4">칫</span>
        <span style="--i: 5">&nbsp;</span>
        <span style="--i: 6">행</span>
        <span style="--i: 7">성</span>
        <span style="--i: 8">&nbsp;</span>
        <span style="--i: 9">여</span>
        <span style="--i: 10">행</span>
        <span style="--i: 11">&nbsp;</span>
        <span style="--i: 12">마</span>
        <span style="--i: 13">치</span>
        <span style="--i: 14">기</span>
      </div>
    </b-button>
  </div>
</template>

<script setup>
import MusicModal from "@/components/planets/MusicModal.vue";
import { onMounted, onUpdated, ref } from "vue";
import { useRouter } from "vue-router";
import { useMusicStore } from "@/store/music";

const router = useRouter();
const images = [
  require("@/assets/PlanetSpeech/MusicSpeech/music_bubble_1.svg"),
  require("@/assets/PlanetSpeech/MusicSpeech/music_bubble_2.svg"),
  require("@/assets/PlanetSpeech/MusicSpeech/music_bubble_3.svg"),
  require("@/assets/PlanetSpeech/MusicSpeech/music_bubble_4.svg"),
  require("@/assets/PlanetSpeech/MusicSpeech/music_bubble_5.svg"),
];
const audios = [
  // 병합 예정
  // require('@/assets/audio/booty-in-the-nest.mp3'),
  require("@/assets/audio/come-on-boy.mp3"),
];
let currentImage = ref(0);
let elementVisible = ref(false);
let elementVisible_2 = ref(false);
let elementVisible_3 = ref(false);
let elementVisible_4 = ref(false);
let elementVisible_5 = ref(false);
const currentAudio = ref(0);

const nextImage = () => {
  if (currentImage.value !== images.length - 1) currentImage.value++;
};
const previousImage = () => {
  if (currentImage.value !== 0) currentImage.value--;
};
const gotoPage = (link) => {
  router.push(link);
};
const complete = () => {
  console.log("EMIT");
  elementVisible.value = false;
  elementVisible_2.value = true;
  elementVisible_3.value = true;
  elementVisible_5.value = true;
  setTimeout(() => (elementVisible_4.value = true), 2000);
};
const endthisPlanet = () => {
  elementVisible.value = false;
  router.push({ name: "planetlist" });
};
onUpdated(() => {
  if (currentImage.value == images.length - 1) {
    setTimeout(() => (elementVisible.value = true), 2000);
  }
});
onMounted(() => {
  // 브금 온 오프용 함수
  useMusicStore().isSoundActive();
});
</script>

<style scoped>
html,
body {
  height: 100%;
}

.button_prev {
  background-color: #ffffff;
  color: #141414;
  border-radius: 0.8vw;
  border-color: #ffffff;
}

body {
  margin: 0;
}

.bubble {
  position: absolute;
  top: 0;
  left: 0;
  right: 3%;
  bottom: 17%;
  /* height: 50%; */
  margin: auto;
}

.title {
  padding-right: 1%;
  padding-top: 1%;
  color: aliceblue;
  font-size: 1.5vw;
  font-weight: 400;
  text-align: right;
}

.jumbotron {
  background: url("@/assets/PlanetBackground/dumchit.svg") no-repeat center
    center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  height: 100vh;
  width: 100%;
  margin: 0;
  padding: 0;
}

.jump {
  position: absolute;
  right: 5%;
  bottom: 5%;
  margin: auto;
  text-align: right;
  font-size: 3rem;
}

.other {
  position: absolute;
  left: 1%;
  top: 2.5%;
  margin: auto;
}

.last {
  position: absolute;
  bottom: 15%;
  left: 44.5%;
  margin: auto;
}

.last_2 {
  position: absolute;
  bottom: 15%;
  left: 43%;
  margin: auto;
}

.button {
  background-color: #f0b7a7;
  color: #ffffff;
  border-radius: 0.8vw;
  border-color: #f0b7a7;
}

.button_prev {
  background-color: #ffffff;
  color: #141414;
  border-radius: 0.8vw;
  border-color: #ffffff;
}

.button_2 {
  border-radius: 1vw;
  border: none;
  background-color: orange;
  position: relative;
  margin: 300px auto 0;
  transition: all 0.3s ease-in-out 0s;
}

.button_2::before {
  content: "";
  border-radius: 1000px;
  min-width: calc(220px + 12px);
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

.button_3 {
  border-radius: 1vw;
  border: none;
  background-color: orange;
  position: relative;
  margin: 300px auto 0;
  transition: all 0.3s ease-in-out 0s;
}

.button_3::before {
  content: "";
  border-radius: 1000px;
  min-width: calc(250px + 12px);
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

.button_3:hover,
.button_3:focus {
  color: #313133;
  transform: translateY(-6px);
}

.button_3:hover::before,
.button_3:focus::before {
  opacity: 1;
}

.button_3:hover::after,
.button_3:focus::after {
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
