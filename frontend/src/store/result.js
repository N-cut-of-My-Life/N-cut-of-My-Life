import { defineStore } from "pinia";
import axios from "axios";

export const useResultStore = defineStore("result", {
  state: () => ({
    from: "",
    resultArticle: {},
    imageFile: null,
  }),
  actions: {
    async downloadResultImage(imageUrl) {
      await axios.get(imageUrl, { responseType: "blob" }).then((response) => {
        this.imageFile = response.data;
      });
    },
  },
});
