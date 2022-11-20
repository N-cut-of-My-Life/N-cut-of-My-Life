<template>
  <section class="timeline">
    <ol>
      <li :style="resultNum" v-if="isAnswered[1]">
        <div>
          <h5>하하호호행성</h5>
          <p>가장 행복했던 때가 언제였는지 적어줘!</p>
        </div>
        <img
          class="planet"
          src="@/assets/planet/haho.png"
          style="transform: scale(0.65); width: 12rem"
        />
      </li>
      <li :style="resultNum" v-if="isAnswered[8]">
        <div>
          <h5>넌너무소중행성</h5>
          <p>너에게 가장 소중한 사람은 누구니?</p>
        </div>
        <img
          class="planet"
          src="@/assets/planet/dearyou.png"
          style="transform: scale(0.8)"
        />
      </li>
      <li :style="resultNum" v-if="isAnswered[2]">
        <div>
          <h5>훌쩍훌쩍행성</h5>
          <p>너의 가장 슬펐던 기억을 적어주겠니?</p>
        </div>
        <img
          class="planet"
          src="@/assets/planet/weep.png"
          style="transform: scale(1)"
        />
      </li>
      <li :style="resultNum" v-if="isAnswered[3]">
        <div>
          <h5>반짝반짝행성</h5>
          <p>가장 소중히 여기는 물건이 뭔지 적어주겠니?</p>
        </div>
        <img class="planet" src="@/assets/planet/blink.png" />
      </li>
      <li :style="resultNum" v-if="isAnswered[4]">
        <div>
          <h5>이루지못행성</h5>
          <p>이루지 못한꿈은 무엇이 있니? 잠시 맡겨두어도 좋단다!</p>
        </div>
        <img
          class="planet"
          src="@/assets/planet/didnt.png"
          style="transform: scale(0.5)"
        />
      </li>
      <li :style="resultNum" v-if="isAnswered[5]">
        <div>
          <h5>괜히글행성</h5>
          <p>후회되는 일은 여기 있는 후회쓰레기통에 버리고 가렴</p>
        </div>
        <img
          class="planet"
          src="@/assets/planet/forget.png"
          style="transform: scale(0.7)"
        />
      </li>
      <li :style="resultNum" v-if="isAnswered[6]">
        <div>
          <h5>둠칫둠칫행성</h5>
          <p>네가 가장 좋아하는 음악은 무엇이니?</p>
        </div>
        <img class="planet" src="@\assets\planet\music.png" />
      </li>
      <li :style="resultNum">
        <div class="genie" v-if="isAnswered[7]">
          <h5>지니 행성</h5>
          <p>너의 소원은 무엇이니?무엇이든 좋으니 적어볼래?</p>
        </div>
        <img
          src="@/assets/space.svg"
          style="position: absolute; left: 85%; top: -10rem; z-index: 20"
        />
        <img
          src="@/assets/genie.svg"
          style="position: absolute; top: 3rem; left: 50%"
          v-if="isAnswered[7]"
        />
      </li>
      <li :style="resultNum"></li>
    </ol>
  </section>
</template>

<script>
import { useResultStore } from "@/store/result";
export default {
  data() {
    return {
      resultNumber: 0,
      isAnswered: [],
      isGenie: 0,
    };
  },
  mounted() {
    this.isAnswered = useResultStore().isInResult;
    this.resultNumber = useResultStore().resultArticle.length;
    if (useResultStore().isInResult[7]) {
      this.isGenie = 1;
    }
    console.log(this.resultNumber);
  },
  computed: {
    resultNum() {
      return {
        "--width": (1 / (this.resultNumber - this.isGenie - 1)) * 82 + "%",
      };
    },
  },
};
</script>

<style scoped lang="scss">
.planet {
  position: absolute;
  width: 12rem;
  height: 12rem;
  border-radius: 50%;
  left: 50%;
}

.timeline {
  background: black;
  color: #fff;
  height: 100vh;
  overflow: hidden;
}

.timeline ol {
  font-size: 0;
  width: 100vw;
  height: 100%;
  padding: 10rem 0;
}

.timeline ol li {
  position: relative;
  display: inline-block;
  list-style-type: none;
  top: 50%;
  height: 1rem;
  background: #fff;
}

.timeline ol li:first-child {
  width: 8%;
}

.timeline ol li:not(:first-child):not(:last-child) {
  width: var(--width);
}

.timeline ol li:last-child {
  position: relative;
  display: inline-block;
  list-style-type: none;
  top: 50%;
  width: 0%;
  height: 1rem;
  background: #fff;
}

.timeline ol li:not(:last-child)::after {
  content: "";
  position: absolute;
  top: 50%;
  left: 100%;
  width: 2rem;
  height: 2rem;
  transform: translateY(-50%);
  border-radius: 50%;
  background: linear-gradient(to right, #f3f3f3, #b5b5b5);
  z-index: 1;
}

.timeline ol li:first-child div {
  position: absolute;
  left: 50%;
  width: 10rem;
  font-size: 1rem;
  white-space: normal;
  color: black;
  background: transparent;
  text-align: center;
  color: white;
}

.timeline ol li:not(:first-child) div {
  position: absolute;
  left: 50%;
  width: 110%;
  font-size: 1rem;
  white-space: normal;
  color: black;
  background: transparent;
  text-align: center;
  color: white;
}

.timeline ol li div::before {
  content: "";
  position: absolute;
  width: 0.5rem;
  border-style: solid;
  border-block-width: 4rem;
}

.timeline ol li:nth-child(odd) div {
  top: 14rem;
}

.timeline ol li:nth-child(odd) div::before {
  top: -50%;
  transform: translateY(-100%);
}

.timeline ol li:nth-child(even) div {
  top: -14rem;
  transform: translateY(-100%);
}

.timeline ol li:nth-child(even) div::before {
  top: 150%;
}

.timeline ol li:first-child img {
  bottom: 4rem;
  left: 50%;
}

.timeline ol li:not(:first-child):nth-child(odd) img {
  bottom: 5rem;
  left: 50%;
}

.timeline ol li:nth-child(even) img {
  top: 4rem;
  left: 50%;
}

.timeline ol li div h5 {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
  text-indent: 0.2rem;
  text-align: start;
}

.timeline ol li div p {
  word-break: keep-all;
  text-align: start;
}

.genie {
  top: 20rem !important;
  left: 80% !important;
  z-index: 1;
}
.genie::before {
  border-block-width: 0px !important;
}
</style>
