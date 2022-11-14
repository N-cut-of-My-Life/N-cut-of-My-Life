<template>
  <video muted autoplay loop playbackRate="0.7">
    <source src="@/assets/intro_video.mp4" type="video/mp4" />
  </video>
  <audio loop autoplay volume="0.7">
    <source src="@/assets/audio/piano-moment.mp3" type="audio/mp3" />
  </audio>
  <splide
    id="carousel"
    :options="options"
    @splide:arrows:updated="checkLastSlide"
  >
    <splide-slide>
      <h1>
        안녕하세요<br />
        추억을 기록하는 우주여행사<br />
        인생N컷입니다.
      </h1>
    </splide-slide>
    <splide-slide>
      <h1>
        당신은<br />
        돌아가고 싶은 순간이<br />
        있으신가요?
      </h1>
    </splide-slide>
    <splide-slide>
      <h1>
        당신의<br />
        가장 행복했던 순간을<br />
        기억하시나요?
      </h1>
    </splide-slide>
    <splide-slide>
      <h1>
        지워버리고 싶은<br />
        후회스러운 기억이<br />
        있지는 않으신가요?
      </h1>
    </splide-slide>
    <splide-slide>
      <h1>
        <br />
        기억이 잘 나지 않는다구요?
      </h1>
    </splide-slide>
    <splide-slide>
      <h1>
        걱정 마세요.<br />
        저희 인생N컷이<br />
        당신의 추억을 잊지 않도록<br />
        도와드리겠습니다.
      </h1>
    </splide-slide>
    <splide-slide>
      <h1>
        <br />
        지금 같이 여행을 떠나볼까요?
      </h1>
    </splide-slide>
  </splide>

  <div v-if="isLast === false" class="jump">
    <b-button class="button" size="sm">
      <router-link :to="{ name: 'planetlist' }" class="nav-link">
        SKIP
      </router-link>
    </b-button>
  </div>
  <div v-else class="jump">
    <b-button v-show="elementVisible" class="button_2" size="lg">
      <router-link :to="{ name: 'planetlist' }" class="nav-link">
        <div class="wave">
          <span style="--i: 1">여</span>
          <span style="--i: 2">행</span>
          <span style="--i: 3">&nbsp;</span>
          <span style="--i: 4">떠</span>
          <span style="--i: 5">나</span>
          <span style="--i: 6">기</span>
          <span style="--i: 7">!</span>
        </div>
      </router-link>
    </b-button>
  </div>
</template>

<script>
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/themes/splide-default.min.css";
import { useMusicStore } from "@/store/music";
// import { usePlanetStore } from "@/store/planet";

export default {
  name: "MainSlider",
  components: {
    Splide,
    SplideSlide,
  },
  data() {
    return {
      options: {
        perPage: 1,
        pauseOnHover: false,
        arrows: true,
        dots: false,
        animatedDots: false,
        padding: "2rem",
        type: "fade",
        autoplay: true,
      },
      isLast: false,
      elementVisible: false,
    };
  },
  updated() {
    if (this.isLast === true) {
      setTimeout(() => (this.elementVisible = true), 2000);
    }
  },
  methods: {
    checkLastSlide(splide, prev, next, prevIndex, nextIndex) {
      if (nextIndex === -1) {
        this.isLast = true;
      } else {
        this.isLast = false;
      }
    },
  },
  mounted() {
    useMusicStore().isSoundActive();
  },
};
</script>

<style scoped>
@font-face {
  font-family: "kakao";
  src: url("@/fonts/KakaoBold.ttf") format("truetype");
}

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

h1 {
  text-align: center;
  color: aliceblue;
  font-size: 3rem;
}

.jump {
  position: absolute;
  right: 5%;
  bottom: 5%;
  margin: auto;
  text-align: right;
  font-size: 3rem;
}

.button {
  background-color: #1f1f1f;
  color: #ffffff;
}

.wave {
  position: relative;
  /* -webkit-box-reflect: below -1px linear-gradient(transparent, #FFFFFF); */
}

.wave span {
  position: relative;
  display: inline-block;
  color: #ffffff;
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
    transform: translateY(-5px);
  }
}

.button_2 {
  border-radius: 2vw;
  border: none;
  background: linear-gradient(to right, #8bbccc, #4c6793);
  position: relative;
  margin: 300px auto 0;
  transition: all 0.3s ease-in-out 0s;
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
</style>

<style>
#carousel .splide__pagination li {
  display: none;
}

#carousel .splide__arrow svg {
  fill: #ffffff;
  height: 1.5em;
  width: 1.5em;
  opacity: 100%;
}

#carousel .splide__arrow {
  width: fit-content;
  top: 45%;
}

#carousel .splide__arrow--next {
  right: 5%;
}

#carousel .splide__arrow--prev {
  left: 5%;
}

#carousel .splide__arrow {
  background: none;
}

#carousel .splide__slide {
  margin: 0;
}

#carousel {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  height: 25%;
  margin: auto;
}
</style>
