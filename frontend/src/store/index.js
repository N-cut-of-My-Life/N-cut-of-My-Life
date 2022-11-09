import { defineStore } from 'pinia'
import index from '@/api/index'
import axios from 'axios'
import { xml2json } from 'xml-js'

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
      items: ''
    }),
  getters: {
  },
  actions: {
    toggleMute() {
      var myAudio = document.getElementById('myaudios');
      myAudio.muted = !myAudio.muted;
    },
    
    getMusicData() {
      axios({
        url: 'https://www.maniadb.com/api/search/기다리다/?sr=song&display=10&key=niinp28@gmail.com&v=0.5',
        method: 'GET'
      })
      .then((res) => {
        
        const json = xml2json(res.data)
        this.items = json
        
      })
    }
    
  },
})