<template>
  <div class="jumbotron">
    <div class="title">은 하 갤 러 리</div>
    
    <!-- masonry 영역 ver2 -->
     <section class="gallery">
        <div class="grid-container">
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/300' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/800' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/304' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/700' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/402' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/300' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/700' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/850' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/420' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/380' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/300' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/660' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/700' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/200' alt="blakcpink">
          </div>
          <div class="grid-item">
              <img class="content" src='https://picsum.photos/500/250' alt="blakcpink">
          </div>
        </div>
      </section>

  </div>
</template>

<script setup>
import imagesLoaded from 'imagesloaded'

const resizeGridItems = () => {
    const items = document.querySelectorAll('.grid-item')
    items.forEach(item=>{
        imagesLoaded(item,(instance)=>{
            const item = instance.elements[0];
            const grid = document.querySelector('.grid-container')
            const rowHeight = parseInt(window.getComputedStyle(grid).getPropertyValue('grid-auto-rows'))
            const rowGap = parseInt(window.getComputedStyle(grid).getPropertyValue('grid-row-gap'))
            const rowSpan =  Math.floor((item.querySelector('.content').offsetHeight+rowGap)/(rowHeight+rowGap))
            item.style.gridRowEnd = "span "+ rowSpan;
        })
    })
    const gallery = document.querySelector('.grid-container')
    imagesLoaded(gallery,()=>{
        document.querySelectorAll('.grid-item').forEach(item=> item.style.visibility = 'visible')
    })
}

window.addEventListener('load',resizeGridItems)
window.addEventListener('resize',resizeGridItems)
</script>

<style scoped>
.title {
    text-align: center;
    padding-top: 3%;
    color: orange;
    font-size: 2vw;
    font-weight: 600;
}

.jumbotron {
    /* background: url("@/assets/galaxy_gallery.svg") no-repeat center center fixed; */
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
    height: 100vh;
    width: 100%;
    margin:0;
    padding:0;
}

/* ver2용 css */
 
.grid-container {
    padding-top: 5rem;
    padding-bottom: 5rem;
    display: grid;
    max-width: 100rem;
    margin: 0 auto;
    gap: 2rem;
    grid-template-columns: repeat(auto-fit,minmax(20rem,1fr));
    grid-auto-rows: 0.5rem;
}

.grid-item {
    border-radius: 2rem;
    overflow: hidden;
    visibility: hidden;
}
img{
    width:100%;
}

</style>