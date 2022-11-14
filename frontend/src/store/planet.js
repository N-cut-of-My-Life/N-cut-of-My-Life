import { defineStore } from "pinia";
import { useAccountStore } from "./account";
import index from "@/api/index";
import axios from "axios";
import { uploadToFirebase } from "@/firebase.js";

export const usePlanetStore = defineStore("planet", {
  state: () => ({
    completeCount: 0,
    minimumConditionsMet: false,
    articleRequest: {
      userId: Number,
      answers: [],
    },
    images: new Map(),
  }),
  actions: {
    completePlanet(planetId, answer, imgFile) {
      this.completeCount++;
      if (this.completeCount === 3) {
        this.minimumConditionsMet = true;
      }
      this.articleRequest.userId = 1;
      this.articleRequest.answers.push({
        questionId: planetId,
        contents: answer,
        imgUrl: null,
        state: "CLOSE",
      });
      console.log(imgFile);
      if (imgFile != null) {
        this.images.set(this.articleRequest.answers.length - 1, imgFile);
      }
    },
    async finishTravel() {
      await this.saveImageToFirebase().then(() => {
        axios({
          url: index.article.postDiary(),
          method: "POST",
          headers: { "X-AUTH-TOKEN": useAccountStore().token },
          data: this.articleRequest,
        })
          .then((res) => {
            console.log(res.data);
          })
          // .then(router.push({ name: "introfirstpage" }))
          .catch((e) => {
            console.log("error", e);
            // router.push({ name: "intro" })
          });
      });
    },
    async saveImageToFirebase() {
      for (const [key, value] of this.images) {
        let fileLocation =
          "image/" +
          Date.now().toString().trim() +
          useAccountStore().userInfo.id;
        console.log(fileLocation);
        await uploadToFirebase(fileLocation, value).then((url) => {
          this.articleRequest.answers.at(key).imgUrl = url;
        });
      }
    },
  },
});
