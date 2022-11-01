<template>
  <div class="jumbotron">
    <div class="title">ㅇㅇㅇ님의 여행 일지</div>
    <main class="content">
      <section class="profile-part">
        <div class="prof image">
          <img src="@/assets/kakao_logo.png" alt="" class="tmp-image">
        </div>
        <div class="prof info">개인정보 자리</div>
      </section>

      <section class="diary-part">
        <div class="last-word">마지막 말</div>
        <div class="letter">편지</div>
        <div class="list">여행기록</div>
        <div class="journal">
          <img src="@/assets/space_diary.png" class="journal-img">
        </div>
        <div class="journal">
          <img src="@/assets/space_diary.png" class="journal-img">
        </div>
        
      </section>
    </main>
  </div>
</template>

<script setup>

</script>

<style scoped>
body {
  margin: 0;
}
.title {
    text-align: center;
    padding-top: 3%;
    color: aliceblue;
    font-size: 2vw;
    font-weight: 600;
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

.content {
  margin: 2%;
  display: flex;
  justify-content: space-between;
  width: 80vw;
  height: 80vh;
  margin: 5vh auto;
  
}
.profile-part {
  width: 37.5%;
  display: grid;
  grid-template-rows: .4fr .3fr;
  gap: 10px;
}
.prof {
  margin: 10px;
  padding: 5px;
  border: 2px solid black;
  border-radius: 20px;
}
.image {
  background-color: aliceblue;
}
.tmp-image {
  width: 25%;
  margin: 0 auto;
}
.info {
  background-color: aqua;

}
.diary-part {
  width: 48.75%;
  display: grid;
  grid-template-rows: 1fr 0.2fr 3fr;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
}
.last-word {
  border: 2px solid black;
  background-color: aliceblue;
  border-radius: 20px;
  padding: 5px;
}
.letter {
  border: 2px solid black;
  background-color: aliceblue;
  border-radius: 20px;
  padding: 5px;
}
.list {
  grid-column: 1/-1;
  font-size: 2rem;
  color: white;
}
.journal-img {
  width: 100%;
}
.journal-img:hover {
  scale: 1.05;
}

</style>