<template>
  <div class="jumbotron">
    <div class="title">이루지못 행성</div>
  </div>
  <img
    v-show="!elementVisible_2"
    class="bubble"
    :src="images[currentImage]"
    alt=""
  />
  <audio loop autoplay volume="0.3">
    <source src="@/assets/audio/mix_dream.mp3" type="audio/mp3" />
  </audio>
  <div class="other">
    <b-button
      @click="gotoPage({ name: 'planetlist' })"
      class="button_prev"
      size="sm"
    >
      <strong>&lt;</strong>&nbsp;&nbsp;다른 행성 가기
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
      v-show="elementVisible && !elementVisible_3"
      class="button_2"
      size="md"
    >
      <div class="wave" @click="modalShow = !modalShow">
        <span style="--i: 1">꿈</span>
        <span style="--i: 2">&nbsp;</span>
        <span style="--i: 3">맡</span>
        <span style="--i: 4">기</span>
        <span style="--i: 5">러</span>
        <span style="--i: 6">&nbsp;</span>
        <span style="--i: 7">가</span>
        <span style="--i: 8">기</span>
        <span style="--i: 9">!</span>
      </div>
    </b-button>
  </div>

  <b-modal
    v-model="modalShow"
    centered
    no-stacking
    id="modal-dream"
    hide-header
    hide-footer
    :no-close-on-backdrop="false"
    style="text-align: center; border-radius: 1vw"
  >
    <img
      data-bs-dismiss="modal"
      aria-label="Close"
      class="x_button"
      src="@/assets/xButton/x_dream.svg"
      style="cursor: pointer; float: right"
      alt=""
    />
    <div
      style="
        font-size: 1.3vw;
        margin-top: 5%;
        margin-bottom: 3%;
        font-weight: 400;
      "
    >
      아쉽게 이루지 못한 꿈을 이 곳에 적어주세요!
    </div>
    <b-container ref="form" style="margin-bottom: 3.8%">
      <b-form-textarea
        v-model="answer"
        id="content"
        placeholder=""
        rows="10"
        max-rows="15"
        required
        style="border-radius: 1vw; background-color: #eceffa"
      >
      </b-form-textarea>
      <div id="length_check">
        {{ textLength }}
      </div>
    </b-container>
    <b-button
      text
      @click="complete"
      style="
        color: #ffffff;
        background-color: #b1afff;
        border: none;
        border-radius: 1vw;
      "
      >저장
    </b-button>
  </b-modal>
  <div class="last_2">
    <b-button
      v-show="elementVisible_4"
      class="button_3"
      size="md"
      @click="endthisPlanet()"
    >
      <div class="wave">
        <span style="--i: 1">이</span>
        <span style="--i: 2">루</span>
        <span style="--i: 3">지</span>
        <span style="--i: 4">못</span>
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

<script>
import { useMusicStore } from "@/store/music";
import { usePlanetStore } from "@/store/planet";
import Swal from "sweetalert2";
export default {
  data() {
    return {
      images: [
        require("@/assets/PlanetSpeech/DreamSpeech/dream_bubble_1.svg"),
        require("@/assets/PlanetSpeech/DreamSpeech/dream_bubble_2.svg"),
        require("@/assets/PlanetSpeech/DreamSpeech/dream_bubble_3.svg"),
        require("@/assets/PlanetSpeech/DreamSpeech/dream_bubble_4.svg"),
      ],
      modalShow: false,
      currentImage: 0,
      elementVisible: false,
      elementVisible_2: false,
      elementVisible_3: false,
      elementVisible_4: false,
      answer: "",
    };
  },
  computed: {
    textLength() {
      return this.answer.length + "/255";
    },
  },
  // created로 하면 생명주기가 더 앞 순위이기에 페이지가 열리고 바로 카운트된다.
  updated() {
    if (this.currentImage == this.images.length - 1) {
      setTimeout(() => (this.elementVisible = true), 2000);
    }
  },
  methods: {
    nextImage() {
      if (this.currentImage !== this.images.length - 1) this.currentImage++;
    },

    previousImage() {
      if (this.currentImage !== 0) this.currentImage--;
    },

    gotoPage(link) {
      this.$router.push(link);
    },
    complete() {
      if (this.answer.length == 0 || this.answer.length > 255) {
        Swal.fire({
          icon: "error",
          title: "등록 실패! 😭",
          text: "텍스트 길이를 확인해 주세요!",
          confirmButtonText: "확인",
        });
        return;
      }
      this.elementVisible_2 = true;
      this.elementVisible_3 = true;
      this.modalShow = false;
      setTimeout(() => (this.elementVisible_4 = true), 1000);
      usePlanetStore().completePlanet(4, this.answer);
    },
    endthisPlanet() {
      this.$router.push({ name: "planetlist" });
    },
  },
  mounted() {
    useMusicStore().isSoundActive();
  },
};
</script>

<style scoped>
body {
  margin: 0;
}

.bubble {
  position: absolute;
  /* top: 0;
    left: 0; */
  right: 24%;
  bottom: 18%;
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
  background: url("@/assets/PlanetBackground/dream.svg") no-repeat center center
    fixed;
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
  bottom: 50%;
  left: 45%;
  margin: auto;
}

.last_2 {
  position: absolute;
  bottom: 50%;
  left: 43%;
  margin: auto;
}

.button {
  background-color: #a28dc4;
  color: #ffffff;
  border-radius: 0.8vw;
  border-color: #a28dc4;
}

.button_prev {
  background-color: #ffffff;
  color: #141414;
  border-radius: 0.8vw;
  border-color: #ffffff;
}

.x_button {
  width: 4%;
}

.button_prev {
  background-color: #ffffff;
  color: #141414;
  border-radius: 0.8vw;
  border-color: #ffffff;
}

.button_2 {
  border-radius: 0.8vw;
  border: none;
  background-color: #81c6e8;
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

.button_3 {
  border-radius: 0.8vw;
  border: none;
  background-color: #81c6e8;
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
<style>
#modal-dream .modal-content {
  background-color: #b8cff8;
}

#modal-dream .modal-header {
  /* border-bottom: #1f1f1f; */
}

#modal-dream .modal-header .btn-close {
  color: white;
}
#length_check {
  text-align: right;
  font-size: 10px;
  margin-top: 3px;
}

.form-control {
  box-shadow: none !important;
  outline: none !important;
}
</style>
