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
          this.galleryList = res.data.data;
        })
        .catch((e) => {
          console.log("error", e);
        });
    },
  },
});
