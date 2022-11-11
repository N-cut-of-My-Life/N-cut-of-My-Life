<template>
  <div class="jumbotron">
    <div class="title">반짝반짝 행성</div>
  </div>
  <img class="bubble" :src="images[currentImage]" alt="" />
  <audio loop autoplay volume="0.3">
    <source src="@/assets/audio/shining-diamond.mp3" type="audio/mp3" />
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
    <b-button v-show="elementVisible" class="button_2" size="md">
      <div class="wave" v-b-modal.modal-treasure>
        <span style="--i: 1">가</span>
        <span style="--i: 2">장</span>
        <span style="--i: 3">&nbsp;</span>
        <span style="--i: 4">소</span>
        <span style="--i: 5">중</span>
        <span style="--i: 6">한</span>
        <span style="--i: 7">&nbsp;</span>
        <span style="--i: 8">물</span>
        <span style="--i: 9">건</span>
        <span style="--i: 10">&nbsp;</span>
        <span style="--i: 11">적</span>
        <span style="--i: 12">으</span>
        <span style="--i: 13">러</span>
        <span style="--i: 14">&nbsp;</span>
        <span style="--i: 15">가</span>
        <span style="--i: 16">기</span>
        <span style="--i: 17">!</span>
      </div>
      <!-- <div v-b-modal.modal-treasure>
                가장 소중한 물건 찾으러 가기!
            </div> -->
    </b-button>
  </div>

  <b-modal
    id="modal-treasure"
    hide-header
    hide-footer
    centered
    no-stacking
    :no-close-on-backdrop="true"
    style="text-align: center; border-radius: 1vw"
  >
    <img
      data-bs-dismiss="modal"
      aria-label="Close"
      class="x_button"
      src="@/assets/xButton/x_genie.svg"
      style="cursor: pointer; float: right"
      alt=""
    />
    <div
      style="
        font-size: 1.3vw;
        margin-top: 5%;
        margin-bottom: 2%;
        font-weight: 400;
      "
    >
      당신의 가장 소중한 물건을 적어주세요!
    </div>
    <b-button
      class="img-btn"
      id="addon"
      size="x-sm"
      style="
        margin-right: 3%;
        border-color: #ffffff;
        padding: 2px 4px;
        color: #ffffff;
        font-size: 0.7vw;
        margin-bottom: 1%;
      "
      >사진 첨부
    </b-button>
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
                >소중한 물건을 담아주세요!</strong
              >
            </div>
          </div>
        </div>
      </b-popover>
      <b-form-textarea
        id="content"
        placeholder=""
        rows="10"
        max-rows="15"
        required
        style="border-radius: 1vw; background-color: #e3ecfc"
      >
      </b-form-textarea>
    </b-container>
    <span>
      <b-button
        text
        @click="submit"
        data-bs-dismiss="modal"
        aria-label="Close"
        style="
          color: #ffffff;
          background-color: #9985c6;
          border: none;
          border-radius: 1vw;
        "
        >저장
      </b-button>
    </span>
  </b-modal>
</template>

<script>
import { useMusicStore } from "@/store/index";
export default {
  data() {
    return {
      item: {
        image: null,
        imageUrl: null,
      },
      images: [
        require("@/assets/PlanetSpeech/TreasureSpeech/treasure_bubble_1.svg"),
        require("@/assets/PlanetSpeech/TreasureSpeech/treasure_bubble_2.svg"),
        require("@/assets/PlanetSpeech/TreasureSpeech/treasure_bubble_3.svg"),
        require("@/assets/PlanetSpeech/TreasureSpeech/treasure_bubble_4.svg"),
        require("@/assets/PlanetSpeech/TreasureSpeech/treasure_bubble_5.svg"),
      ],
      currentImage: 0,
      elementVisible: false,
    };
  },
  updated() {
    if (this.currentImage == this.images.length - 1) {
      setTimeout(() => (this.elementVisible = true), 2000);
    }
  },
  methods: {
    onUpload(e) {
      const file = e.target.files[0];
      this.image = file;
      this.item.imageUrl = URL.createObjectURL(file);
    },
    nextImage() {
      if (this.currentImage !== this.images.length - 1) this.currentImage++;
    },

    previousImage() {
      if (this.currentImage !== 0) this.currentImage--;
    },

    gotoPage(link) {
      this.$router.push(link);
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

.x_button {
  width: 4%;
}

body {
  margin: 0;
}

.bubble {
  position: absolute;
  left: 9%;
  bottom: 10%;
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
.jumbotron {
  background: url("@/assets/PlanetBackground/treasure.svg") no-repeat center
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
  bottom: 8%;
  left: 40.5%;
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

.button_2 {
  border-radius: 1vw;
  border: none;
  background-color: #bb9f7f;
  position: relative;
  margin: 300px auto 0;

  font-size: 1.3vw;
  transition: all 0.3s ease-in-out 0s;
  cursor: pointer;
}

.button_2::before {
  content: "";
  border-radius: 1000px;
  min-width: calc(280px + 12px);
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
#modal-treasure .modal-content {
  background-color: #b1afff;
}

#modal-treasure .modal-header {
  /* border-bottom: #1f1f1f; */
}

#modal-treasure .modal-header .btn-close {
  color: white;
}
.popover {
  --bs-popover-arrow-width: 0rem;
  --bs-popover-arrow-height: 0rem;
  --bs-popover-body-margin-x: 1rem;
}
</style>
