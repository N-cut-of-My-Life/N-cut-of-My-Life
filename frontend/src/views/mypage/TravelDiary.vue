<template>
  <div class="jumbotron">
    <div class="title">My Page</div>
    <main class="content">
      <section class="profile-part">
        <div class="prof-photo-box">
          <img src="@/assets/astronaut_riding.png" class="prof-photo">
        </div>
        <div class="prof-id">test123@naver.com</div>
        <div class="prof-journal-title">OO님의 여행일지</div>
        <div class="prof-journals">
          <div class="journal">
            <img src="@/assets/space_diary.png" class="journal-img">
          </div>
          <div class="journal">
            <img src="@/assets/space_diary.png" class="journal-img">
          </div>
        </div>
      </section>

      <section class="diary-part">
        <!-- 상위 2개 아이템들 -->
        <div class="upper-items">
          <div class="flip flip-vertical">
            <div class="front">
              <img src="@/assets/post_paper.png">
              <h1 class="text-shadow">나에게 전하는 한마디</h1>
            </div>
            <div class="back">
              <p>나는 항상 최고야!!!!! 짜릿해!!!! dmgkgkdgksdkgkgaskg</p>
            </div>
          </div>
          <div class="letter">
            <img src="@/assets/mailbox.png" class="letter-img">
          </div>
        </div>
        <!-- 일지 상세 -->
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
        
        
      </section>
    </main>
  </div>
</template>

<script setup>

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

h1 {
  font-size: 2.2em;
}

.flip {
  position: relative;
}
.flip > .front,
.flip > .back {
  display: block;
  transition-timing-function: cubic-bezier(0.175, 0.885, 0.32, 1.275);
  transition-duration: 2s;
  transition-property: transform, opacity;
}
.flip > .front {
  transform: rotateY(0deg);
}
.flip > .back {
  position: absolute;
  opacity: 0;
  top: 0px;
  left: 0px;
  width: 100%;
  height: 100%;
  transform: rotateY(-180deg);
}
.flip:hover > .front {
  transform: rotateY(180deg);
}

.flip:hover > .back {
  opacity: 1;
  transform: rotateY(0deg);
}
.flip.flip-vertical > .back {
  transform: rotateX(-180deg);
}
.flip.flip-vertical:hover > .front {
  transform: rotateX(180deg);
}

.flip.flip-vertical:hover > .front .text-shadow{
  transform: rotateX(180deg);
  opacity: 0;
}
.flip.flip-vertical:hover > .back {
  transform: rotateX(0deg);
}

.flip {
  position: relative;
  display: inline-block;
  margin-right: 2px;
  margin-top: 1em;
  width: 25vw;
}
.flip > .front,
.flip > .back {
  display: block;
  color: white;
  width: inherit;
  background-size: cover !important;
  background-position: center !important;
  height: 220px;
  /* padding: 1em 2em; */
  /* background: #313131;
  border-radius: 10px; */
}

.flip > .front img {
  width: 100%;
  position: relative;
}

.flip > .front p,
.flip > .back p {
  font-family: 'ONE-Mobile-POP';
  font-size: 1.5rem;
  line-height: 160%;
  color: #999;
  width: 100%;
  position: absolute;
  top: 35%;
  left: 30%;
  transform: translateX(-18%);
  word-wrap: break-word;
}

.text-shadow {
  font-family: 'MapleStoryOTFBold';
  color: black;
  position: absolute;
  top: 40%;
  left: 10%;
}
/* .last-word-front {
  position: relative;
  text-align: center;
  display: flex;
  justify-content: center;
}

.last-word-img {
  width: 90%;
}

.text-on-img {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-family: 'ONE-Mobile-POP';
} */

.letter {
  /* display: flex;
  justify-content: center; */
  margin-left: auto;
  margin-top: -3em
}

.letter-img {
  width: 15vw;
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