<template>
  <!-- <audio loop autoplay volume="0.5">
    <source src="@/assets/audio/daylight.mp3" type="audio/mp3" />
  </audio> -->
  <video muted autoplay loop playbackRate="0.5">
    <source src="@/assets/space.mp4" type="video/mp4" />
  </video>
  <div class="menu-box">
    <div class="text-box">
      <span class="title">인생 </span><span class="n">N</span
      ><span class="title">컷</span>
      <div class="subtitle">당신을 기록하는 우주여행</div>
      <div class="title-ment">
        <div class="wave">
          <span style="--i: 1">우</span>
          <span style="--i: 2">주</span>
          <span style="--i: 3">선</span>
          <span style="--i: 4">을</span>
          <span style="--i: 5">&nbsp;</span>
          <span style="--i: 6">타</span>
          <span style="--i: 7">고</span>
          <span style="--i: 8">&nbsp;</span>
          <span style="--i: 9">추</span>
          <span style="--i: 10">억</span>
          <span style="--i: 11">을</span>
          <span style="--i: 12">&nbsp;</span>
          <span style="--i: 13">떠</span>
          <span style="--i: 14">올</span>
          <span style="--i: 15">려</span>
          <span style="--i: 16">보</span>
          <span style="--i: 17">아</span>
          <span style="--i: 18">요</span>
          <span style="--i: 19">!</span>
        </div>
      </div>
    </div>

    <div class="login-btn">
      <a
        @click="login()"
        class="nav-link"
        style="text-decoration: none; color: inherit"
      >
        &nbsp;카카오 로그인
      </a>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    login() {
      const params = {
        redirectUri: process.env.VUE_APP_KAKAO_REDIRECT_URI,
      };
      window.Kakao.Auth.authorize(params);
    },
  },
};
</script>

<style scoped>
@font-face {
  font-family: "kakao";
  src: url("@/fonts/KakaoBold.ttf") format("truetype");
}

@font-face {
  font-family: "yun";
  src: url("@/fonts/YunTaemin.ttf") format("truetype");
}

@font-face {
  font-family: "ONE-Mobile-POP";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/ONE-Mobile-POP.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "MaplestoryOTFBold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/MaplestoryOTFBold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "establishRetrosansOTF";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2112@1.0/establishRetrosansOTF.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "SDSamliphopangche_Outline";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
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
  animation: wave 4s infinite;
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
  color: #ebeaea;
  font-size: 5.7rem;
  font-family: SDSamliphopangche_Outline;
}

.n {
  color: #a3c7d6;
  font-size: 5.7rem;
  font-family: SDSamliphopangche_Outline;
}

.subtitle {
  color: #e2636a;
  font-size: 2rem;
  margin-top: 8%;
  font-family: MaplestoryOTFBold;
}

.title-ment {
  color: #ffffff;
  font-size: 1.43rem;
  margin-top: 4%;
  font-family: MaplestoryOTFBold;
}

.login-btn {
  text-align: center;
  color: rgba(0, 0, 0, 0.85);
  background-color: #fee500;
  border-radius: 12px;
  font-family: kakao;
  font-size: 1.5rem;
  width: 12%;
  height: auto;
  padding: 0 10px 0 10px;
  cursor: pointer;
}

.nav-link::before {
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
  height: 100vh;
  padding-top: 7%;
  padding-bottom: 7%;
  /* 테스트 */
}
</style>
