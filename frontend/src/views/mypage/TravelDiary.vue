<template>
  <my-modal style="z-index: 5"></my-modal>
  <!-- 돌아가기 버튼 -->
  <div class="other">
    <b-button
      @click="router.push({ name: 'introfirstpage' })"
      class="button_prev"
      size="sm"
    >
      <strong>&lt;</strong>&nbsp;&nbsp;홈페이지
    </b-button>
  </div>

  <div class="jumbotron">
    <div class="title">My Page</div>
    <main class="content">
      <section style="width: 100%">
        <div class="back-square"></div>
        <div class="upper-part">
          <div class="upper-image">
            <img :src="user.profileImage" class="prof-photo" />
          </div>
          <div class="upper-right">
            <div class="top">
              <div class="userinfo">여행자 정보</div>
              <div class="myinfo">
                <div>{{ user.name }}({{ user.email }})</div>
              </div>
            </div>
            <div class="bottom">
              <div class="bottom-item">
                <div class="bottom-title">총 여행횟수</div>
              </div>
              <div class="bottom-item">
                <div class="bottom-title">다녀온 행성 수</div>
              </div>
              <div class="bottom-item">
                <div class="bottom-info">
                  {{ accountStore.myArticles?.length }}
                </div>
              </div>
              <div class="bottom-item">
                <div class="bottom-info">
                  {{ accountStore.myArticles[idx]?.answers?.length - 1 }}
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="prof-slide-part">
          <span class="prof-slide-title"
            >{{ user.name }}님의 여행기록({{
              accountStore.myArticles.length
            }})</span
          >
          <span class="prof-slide-router" v-if="isClicked === true"
            >자세히 보기 >></span
          >
        </div>
        <div class="carousel-part">
          <splide id="diary-carousel" :options="options">
            <template
              v-for="(article, index) in [...accountStore.myArticles].reverse()"
              :key="index"
            >
              <splide-slide @click="getCurIdx(index)">
                <div class="prof-journals">
                  <div class="journal">
                    <img src="@/assets/space_diary.png" class="journal-img" />
                    <div class="diary-title">
                      {{ article.createDate.slice(0, 10) }} 여행기록
                    </div>
                  </div>
                </div>
              </splide-slide>
            </template>
          </splide>
        </div>
      </section>
    </main>
  </div>
</template>

<script setup>
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/themes/splide-default.min.css";
import MyModal from "@/components/mypage/MyModal.vue";
import { useRouter } from "vue-router";
import { useAccountStore } from "@/store/account";
import { ref } from "vue";
// import { onMounted, ref } from "vue-demi";

const router = useRouter();
const accountStore = useAccountStore();
// const myArticles = ref({});
const user = accountStore.userInfo;

let idx = ref(0);
let isClicked = ref(false);
const getCurIdx = (index) => {
  console.log(index);
  isClicked.value = true;
};
accountStore.getMyArticles();
// myArticles.value = accountStore.myArticles;
console.log(accountStore.userInfo);
// onMounted(() => {});

const options = {
  perPage: 3,
  pauseOnHover: false,
  padding: "0.3rem",
  arrows: true,
  pagination: false,
  focus: 0,
  wheel: true,
};
</script>
<style scoped>
@font-face {
  font-family: "MaplestoryOTFBold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/MaplestoryOTFBold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "ONE-Mobile-POP";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/ONE-Mobile-POP.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "ONE-Mobile-Regular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/ONE-Mobile-Regular.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
body {
  margin: 0;
}
.other {
  position: absolute;
  left: 1%;
  top: 2.5%;
  margin: auto;
}

.button_prev {
  background-color: #ffffff;
  color: #141414;
  border-radius: 0.8vw;
  border-color: #ffffff;
}

.jumbotron {
  background: url("@/assets/travel_diary.svg") no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  height: 100vh;
  width: 100%;
  margin: 0;
  padding: 0;
}

.title {
  text-align: center;
  padding-top: 3%;
  color: aliceblue;
  font-size: 2vw;
  font-weight: 600;
}

.content {
  display: flex;
  justify-content: center;
  margin: 2vh auto;
  flex-direction: column;
  align-items: center;
  gap: 2.5%;
  width: 80%;
  height: 80%;
  margin-top: 3%;
}

/* .back-square {
  position: absolute;
  width: 80%;
  height: 80%;
  z-index: 0;
  background-color: aliceblue;
} */
/* 왼쪽 파트 */
/* 페이지 재구성: 메인 파트 */
.upper-part {
  display: flex;
  justify-content: space-between;
  width: 70%;
  margin: 0 18%;
}
.upper-right {
  width: 70%;
}
.upper-right .top {
  display: flex;
  min-height: 25%;
  gap: 20%;
  margin-left: 12%;
}
.upper-right .top .userinfo {
  display: flex;
  align-items: center;
  font-size: 1.5rem;
  font-family: "ONE-Mobile-Regular";
}
.upper-right .top .myinfo {
  display: flex;
  align-items: center;
  font-size: 1.5rem;
  font-family: "MapleStoryOTFBold";
}
.bottom {
  height: 80%;
  display: grid;
  grid-template-columns: 45% 45%;
  grid-template-rows: 40% 40%;
  justify-items: center;
  gap: 10px;
}
.bottom-item:nth-child(1) {
  align-self: end;
}
.bottom-item:nth-child(2) {
  align-self: end;
}
.bottom-title {
  text-align: center;
  font-family: "ONE-Mobile-POP";
  font-size: 1.3rem;
}
.bottom-info {
  text-align: center;
  font-family: "MaplestoryOTFBold";
  font-size: 2.5rem;
  color: rgb(248, 30, 88);
}
.prof-photo {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  border: 1px solid black;
  object-fit: cover;
}
/* .prof-id {
  font-family: "MaplestoryOTFBold";
  text-align: center;
  color: rgb(224, 227, 230);
} */

.carousel-part {
  width: 70%;
  display: flex;
  justify-content: center;
  margin: 0 auto;
}
.prof-slide-part {
  width: 70%;
  margin: 0 auto;
  text-align: center;
  position: relative;
}
.prof-slide-title {
  font-family: "MapleStoryOTFBold";
  font-size: 2rem;
}
.prof-slide-router {
  font-family: "ONE-Mobile-Regular";
  position: absolute;
  top: 50%;
  right: 1%;
}
.prof-journals {
  display: flex;
}

.journal-img {
  width: 100%;
  position: relative;
}

.diary-title {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 100%;
  font-family: "MapleStoryOTFBold";
  color: aliceblue;
  transform: translate(-25%, -350%);
}
.journal:hover {
  scale: 1.05;
}

/* 오른쪽 파트 */
/* 페이지 재구성에 따라 주석처리, 혹시 다시 쓰일까봐 지우지는 않겠음 */
/* .diary-part {
  width: 52%;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.upper-items {
  display: flex;
}

.last-word {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.last-word img {
  width: 100%;
}

.text-shadow {
  font-family: "MapleStoryOTFBold";
  font-size: 1.5rem;
  color: black;
  position: absolute;
  width: 75%;
  top: 40%;
  left: 15%;
  word-wrap: break-word;
}

.l-button {
  display: inline-block;
  margin: 0;
  padding: 10px 15px;
  border: none;
  color: #fff;
  text-align: center;
  font-size: 14px;
  font-weight: bold;
  -webkit-transition: 0.3s all ease-in-out;
  transition: 0.3s all ease-in-out;
}

.letter-img {
  width: 15vw;
  transform: rotate(180deg) scaleY(-1);
}

.letter-img:hover {
  scale: 1.1;
}

.strip {
  display: flex;
}

.strip + .strip {
  margin-top: 1rem;
}

.film {
  aspect-ratio: 16 / 9;
  background: #000;
  background-image: linear-gradient(to right, #fff, #fff),
    linear-gradient(to right, #fff, #fff), linear-gradient(to right, #fff, #fff),
    linear-gradient(to right, #fff, #fff), linear-gradient(to right, #fff, #fff),
    linear-gradient(to right, #fff, #fff), linear-gradient(to right, #fff, #fff),
    linear-gradient(to right, #fff, #fff), linear-gradient(to right, #fff, #fff),
    linear-gradient(to right, #fff, #fff), linear-gradient(to right, #fff, #fff),
    linear-gradient(to right, #fff, #fff), linear-gradient(to right, #fff, #fff),
    linear-gradient(to right, #fff, #fff), linear-gradient(to right, #fff, #fff),
    linear-gradient(to right, #fff, #fff), linear-gradient(to right, #fff, #fff),
    linear-gradient(to right, #fff, #fff), linear-gradient(to right, #fff, #fff),
    linear-gradient(to right, #fff, #fff), linear-gradient(to right, #fff, #fff),
    linear-gradient(to right, #fff, #fff);

  background-repeat: no-repeat;
  background-position: 5% 5%, 15% 5%, 25% 5%, 35% 5%, 45% 5%, 55% 5%, 65% 5%,
    75% 5%, 85% 5%, 95% 5%, 5% 95%, 15% 95%, 25% 95%, 35% 95%, 45% 95%, 55% 95%,
    65% 95%, 75% 95%, 85% 95%, 95% 95%;

  background-size: 5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%,
    5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%, 5% 8%,
    5% 8%, 5% 8%;
}

.film__frame {
  padding: 13% 5%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.film img {
  max-width: 100%;
  border-radius: 2px;
} */
</style>

<style>
/* 캐러셀용 스타일 태그입니다. */
#diary-carousel {
  width: 100%;
}
.splide__arrow--prev {
  left: -2em;
}
.splide__arrow--next {
  right: -2em;
}
</style>
