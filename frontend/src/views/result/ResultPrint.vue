<template>
  <div ref="printArea" class="wrapper">
    <landscape-form></landscape-form>
    <portrait-form
      :lastwordShow="lastwordShow"
      @lastwordShowFromPortrait="getLastwordShow"
    ></portrait-form>
    <audio id="myaudios" loop autoplay volume="0.3">
      <source src="@/assets/audio/mix_result.mp3" type="audio/mp3" />
    </audio>
  </div>
  <div class="btns">
    <a id="button-go-gallery" @click="goGallery">은하갤러리</a>
    <a id="button-go-home" @click="goHome">Home</a>
    <a id="kakaotalk-sharing-url-btn" @click="kakaoShare" v-show="lastwordShow"
      >친구에게 인생N컷 추천하기</a
    >
    <a id="pdf-image-save" v-show="lastwordShow" @click="printImage"
      >결과 이미지 저장하기</a
    >
    <a id="pdf-button-area" v-show="lastwordShow" @click="printPdf"
      >결과 PDF로 저장하기</a
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
    goGallery() {
      this.$router.push("/galaxygallery");
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
  created() {
    this.from = useResultStore().from;
  },
  mounted() {
    useMusicStore().isSoundActive();
  },
};
</script>
<style scoped>
font-face {
  font-family: "kakao";
  src: url("@/fonts/KakaoBold.ttf") format("truetype");
}
.wrapper {
  /* overflow-x: hidden; */
}
.btns {
  display: flex;
  flex-direction: column-reverse;
  position: fixed;
  width: 3rem;
  z-index: 100;
  bottom: 10%;
  right: 1%;
  overflow: hidden;
}
.btns:hover {
  width: 17rem;
}
#button-go-home {
  margin: 0.25rem 0rem;
  padding: 0.75rem 0rem 0.75rem 0.25rem;
  font-size: 0rem;
  color: rgba(0, 0, 0, 0.85);
  border-radius: 12px;
  height: auto;
  background-color: #fee500;
  font: kakao;
  cursor: pointer;
}
.btns:hover > #button-go-home {
  padding: 0.5rem 0 0.5rem 0.25rem;
  font-size: 1.25rem;
  line-height: 1.5rem;
}
#button-go-home:hover {
  background-color: white;
}
#button-go-home::before {
  content: "";
  background-image: url("@/assets/house.png");
  margin-left: 0.5rem;
  background-size: 100% 100%;
  display: inline-block;
  width: 1.5rem;
  height: 1.5rem;
  margin-right: 0.5rem;
}
#button-go-gallery {
  margin: 0.25rem 0rem;
  padding: 0.75rem 0rem 0.75rem 0.25rem;
  font-size: 0rem;
  color: rgba(0, 0, 0, 0.85);
  border-radius: 12px;
  height: auto;
  background-color: #fee500;
  font: kakao;
  cursor: pointer;
}

.btns:hover > #button-go-gallery {
  padding: 0.5rem 0 0.5rem 0.25rem;
  font-size: 1.25rem;
  line-height: 1.5rem;
}
#button-go-gallery:hover {
  background-color: white;
}
#button-go-gallery::before {
  content: "";
  background-image: url("@/assets/galaxy_icon.png");
  margin-left: 0.5rem;
  background-size: 100% 100%;
  display: inline-block;
  width: 1.5rem;
  height: 1.5rem;
  margin-right: 0.5rem;
}
#pdf-button-area {
  margin: 0.25rem 0rem;
  padding: 0.75rem 0rem 0.75rem 0.25rem;
  font-size: 0rem;
  color: rgba(0, 0, 0, 0.85);
  border-radius: 12px;
  height: auto;
  background-color: #fee500;
  font: kakao;
  cursor: pointer;
}
.btns:hover > #pdf-button-area {
  padding: 0.5rem 0 0.5rem 0.25rem;
  font-size: 1.25rem;
  line-height: 1.5rem;
}
#pdf-button-area:hover {
  background-color: white;
}
#pdf-button-area::before {
  content: "";
  background-image: url("@/assets/pdf_logo.png");
  margin-left: 0.5rem;
  background-size: 100% 100%;
  display: inline-block;
  width: 1.5rem;
  height: 1.5rem;
  margin-right: 0.5rem;
}
#pdf-image-save {
  margin: 0.25rem 0rem;
  padding: 0.75rem 0rem 0.75rem 0.25rem;
  font-size: 0rem;
  color: rgba(0, 0, 0, 0.85);
  border-radius: 12px;
  height: auto;
  background-color: #fee500;
  font: kakao;
  cursor: pointer;
}
.btns:hover > #pdf-image-save {
  padding: 0.5rem 0 0.5rem 0.25rem;
  font-size: 1.25rem;
  line-height: 1.5rem;
}
#pdf-image-save:hover {
  background-color: white;
}
#pdf-image-save::before {
  content: "";
  background-image: url("@/assets/image_logo.png");
  margin-left: 0.5rem;
  background-size: 100% 100%;
  display: inline-block;
  width: 1.5rem;
  height: 1.5rem;
  margin-right: 0.5rem;
}
#kakaotalk-sharing-url-btn {
  margin: 0.25rem 0rem;
  padding: 0.75rem 0rem 0.75rem 0.25rem;
  font-size: 0rem;
  color: rgba(0, 0, 0, 0.85);
  border-radius: 12px;
  height: auto;
  background-color: #fee500;
  font: kakao;
  cursor: pointer;
}
.btns:hover > #kakaotalk-sharing-url-btn {
  padding: 0.5rem 0 0.5rem 0.25rem;
  font-size: 1.25rem;
  line-height: 1.5rem;
}
#kakaotalk-sharing-url-btn:hover {
  background-color: white;
}
#kakaotalk-sharing-url-btn::before {
  content: "";
  background-image: url("@/assets/kakao_logo.png");
  margin-left: 0.5rem;
  background-size: 100% 100%;
  display: inline-block;
  width: 1.5rem;
  height: 1.5rem;
  margin-right: 0.5rem;
}
</style>
<style>
body::-webkit-scrollbar {
  width: 0.8vw;
}

body::-webkit-scrollbar-track {
  background-color: transparent;
}

body::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background-color: #d2daff;
}

body::-webkit-scrollbar-button {
  width: 0;
  height: 0;
}
</style>
