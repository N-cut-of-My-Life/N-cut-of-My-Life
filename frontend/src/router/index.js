import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'intro',
    component: () => import('../views/IntroView.vue')
  },
  {
    path: "/introfirstpage",
    name: "introfirstpage",
    component: () => import("@/intro/IntroFirstPage.vue"),
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
