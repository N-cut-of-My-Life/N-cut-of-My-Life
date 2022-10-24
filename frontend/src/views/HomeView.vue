<template>
  <video muted autoplay loop playbackRate="1.0">
      <source src="../assets/space.mp4" type="video/mp4">
  </video>

  <div class="text-box">
    <div class="title">인생 N컷</div>
    <div class="subtitle">당신을 기록하는 우주여행</div>
    <div class="title-ment">우주선을 타고 추억을 떠올려봐요!</div>
  </div>

  <div class="login-btn">
    카카오 로그인
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
.text-box {
  position: absolute;
  top: 10%;
  left: 38%;
}
.title {
  color: aliceblue;
  font-size: 5rem;
  text-align: center;
}
.subtitle {
  color: #E2636A;
  font-size: 2rem;
  text-align: center;
  margin-top: 3%;
}
.title-ment {
  color: aliceblue;
  font-size: 1rem;
  text-align: center;
  margin-top: 3%;
}
.login-btn {
  position: absolute;
  bottom: 10%;
  left: 47%;
  background-color: #FEF01B;
  border-radius: 10px;
  text-align: end;
  font-family: 'kakao';
}
</style>