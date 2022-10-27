<template>
  <video muted autoplay loop playbackRate="0.7">
    <source src="@/assets/intro_video.mp4" type="video/mp4">
  </video>
  <h1>
    안녕하세요<br />
    추억을 기록하는 우주여행사<br />
    인생N컷입니다.
  </h1>
  <div class="jump">
    <b-button class="button" size="sm">
      <router-link :to="{ name: 'introfirstpage' }" class="nav-link">
        뒤로
      </router-link>
    </b-button>
    &nbsp;
    <b-button class="button" size="sm">
      <router-link :to="{ name: 'introstory2' }" class="nav-link">
        다음
      </router-link>
    </b-button>
  </div>
</template>

<script setup>
</script>

<style scoped>
@font-face {
  font-family: 'kakao';
  src: url('@/fonts/KakaoBold.ttf') format('truetype');
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
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  height: 25%;
  margin: auto;
  text-align: center;
  color: aliceblue;
  font-size: 3rem;
}
/* .pre {
  position: absolute;
  left: 5%;
  bottom: 5%;
  margin: auto;
  text-align: right;
  color: aliceblue;
  font-size: 3rem;
} */
.jump {
  position: absolute;
  right: 5%;
  bottom: 5%;
  margin: auto;
  text-align: right;
  font-size: 3rem;
}
.button{
  background-color: #f9f9f9;
  color:#141414;
}

</style>