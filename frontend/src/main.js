import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { createPinia } from "pinia";
import { createPersistedState } from "pinia-plugin-persistedstate";
import BootstrapVue3 from "bootstrap-vue-3";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue-3/dist/bootstrap-vue-3.css";
import MasonryWall from "@yeger/vue-masonry-wall";
import "regenerator-runtime";
import VueHtml2Canvas from "vue-html2canvas";

const pinia = createPinia();
pinia.use(createPersistedState());

createApp(App)
  .use(pinia)
  .use(router)
  .use(BootstrapVue3)
  .use(MasonryWall)
  .use(VueHtml2Canvas)
  .mount("#app");

window.Kakao.init(process.env.VUE_APP_KAKAO_JS_SDK);
