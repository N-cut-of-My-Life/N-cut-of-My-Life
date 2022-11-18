const HOST = "https://k7b105.p.ssafy.io/api/";

const ANSWER = "answer/";
const ARTICLE = "article/";
const USER = "user/";

export default {
  account: {
    postLogin: () => HOST + USER + "login",
    getRefreshToken: () => HOST + USER + "token",
    getMyArticles: () => HOST + ARTICLE,
  },

  answer: {
    getGalleryList : (userId) => HOST + ANSWER + `${userId}`,
    getGalleryOne : (answerId, userId) => HOST + ANSWER + `${answerId}/${userId}`,
    manageLike: (answerId, userId) => HOST + ANSWER + `${answerId}/${userId}`,
  },

  article: {
    getDiary: (userID) => HOST + ARTICLE + `${userID}`,
    postDiary: () => HOST + ARTICLE,
  },
};
