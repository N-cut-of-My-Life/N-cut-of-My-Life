import { defineStore } from "pinia";
import axios from "axios";
import { xml2json } from "xml-js";

export const useMusicStore = defineStore("music", {
  persist: {
    storage: sessionStorage,
  },
  state: () => ({
    isMute: true,
    songs: [],
  }),
  getters: {},
  actions: {
    toggleMute() {
      this.isMute = !this.isMute;
    },
    isSoundActive() {
      if (this.isMute === true) {
        document.querySelector("audio").muted = true;
      } else {
        document.querySelector("audio").muted = false;
      }
    },

    getMusicData(keyword) {
      axios({
        url: `https://www.maniadb.com/api/search/${keyword}/?sr=song&display=5&key=niinp28@gmail.com&v=0.5`,
        method: "GET",
      }).then((res) => {
        const json_string = xml2json(res.data);
        const json = JSON.parse(json_string);
        let lst = json.elements[0].elements[0].elements.slice(8);
        let songsList = new Array();
        // 가수, 제목, 앨범표지 추출
        for (let e1 of lst) {
          let song = new Object();
          for (let e2 of e1.elements) {
            if (e2.name === "title") {
              console.log(e2.elements[0].cdata);
              song["title"] = e2.elements[0].cdata;
            }
            if (e2.name === "maniadb:album") {
              console.log(e2.elements[3].elements[0].cdata);
              song["image"] = e2.elements[3].elements[0].cdata;
            }
            if (e2.name === "maniadb:artist") {
              console.log(e2.elements[1].elements[0].cdata);
              song["artist"] = e2.elements[1].elements[0].cdata;
            }
          }
          if (song !== {}) {
            songsList.push(song);
          }
        }
        this.songs = songsList;
      });
    },
  },
});
