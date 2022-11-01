<template>
    <div class="jumbotron">
        <div class="title">훌쩍훌쩍 행성</div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop waterdrop-small"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop waterdrop-small"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop waterdrop-small"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop waterdrop-small"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop waterdrop-small"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop waterdrop-big"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop waterdrop-small"></div>
        <div class="waterdrop waterdrop-small"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop waterdrop-small"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop waterdrop-small"></div>
        <div class="waterdrop"></div>
        <div class="waterdrop waterdrop-small"></div>
        <div class="waterdrop waterdrop-big"></div>
    </div>
    <img :src="images[currentImage]" />
    <div class="other">
        <b-button @click="gotoPage({ name: 'planetlist' })" class="button_prev" size="sm"><strong>&lt;</strong>&nbsp;&nbsp;다른 행성 가기</b-button>
    </div>
    <div class="jump">
        <b-button @click="previousImage()" class="button" size="sm" :disabled="currentImage === 0">
            뒤로
        </b-button>
        &nbsp;
        <b-button @click="nextImage()" class="button" size="sm" :disabled="currentImage === (images.length - 1)">
            다음
        </b-button>
    </div>
    <div class="last">
        <b-button v-if="currentImage === (images.length - 1)" class="button_2" size="md">
            <div class="wave">
                <span style="--i: 1">슬</span>
                <span style="--i: 2">펐</span>
                <span style="--i: 3">던</span>
                <span style="--i: 4">&nbsp;</span>
                <span style="--i: 5">기</span>
                <span style="--i: 6">억</span>
                <span style="--i: 7">&nbsp;</span>
                <span style="--i: 8">남</span>
                <span style="--i: 9">기</span>
                <span style="--i: 10">기</span>
            </div>
        </b-button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            images: [
                require('@/assets/PlanetSpeech/SadSpeech/sad_bubble_1.svg'),
                require('@/assets/PlanetSpeech/SadSpeech/sad_bubble_2.svg'),
                require('@/assets/PlanetSpeech/SadSpeech/sad_bubble_3.svg'),
            ],
            currentImage: 0
        }
    },
    methods: {
        nextImage() {
            if (this.currentImage !== (this.images.length - 1))
                this.currentImage++;
        },

        previousImage() {
            if (this.currentImage !== 0)
                this.currentImage--;
        },

        gotoPage(link) {
            this.$router.push(link)
        }
    }
}
</script>

<style scoped>
body {
    margin: 0;
}

img {
    position: absolute;
    /* top: 0;
    left: 0; */
    right: 22%;
    bottom: 34%;
    /* height: 50%; */
    margin: auto;
}

.title {
    padding-right: 1%;
    padding-top: 1%;
    color: aliceblue;
    font-size: 1.5vw;
    font-weight: 400;
    text-align: right;
}

.jumbotron {
    background: url("@/assets/PlanetBackground/sad.svg") no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
    height: 100vh;
    width: 100%;
    margin: 0;
    padding: 0;
    z-index: -10;
}

.jump {
    position: absolute;
    right: 5%;
    bottom: 5%;
    margin: auto;
    text-align: right;
    font-size: 3rem;
}

.other {
    position: absolute;
    left: 1%;
    top: 2.5%;
    margin: auto;
}

.last {
    position: absolute;
    bottom: 50%;
    left: 45%;
    margin: auto;
}

.button {
    background-color: #a28dc4;
    color: #ffffff;
    border-radius: 0.8vw;
    border-color: #a28dc4;
}

.button_prev {
    background-color:#ffffff;
    color: #141414;
    border-radius: 0.8vw;
    border-color: #ffffff;
}

.button_2 {
    border-radius: 0.8vw;
    /* border-color: #81c6e8; */
    background-color: #81c6e8;
    position: relative;
    margin: 300px auto 0;
}


.wave {
    position: relative;
    /* -webkit-box-reflect: below -1px linear-gradient(transparent, #FFFFFF); */
}

.wave span {
    position: relative;
    display: inline-block;
    color: #FFFFFF;
    font-size: 1.2vw;
    text-transform: uppercase;
    animation: wave 3s infinite;
    animation-delay: calc(0.1s * var(--i));
}

.waterdrop {
  position: absolute;
  top: -15px;
  background: #659ac7;
  border-radius: 50%;
  width: 7px;
  height: 7px;
  transform-origin: 50% 100%;
  animation: rain 1.2s linear infinite both;
}

.waterdrop:before {
  position: absolute;
  content: "";
  display: block;
  top: -1px;
  right: 0;
  width: 0px;
  height: 0px;
  border-width: 3px;
  border-style: solid;
  border-color: #659ac7;
  border-bottom-color: transparent;
  border-left-color: transparent;
  transform: skewY(-35deg);
}

.waterdrop-small {
  top: -14px;
  width: 5px;
  height: 5px;
  background: #4c7395;
  animation-duration: 1.8s;
}

.waterdrop-small:before {
  border-width: 2px;
  border-top-color: #4c7395;
  border-right-color: #4c7395;
}

.waterdrop-big {
  top: -17px;
  width: 9px;
  height: 9px;
  background: #7fc1f9;
  animation-duration: 1s;
}

.waterdrop-big:before {
  border-width: 4px;
  border-top-color: #7fc1f9;
  border-right-color: #7fc1f9;
}

.waterdrop:nth-child(1){
  left: 10%;
  animation-delay: 0.1s;
  animation-duration: 1.1s;
}

.waterdrop:nth-child(2){
  left: 57%;
  animation-delay: 0.03s;
  animation-duration: 1.13s;
}

.waterdrop:nth-child(3){
  left: 57.5%;
  animation-delay: 1.5s;
  animation-duration: 0.91s;
}

.waterdrop:nth-child(4){
  left: 67.5%;
  animation-delay: 0.27s;
  animation-duration: 0.9s;
}

.waterdrop:nth-child(5){
  left: 95%;
  animation-delay: 2.38s;
  animation-duration: 0.85s;
}

.waterdrop:nth-child(6){
  left: 55%;
  animation-delay: 3.2s;
}

.waterdrop:nth-child(7){
  left: 42.5%;
  animation-delay: 0.1s;
}

.waterdrop:nth-child(8){
  left: 50%;
  animation-delay: 2.76s;
}

.waterdrop:nth-child(9){
  left: 70%;
  animation-delay: 1.6s;
}

.waterdrop:nth-child(10) {
  left: 79%;
  animation-delay: 2.2s;
}

.waterdrop:nth-child(11) {
  left: 92%;
  animation-delay: 1.94s;
}

.waterdrop:nth-child(12) {
  left: 7.5%;
  animation-delay: 4s;
}

.waterdrop:nth-child(13) {
  left: 42%;
  animation-delay: 3.6s;
}

.waterdrop:nth-child(14) {
  left: 75%;
  animation-delay: 0.32s;
}

.waterdrop:nth-child(15) {
  left: 38.75%;
  animation-delay: 0.6s;
}

.waterdrop:nth-child(16) {
  left: 89.75%;
}

.waterdrop:nth-child(17) {
  left: 76.5%;
  animation-delay: 0.48s;
}

.waterdrop:nth-child(18) {
  left: 44.75%;
  animation-delay: 1.65s;
}

.waterdrop:nth-child(19) {
  left: 87%;
}
.waterdrop:nth-child(20){
  left: 5%;
  animation-delay: 0.1s;
  animation-duration: 1.1s;
}

.waterdrop:nth-child(21){
  left: 15%;
  animation-delay: 0.03s;
  animation-duration: 1.13s;
}

.waterdrop:nth-child(22){
  left: 53.75%;
  animation-delay: 1.5s;
  animation-duration: 0.91s;
}

.waterdrop:nth-child(23){
  left: 58.75%;
  animation-delay: 0.27s;
  animation-duration: 0.9s;
}

.waterdrop:nth-child(24){
  left: 47.5%;
  animation-delay: 2.38s;
  animation-duration: 0.85s;
}

.waterdrop:nth-child(25){
  left: 10%;
  animation-delay: 3.2s;
}

.waterdrop:nth-child(26){
  left: 46.25%;
  animation-delay: 0.1s;
}

.waterdrop:nth-child(27){
  left: 25%;
  animation-delay: 2.76s;
}

.waterdrop:nth-child(28){
  left: 40%;
  animation-delay: 1.6s;
}

.waterdrop:nth-child(29) {
  left: 40%;
  animation-delay: 2.2s;
}

.waterdrop:nth-child(30) {
  left: 50%;
  animation-delay: 1.94s;
}

.waterdrop:nth-child(31) {
  left: 3.75%;
  animation-delay: 4s;
}

.waterdrop:nth-child(32) {
  left: 19.375%;
  animation-delay: 3.6s;
}

.waterdrop:nth-child(33) {
  left: 41.775%;
  animation-delay: 0.32s;
}

.waterdrop:nth-child(34) {
  left: 38.25%;
  animation-delay: 0.6s;
}

.waterdrop:nth-child(35) {
  left: 22.375%;
}

.waterdrop:nth-child(36) {
  left: 40.5%;
  animation-delay: 0.48s;
}

.waterdrop:nth-child(37) {
  left: 8.5%;
  animation-delay: 1.65s;
}

.waterdrop:nth-child(38) {
  left: 37.5%;
}

@keyframes rain {
  0% {
    transform: translate(0, 0) scaleX(1) scaleY(1) rotate(-16deg);
  }

  70% {
    transform: translate(-10vw, 99vh) scaleX(1) scaleY(1) rotate(-16deg);
  }

  100% {
    transform: translate(-10vw, 100vh) scaleX(3) scaleY(0) rotate(0);
  }
}


@keyframes wave {

    0%,
    40%,
    100% {
        transform: translateY(0);
    }

    20% {
        transform: translateY(-10px);
    }
}
</style>