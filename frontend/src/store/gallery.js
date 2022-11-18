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
        url: index.answer.getGalleryList(userId),
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
    getGalleryOne(answerId, userId) {
      axios({
        url: index.answer.getGalleryOne(answerId, userId),
        method: "GET",
        headers: { "X-AUTH-TOKEN": useAccountStore().token },
      })
        .then((res) => {
          for (let gallery of this.galleryList) {
            if (gallery.answerId == res.data.data.answerId) {
              gallery.like = res.data.data.like;
              gallery.isMine = res.data.data.isMine;
              break;
            }
          }
        })
        .catch((e) => {
          console.log("error", e);
        });
    },
    addLike(answerId, userId) {
      axios({
        url: index.answer.manageLike(answerId, userId),
        method: "POST",
        headers: { "X-AUTH-TOKEN": useAccountStore().token },
      })
        .then((res) => {
          console.log(res.data);
          this.getGalleryOne(answerId, userId);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    deleteLike(answerId, userId) {
      axios({
        url: index.answer.manageLike(answerId, userId),
        method: "DELETE",
        headers: { "X-AUTH-TOKEN": useAccountStore().token },
      })
        .then((res) => {
          console.log(res.data);
          this.getGalleryOne(answerId, userId);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
});
