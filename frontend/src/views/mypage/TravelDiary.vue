<template>
  <my-modal style="z-index: 5;"></my-modal>
  <div class="other">
    <b-button @click="router.push({ name: 'introfirstpage' })" class="button_prev" size="sm">
      <strong>&lt;</strong>&nbsp;&nbsp;홈페이지
    </b-button>
  </div>

  <div class="jumbotron">
    <div class="title">My Page</div>
    <main class="content">
      <section class="profile-part">
        <div class="prof-photo-box">
          <img src="@/assets/astronaut_riding.png" class="prof-photo">
        </div>
        <div class="prof-id">test123@naver.com</div>
        <div class="prof-journal-title">OO님의 여행일지</div>
        <div>
          <!-- <div class="journal">
            <img src="@/assets/space_diary.png" class="journal-img">
          </div>
          <div class="journal">
            <img src="@/assets/space_diary.png" class="journal-img">
          </div> -->
          <splide id="carousel" :options="options">
            <splide-slide>
              <div class="prof-journals">
                <div class="journal">
                  <img src="@/assets/space_diary.png" class="journal-img">
                </div>
                <div class="journal">
                  <img src="@/assets/space_diary.png" class="journal-img">
                </div>
              </div>
            </splide-slide>
            <splide-slide>
              <div class="prof-journals">
                <div class="journal">
                  <img src="@/assets/space_diary.png" class="journal-img">
                </div>
                <div class="journal">
                  <img src="@/assets/space_diary.png" class="journal-img">
                </div>
              </div>
            </splide-slide>
          </splide>
        </div>
      </section>

      <section class="diary-part">
        <!-- 상위 2개 아이템들 -->
        <div class="upper-items">
    
          <div class="last-word">
            <img src="@/assets/post_paper.png">
            <h1 class="text-shadow">지금은 고민이 참 많을테지만, 언젠간 난 이겨낼거야</h1>
          </div>

          <label class="l-button letter" for="lightbox-1">
            <img src="@/assets/mailbox.png" class="letter-img">
          </label>
          
        </div>
        <!-- 일지 상세 -->
        <div class="below-part">
          <div class="strip">
            <div class="film">
              <div class="film__frame"><img src="https://via.placeholder.com/1920x1080"></div>
            </div>
            <div class="film">
              <div class="film__frame"><img src="https://via.placeholder.com/1920x1080"></div>
            </div>
            <div class="film">
              <div class="film__frame"><img src="https://via.placeholder.com/1920x1080"></div>
            </div>
          </div>       
        </div>
        
      </section>
    </main>
  </div>
</template>

<script setup>
import { Splide, SplideSlide } from '@splidejs/vue-splide';
import '@splidejs/splide/dist/css/themes/splide-default.min.css'
import MyModal from '@/components/mypage/MyModal.vue'
import { useRouter } from 'vue-router'
const router =  useRouter();
const options = {
  perPage: 1,
  pauseOnHover: false,
  arrows: true,
  dots: false,
  animatedDots: false,
  padding: "2rem",
  type: "fade",
  autoplay: true,
}

</script>
<style scoped>
@font-face {
  font-family: 'MaplestoryOTFBold';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/MaplestoryOTFBold.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'ONE-Mobile-POP';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/ONE-Mobile-POP.woff') format('woff');
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
  margin:0;
  padding:0;
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
  justify-content: space-between;
  width: 80vw;
  height: 80vh;
  margin: 2vh auto;
  
}

/* 왼쪽 파트 */
.profile-part {
  width: 37.5%;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2.5%;

}

.prof-photo-box {
  width: 40%;
  height: 30%;
  border-radius: 70%;
  overflow: hidden;
}

.prof-photo {
  width: 100%;
  height: 100%;
  object-fit: cover; 
}

.prof-id {
  font-family: 'MaplestoryOTFBold';
  text-align: center;
  color: rgb(224, 227, 230);
}

.prof-journal-title {
  font-family: 'MapleStoryOTFBold';
  font-size: 2rem;
}

.prof-journals {
  display: flex;
}

.journal-img {
  width: 100%;
}

.journal-img:hover {
  scale: 1.05;
}

/* 오른쪽 파트 */
.diary-part {
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
  font-family: 'MapleStoryOTFBold';
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
}



</style>