const HOST = 'https://k7b105.p.ssafy.io/api/'

const ANSWER = 'answer/'
const ARTICLE = 'article/'

export default {
  answer: {
    getLastWord: () => HOST + ANSWER,
  },

  article: {
    getDiary: (userID) => HOST + ARTICLE + `${userID}`
  }
}