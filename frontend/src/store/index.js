import { defineStore } from 'pinia'
import index from '@/api/index'
import axios from 'axios'

// for 은하 갤러리
export const useGalleryStore = defineStore('gallery', {
  state: () => ({
    lastWords: []
  }),
  getters: {},
  actions: {
    getLastWord() {
      axios({
        url: index.answer.getLastWord(),
        method: 'GET'
      })
        .then((res) => {
          console.log(res)
          this.lastWords = res.data
      })
        .catch((err) => {
          console.log(err)
      })
    }
  }
})

// for 둠칫둠칫 행성
export const useMusicStore = defineStore('music', {
  state: () => ({
      isMute: false, 
    }),
  getters: {
  },
  actions: {
    toggleMute() {
      var myAudio = document.getElementById('myaudios');
      myAudio.muted = !myAudio.muted;
    }
  },
})