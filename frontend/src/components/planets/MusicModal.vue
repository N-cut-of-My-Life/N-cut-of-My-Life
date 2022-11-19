<template>
  <!--modals-->
  <div id="openModal-about" class="modalDialog">
    <div class="modal-container">
      <h2 style="text-align: center; margin-bottom: 2%">음악 검색</h2>
      <a href="#close" title="Close" class="close">x</a>
      <div style="text-align: center">
        <input
          class="search__input"
          type="text"
          placeholder="제목 + 가수로 검색해주세요!"
          @keyup.enter="musicStore.getMusicData(keyword)"
          v-model="keyword"
        />
      </div>
      <div class="container">
        <div
          v-if="!musicStore.isSearch"
          style="margin-left: 2%; margin-right: 2%"
        >
          <br />
          <h3>Something</h3>
        </div>
        <div v-else>
          <ul
            v-for="(song, index) in musicStore.songs"
            :key="index"
            class="song"
            style="padding: 0; border-radius: 0.5vw"
            @click="select(song)"
          >
            <img
              class="info"
              :src="song.img"
              :alt="song.title"
              style="border-radius: 0.5vw"
            />
            <div class="title-artist">
              <div class="title">{{ song.title }}</div>
              <div class="artist">{{ song.artist }}</div>
            </div>
          </ul>
        </div>
      </div>
      <!-- v-show="elementVisible" -->
      <div
        v-if="musicStore.isSearch"
        style="text-align: center; margin-top: 2.8%"
      >
        <b-button
          size="sm"
          class="submit_button"
          @click="complete"
          onclick="location.href='#close'"
        >
          완료
        </b-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useMusicStore } from "@/store/music";
import { usePlanetStore } from "@/store/planet";
import { defineEmits, ref } from "vue";
import Swal from "sweetalert2";
const emit = defineEmits(["complete"]);
const musicStore = useMusicStore();
let selectedName = "";
let selectedAlbum = "";
const complete = () => {
  console.log(selectedName);
  if (selectedName === "") {
    Swal.fire({
      icon: "error",
      title: "등록 실패! 😭",
      text: "음악을 선택해 주세요!",
      confirmButtonText: "확인",
    }).then(() => {
      window.location = "#openModal-about";
    });
    return;
  }
  emit("complete");
  usePlanetStore().completePlanet(6, selectedName, "CLOSE", selectedAlbum);
};
let keyword = ref("");
const select = (song) => {
  selectedName = song.artist + " - " + song.title;
  selectedAlbum = song.img;
};

window.addEventListener("click", (event) => {
  if (event.target.id === "openModal-about") {
    console.log("바깥임");
    window.location = "#close";
  }
});
</script>

<style scoped>
/* modal */
* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
.modalDialog {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.8);
  z-index: 99999;
  opacity: 0;
  -webkit-transition: opacity 100ms ease-in;
  -moz-transition: opacity 100ms ease-in;
  transition: opacity 100ms ease-in;
  pointer-events: none;
}
.modalDialog:target {
  opacity: 1;
  pointer-events: auto;
}
.modalDialog > div {
  max-width: 800px;
  width: 90%;
  position: relative;
  margin: 10% auto;
  padding: 20px;
  border-radius: 3px;
  background: #fff;
}
.close {
  font-family: Arial, Helvetica, sans-serif;
  background: #f26d7d;
  color: #fff;
  line-height: 10px;
  position: absolute;
  right: -9px;
  text-align: center;
  top: -10px;
  width: 20px;
  height: 20px;
  text-decoration: none;
  font-weight: bold;
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  border-radius: 50%;
  -moz-box-shadow: 1px 1px 3px #000;
  -webkit-box-shadow: 1px 1px 3px #000;
  box-shadow: 1px 1px 3px #000;
  padding-top: 5px;
}
.close:hover {
  background: #fa3f6f;
}

/* search bar */
.search__input {
  width: 97%;
  padding: 12px 24px;

  background-color: transparent;
  transition: transform 250ms ease-in-out;
  font-size: 14px;
  line-height: 18px;

  color: #575756;
  background-color: transparent;
  /*         background-image: url(http://mihaeltomic.com/codepen/input-search/ic_search_black_24px.svg); */

  background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24'%3E%3Cpath d='M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z'/%3E%3Cpath d='M0 0h24v24H0z' fill='none'/%3E%3C/svg%3E");
  background-repeat: no-repeat;
  background-size: 18px 18px;
  background-position: 95% center;
  border-radius: 50px;
  border: 1px solid #575756;
  transition: all 250ms ease-in-out;
  backface-visibility: hidden;
  transform-style: preserve-3d;
}

.search__input::placeholder {
  color: color(#575756 a(0.8));
  text-transform: uppercase;
  letter-spacing: 1.5px;
}

.search__input:hover,
:focus {
  padding: 12px 0;
  outline: 0;
  border: 1px solid transparent;
  border-bottom: 1px solid #575756;
  border-radius: 0;
  background-position: 100% center;
}

/* 검색 결과 */
.song {
  border: 1px solid black;
  display: flex;
  margin: 10px auto;
}

h3 {
  font-size: 1.2vw;
  font-weight: 500;
  margin-bottom: 1.5%;
}
.info {
  width: 10%;
  height: auto;
}

.title-artist {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left: 2%;
}

.title {
  margin-bottom: 3%;
  font-weight: 600;
}

.complete_button {
  display: none;
}
.container {
  max-height: 300px;
  overflow-x: auto;
  overflow-y: auto;
}
.submit_button {
  border: 1vw;
  background-color: #d9adad;
  padding-left: 1%;
  padding-right: 1%;
  padding-top: 0.5%;
  padding-bottom: 0.5%;
}
</style>

<style>
.container::-webkit-scrollbar {
  width: 0.8vw;
}
.container::-webkit-scrollbar-track {
  background-color: transparent;
}
.container::-webkit-scrollbar-thumb {
  border-radius: 10px;
  /* background-color: #a1a1a1; */
  /* background-color: #7895b2; */
  /* background-color: #aebdca; */
  background-color: #efdad7;
}
.container::-webkit-scrollbar-button {
  width: 0;
  height: 0;
}
</style>
