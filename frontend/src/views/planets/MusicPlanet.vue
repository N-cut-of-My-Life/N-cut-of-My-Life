<template>
    <div class="jumbotron">
        <div class="title">둠칫둠칫 행성</div>
    </div>
    <img :src="images[currentImage]" />
    <div class="other">
        <b-button @click="gotoPage({ name: 'planetlist' })" class="button_prev" size="sm">
            <strong>&lt;</strong>&nbsp;&nbsp;다른 행성 가기</b-button>
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
    <div v-if="currentImage === (images.length - 1)" class="last">
        <b-button v-show="elementVisible" class="button_2" size="md">
            <div class="wave">
                <span style="--i: 1">음</span>
                <span style="--i: 2">악</span>
                <span style="--i: 3">&nbsp;</span>
                <span style="--i: 4">검</span>
                <span style="--i: 5">색</span>
                <span style="--i: 6">하</span>
                <span style="--i: 7">러</span>
                <span style="--i: 8">&nbsp;</span>
                <span style="--i: 9">가</span>
                <span style="--i: 10">기</span>
                <span style="--i: 11">!</span>
            </div>
        </b-button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            images: [
                require('@/assets/PlanetSpeech/MusicSpeech/music_bubble_1.svg'),
                require('@/assets/PlanetSpeech/MusicSpeech/music_bubble_2.svg'),
                require('@/assets/PlanetSpeech/MusicSpeech/music_bubble_3.svg'),
                require('@/assets/PlanetSpeech/MusicSpeech/music_bubble_4.svg'),
                require('@/assets/PlanetSpeech/MusicSpeech/music_bubble_5.svg'),
            ],
            currentImage: 0,
            elementVisible: false
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
        },
    },
    updated() {
        if(this.currentImage==(this.images.length-1)){
            setTimeout(() => this.elementVisible = true, 2000)
        }
    },
}
</script>

<style scoped>
html, body {
  height: 100%;
}

.button_prev {
    background-color: #ffffff;
    color: #141414;
    border-radius: 0.8vw;
    border-color: #ffffff;
}

body {
    margin: 0;
}

img {
    position: absolute;
    top: 0;
    left: 0;
    right: 3%;
    bottom: 17%;
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
    background: url("@/assets/PlanetBackground/dumchit.svg") no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
    height: 100vh;
    width: 100%;
    margin: 0;
    padding: 0;
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
    bottom: 15%;
    left: 45%;
    margin: auto;
}

.button {
    background-color: #f0b7a7;
    color: #ffffff;
    border-radius: 0.8vw;
    border-color: #f0b7a7;
}
.button_prev {
    background-color: #ffffff;
    color: #141414;
    border-radius: 0.8vw;
    border-color: #ffffff;
}

.button_2 {
    border-radius: 1vw;
    border: none;
    background-color: orange;
    position: relative;
    margin: 300px auto 0;
    transition: all 0.3s ease-in-out 0s;
}

.button_2::before {
    content: '';
    border-radius: 1000px;
    min-width: calc(220px + 12px);
    min-height: calc(60px + 12px);
    box-shadow: 0 0 60px #ffffff;;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    opacity: 0.5;
    transition: all .3s ease-in-out 0s;
    animation: ring 1.5s infinite;
}

.button_2:hover,
.button_2:focus {
    color: #313133;
    transform: translateY(-6px);
}

.button_2:hover::before,
.button_2:focus::before {
    opacity: 1;
}

.button_2:hover::after,
.button_2:focus::after {
    animation: none;
    display: none;
}

@keyframes ring {
    0% {
        width: fit-content;
        height: fit-content;
        opacity: 1;
    }

    100% {
        width: fit-content;
        height: fit-content;
        opacity: 0;
    }
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

@keyframes wave {

    0%,
    40%,
    100% {
        transform: translateY(0);
    }

    20% {
        transform: translateY(-3px);
    }
}
</style>