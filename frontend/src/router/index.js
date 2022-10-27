import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'intro',
    component: () => import('@/views/intro/IntroView.vue')
  },
  {
    path: "/introfirstpage",
    name: "introfirstpage",
    component: () => import("@/views/intro/IntroFirstPage.vue"),
  },
  {
    path: "/introstory",
    name: "introstory",
    component: () => import("@/views/intro/IntroStory.vue"),
  },
  {
    path: "/galaxygallery",
    name: "galaxygallery",
    component: () => import("@/views/gallery/GalaxyGallery.vue"),
  },
  {
    path: "/traveldiary",
    name: "traveldiary",
    component: () => import("@/views/mypage/TravelDiary.vue"),
  },
  {
    path: "/planetlist",
    name: "planetlist",
    component: () => import("@/views/planets/PlanetList.vue"),
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
