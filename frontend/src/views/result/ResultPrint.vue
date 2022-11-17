<template>
  <button class="button-go-home" @click="goHome">Home</button>
  <div ref="printArea" class="wrapper">
    <landscape-form></landscape-form>
    <portrait-form
      :lastwordShow="lastwordShow"
      @lastwordShowFromPortrait="getLastwordShow"
    ></portrait-form>
    <audio id="myaudios" loop autoplay volume="0.2">
      <source src="@/assets/audio/out-of-time.mp3" type="audio/mp3" />
    </audio>
  </div>
  <div class="btns" v-show="lastwordShow">
    <a id="pdf-button-area" @click="printPdf">결과 PDF로 저장하기</a>
    <a id="pdf-image-save" @click="printImage">결과 이미지 저장하기</a>
    <a id="kakaotalk-sharing-url-btn" @click="kakaoShare"
      >친구에게 인생N컷 추천하기</a
    >
  </div>
</template>

<script>
import LandscapeForm from "@/components/result/LandscapeForm.vue";
import PortraitForm from "@/components/result/PortraitForm.vue";
import { useMusicStore } from "@/store/music";
import { useResultStore } from "@/store/result";
import html2pdf from "html2pdf.js";
import { saveAs } from "file-saver";

export default {
  name: "submission-detail",
  data() {
    return {
      from: "",
      output: null,
      lastwordShow: false,
    };
  },
  components: {
    LandscapeForm,
    PortraitForm,
  },
  methods: {
    getLastwordShow(lastwordShow) {
      this.lastwordShow = lastwordShow;
    },
    goHome() {
      this.$router.push("/introfirstpage");
    },
    kakaoShare() {
      window.Kakao.Share.sendScrap({
        requestUrl: "https://k7b105.p.ssafy.io/",
        templateId: 85712,
      });
    },
    async printImage() {
      const el = this.$refs.printArea;
      const options = {
        type: "dataURL",
      };
      this.output = await this.$html2canvas(el, options);
      this.downloadItem(this.output);
    },
    async downloadItem(url) {
      await useResultStore()
        .downloadResultImage(url)
        .then(() => {
          const today = new Date();
          const title =
            "인생N컷_여행일지_" +
            today.getFullYear() +
            "" +
            (today.getMonth() + 1) +
            "" +
            today.getDate() +
            ".png";
          saveAs(useResultStore().imageFile, title);
        });
    },
    printPdf() {
      html2pdf(this.$refs.printArea, {
        margin: 0,
        filename: "document.pdf",
        image: { type: "jpg", quality: 0.95 },
        html2canvas: {
          scrollY: 0,
          scale: 1,
          dpi: 300,
          letterRendering: false,
          ignoreElements: function (element) {
            if (element.id == "pdf-button-area") {
              return true;
            }
          },
        },
        jsPDF: {
          orientation: "portrait",
          unit: "in",
          format: [18, 70],
          compressPDF: true,
        },
      });
    },
  },
  mounted() {
    useMusicStore().isSoundActive();
    this.from = useResultStore().from;
    console.log(this.from);
  },
};
</script>
<style scoped>
font-face {
  font-family: "kakao";
  src: url("@/fonts/KakaoBold.ttf") format("truetype");
}
.button-go-home {
  position: fixed;
  top: 48%;
  border: 0.5px solid white;
  background: transparent;
  color: white;
  border-radius: 10%;
  margin: 1rem 0.5rem;
  padding: 0.25rem 0.25rem 0.5rem;
  font-size: 20px;
  font-family: Exo;
  z-index: 100;
}
.button-go-home:hover {
  background-color: white;
  color: #141414;
}
.wrapper {
  overflow-x: hidden;
}
.btns {
  display: flex;
  flex-direction: column;
  position: fixed;
  width: 2rem;
  z-index: 100;
  top: 10%;
  right: 1%;
  overflow: hidden;
}
.btns:hover {
  width: 15.5rem;
}
#pdf-button-area {
  margin: 0.25rem 0rem;
  padding: 0.25rem 0rem 0.25rem;
  font-size: 0rem;
  color: rgba(0, 0, 0, 0.85);
  border-radius: 12px;
  height: auto;
  padding: 0, 0.5rem;
  background-color: #fee500;
  font: kakao;
  cursor: pointer;
}
.btns:hover > #pdf-button-area {
  margin: 0.25rem;
  padding: 0;
  font-size: 1.125rem;
}
#pdf-button-area::before {
  content: "";
  background-image: url("@/assets/pdf_logo.png");
  margin-left: 0.5rem;
  background-size: 100% 100%;
  display: inline-block;
  width: 1.25rem;
  height: 1.25rem;
  margin-right: 0.25rem;
}
#pdf-image-save {
  margin: 0.25rem 0rem;
  padding: 0.25rem 0rem 0.25rem;
  font-size: 0rem;
  color: rgba(0, 0, 0, 0.85);
  border-radius: 12px;
  height: auto;
  padding: 0, 0.5rem;
  background-color: #fee500;
  font: kakao;
  cursor: pointer;
}
.btns:hover > #pdf-image-save {
  margin: 0.25rem;
  padding: 0;
  font-size: 1.125rem;
}
#pdf-image-save::before {
  content: "";
  background-image: url("@/assets/image_logo.png");
  margin-left: 0.5rem;
  background-size: 100% 100%;
  display: inline-block;
  width: 1.25rem;
  height: 1.25rem;
  margin-right: 0.25rem;
}
#kakaotalk-sharing-url-btn {
  margin: 0.25rem 0rem;
  padding: 0.25rem 0rem 0.25rem;
  font-size: 0rem;
  color: rgba(0, 0, 0, 0.85);
  border-radius: 12px;
  height: auto;
  padding: 0, 0.5rem;
  background-color: #fee500;
  font: kakao;
  cursor: pointer;
}
.btns:hover > #kakaotalk-sharing-url-btn {
  margin: 0.25rem;
  padding: 0;
  font-size: 1.125rem;
}
#kakaotalk-sharing-url-btn::before {
  content: "";
  background-image: url("@/assets/kakao_logo.png");
  margin-left: 0.5rem;
  background-size: 100% 100%;
  display: inline-block;
  width: 1.25rem;
  height: 1.25rem;
  margin-right: 0.25rem;
}
</style>
