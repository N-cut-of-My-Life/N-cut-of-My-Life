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
          <img
            v-if="item.img !== ''"
            :src="require(`@/assets/${item.img}`)"
            alt=""
          />
          <span class="content">{{ item.content }}</span>
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
            {{ item.likes }}
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

onMounted(() => {
  useMusicStore().isSoundActive();
});
const router = useRouter();

const galleryStore = useGalleryStore();
const getStoreList = galleryStore.getGalleryList();
getStoreList;

console.log(galleryStore.galleryList.data);
console.log(typeof galleryStore.galleryList);

// 텍스트 길이, 이미지 크기에 따라
// 텍스트 박스 잘 바뀌는지 확인하고자 더미데이터 생성
const items = [
  { content: "오늘도 홧팅쓰", img: "", likes: "79" },
  { content: "뷰다 뷰", img: "logo.png", likes: "0" },
  { content: "난 해낼 수 있어", img: "", likes: "100" },
  {
    content:
      "누구보다 빠르게 난 남들과는 다르게 색다르게 리듬을 타는 비트 위에 나그네",
    img: "",
    likes: "5",
  },
  {
    content:
      "유후 앤 다하 잇츠 모얼 댄 라이크 L 다음 또 O 다음 나 예예예, 유후 앤 다하 잇츠 로얼 댄 라이크! 왓츠 애프터라이크",
    img: "logo.png",
    likes: "33",
  },
  {
    content: "가끔은 정말 헷갈리지만 분명한 건 Got me looking for attention",
    img: "logo.png",
    likes: "20",
  },
  {
    content:
      'I worked my whole life just to get right, just to be like Look at me, I"m never coming down I worked my whole life Just to get high, just to realize Everything I need is on the Everything i need is on the ground',
    img: "logo.png",
    likes: "2",
  },
  {
    content:
      "어느 날 한라산 산삼이 먹고 싶다며 나를 데려간 제주도에서 저기 저 돌하르방 코는 아들 낳을 돌하르방 코인가 딸 낳을 돌하르방 코인가를 묻는 너를 보며 난 액자 속 사진을 찢어 버렸다 너와 헤어진 후 힘들어하는 내게 박 법학박사님과 백 법학박사님께서 차라리 상담담당 선생님 성 선생님을 추천해주셨어 그렇게 찾은 러브코치 상담담당 선생님 성 선생님이 내게 말했어 자기는 참치 꽁치찜을 좋아한다고 이게 무슨 헛소린가 싶어 난 청송콩찰떡이 좋다고 했지 시덥잖은 농담 속에 서울 찹쌀 촌 찹쌀같이 나눠져 있던 마음이 쿵더더덕 덩기더더덕 녹아버렸어 너로 인해 고장난 내 마음의 보일러 지금부터 난 난방방법 변경을 할 거야 공간 감각이 사라질 정도로 내 몸에서 합성 착향료가 날 때까지 (Brrr) 너랑 헤어진 후 난 편판선 군의 소개로 판편숙 양을 만났어 그녀는 간장공장 공장장의 친구 중앙청 창살 외창살 시청 창살 쌍창살을 관리했어",
    img: "logo.png",
    likes: "3",
  },
];
// const items = galleryStore.galleryList
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
