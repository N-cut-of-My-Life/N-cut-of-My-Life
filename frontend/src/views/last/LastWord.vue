<template>
  <video muted autoplay loop playbackRate="0.75" style="width: 100%">
    <source src="@/assets/space_window.mp4" type="video/mp4" />
  </video>
  <audio
    id="myaudios"
    loop
    autoplay
    :src="audios[currentAudio]"
    volume="0.2"
  ></audio>
  <img
    v-show="!elementVisible_2"
    class="bubble"
    :src="images[currentImage]"
    alt=""
  />
  <div class="jump" v-show="!elementVisible_2">
    <b-button
      @click="previousImage()"
      class="button"
      size="sm"
      :disabled="currentImage === 0"
    >
      뒤로
    </b-button>
    &nbsp;
    <b-button
      @click="nextImage()"
      class="button"
      size="sm"
      :disabled="currentImage === images.length - 1"
    >
      다음
    </b-button>
  </div>
  <div v-if="currentImage === images.length - 1" class="last">
    <b-button
      v-show="elementVisible && !elementVisible_3"
      class="button_2"
      size="md"
      @click="modalShow = !modalShow"
    >
      <div class="wave">
        <span style="--i: 1">나</span>
        <span style="--i: 2">에</span>
        <span style="--i: 3">게</span>
        <span style="--i: 4">&nbsp;</span>
        <span style="--i: 5">남</span>
        <span style="--i: 6">기</span>
        <span style="--i: 7">는</span>
        <span style="--i: 8">&nbsp;</span>
        <span style="--i: 9">마</span>
        <span style="--i: 10">지</span>
        <span style="--i: 11">막</span>
        <span style="--i: 12">&nbsp;</span>
        <span style="--i: 13">말</span>
        <span style="--i: 14">을</span>
        <span style="--i: 15">&nbsp;</span>
        <span style="--i: 16">이</span>
        <span style="--i: 17">&nbsp;</span>
        <span style="--i: 18">곳</span>
        <span style="--i: 19">에</span>
        <span style="--i: 20">&nbsp;</span>
        <span style="--i: 21">남</span>
        <span style="--i: 22">겨</span>
        <span style="--i: 23">주</span>
        <span style="--i: 24">세</span>
        <span style="--i: 25">요</span>
        <span style="--i: 26">!</span>
      </div>
    </b-button>
  </div>
  <b-modal
    v-model="modalShow"
    id="modal-last"
    hide-header
    hide-footer
    centered
    no-stacking
    style="text-align: center; border-radius: 1vw"
    :no-close-on-backdrop="false"
  >
    <img
      data-bs-dismiss="modal"
      aria-label="Close"
      class="x_button"
      src="@/assets/xButton/x_sad.svg"
      style="cursor: pointer; float: right"
      alt=""
    />
    <div
      style="
        font-size: 1.3vw;
        margin-top: 5%;
        margin-bottom: 3%;
        font-weight: 400;
        color: #ffffff;
      "
    >
      나에게 남길 마지막 말을 적어주세요!
    </div>
    <b-container ref="form" style="margin-bottom: 3.8%">
      <b-popover target="addon" placement="right" style="margin-left: 1%">
        <input type="file" accept="image/*" @change="onUpload" />
        <div>
          <img
            v-if="item.imageUrl"
            :src="item.imageUrl"
            style="margin-top: 2%; max-width: 16vw; height: auto"
            alt=""
          />
          <div v-else>
            <div style="margin-top: 1vh">
              <strong style="font-size: 1.1vw"
                >행복했던 순간을 담아주세요!</strong
              >
            </div>
          </div>
        </div>
      </b-popover>
    </b-container>
    <b-row style="width: 65%; float: right; margin-bottom: 1%"
      ><b-col cols="6" style="text-align: right; padding-right: 0">
        <span
          style="
            text-align: right;
            float: right;
            font-size: small;
            color: aliceblue;
          "
          >은하갤러리 공유 여부</span
        ></b-col
      ><b-col cols="2" style="padding-right: 0; padding-left: 0">
        <label class="switch">
          <input type="checkbox" v-model="isOpenState" />
          <span class="slider round"></span> </label
      ></b-col>
      <b-col cols="3" style="padding-left: 0; padding-right: 0">
        <b-button
          class="img-btn"
          id="addon"
          size="x-sm"
          style="
            border-color: #ffffff;
            padding: 1px 2px;
            color: #ffffff;
            font-size: 0.7vw;
          "
          >사진 첨부
        </b-button>
      </b-col>
    </b-row>
    <b-container ref="form" style="margin-bottom: 3.8%; margin-top: 2%">
      <b-form-textarea
        v-model="answer"
        id="content"
        placeholder=""
        rows="10"
        max-rows="15"
        required
        style="border-radius: 1vw; background-color: #cfd4df"
      >
      </b-form-textarea>
      <div id="length_check">
        {{ textLength }}
      </div>
    </b-container>
    <b-button
      text
      @click="finishTravel"
      style="
        color: #ffffff;
        background-color: #25316d;
        border: none;
        border-radius: 1vw;
      "
    >
      저장
    </b-button>
  </b-modal>
  <div class="last">
    <b-button
      v-show="elementVisible_4"
      class="button_2"
      size="md"
      @click="gotoPrint"
    >
      <div class="wave">
        <span style="--i: 1">수</span>
        <span style="--i: 2">고</span>
        <span style="--i: 3">하</span>
        <span style="--i: 4">셨</span>
        <span style="--i: 5">습</span>
        <span style="--i: 6">니</span>
        <span style="--i: 7">다</span>
        <span style="--i: 8">!</span>
        <span style="--i: 9">&nbsp;</span>
        <span style="--i: 10">이</span>
        <span style="--i: 11">제</span>
        <span style="--i: 12">&nbsp;</span>
        <span style="--i: 13">여</span>
        <span style="--i: 14">행</span>
        <span style="--i: 15">을</span>
        <span style="--i: 16">&nbsp;</span>
        <span style="--i: 17">돌</span>
        <span style="--i: 18">아</span>
        <span style="--i: 19">보</span>
        <span style="--i: 20">러</span>
        <span style="--i: 21">&nbsp;</span>
        <span style="--i: 22">가</span>
        <span style="--i: 23">볼</span>
        <span style="--i: 24">까</span>
        <span style="--i: 25">요</span>
        <span style="--i: 26">?</span>
      </div>
    </b-button>
  </div>
</template>

<script setup>
import { onUpdated, ref, onMounted, computed } from "vue";
import { useMusicStore } from "@/store/music";
import { usePlanetStore } from "@/store/planet";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";

let answer = ref("");
let isOpenState = ref(false);
let item = ref({ image: "hello", imageUrl: null });

onMounted(() => {
  useMusicStore().isSoundActive();
});

const textLength = computed(() => {
  return answer.value.length + "/255";
});

const modalShow = ref(false);

const router = useRouter();
const images = [
  require("@/assets/PlanetSpeech/LastSpeech/last_bubble_1.svg"),
  require("@/assets/PlanetSpeech/LastSpeech/last_bubble_2.svg"),
  require("@/assets/PlanetSpeech/LastSpeech/last_bubble_3.svg"),
  require("@/assets/PlanetSpeech/LastSpeech/last_bubble_4.svg"),
  require("@/assets/PlanetSpeech/LastSpeech/last_bubble_5.svg"),
  require("@/assets/PlanetSpeech/LastSpeech/last_bubble_6.svg"),
];
const audios = [require("@/assets/audio/mix_last.mp3")];
let currentImage = ref(0);
let elementVisible = ref(false);
let elementVisible_2 = ref(false);
let elementVisible_3 = ref(false);
let elementVisible_4 = ref(false);
const currentAudio = ref(0);

const onUpload = (e) => {
  const file = e.target.files[0];
  item.value.image = file;
  item.value.imageUrl = URL.createObjectURL(file);
};

const nextImage = () => {
  if (currentImage.value !== images.length - 1) currentImage.value++;
};
const previousImage = () => {
  if (currentImage.value !== 0) currentImage.value--;
};
const complete = () => {
  if (answer.value.length == 0 || answer.value.length > 255) {
    Swal.fire({
      icon: "error",
      title: "일지 등록 실패! 😭",
      text: "길이가 올바르지 않습니다.",
      confirmButtonText: "확인",
    });
    return;
  }
  elementVisible_2.value = true;
  elementVisible_3.value = true;
  modalShow.value = false;
  setTimeout(() => (elementVisible_4.value = true), 1000);
  usePlanetStore().completePlanet(
    9,
    answer.value,
    isOpenState.value ? "OPEN" : "CLOSE",
    item.value.image
  );
};
const finishTravel = () => {
  complete();
  usePlanetStore().finishTravel();
};
const gotoPrint = () => {
  router.push({ name: "resultprint" });
};
onUpdated(() => {
  if (currentImage.value == images.length - 1) {
    setTimeout(() => (elementVisible.value = true), 2000);
  }
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
  right: 46.5%;
  bottom: 10%;
  /* height: 50%; */
  margin: auto;
}

.img-btn {
  /* left: 0%;
    top: 0%; */
  background-color: transparent;
  float: right;
  /* position: absolute; */
}

.img-btn:active {
  background-color: transparent;
}

.jump {
  position: absolute;
  right: 5%;
  bottom: 5%;
  margin: auto;
  text-align: right;
  font-size: 3rem;
}

.last {
  position: absolute;
  bottom: 50%;
  left: 37.5%;
  margin: auto;
}

.button {
  background-color: #7882a4;
  color: #ffffff;
  border-radius: 0.8vw;
  border-color: #7882a4;
}

.button_2 {
  border-radius: 1vw;
  border: none;
  background-color: #7c99ac;
  position: relative;
  margin: 300px auto 0;
  transition: all 0.3s ease-in-out 0s;
}

.button_2::before {
  content: "";
  border-radius: 1000px;
  min-width: calc(410px + 12px);
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

.x_button {
  width: 4%;
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

.switch {
  position: relative;
  display: inline-block;
  width: 34px;
  height: 21px;
  margin-right: 5%;
  margin-bottom: 2%;
}

.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 17px;
  width: 17px;
  left: 2.5px;
  bottom: 2px;
  background-color: white;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

input:checked + .slider {
  background-color: #2196f3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196f3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(13px);
  -ms-transform: translateX(13px);
  transform: translateX(13px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 17px;
}

.slider.round:before {
  border-radius: 50%;
}

#length_check {
  text-align: right;
  font-size: small;
}
</style>

<style>
#modal-last .modal-content {
  background-color: #5f6f94;
}
.form-control {
  box-shadow: none !important;
  outline: none !important;
}
</style>
