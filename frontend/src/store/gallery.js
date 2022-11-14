import { defineStore } from "pinia";
import index from "@/api/index";
import { useAccountStore } from "@/store/account";
import axios from "axios";

export const useGalleryStore = defineStore("gallery", {
  state: () => ({
    galleryList: [],
  }),
  getters: {},
  actions: {
    getGalleryList(userID) {
      axios({
        url: index.answer.getGallery(userID),
        method: "GET",
        headers: { "X-AUTH-TOKEN": useAccountStore().token },
      })
        .then((res) => {
          console.log("저기");
          console.log(res.data);
          this.galleryList = res.data;
          console.log("list 출력하기");
          console.log(this.galleryList);
        })
        .catch((e) => {
          console.log("error", e);
        });
    },
  },
});
