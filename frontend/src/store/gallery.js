import { defineStore } from "pinia";
import index from "@/api/index";
import { useAccountStore } from "@/store/account";
import axios from "axios";

export const useGalleryStore = defineStore("gallery", {
  persist: {
    storage: sessionStorage,
  },
  state: () => ({
    galleryList: [],
    isLoading: true,
  }),
  getters: {},
  actions: {
    getGalleryList(userID) {
      this.isLoading=true;
      axios({
        url: index.answer.getGallery(userID),
        method: "GET",
        headers: { "X-AUTH-TOKEN": useAccountStore().token },
      })
        .then((res) => {
          this.galleryList = res.data.data;
          console.log(this.galleryList);
          this.isLoading=false
        })
        .catch((e) => {
          console.log("error", e);
          this.isLoading=false
        });
    },

    addLike(answerID, userID) {
      axios({
        url: index.answer.manageLike(answerID, userID),
        method: "POST",
        headers: { "X-AUTH-TOKEN": useAccountStore().token },
      })
        .then((res) => {
          console.log(res.data);
          this.getGalleryList(userID);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    deleteLike(answerID, userID) {
      axios({
        url: index.answer.manageLike(answerID, userID),
        method: "DELETE",
        headers: { "X-AUTH-TOKEN": useAccountStore().token },
      })
        .then((res) => {
          console.log(res.data);
          this.getGalleryList(userID);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
});
