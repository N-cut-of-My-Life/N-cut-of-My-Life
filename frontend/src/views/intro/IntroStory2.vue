<template>
    <video muted autoplay loop playbackRate="0.7">
      <source src="@/assets/intro_video.mp4" type="video/mp4">
    </video>
    <h1>
      당신은<br/>
      돌아가고 싶은 순간이<br/>
      있으신가요?
    </h1>
  </template>
  
  <script setup>
  </script>
  
  <style scoped>
  @font-face {
    font-family: 'kakao';
    src: url('@/fonts/KakaoBold.ttf') format('truetype');
  }
  
  body {
    margin: 0;
  }
  
  video {
    position: fixed;
    right: 0;
    bottom: 0;
    min-width: 100%;
    min-height: 100%;
    width: auto;
    height: auto;
    z-index: -100;
    background-size: cover;
  }
  
  h1{
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    height: 25%;
    margin: auto;
    text-align: center;
    color:aliceblue;
    font-size: 3rem;
    }
  </style>