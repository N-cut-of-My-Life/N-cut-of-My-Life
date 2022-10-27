<template>
  <video muted autoplay loop playbackRate="0.7">
    <source src="@/assets/space.mp4" type="video/mp4">
  </video>
  <div class="menu-box">
    <div class="text-box">
      <div class="title">인생 N컷</div>
      <div class="subtitle">당신을 기록하는 우주여행</div>
      <div class="title-ment">우주선을 타고 추억을 떠올려보아요!</div>
    </div>

    <div class="login-btn">
      <router-link :to="{ name: 'introfirstpage' }" class="nav-link" style="text-decoration: none; color: inherit;">
        카카오 로그인
      </router-link>
    </div>

  </div>
</template>

<script setup>
</script>

<style scoped>
@font-face {
  font-family: 'kakao';
  src: url('@/fonts/KakaoBold.ttf') format('truetype');
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
  text-align: center;
}

.title {
  color: aliceblue;
  font-size: 5rem;
}

.subtitle {
  color: #E2636A;
  font-size: 2rem;
  margin-top: 8%;
}

.title-ment {
  color: aliceblue;
  font-size: 1.2rem;
  margin-top: 4%;
}

.login-btn {
  text-align: center;
  background-color: #FEF01B;
  border-radius: 10px;
  font-family: 'kakao';
  font-size: 1.5rem;
  width: 10%;
  height: auto;
  padding: 0 10px 0 10px;
  cursor: pointer;
}

.login-btn::before {
  content: "";
  background-image: url("@/assets/kakao_logo.png");
  background-size: 100% 100%;
  display: inline-block;
  width: 1rem;
  height: 1rem;
}

.menu-box {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  height: 69vh;
  padding-top: 7%;
  padding-bottom: 7%;
  /* 테스트 */
}
</style>