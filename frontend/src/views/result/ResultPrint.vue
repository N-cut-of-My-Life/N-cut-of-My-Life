<template>
  <button class="button-go-back" @click="goBack">〈</button>
  <button class="button-go-home" @click="goHome">Intro</button>
  <div ref="pdfarea" class="wrapper">
    <landscape-form></landscape-form>
    <portrait-form></portrait-form>
    <audio id="myaudios" loop autoplay volume="0.2">
      <source src="@/assets/audio/out-of-time.mp3" type="audio/mp3" />
    </audio>
    <button id="pdf-button-area" @click="exportToPDF">다운로드</button>
  </div>
  <div class="kakao-sharing-btn">
    <button id="kakaotalk-sharing-result-btn" @click="kakaoShare">
      <img
        src="https://developers.kakao.com/assets/img/about/logos/kakaotalksharing/kakaotalk_sharing_btn_medium.png"
        alt="카카오톡 공유 보내기 버튼"
      />
      여행일지 카카오톡 공유하기
    </button>
    <button id="kakaotalk-sharing-url-btn" @click="kakaoShare">
      <img
        src="https://developers.kakao.com/assets/img/about/logos/kakaotalksharing/kakaotalk_sharing_btn_medium.png"
        alt="카카오톡 공유 보내기 버튼"
      />
      친구에게도 인생N컷 추천하기
    </button>
  </div>
</template>

<script>
import LandscapeForm from "@/components/result/LandscapeForm.vue";
import PortraitForm from "@/components/result/PortraitForm.vue";
import html2pdf from "html2pdf.js";
import { useMusicStore } from "@/store/music";

export default {
  name: "submission-detail",
  components: {
    LandscapeForm,
    PortraitForm,
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    goHome() {
      this.$router.push("/");
    },
    kakaoShare() {
      window.Kakao.Share.sendScrap({
        requestUrl: "https://k7b105.p.ssafy.io/",
        templateId: 85712,
      });
    },
    exportToPDF() {
      html2pdf(this.$refs.pdfarea, {
        margin: 0,
        filename: "document.pdf",
        image: { type: "jpg", quality: 0.95 },
        html2canvas: {
          scrollY: 0,
          scale: 1,
          dpi: 300,
          letterRendering: true,
          ignoreElements: function (element) {
            if (element.id == "pdf-button-area") {
              return true;
            }
          },
        },
        jsPDF: {
          orientation: "landscape",
          unit: "in",
          format: [18, 8],
          compressPDF: true,
        },
      });
    },
    mounted() {
      useMusicStore().isSoundActive();
    },
  },
};
</script>
<style scoped>
.button-go-back {
  position: fixed;
  border: 0.5px solid white;
  background: transparent;
  color: white;
  border-radius: 10%;
  margin: 28px 8px;
  padding: 0px 10px 2px;
  font-size: 20px;
  font-family: Exo;
  z-index: 100;
}
.button-go-back:hover {
  background-color: white;
  color: #141414;
}
.button-go-home {
  position: absolute;
  top: 88%;
  border: 0.5px solid white;
  background: transparent;
  color: white;
  border-radius: 10%;
  margin: 28px 8px;
  padding: 6px 40px 8px;
  font-size: 20px;
  font-family: Exo;
}
.button-go-home:hover {
  background-color: white;
  color: #141414;
}
.kakao-sharing-btn {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.wrapper {
  overflow-x: hidden;
}
</style>
