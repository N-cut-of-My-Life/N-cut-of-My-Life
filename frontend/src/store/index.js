import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', {
  state: () => ({ count: 0 }),
  getters: {
    double: state => state.count * 2,
  },
  actions: {
    increment() {
      this.count++
    },
  },
})

export const useMusicStore = defineStore('music', {
  state: () => ({ count: 0 }),
  getters: {
  },
  actions: {
    toggleMute() {
      var myAudio = document.getElementById('myaudios');
      myAudio.muted = !myAudio.muted;
    }
  },
})