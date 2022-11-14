import { defineStore } from "pinia";
import { useAccountStore } from "./account";
import index from "@/api/index";
import axios from "axios";

export const usePlanetStore = defineStore("planet", {
  state: () => ({
    completeCount: 0,
    minimumConditionsMet: false,
    articleRequest: {
      userId: Number,
      answers: [
        // {
        //   questionId: Number,
        //   contents: String,a
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
        imgUrl: "url",
        state: "CLOSE",
      });
    },
    finishTravel() {
      console.log(this.articleRequest);
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
    },
  },
});
