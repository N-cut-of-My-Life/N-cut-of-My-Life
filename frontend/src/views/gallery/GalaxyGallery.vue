<template>
  <GalleryLoader v-if="isLoading"></GalleryLoader>
  <div v-else style="display: grid; padding: 0; margin: 0">
    <video muted autoplay loop playbackRate="0.9">
      <source src="@/assets/galaxy.mp4" type="video/mp4" />
    </video>
    <div class="other">
      <b-button
        @click="router.push({ name: 'introfirstpage' })"
        class="button_prev"
        size="sm"
      >
        <strong>&lt;</strong>&nbsp;&nbsp;HOME
      </b-button>
    </div>

    <!-- <div class="jumbotron"> -->
    <div class="title">은 하 갤 러 리</div>
    <audio loop autoplay volume="0.3">
      <source src="@/assets/audio/mix_gallery.mp3" type="audio/mp3" />
    </audio>
    <img class="reloadBtn" @click="reload()" src="@/assets/refresh.png" />
    <div v-if="modalShow == true" class="img-modal" id="modal-outside">
      <img class="selected-img" id="selected" :src="selectedImage" />
    </div>
    <div class="modal-x-btn">
      <img
        v-if="modalShow == true"
        src="@/assets/xButton/x_galaxy.svg"
        alt=""
        @click="toggleModal()"
      />
    </div>
    <!-- masonry 영역 ver2 -->
    <MasonryWall
      :items="[...galleryStore.galleryList].reverse()"
      :ssr-columns="1"
      :column-width="200"
      :gap="16"
    >
      <template #default="{ item }">
        <div class="common" :style="randomBackgroundColor()">
          <img
            v-if="item.imgUrl"
            :src="item.imgUrl"
            alt=""
            class="item-image"
            @click="showImgModal(item.imgUrl)"
          />
          <span class="content">{{ item.contents }}</span>
          <b-row>
            <b-col cols="8"></b-col>
            <b-col cols="4">
              <div class="like" @click="manageLike(item.answerId, item.isMine)">
                <svg
                  v-if="item.isMine === 'FALSE'"
                  xmlns="http://www.w3.org/2000/svg"
                  width="16"
                  height="16"
                  fill="currentColor"
                  class="bi bi-heart"
                  viewBox="0 0 16 16"
                >
                  <path
                    d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"
                  />
                </svg>
                <svg
                  v-else
                  xmlns="http://www.w3.org/2000/svg"
                  width="16"
                  height="16"
                  fill="currentColor"
                  class="bi bi-heart-fill"
                  viewBox="0 0 16 16"
                  style="color: red"
                >
                  <path
                    fill-rule="evenodd"
                    d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"
                  />
                </svg>
                {{ item.like }}
              </div></b-col
            >
          </b-row>
        </div>
      </template>
    </MasonryWall>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { useGalleryStore } from "@/store/gallery";
import { useMusicStore } from "@/store/music";
import { ref, onMounted } from "vue";
import { useAccountStore } from "@/store/account";
import GalleryLoader from "@/load/GalleryLoader.vue";

const userId = useAccountStore().userInfo.userId;
const router = useRouter();

const galleryStore = useGalleryStore();
const GalleryArticles = galleryStore.getGalleryList(userId);
GalleryArticles;

let modalShow = ref(false);
let selectedImage = ref("");

// let items = galleryStore.galleryList;

const reload = () => {
  galleryStore.getGalleryList(userId);
};

const manageLike = (answerId, isMine) => {
  if (isMine === "TRUE") {
    galleryStore.deleteLike(answerId, userId);
  } else {
    galleryStore.addLike(answerId, userId);
  }
};
onMounted(() => {
  useMusicStore().isSoundActive();
});

const randomBackgroundColor = () => {
  const colors = [
    "#ffeecd",
    "#fff9d6",
    // '#daffcf',
    "#dcfffd",
    "#e1edff",
    "#f0e7ff",
    "#ffecfa",
    "#ffe7e8",
    // 'white',
    "#ffe1e1",
    "#fffad7",
    "#cdf0ea",
    "#fff5e4",
    "#d6efed",
    "#d3cedf",
    "#f1f0c0",
    "#fefbe7",
    "#d3dedc",
    "#eed7ce",
    "#ded9c4",
    "#f9f9f9",
  ];
  const idx = Math.floor(Math.random() * colors.length);
  return "background-color: " + colors[idx];
};

const showImgModal = (imgUrl) => {
  console.log("!!!hello");
  toggleModal();
  selectedImage.value = imgUrl;
  console.log(modalShow.value);
  console.log(selectedImage.value);
};

const toggleModal = () => {
  modalShow.value = !modalShow.value;
};

window.addEventListener("click", (event) => {
  console.log(event.target.id);
  if (event.target.id === "modal-outside") {
    toggleModal();
  }
});
</script>

<style scoped>
.body {
  height: 100vh;
}
@font-face {
  font-family: "KyoboHand";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/KyoboHand.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
.other {
  position: absolute;
  left: 1%;
  top: 2.5%;
  margin: auto;
}

.button_prev {
  background-color: #e1edff;
  color: #141414;
  border-radius: 0.8vw;
  border: none;
}
.title {
  text-align: center;
  padding-top: 3%;
  /* color: rgb(255, 188, 64); */
  color: #b1b2ff;
  font-size: 2vw;
  font-weight: 600;
  margin-bottom: 2%;
  margin-top: 0;
  font-family: KyoboHand;
  font-size: 2.7vw;
}

video {
  position: fixed;
  right: 0;
  bottom: 0;
  min-width: 100%;
  min-height: 100%;
  width: auto;
  height: auto;
  z-index: -100;
  background-size: cover;
}

/* .jumbotron {
  background: url("@/assets/galaxy_gallery.svg") no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  width: 100%;
  min-height: 100vh;
  margin: 0;
  padding: 0;
} */

.reloadBtn {
  position: fixed;
  bottom: 5%;
  right: 3%;
  width: 3em;
}

.reloadBtn:hover {
  cursor: pointer;
}

/* masonry layout용 css */

.masonry-wall {
  margin-left: 1%;
  margin-right: 1%;
}
.common {
  background-color: white;
  border-radius: 0.375rem;
  display: flex;
  flex-direction: column;
}
.item-image {
  max-width: 100%;
  border-radius: 0.375rem;
  cursor: pointer;
}

.content {
  padding: 10px 10px 5px 10px;
  font-family: KyoboHand;
  -webkit-touch-callout: text; /* iOS Safari */
  -webkit-user-select: text; /* Safari */
  -moz-user-select: text; /* Old versions of Firefox */
  -ms-user-select: text; /* Internet Explorer/Edge */
  user-select: text;
  font-size: 1vw;
}
.like {
  text-align: end;
  padding: 10px 10px 5px 10px;
  margin-top: auto;
  cursor: pointer;
  font-family: KyoboHand;
  display: block;
}

.img-modal {
  width: 100%;
  height: 100vh;
  position: fixed;
  justify-content: center;
  text-align: center;
  background-color: #14141495;
  display: flex;
  animation: fadein 0.5s;
  -moz-animation: fadein 0.5s; /* Firefox */
  -webkit-animation: fadein 0.5s; /* Safari and Chrome */
  -o-animation: fadein 0.5s;
}

.selected-img {
  width: auto;
  max-height: 100vh;
  margin: auto;
}

.modal-x-btn {
  cursor: pointer;
  float: right;
  position: fixed;
  top: 2%;
  right: 1%;
}

@keyframes fadein {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
@-moz-keyframes fadein {
  /* Firefox */
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
@-webkit-keyframes fadein {
  /* Safari and Chrome */
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
@-o-keyframes fadein {
  /* Opera */
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

@keyframes fadein {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
@-moz-keyframes fadein {
  /* Firefox */
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

@keyframes fadeout {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
@-moz-keyframes fadeout {
  /* Firefox */
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
@-webkit-keyframes fadeout {
  /* Safari and Chrome */
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
@-o-keyframes fadeout {
  /* Opera */
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}

@keyframes fadeout {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
@-moz-keyframes fadeout {
  /* Firefox */
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
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
  /* background-color: #a1a1a1; */
  /* background-color: #7895b2; */
  /* background-color: #aebdca; */
  background-color: #d2daff;
}

body::-webkit-scrollbar-button {
  width: 0;
  height: 0;
}
</style>
