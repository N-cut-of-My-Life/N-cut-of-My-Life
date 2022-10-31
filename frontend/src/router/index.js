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
    path: "/introstory1",
    name: "introstory1",
    component: () => import("@/views/intro/IntroStory1.vue"),
  },
  {
    path: "/introstory2",
    name: "introstory2",
    component: () => import("@/views/intro/IntroStory2.vue"),
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
  {
    path: "/musicplanet",
    name: "musicplanet",
    component: () => import("@/views/planets/MusicPlanet.vue"),
  },
  {
    path: "/dreamplanet",
    name: "dreamplanet",
    component: () => import("@/views/planets/DreamPlanet.vue"),
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
