<template>
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
    <div class="title">은 하 갤 러 리</div>
    <audio loop autoplay volume="0.3">
      <source src="@/assets/audio/mix_gallery.mp3" type="audio/mp3" />
    </audio>
    <!-- masonry 영역 ver2 -->
    <MasonryWall :items="items" :ssr-columns="1" :column-width="200" :gap="16">
      <template #default="{ item }">
        <div class="common">
          <!-- api호출이 아닌 상태이기 떄문에 추후 변경:require 이후 코드 -->
          <img v-if="!item.imgUrl" :src="item.imgUrl" alt="" />
          <span class="content">{{ item.contents }}</span>
          <div class="like">
            <svg
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
          </div>
          <!-- 댓글 공간 -->
        </div>
      </template>
    </MasonryWall>
  </div>
</template>

<script setup>
import MasonryWall from "@yeger/vue-masonry-wall";
import { useRouter } from "vue-router";
import { useGalleryStore } from "@/store/gallery";
import { useMusicStore } from "@/store/music";
import { onMounted } from "vue";
import { useAccountStore } from "@/store/account";

const userID = useAccountStore().userInfo.userId;

onMounted(() => {
  useMusicStore().isSoundActive();
});
const router = useRouter();

const galleryStore = useGalleryStore();
const getStoreList = galleryStore.getGalleryList(userID);
getStoreList;

let items = galleryStore.galleryList;
</script>

<style scoped>
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
.title {
  text-align: center;
  padding-top: 3%;
  color: orange;
  font-size: 2vw;
  font-weight: 600;
}

.jumbotron {
  background: url("@/assets/galaxy_gallery.svg") no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  width: 100%;
  min-height: 100vh;
  margin: 0;
  padding: 0;
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
.content {
  padding: 10px 10px 5px 10px;
}
.like {
  text-align: end;
  padding: 10px 10px 5px 10px;
  margin-top: auto;
}
</style>
