<template>
  <!--modals-->
  <div id="openModal-about" class="modalDialog">
    <div>
      <h1 style="text-align: center">음악 검색</h1>
      <a href="#close" title="Close" class="close">X</a>
      <input
        class="search__input"
        type="text"
        placeholder="Search"
        @keyup.enter="musicStore.getMusicData(keyword)"
        v-model="keyword"
      />
      <div class="container">
        <ul v-for="(song, index) in musicStore.songs" :key="index" class="song">
          <img :src="song.image" :alt="song.title" />
          <div class="title-artist">
            <div class="title">{{ song.title }}</div>
            <div class="artist">{{ song.artist }}</div>
          </div>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useMusicStore } from "@/store/index";
const musicStore = useMusicStore();
let keyword = "";
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
  line-height: 25px;
  position: absolute;
  right: -12px;
  text-align: center;
  top: -10px;
  width: 34px;
  height: 34px;
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
  width: 100%;
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

img {
  width: 10%;
  height: auto;
}

.title-artist {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>
