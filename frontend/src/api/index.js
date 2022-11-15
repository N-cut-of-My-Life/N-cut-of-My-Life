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
    getGallery: (userID) => HOST + ANSWER + `${userID}`,
    manageLike: (answerID, userID) => HOST + ANSWER + `${answerID}/${userID}`,
  },

  article: {
    getDiary: (userID) => HOST + ARTICLE + `${userID}`,
    postDiary: () => HOST + ARTICLE,
  },
};
