<template>
  <div ref="pdfarea">
    <section class="result-print">
      <button id="pdf-button-area" @click="exportToPDF">다운로드</button>

      <ol>
        <li>
          <div style="left: 0rem">
            <p class="ttl">하하호호행성</p>
            <p class="ctnt">가장 행복했던 때가<br />언제였는지 적어줘!</p>
          </div>
          <img
            class="planet"
            src="@/assets/planet/haho.png"
            style="
              width: 12rem;
              height: 12rem;
              border-radius: 50%;
              transform: scale(0.65);
            "
          />
        </li>
        <li>
          <div>
            <p class="ttl">넌너무소중행성</p>
            <p class="ctnt">
              너에게<br />
              가장 소중한 사람은 누구니?
            </p>
          </div>
          <img
            class="planet"
            src="@/assets/planet/dearyou.png"
            style="
              width: 12rem;
              height: 12rem;
              border-radius: 50%;
              transform: scale(0.8);
            "
          />
        </li>
        <li>
          <div>
            <p class="ttl">훌쩍훌쩍행성</p>
            <p class="ctnt">
              너의 가장 슬펐던 기억을<br />
              적어주겠니?
            </p>
          </div>
          <img
            class="planet"
            src="@/assets/planet/weep.png"
            style="
              width: 12rem;
              height: 12rem;
              border-radius: 50%;
              transform: scale(1);
            "
          />
        </li>
        <li>
          <div>
            <p class="ttl">반짝반짝행성</p>
            <p class="ctnt">
              가장 소중히 여기는 물건이<br />
              뭔지 적어주겠니?
            </p>
          </div>
          <img class="planet" src="@/assets/planet/blink.png" />
        </li>
        <li>
          <div>
            <p class="ttl">이루지못행성</p>
            <p class="ctnt">
              이루지 못한꿈은 무엇이 있니?<br />
              잠시 맡겨두어도 좋단다!
            </p>
          </div>
          <img
            class="planet"
            src="@/assets/planet/didnt.png"
            style="
              width: 12rem;
              height: 12rem;
              border-radius: 50%;
              transform: scale(0.5);
            "
          />
        </li>
        <li>
          <div>
            <p class="ttl">괜히글행성</p>
            <p class="ctnt">
              후회되는 일은 여기 있는<br />
              후회쓰레기통에 버리고 가렴
            </p>
          </div>
          <img
            class="planet"
            src="@/assets/planet/forget.png"
            style="
              width: 12rem;
              height: 12rem;
              border-radius: 50%;
              transform: scale(0.7);
            "
          />
        </li>
        <li>
          <div>
            <p class="ttl">둠칫둠칫행성</p>
            <p class="ctnt">
              네가 가장 좋아하는 음악은<br />
              무엇이니?
            </p>
          </div>
          <img
            class="planet"
            src="@\assets\planet\music.png"
            style="width: 12rem; height: 12rem"
          />
        </li>
        <li>
          <div class="genie" style="left: -1rem; top: 17rem; z-index: 22">
            <p class="ttl">지니행성</p>
            <p class="ctnt">
              너의 소원은 무엇이니?<br />무엇이든 좋으니 적어볼래?
            </p>
          </div>
          <img
            src="@/assets/space.svg"
            style="position: absolute; top: -10rem"
          />
          <img
            src="@/assets/genie.svg"
            style="position: absolute; top: 10rem; left: 4rem"
          />
        </li>
      </ol>
    </section>
  </div>
</template>

<script>
import html2pdf from "html2pdf.js";

export default {
  name: "submission-detail",
  methods: {
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
  },
};
</script>
<style scoped>
.genie::before {
  border-block-width: 0px !important;
}

.planet {
  position: absolute;
}

.result-print ol li:nth-child(odd) img {
  top: 10vh;
  left: 50%;
}

.result-print ol li:nth-child(even) img {
  left: 50%;
  bottom: 10vh;
}

.result-print {
  display: -webkit-flex;
  display: flex;
  height: 100vh;
  white-space: nowrap;
  overflow: hidden;
  background: black;
  -webkit-align-items: center;
  align-items: center;
  -webkit-justify-content: center;
  justify-content: center;
}
.result-print ol {
  -webkit-flex: 1;
  flex: 1;
  font-size: 0;
  width: 100vw;
  padding: 15rem 0;
  transition: all 1s;
}
.result-print ol li {
  position: relative;
  display: inline-block;
  list-style-type: none;
  width: 11.2%;
  height: 0.5rem;
  background: #fff;
}
.result-print ol li:first-child {
  width: 8%;
}
.result-print ol li:last-child {
  width: 12%;
}
.result-print ol li:not(:first-child) {
  margin-left: 1rem;
}
.result-print ol li:not(:last-child)::after {
  content: "";
  position: absolute;
  top: 50%;
  left: calc(100% - 0.5rem);
  bottom: 0;
  width: 2rem;
  height: 2rem;
  transform: translateY(-50%);
  border-radius: 50%;
  background: linear-gradient(to right, #f3f3f3, #b5b5b5);
  z-index: 20;
}
.result-print ol li div {
  position: absolute;
  left: calc(50% - 2.5rem);
  width: 16rem;
  padding: 1rem;
  padding-left: 5rem;
  font-size: 1rem;
  white-space: nowrap;
  color: white;
  background: transparent;
}

.result-print ol li:first-child div::before {
  content: "";
  position: absolute;
  top: 100%;
  left: 8.2rem;
  width: 0;
  height: 0;
  border-style: solid;
  border-block-width: 4rem;
}

.result-print ol li:not(:first-child) div::before {
  content: "";
  position: absolute;
  top: 100%;
  left: 8.2rem;
  width: 0;
  height: 0;
  border-style: solid;
  border-block-width: 4rem;
}

.result-print ol li:nth-child(odd) div {
  top: -13.5rem;
  transform: translateY(-100%);
}

.result-print ol li:nth-child(odd) div::before {
  top: 120%;
}

.result-print ol li:nth-child(even) div {
  top: calc(100% + 16rem);
}

.result-print ol li:nth-child(even) div::before {
  top: -144%;
}
.ttl {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
  text-indent: 0.1rem;
}
.ctnt {
  overflow-wrap: normal;
}
</style>
