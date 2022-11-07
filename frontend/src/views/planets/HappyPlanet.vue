<template>
    <div class="jumbotron">
        <div class="title">하하호호 행성</div>
    </div>
    <!-- <vue-audio :file="audios[currentAudio]" autoplay/> -->
    <audio loop autoplay controls :src="audios[currentAudio]">
    </audio>
    <img class="story" :src="images[currentImage]" />
    <div class="other">
        <b-button @click="gotoPage({ name: 'planetlist' })" class="button_prev" size="sm">
            <strong>&lt;</strong>&nbsp;&nbsp;다른 행성 가기
        </b-button>
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
            <div class="wave" v-b-modal.modal-happy>
                <span style="--i: 1">가</span>
                <span style="--i: 2">장</span>
                <span style="--i: 3">&nbsp;</span>
                <span style="--i: 4">행</span>
                <span style="--i: 5">복</span>
                <span style="--i: 6">했</span>
                <span style="--i: 7">던</span>
                <span style="--i: 8">&nbsp;</span>
                <span style="--i: 9">순</span>
                <span style="--i: 10">간</span>
                <span style="--i: 11">&nbsp;</span>
                <span style="--i: 12">적</span>
                <span style="--i: 13">으</span>
                <span style="--i: 14">러</span>
                <span style="--i: 15">&nbsp;</span>
                <span style="--i: 16">가</span>
                <span style="--i: 17">기</span>
                <span style="--i: 18">!</span>
            </div>
        </b-button>
    </div>
 
    <b-modal centered no-stacking id="modal-happy" hide-header hide-footer :no-close-on-backdrop="true" :close-on-esc="true"
        style="text-align: center; border-radius: 1vw;">
        <img data-bs-dismiss="modal" aria-label="Close" class="x_button" src="@/assets/xButton/x_happy.svg"
            style="cursor:pointer; float: right;" />
        <div style="font-size:1.3vw; margin-top: 5%; margin-bottom: 3%; font-weight: 400;">가장 행복했던 순간을 이 곳에 적어주세요!</div>
        <b-container ref="form" style="margin-bottom:3.8%">
            <b-form-textarea id="content" placeholder="" rows="10" max-rows="15" required
                style="border-radius: 1vw; background-color: #f7eadb;">
            </b-form-textarea>
        </b-container>
        <b-button text @click="submit"
            style="color: #ffffff; background-color: #d2aa62; border: none; border-radius: 1vw;">저장
        </b-button>
    </b-modal>
</template>

<script>
// import VueAudio from 'vue-audio'

export default {
    data() {
        return {
            images: [
                require('@/assets/PlanetSpeech/HappySpeech/happy_bubble_1.svg'),
                require('@/assets/PlanetSpeech/HappySpeech/happy_bubble_2.svg'),
                require('@/assets/PlanetSpeech/HappySpeech/happy_bubble_3.svg'),
            ],
            audios: [
                require('@/assets/audio/flower-dance.mp3'),
                require('@/assets/audio/motivational-day.mp3'),
            ],
            currentImage: 0,
            elementVisible: false,
            currentAudio: 0,
        }
    },
    // components: {
    //     'vue-audio': VueAudio
    // },
    updated() {
        if (this.currentImage == (this.images.length - 1)) {
            setTimeout(() => this.elementVisible = true, 2000)
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
    }
}
</script>

<style scoped>
body {
    margin: 0;
}

.story {
    position: absolute;
    right: 40%;
    bottom: 15%;
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
    background: url("@/assets/PlanetBackground/haha.svg") no-repeat center center fixed;
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

.x_button {
    width: 4%;
}

.last {
    position: absolute;
    bottom: 20%;
    left: 9%;
    margin: auto;
}

.button {
    background-color: #90a17d;
    color: #ffffff;
    border-radius: 0.8vw;
    border: none;
}

.button_prev {
    background-color: #ffffff;
    color: #141414;
    border-radius: 0.8vw;
    border-color: #ffffff;
}

.button_2 {
    border-radius: 0.8vw;
    /* border-color: #81c6e8; */
    border: none;
    background-color: orange;
    position: relative;
    margin: 300px auto 0;
    transition: all 0.3s ease-in-out 0s;
}

.button_prev {
    background-color: #ffffff;
    color: #141414;
    border-radius: 0.8vw;
    border-color: #ffffff;
}

.button_2::before {
    content: '';
    border-radius: 1000px;
    min-width: calc(300px + 12px);
    min-height: calc(60px + 12px);
    box-shadow: 0 0 60px #ffffff;
    ;
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
<style>
#modal-happy .modal-content {
    background-color: #e6bb88;
    ;
}

#modal-happy .modal-header {
    /* border-bottom: #1f1f1f; */
}

#modal-happy .modal-header .btn-close {
    color: white;
}
</style>