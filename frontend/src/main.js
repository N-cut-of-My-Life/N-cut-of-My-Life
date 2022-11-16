import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { createPinia } from "pinia";
import BootstrapVue3 from "bootstrap-vue-3";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue-3/dist/bootstrap-vue-3.css";
import MasonryWall from "@yeger/vue-masonry-wall";
import "regenerator-runtime";
import VueHtml2Canvas from "vue-html2canvas";

createApp(App)
  .use(createPinia())
  .use(router)
  .use(BootstrapVue3)
  .use(MasonryWall)
  .use(VueHtml2Canvas)
  .mount("#app");
window.Kakao.init(process.env.VUE_APP_KAKAO_JS_SDK);
