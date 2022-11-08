const HOST = 'https://k7b105.p.ssafy.io/api/'

const ANSWER = 'answer/'
const ARTICLE = 'article/'

export default {
  answer: {
    getGallery: () => HOST + ANSWER,
  },

  article: {
    getDiary: (userID) => HOST + ARTICLE + `${userID}`
  }
}