import { defineStore } from "pinia";
import { useAccountStore } from "./account";
import index from "@/api/index";
import axios from "axios";
import { uploadToFirebase } from "@/firebase.js";

export const usePlanetStore = defineStore("planet", {
  persist: {
    storage: sessionStorage,
  },
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
    completePlanet(planetId, answer, openState = "CLOSE", imgFile) {
      this.completeCount++;
      if (this.completeCount === 3) {
        this.minimumConditionsMet = true;
      }
      this.articleRequest.userId = useAccountStore().userInfo.userId;
      this.articleRequest.answers.push({
        questionId: planetId,
        contents: answer,
        imgUrl: null,
        state: openState,
      });
      if (imgFile != null) {
        this.images.set(this.articleRequest.answers.length - 1, imgFile);
      }
    },
    completeMusicPlanet(planetId, answer, openState = "CLOSE", imgUrl) {
      this.completeCount++;
      if (this.completeCount === 3) {
        this.minimumConditionsMet = true;
      }
      this.articleRequest.userId = useAccountStore().userInfo.userId;
      this.articleRequest.answers.push({
        questionId: planetId,
        contents: answer,
        imgUrl: imgUrl,
        state: openState,
      });
      if (imgUrl != null) {
        this.images.set(
          this.articleRequest.answers.length - 1,
          new Blob(imgUrl)
        );
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
