<template>
  <video muted autoplay loop playbackRate="1.0">
    <source src="@/assets/intro_video.mp4" type="video/mp4">
  </video>
  <!-- <audio id="cosmos" loop autoplay>
    <source src="@/assets/audio/cosmos.mp3" type="audio/mp3">
  </audio> -->
  <audio id="cosmos" loop autoplay volume="0.7">
    <source src="@/assets/audio/new-horizons.mp3" type="audio/mp3">
  </audio>
  <audio id="burung" volume="0.3">
    <source src="@/assets/audio/nitro-activation.mp3" type="audio/mp3">
  </audio>
  <div style="color: #ffffff;  text-align: right;  padding-right:78px; margin-top: 3.5%;">어서오세요, <span
      style="font-weight:400">최재현</span>님</div>
  <b-navbar toggleable="lg" style="float: right; margin-right: 50px;">
    <b-navbar-nav>
      <b-nav-item>
        <router-link :to="{ name: 'traveldiary' }" class="nav-link">
          나의 여행 일지
        </router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link :to="{ name: 'galaxygallery' }" class="nav-link">
          은하갤러리
        </router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link :to="{ name: 'planetlist' }" class="nav-link">
          행성 목록
        </router-link>
      </b-nav-item>
    </b-navbar-nav>
  </b-navbar>
  <br /><br /><br /><br /><br />
  <div class="img-box">
    <img src="@/assets/astronaut_riding.png" class="img-rocket rocket-bounce" alt=""/>
  </div>
  <br /><br /><br /><br />
  <div class="box">
    <div class="start-btn" @click="rocketLaunch">
      여행 시작하기
    </div>
  </div>
</template>

<script setup>
import { useRouter, useRoute } from 'vue-router'
import { useMusicStore } from '@/store/index'
import { onMounted } from 'vue'

const router = useRouter()
const route = useRoute()
const code = route.query.code;


// 클릭 시 로켓 발사
const rocketLaunch = () => {
  console.log(code)
  const rocket = document.querySelector('.img-rocket')
  document.getElementById('cosmos').pause();
  document.getElementById('burung').play();
  rocket.classList.remove('rocket-bounce')
  rocket.classList.add('rocket-launch')
  rocket.addEventListener('animationend', (event) => {
    if (event.animationName.includes('launch')) {
      router.push({ name: 'introstory' })
    }
  });
};

onMounted(() => {
  const isMute = useMusicStore().isMute
  if(isMute === true){
    document.querySelector('#cosmos').muted = true
    console.log(document.querySelector('audio'))
  } else {
    document.querySelector('#cosmos').muted = false
    console.log(document.querySelector('audio'))
  }
})
</script>

<style scoped>
@font-face {
  font-family: 'kakao';
  src: url('@/fonts/KakaoBold.ttf') format('truetype');
}

.navbar-expand-lg .navbar-nav .nav-link a:hover {
  text-decoration: none;
  color: #ffffff;
  font-weight: 600;
}

.navbar-expand-lg .navbar-nav .nav-link {
  color: #ffffff;
}

body {
  margin: 0;
}

.img-box {
  text-align: center;
}

.img-rocket {
  width: 40%;
}

.rocket-launch {
  animation-name: shake, launch;
  animation-delay: 0s, 1s;
  animation-duration: 0.75s, 1.5s;
  animation-timing-function: ease-in, ease-in-out;
  animation-fill-mode: forwards;
}

.rocket-bounce {
  animation: bounce .75s infinite alternate;
  -webkit-animation: bounce .75s infinite alternate;

}

@keyframes bounce {
  from {
    transform: translateY(0px);
  }

  to {
    transform: translateY(-15px);
  }
}

@-webkit-keyframes bounce {
  from {
    transform: translateY(0px);
  }

  to {
    transform: translateY(-15px);
  }
}

@keyframes shake {
  0% {
    transform: translate(1px, 1px) rotate(0deg);
  }

  10% {
    transform: translate(-1px, -2px) rotate(-1deg);
  }

  20% {
    transform: translate(-3px, 0px) rotate(1deg);
  }

  30% {
    transform: translate(3px, 2px) rotate(0deg);
  }

  40% {
    transform: translate(1px, -1px) rotate(1deg);
  }

  50% {
    transform: translate(-1px, 2px) rotate(-1deg);
  }

  60% {
    transform: translate(-3px, 1px) rotate(0deg);
  }

  70% {
    transform: translate(3px, 1px) rotate(-1deg);
  }

  80% {
    transform: translate(-1px, -1px) rotate(1deg);
  }

  90% {
    transform: translate(1px, 2px) rotate(0deg);
  }

  100% {
    transform: translate(1px, -2px) rotate(-1deg);
  }
}

@keyframes launch {
  0% {
    transform: translateX(0px);
  }

  100% {
    transform: translate(-1800px, -300px);
  }
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

.nav-link {
  text-decoration: none;
  color: inherit;
  font-size: x-large;
  padding: 0;
}

.start-btn {
  text-align: center;
  background-color: #ffffff;
  border-radius: 10px;
  font-family: kakao;
  font-size: 1.5rem;
  width: 10%;
  height: auto;
  cursor: pointer;
}

.box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>