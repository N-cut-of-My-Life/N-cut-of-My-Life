import { defineStore } from "pinia";
import axios from "axios";

export const useResultStore = defineStore("result", {
  persist: {
    storage: sessionStorage,
  },
  state: () => ({
    from: "",
    resultArticle: [],
    isInResult: [
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
      false,
    ],
    resultContents: [{}, {}, {}, {}, {}, {}, {}, {}, {}, {}],
    imageFile: null,
  }),
  actions: {
    async downloadResultImage(imageUrl) {
      await axios.get(imageUrl, { responseType: "blob" }).then((response) => {
        this.imageFile = response.data;
      });
    },
    isExsitingAnswer() {
      this.resultArticle.forEach((data) => {
        this.isInResult[data.questionId] = true;
        this.resultContents[data.questionId] = {
          contents: data.contents,
          imageUrl: data.imageUrl,
        };
      });
    },
  },
});
