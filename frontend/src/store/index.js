import { defineStore } from "pinia";
import index from "@/api/index";
import axios from "axios";
import { xml2json } from "xml-js";
// import { ref } from 'vue'
// for 은하 갤러리

export const useAccountStore = defineStore("account", {
  state: () => ({
    token: null,
  }),
  getters: {
    isLogin(state) {
      return !!state.token;
    },
  },
  actions: {
    kakaoLogin(code) {
      console.log(this.token);
      axios({
        url: index.account.postLogin(),
        method: "POST",
        data: {
          accessToken: code,
        },
      })
        .then((res) => {
          console.log(res.data.data.accessToken);
          this.token = res.data.data.accessToken;
          console.log(this.token);
          console.log(this.isLogin);
        })
        // .then(router.push({ name: "introfirstpage" }))
        .catch((e) => {
          console.log("error", e);
          // router.push({ name: "intro" })
        });
    },
    refreshToken() {
      axios({
        url: index.account.getRefreshToken(),
        method: "GET",
      }).then((res) => {
        console.log(res);
      });
    },
  },
});

export const useGalleryStore = defineStore("gallery", {
  state: () => ({
    lastWords: [],
  }),
  getters: {},
  actions: {
    getLastWord() {
      ({
        url: index.answer.getLastWord(),
        method: "GET",
      }
        .then((res) => {
          console.log(res);
          this.lastWords = res.data;
        })
        .catch((err) => {
          console.log(err);
        }));
    },
  },
});

export const usePlanetStore = defineStore("planet", {
  state: () => ({
    completeCount: 0,
    minimumConditionsMet: false,
    articleRequest: {
      userId: Number,
      answers: [
        // {
        //   questionId: Number,
        //   contents: String,
        //   imgUrl: String,
        //   state: String,
        // },
      ],
    },
  }),
  actions: {
    completePlanet(planetId, answer) {
      this.completeCount++;
      if (this.completeCount === 3) {
        this.minimumConditionsMet = true;
      }
      this.articleRequest.userId = 1;
      this.articleRequest.answers.push({
        questionId: planetId,
        contents: answer,
        imgUrlL: "url",
        state: "CLOSE",
      });
    },
    finishTravel() {
      axios({
        url: index.article.postDiary(),
        method: "POST",
        headers: { "X-AUTH-TOKEN": useAccountStore().token },
        data: {
          articleRequest: this.articleRequest,
        },
      })
        .then((res) => {
          console.log(res.data);
        })
        // .then(router.push({ name: "introfirstpage" }))
        .catch((e) => {
          console.log("error", e);
          // router.push({ name: "intro" })
        });
    },
  },
});

// for 둠칫둠칫 행성
export const useMusicStore = defineStore("music", {
  state: () => ({
    isMute: false,
    songs: [],
  }),
  getters: {},
  actions: {
    toggleMute() {
      this.isMute = !this.isMute;
    },
    isSoundActive() {
      console.log(this.isMute);
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
