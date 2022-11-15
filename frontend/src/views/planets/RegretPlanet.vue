<template>
  <div class="jumbotron">
    <div class="title">괜히글 행성</div>
  </div>
  <img
    v-show="!elementVisible_2"
    class="bubble"
    :src="images[currentImage]"
    alt=""
  />
  <audio loop autoplay volume="0.3">
    <source src="@/assets/audio/mix_regret.mp3" type="audio/mp3" />
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
      @click="modalShow = !modalShow"
    >
      <div class="wave">
        <span style="--i: 1">후</span>
        <span style="--i: 2">회</span>
        <span style="--i: 3">&nbsp;</span>
        <span style="--i: 4">버</span>
        <span style="--i: 5">리</span>
        <span style="--i: 6">기</span>
        <span style="--i: 7">!</span>
      </div>
    </b-button>
  </div>

  <b-modal
    v-model="modalShow"
    id="modal-regret"
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
      "
    >
      가장 후회되는 일을 적어주세요!
    </div>
    <b-container ref="form" style="margin-bottom: 3.8%">
      <b-form-textarea
        v-model="answer"
        id="content"
        placeholder=""
        rows="10"
        max-rows="15"
        required
        style="border-radius: 1vw; background-color: #e3ecfc"
      >
      </b-form-textarea>
      <div id="length_check">
        {{ textLength }}
      </div>
    </b-container>
    <b-button
      @click="complete"
      style="
        color: #ffffff;
        background-color: #25316d;
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
        <span style="--i: 1">괜</span>
        <span style="--i: 2">히</span>
        <span style="--i: 3">글</span>
        <span style="--i: 4">&nbsp;</span>
        <span style="--i: 5">행</span>
        <span style="--i: 6">성</span>
        <span style="--i: 7">&nbsp;</span>
        <span style="--i: 8">여</span>
        <span style="--i: 9">행</span>
        <span style="--i: 10">&nbsp;</span>
        <span style="--i: 11">마</span>
        <span style="--i: 12">치</span>
        <span style="--i: 13">기</span>
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
        require("@/assets/PlanetSpeech/RegretSpeech/regret_bubble_1.svg"),
        require("@/assets/PlanetSpeech/RegretSpeech/regret_bubble_2.svg"),
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
          title: "일지 등록 실패! 😭",
          text: "길이가 올바르지 않습니다.",
          confirmButtonText: "확인",
        });
        return;
      }
      this.elementVisible_2 = true;
      this.elementVisible_3 = true;
      this.modalShow = false;
      setTimeout(() => (this.elementVisible_4 = true), 1000);
      usePlanetStore().completePlanet(5, this.answer);
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
html,
body {
  height: 100%;
}

body {
  margin: 0;
}

.bubble {
  position: absolute;
  right: 15%;
  bottom: 24%;
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
  background: url("@/assets/PlanetBackground/regret.svg") no-repeat center
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
  bottom: 52%;
  left: 37%;
  margin: auto;
}

.last_2 {
  position: absolute;
  bottom: 52%;
  left: 45%;
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

.button_2 {
  border-radius: 0.8vw;
  /* border-color: #81c6e8; */
  background-color: #9cb4cc;
  position: relative;
  margin: 300px auto 0;
  transition: all 0.3s ease-in-out 0s;
  font-size: 1.3vw;
  cursor: pointer;
}

.button_2::before {
  content: "";
  border-radius: 1000px;
  min-width: calc(160px + 12px);
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
  background-color: #9cb4cc;
  position: relative;
  margin: 300px auto 0;
  transition: all 0.3s ease-in-out 0s;
}

.button_3::before {
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
#modal-regret .modal-content {
  background-color: #5f6f94;
}

#modal-regret .modal-header {
  /* border-bottom: #1f1f1f; */
}

#modal-regret .modal-header .btn-close {
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
