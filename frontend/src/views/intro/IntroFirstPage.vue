<template>
  <video muted autoplay loop playbackRate="1.0">
    <source src="@/assets/intro_video.mp4" type="video/mp4">
  </video>
  <div class="nav">
    <span style="margin-right:2%">어서오세요, ㅇㅇㅇ님</span>
    <router-link :to="{ name: 'traveldiary' }" class="nav-link">
      나의 여행 일지
    </router-link>
    <router-link :to="{ name: 'galaxygallery' }" class="nav-link">
      은하갤러리
    </router-link>
    <router-link :to="{ name: 'planetlist' }" class="nav-link">
      행성 목록
    </router-link>
  </div>
  <div class="box">
    <div class="start-btn">
      <router-link :to="{ name: 'introstory1' }" class="nav-link" style="text-decoration: none; color: inherit;">
        여행 시작하기
      </router-link>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
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

.nav {
  color: #ffffff;
  text-align: right;
  padding-right: 5%;
  margin-top: 5%;
}

.nav-link {
  text-decoration: none;
  color: inherit;
  font-size: x-large;
  margin-right: 2%;
}

.start-btn {
  margin-top: 35%;
  text-align: center;
  background-color: #ffffff;
  border-radius: 10px;
  font-family: 'kakao';
  font-size: 1.5rem;
  width: 10%;
  height: auto;
  cursor: pointer;
}

.box {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}
</style>