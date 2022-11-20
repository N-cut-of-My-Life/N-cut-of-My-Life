import { defineStore } from "pinia";
import index from "@/api/index";
import axios from "axios";
import router from "@/router";

export const useAccountStore = defineStore("account", {
  persist: {
    storage: sessionStorage,
    paths: ["userInfo", "myArticles", "planetVisited"],
  },
  state: () => ({
    token: null,
    userInfo: {},
    myArticles: [],
    planetVisited: 0,
  }),
  getters: {
    isLogin(state) {
      return !!state.token;
    },
  },
  actions: {
    async kakaoLogin(code) {
      await axios({
        url: index.account.postLogin(),
        method: "POST",
        data: {
          accessToken: code,
        },
      })
        .then((res) => {
          this.token = res.data.data.accessToken;
          this.userInfo.userId = res.data.data.userId;
          this.userInfo.name = res.data.data.name;
          this.userInfo.email = res.data.data.email;
          this.userInfo.profileImage = res.data.data.profileImage;
          router.replace({ name: "introfirstpage" });
          console.log(this.token);
          console.log(this.userInfo);
          console.log("isLogin : ", this.isLogin);
        })
        // .then(router.push({ name: "introfirstpage" }))
        .catch((e) => {
          console.log("error", e);
          // router.push({ name: "intro" })
        });
    },
    async refreshToken() {
      await axios({
        url: index.account.getRefreshToken(),
        method: "GET",
      })
        .then((res) => {
          console.log(res);
          this.token = res.data.data;
        })
        .catch((err) => {
          Promise.reject(err);
        });
    },
    getMyArticles() {
      axios({
        url: index.account.getMyArticles() + this.userInfo.userId,
        method: "GET",
        headers: { "X-AUTH-TOKEN": this.token },
      }).then((res) => {
        console.log(res.data);
        this.myArticles = res.data.data;
        let visited = 0;
        for (let item of res.data.data) {
          visited += item.answers.length;
        }
        this.planetVisited = visited;
      });
    },
  },
});
