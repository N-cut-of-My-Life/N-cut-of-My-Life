import { defineStore } from "pinia";
import { useAccountStore } from "./account";
import axios from "axios";
import index from "@/api/index";

export const useMusicStore = defineStore("music", {
  persist: {
    storage: sessionStorage,
  },
  state: () => ({
    isMute: true,
    isSearch: false,
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
      this.isSearch = false;
      axios({
        url: index.music.getMusic(keyword),
        method: "GET",
        headers: { "X-AUTH-TOKEN": useAccountStore().token },
      }).then((res) => {
        // const json_string = xml2json(res.data);
        // const json = JSON.parse(json_string);
        // let lst = json.elements[0].elements[0].elements.slice(8);
        // let songsList = new Array();
        // // 가수, 제목, 앨범표지 추출
        // for (let e1 of lst) {
        //   let song = new Object();
        //   for (let e2 of e1.elements) {
        //     if (e2.name === "title") {
        //       console.log(e2.elements[0].cdata);
        //       song["title"] = e2.elements[0].cdata;
        //     }
        //     if (e2.name === "maniadb:album") {
        //       console.log(e2.elements[3].elements[0].cdata);
        //       song["image"] = e2.elements[3].elements[0].cdata;
        //     }
        //     if (e2.name === "maniadb:artist") {
        //       console.log(e2.elements[1].elements[0].cdata);
        //       song["artist"] = e2.elements[1].elements[0].cdata;
        //     }
        //   }
        //   if (song !== {}) {
        //     songsList.push(song);
        //   }
        // }
        // this.songs = songsList;
        this.isSearch = true;
        this.songs = res.data.data;
        console.log(this.songs);
      });
    },
  },
});
