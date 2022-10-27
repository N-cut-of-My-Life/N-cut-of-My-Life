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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
