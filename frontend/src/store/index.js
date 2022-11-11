import { defineStore } from 'pinia'
import index from '@/api/index'
import axios from 'axios'
import { xml2json } from 'xml-js'
// import { ref } from 'vue'
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
      songs: [],
    }),
  getters: {
  },
  actions: {
    toggleMute() {
      this.isMute = !this.isMute
    },
    isSoundActive() {
      console.log(this.isMute)
      if(this.isMute === true){
        document.querySelector('audio').muted = true
      } else {
        document.querySelector('audio').muted = false
      }
    },
    
    getMusicData(keyword) {
      axios({
        url: `https://www.maniadb.com/api/search/${keyword}/?sr=song&display=5&key=niinp28@gmail.com&v=0.5`,
        method: 'GET'
      })
      .then((res) => {
        
        const json_string = xml2json(res.data)
        const json = JSON.parse(json_string)
        let lst = (json.elements[0].elements[0].elements).slice(8)
        let songsList = new Array()
        // 가수, 제목, 앨범표지 추출
        for(let e1 of lst){
          let song = new Object()
          for(let e2 of e1.elements){
            if(e2.name === 'title'){
              console.log(e2.elements[0].cdata)
              song['title'] = e2.elements[0].cdata
            }
            if(e2.name === 'maniadb:album'){
              console.log(e2.elements[3].elements[0].cdata)
              song['image'] = e2.elements[3].elements[0].cdata
            }
            if(e2.name ==='maniadb:artist'){
              console.log(e2.elements[1].elements[0].cdata)
              song['artist'] = e2.elements[1].elements[0].cdata
            }
          }
          if(song !== {}){
            songsList.push(song)
          }
        }
        this.songs = songsList
      })
    }
    
  },
})