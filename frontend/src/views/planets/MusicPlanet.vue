<template>
    <div class="jumbotron">
        <div class="title">둠칫둠칫 행성</div>
    </div>
    <img :src="images[currentImage]" />
    <div class="other">
        <b-button @click="gotoPage({ name: 'planetlist' })" class="button" size="sm">다른 행성 가기</b-button>
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
                require('@/assets/PlanetSpeech/MusicSpeech/music_bubble_1.png'),
                require('@/assets/PlanetSpeech/MusicSpeech/music_bubble_2.png'),
                require('@/assets/PlanetSpeech/MusicSpeech/music_bubble_3.png'),
                require('@/assets/PlanetSpeech/MusicSpeech/music_bubble_4.png'),
                require('@/assets/PlanetSpeech/MusicSpeech/music_bubble_5.png'),
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
    top: 0;
    left: 0;
    right: 3%;
    bottom: 17%;
    /* height: 50%; */
    margin: auto;
}

.title {
    padding-left: 1%;
    padding-top: 1%;
    color: aliceblue;
    font-size: 1.5vw;
    font-weight: 400;
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
    left: 5%;
    bottom: 5%;
    margin: auto;
    text-align: right;
    font-size: 3rem;
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

.button_2 {
    border-radius: 0.8vw;
    border-color: orange;
    background-color: orange;
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
        transform: translateY(-10px);
    }
}
</style>