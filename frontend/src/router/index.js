import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'intro',
    component: () => import('../views/IntroView.vue')
  },
  {
    path: '/story',
    name: 'story',
    component: () => import('../views/IntroStory.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
