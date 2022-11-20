import { createRouter, createWebHistory } from "vue-router";
import { useAccountStore } from "@/store/account";

const routes = [
  {
    path: "/:pathMatch(.*)*",
    redirect: "/pagenotfound",
    meta: { authNotReq: true },
  },
  {
    path: "/",
    name: "intro",
    component: () => import("@/views/intro/IntroView.vue"),
    meta: { authNotReq: true },
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/login/KakaoLogin.vue"),
    meta: { authNotReq: true },
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
  {
    path: "/happyplanet",
    name: "happyplanet",
    component: () => import("@/views/planets/HappyPlanet.vue"),
  },
  {
    path: "/sadplanet",
    name: "sadplanet",
    component: () => import("@/views/planets/SadPlanet.vue"),
  },
  {
    path: "/genieplanet",
    name: "genieplanet",
    component: () => import("@/views/planets/GeniePlanet.vue"),
  },
  {
    path: "/preciousplanet",
    name: "preciousplanet",
    component: () => import("@/views/planets/PreciousPlanet.vue"),
  },
  {
    path: "/regretplanet",
    name: "regretplanet",
    component: () => import("@/views/planets/RegretPlanet.vue"),
  },
  {
    path: "/treasureplanet",
    name: "treasureplanet",
    component: () => import("@/views/planets/TreasurePlanet.vue"),
  },
  {
    path: "/resultprint",
    name: "resultprint",
    component: () => import("@/views/result/ResultPrint.vue"),
    meta: { authNotReq: true },
  },
  {
    path: "/pagenotfound",
    name: "pagenotFound",
    component: () => import("@/views/404/PageNotFound.vue"),
    meta: { authNotReq: true },
  },
  {
    path: "/lastword",
    name: "lastword",
    component: () => import("@/views/last/LastWord.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;

router.afterEach(async (to) => {
  const token = useAccountStore().token;

  if (
    to.matched.some(function (routeInfo) {
      return routeInfo.meta.authNotReq;
    }) ||
    token != null
  ) {
    // useAccountStore().refreshToken();
    console.log("go to next ", to.fullPath);
    // next(to.fullPath);
  } else {
    await useAccountStore().refreshToken();
    if (useAccountStore().token === null) {
      router.push({ name: "intro" });
      alert("로그인 필요");
    }
  }
});
