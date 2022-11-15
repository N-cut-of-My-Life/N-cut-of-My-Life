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
          console.log(this.galleryList);
        })
        .catch((e) => {
          console.log("error", e);
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
