<template>
  <div
    data-aos="fade-right"
    data-aos-offset="50"
    data-aos-easing="ease-in-sine"
    class="timeline haha"
    v-if="isAnswered[1]"
  >
    <ul>
      <li :style="[hahaShow ? { 'background-color': '#feb139' } : {}]">
        <span>하하호호행성</span>
        <div class="content" @mouseover="hahaShowPtag">
          <h3>가장 행복했던 때가 언제였는지 적어줘!</h3>
          <p v-show="hahaShow">
            {{ resultContents[1].contents }}
          </p>
        </div>
      </li>
    </ul>
  </div>
  <div
    data-aos="fade-left"
    data-aos-offset="50"
    data-aos-easing="ease-in-sine"
    class="timeline precious"
    v-if="isAnswered[2]"
  >
    <ul>
      <li
        :style="[
          preciousShow
            ? {
                'background-color': '#f6b8b8',
              }
            : {},
        ]"
      >
        <span>넌너무소중행성</span>
        <div class="content" @mouseover="preciousShowPtag">
          <h3>너에게 가장 소중한 사람은 누구니?</h3>
          <p v-show="preciousShow">
            {{ resultContents[2].contents }}
          </p>
        </div>
      </li>
    </ul>
  </div>
  <div
    data-aos="fade-right"
    data-aos-offset="50"
    data-aos-easing="ease-in-sine"
    class="timeline sad"
    v-if="isAnswered[3]"
  >
    <ul>
      <li :style="[sadShow ? { 'background-color': '#0a1931' } : {}]">
        <span>훌쩍훌쩍행성 </span>
        <div class="content" @mouseover="sadShowPtag">
          <h3>너의 가장 슬펐던 기억을 적어주겠니?</h3>
          <p v-show="sadShow">
            {{ resultContents[3].contents }}
          </p>
        </div>
      </li>
    </ul>
  </div>
  <div
    data-aos="fade-left"
    data-aos-offset="50"
    data-aos-easing="ease-in-sine"
    class="timeline treasure"
    v-if="isAnswered[4]"
  >
    <ul>
      <li :style="[treasureShow ? { 'background-color': '#ff7a00' } : {}]">
        <span>반짝반짝행성 </span>
        <div class="content" @mouseover="treasureShowPtag">
          <h3>가장 소중히 여기는 물건이 뭔지 적어주겠니?</h3>
          <p v-show="treasureShow">
            {{ resultContents[4].contents }}
          </p>
        </div>
      </li>
    </ul>
  </div>
  <div
    data-aos="fade-right"
    data-aos-offset="50"
    data-aos-easing="ease-in-sine"
    class="timeline dream"
    v-if="isAnswered[5]"
  >
    <ul>
      <li :style="[dreamShow ? { 'background-color': '#c56183' } : {}]">
        <span>이루지못행성 </span>
        <div class="content" @mouseover="dreamShowPtag">
          <h3>이루지 못한꿈은 무엇이 있니? 잠시 맡겨두어도 좋단다!</h3>
          <p v-show="dreamShow">
            {{ resultContents[5].contents }}
          </p>
        </div>
      </li>
    </ul>
  </div>
  <div
    data-aos="fade-left"
    data-aos-offset="50"
    data-aos-easing="ease-in-sine"
    class="timeline regret"
    v-if="isAnswered[6]"
  >
    <ul>
      <li :style="[regretShow ? { 'background-color': '#6d9886' } : {}]">
        <span>괜히글행성 </span>
        <div class="content" @mouseover="regretShowPtag">
          <h3>후회되는 일은 여기 있는 후회쓰레기통에 버리고 가렴</h3>
          <p v-show="regretShow">
            {{ resultContents[6].contents }}
          </p>
        </div>
      </li>
    </ul>
  </div>
  <div
    data-aos="fade-right"
    data-aos-offset="50"
    data-aos-easing="ease-in-sine"
    class="timeline dumchit"
    v-if="isAnswered[7]"
  >
    <ul>
      <li :style="[dumchitShow ? { 'background-color': '#f5c7a9' } : {}]">
        <span>둠칫둠칫행성</span>
        <div class="content" @mouseover="dumchitShowPtag">
          <h3>네가 가장 좋아하는 음악은 무엇이니?</h3>
          <p v-show="dumchitShow">
            {{ resultContents[7].contents }}
          </p>
        </div>
      </li>
    </ul>
  </div>
  <div
    data-aos="fade-left"
    data-aos-offset="50"
    data-aos-easing="ease-in-sine"
    class="timeline genie"
    v-if="isAnswered[8]"
  >
    <ul>
      <li :style="[genieShow ? { 'background-color': '#b270a2' } : {}]">
        <span>지니행성</span>
        <div class="content" @mouseover="genieShowPtag">
          <h3>너의 소원은 무엇이니? 무엇이든 좋으니 적어볼래?</h3>
          <p v-show="genieShow">
            {{ resultContents[8].contents }}
          </p>
        </div>
      </li>
    </ul>
  </div>
  <div
    data-aos="fade-down"
    data-aos-offset="50"
    data-aos-easing="ease-in-sine"
    class="timeline lastword"
    v-if="isAnswered[9]"
  >
    <ul>
      <li :style="[lastwordShow ? { 'background-color': '#d4f6cc' } : {}]">
        <span>나에게 하고 싶은 한마디</span>
        <div class="content" @mouseover="lastwordShowPtag">
          <h3>너에게 남기고 싶은 말이 있니?</h3>
          <p v-show="lastwordShow">
            {{ resultContents[9].contents }}
          </p>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { useResultStore } from "@/store/result";
export default {
  data() {
    return {
      isAnswered: [],
      resultContents: [],
      hahaShow: false,
      preciousShow: false,
      sadShow: false,
      treasureShow: false,
      dreamShow: false,
      regretShow: false,
      dumchitShow: false,
      genieShow: false,
      lastwordShow: false,
    };
  },
  mounted() {
    this.isAnswered = useResultStore().isInResult;
    console.log(this.isAnswered);
    this.resultContents = useResultStore().resultContents;
    console.log(this.resultContents);
  },
  methods: {
    hahaShowPtag() {
      if (!this.hahaShow) {
        this.hahaShow = !this.hahaShow;
      }
    },
    preciousShowPtag() {
      if (!this.preciousShow) {
        this.preciousShow = !this.preciousShow;
      }
    },
    sadShowPtag() {
      if (!this.sadShow) {
        this.sadShow = !this.sadShow;
      }
    },
    treasureShowPtag() {
      if (!this.treasureShow) {
        this.treasureShow = !this.treasureShow;
      }
    },
    dreamShowPtag() {
      if (!this.dreamShow) {
        this.dreamShow = !this.dreamShow;
      }
    },
    regretShowPtag() {
      if (!this.regretShow) {
        this.regretShow = !this.regretShow;
      }
    },
    dumchitShowPtag() {
      if (!this.dumchitShow) {
        this.dumchitShow = !this.dumchitShow;
      }
    },
    genieShowPtag() {
      if (!this.genieShow) {
        this.genieShow = !this.genieShow;
      }
    },
    lastwordShowPtag() {
      if (!this.lastwordShow) {
        this.lastwordShow = !this.lastwordShow;
        this.$emit("lastwordShowFromPortrait", this.lastwordShow);
      }
    },
  },
};
</script>

<style scoped>
.timeline {
  width: 100vw;
  height: 80vh;
  margin: -0.1rem;
  overflow: hidden;
}
.haha {
  background-image: url("@/assets/PlanetBackground/haha.svg");
  background-position: left bottom;
  background-repeat: no-repeat;
  background-size: 100%;
  background-color: rgba(255, 255, 255, 255);
}
.haha ul li span {
  display: inline-block;
  background-color: #ce49bf;
  color: #feb139;
  border-radius: 1.5rem;
  padding: 0.25rem 0.5rem;
  font-size: 1rem;
  text-align: center;
}
.haha ul li .content h3 {
  color: #ce49bf;
  font-size: 1.25rem;
  padding-top: 0.5rem;
}
.haha ul li .content p {
  width: 50vw;
  height: 80vh;
  padding: 0;
  font-size: 1rem;
  color: white;
}
/* .haha ul li:hover {
  background-color: #feb139;
} */
.precious {
  background-image: url("@/assets/PlanetBackground/precious.svg");
  background-position: left bottom;
  background-repeat: no-repeat;
  background-size: 100%;
  background-color: rgba(255, 255, 255, 255);
}
.precious ul li span {
  display: inline-block;
  background-color: #ffed99;
  color: #f6b8b8;
  border-radius: 1.5rem;
  padding: 0.25rem 0.5rem;
  font-size: 1rem;
  text-align: center;
}
.precious ul li .content h3 {
  color: #ffed99;
  font-size: 1.25rem;
  padding-top: 0.5rem;
}
.precious ul li .content p {
  width: auto;
  height: 80vh;
  padding: 0;
  font-size: 1rem;
  color: white;
}
/* .precious ul li:hover {
  background-color: #f6b8b8;
} */
.sad {
  background-image: url("@/assets/PlanetBackground/sad_2.svg");
  background-position: right bottom;
  background-repeat: no-repeat;
  background-size: 100%;
  background-color: rgba(255, 255, 255, 255);
}
.sad ul li span {
  display: inline-block;
  background-color: #ffc947;
  color: #0a1931;
  border-radius: 1.5rem;
  padding: 0.25rem 0.5rem;
  font-size: 1rem;
  text-align: center;
}
.sad ul li .content h3 {
  color: #ffc947;
  font-size: 1.25rem;
  padding-top: 0.5rem;
}
.sad ul li .content p {
  width: 50vw;
  height: 80vh;
  padding: 0;
  font-size: 1rem;
  color: white;
}
.sad ul li:hover {
  background-color: #0a1931;
}
.treasure {
  background-image: url("@/assets/PlanetBackground/treasure.svg");
  background-position: left bottom;
  background-repeat: no-repeat;
  background-size: 100%;
  background-color: rgba(255, 255, 255, 255);
}
.treasure ul li span {
  display: inline-block;
  background-color: #ffefcf;
  color: #ff7a00;
  border-radius: 1.5rem;
  padding: 0.25rem 0.5rem;
  font-size: 1rem;
  text-align: center;
}
.treasure ul li .content h3 {
  color: #ffefcf;
  font-size: 1.25rem;
  padding-top: 0.5rem;
}
.treasure ul li .content p {
  width: 50vw;
  height: 80vh;
  padding: 0;
  font-size: 1rem;
  color: white;
}
.treasure ul li:hover {
  background-color: #ff7a00;
}
.dream {
  background-image: url("@/assets/PlanetBackground/dream_2.svg");
  background-position: right bottom;
  background-repeat: no-repeat;
  background-size: 100%;
  background-color: rgba(255, 255, 255, 255);
}
.dream ul li span {
  display: inline-block;
  background-color: #fadcaa;
  color: #c56183;
  border-radius: 1.5rem;
  padding: 0.25rem 0.5rem;
  font-size: 1rem;
  text-align: center;
}
.dream ul li .content h3 {
  color: #fadcaa;
  font-size: 1.25rem;
  padding-top: 0.5rem;
}
.dream ul li .content p {
  width: 50vw;
  height: 80vh;
  padding: 0;
  font-size: 1rem;
  color: #fb7b6b;
}
.dream ul li:hover {
  background-color: #c56183;
}
.regret {
  background-image: url("@/assets/PlanetBackground/regret.svg");
  background-position: left bottom;
  background-repeat: no-repeat;
  background-size: 100%;
  background-color: rgba(255, 255, 255, 255);
}
.regret ul li span {
  display: inline-block;
  background-color: #f2e7d5;
  color: #6d9886;
  border-radius: 1.5rem;
  padding: 0.25rem 0.5rem;
  font-size: 1rem;
  text-align: center;
}
.regret ul li .content h3 {
  color: #f2e7d5;
  font-size: 1.25rem;
  padding-top: 0.5rem;
}
.regret ul li .content p {
  width: 50vw;
  height: 80vh;
  padding: 0;
  font-size: 1rem;
  color: white;
}
.regret ul li:hover {
  background-color: #6d9886;
}
.dumchit {
  background-image: url("@/assets/PlanetBackground/dumchit_2.svg");
  background-position: right bottom;
  background-repeat: no-repeat;
  background-size: 100%;
  background-color: rgba(255, 255, 255, 255);
}
.dumchit ul li span {
  display: inline-block;
  background-color: #647e68;
  color: #f5c7a9;
  border-radius: 1.5rem;
  padding: 0.25rem 0.5rem;
  font-size: 1rem;
  text-align: center;
}
.dumchit ul li .content h3 {
  color: #647e68;
  font-size: 1.25rem;
  padding-top: 0.5rem;
}
.dumchit ul li .content p {
  width: 50vw;
  height: 80vh;
  padding: 0;
  font-size: 1rem;
  color: white;
}
.dumchit ul li:hover {
  background-color: #f5c7a9;
}
.genie {
  background-image: url("@/assets/PlanetBackground/genie.svg");
  background-position: left bottom;
  background-repeat: no-repeat;
  background-size: 100%;
  background-color: rgba(255, 255, 255, 255);
}
.genie ul li span {
  display: inline-block;
  background-color: #fce2db;
  color: #b270a2;
  border-radius: 1.5rem;
  padding: 0.25rem 0.5rem;
  font-size: 1rem;
  text-align: center;
}
.genie ul li .content h3 {
  color: #fce2db;
  font-size: 1.25rem;
  padding-top: 0.5rem;
}
.genie ul li .content p {
  width: 50vw;
  height: 80vh;
  padding: 0;
  font-size: 1rem;
  color: white;
}
.genie ul li:hover {
  background-color: #b270a2;
}
.lastword {
  background-image: url("@/assets/PlanetBackground/lastword.png");
  background-position: right bottom;
  background-repeat: no-repeat;
  background-size: 100%;
  background-color: rgba(255, 255, 255, 255);
}
.lastword ul li span {
  display: inline-block;
  background-color: #ef5b0c;
  color: #d4f6cc;
  border-radius: 1.5rem;
  padding: 0.25rem 0.5rem;
  font-size: 1rem;
  text-align: center;
}
.lastword ul li .content h3 {
  color: #ef5b0c;
  font-size: 1.25rem;
  padding-top: 0.5rem;
}
.lastword ul li .content p {
  width: 50vw;
  height: 80vh;
  padding: 0;
  font-size: 1rem;
  color: #100f0f;
}
.lastword ul li:hover {
  background-color: #d4f6cc;
}

.timeline ul {
  list-style-type: none;
  border-left: 0.25rem solid #094a68;
  padding: 6rem 3rem 6rem 0.25rem;
  margin-left: 20rem;
  margin-right: 20rem;
  height: 100vh;
}
.timeline ul li {
  padding: 1.5rem 1.5rem;
  position: relative;
  cursor: pointer;
  transition: 0.5s;
}
.timeline ul li .content {
  height: 40vh;
  display: absolute;
  overflow: hidden;
}

/* .timeline ul li .content:hover p {
  opacity: 1;
} */
.timeline ul li:before {
  position: absolute;
  content: "";
  width: 1rem;
  height: 1rem;
  background-color: #34ace0;
  border-radius: 50%;
  left: -0.9rem;
  top: 1.75rem;
  transition: 0.5s;
}
.timeline ul li:hover:before {
  background-color: #0f0;
  box-shadow: 0 0 0.75rem 0.125rem #0f0;
}
@media (max-width: 300px) {
  .timeline {
    width: 100%;
    padding: 0;
  }
  .timeline ul li .content h3 {
    color: #34ace0;
    font-size: 1rem;
  }
}

/* .timeline ul li span {
      display: inline-block;
      background-color: #38e54d;
      border-radius: 1.5rem;
      padding: 0.25rem 0.5rem;
      font-size: 1rem;
      text-align: center;
    }
    .timeline ul li .content h3 {
      color: #38e54d;
      font-size: 1.25rem;
      padding-top: 0.5rem;
    }
    .timeline ul li .content p {
      width: 50vw;
      height: 80vh;
      padding: 0;
      font-size: 1rem;
      color: white;
      opacity: 0;
    } */
/* .timeline ul li:hover {
      background-color: #071f2a;
      } */
/* .timeline:nth-child(2) {
      background-image: url("@/assets/PlanetBackground/haha.svg");
      background-position: left bottom;
      background-repeat: no-repeat;
      background-size: 100%;
      background-color: rgba(255, 255, 255, 255);
    }
    .timeline:nth-child(3) {
      background-image: url("@/assets/PlanetBackground/precious.svg");
      background-position: left bottom;
      background-repeat: no-repeat;
      background-size: 100%;
      background-color: rgba(255, 255, 255, 255);
    }
    .timeline:nth-child(4) {
      background-image: url("@/assets/PlanetBackground/sad_2.svg");
      background-position: right bottom;
      background-repeat: no-repeat;
      background-size: 100%;
      background-color: rgba(255, 255, 255, 255);
    }
    .timeline:nth-child(5) {
      background-image: url("@/assets/PlanetBackground/treasure.svg");
      background-position: left bottom;
      background-repeat: no-repeat;
      background-size: 100%;
      background-color: rgba(255, 255, 255, 255);
    }
    .timeline:nth-child(6) {
      background-image: url("@/assets/PlanetBackground/dream_2.svg");
      background-position: right bottom;
      background-repeat: no-repeat;
      background-size: 100%;
      background-color: rgba(255, 255, 255, 255);
    }
    .timeline:nth-child(7) {
      background-image: url("@/assets/PlanetBackground/regret.svg");
      background-position: left bottom;
      background-repeat: no-repeat;
      background-size: 100%;
      background-color: rgba(255, 255, 255, 255);
    }
    .timeline:nth-child(8) {
      background-image: url("@/assets/PlanetBackground/dumchit_2.svg");
      background-position: right bottom;
      background-repeat: no-repeat;
      background-size: 100%;
      background-color: rgba(255, 255, 255, 255);
    }
    .timeline:nth-child(9) {
      background-image: url("@/assets/PlanetBackground/genie.svg");
      background-position: left bottom;
      background-repeat: no-repeat;
      background-size: 100%;
      background-color: rgba(255, 255, 255, 255);
    }
    .timeline:nth-child(9) {
      background-image: url("@/assets/PlanetBackground/sad_2.svg");
      background-position: right bottom;
      background-repeat: no-repeat;
      background-size: 100%;
      background-color: rgba(255, 255, 255, 255);
    } */
</style>
