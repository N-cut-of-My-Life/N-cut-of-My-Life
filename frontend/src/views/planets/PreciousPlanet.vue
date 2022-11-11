<template>
    <div class="jumbotron">
        <div class="title">넌너무소중 행성</div>
    </div>
    <img v-show="!elementVisible" class="bubble" :src="images[currentImage]" alt="" />
    <audio id="mix_precious" loop autoplay volume="0.3">
        <source src="@/assets/audio/mix_precious.mp3" type="audio/mp3">
    </audio>
    <audio id="trainsound" volume="0.3">
        <source src="@/assets/audio/train_sound.mp3" type="audio/mp3">
    </audio>
    <img src="@/assets/trainman.svg" class="trainman" alt="" />
    <div class="other">
        <b-button @click="gotoPage({ name: 'planetlist' })" variant="warning" class="button_prev" size="sm">
            <strong>&lt;</strong>&nbsp;&nbsp;다른 행성 가기
        </b-button>
    </div>
    <div v-show="!elementVisible" class="jump">
        <b-button @click="previousImage()" variant="warning" class="button" size="sm" :disabled="currentImage === 0">
            뒤로
        </b-button>
        &nbsp;
        <b-button @click="nextImage()" variant="warning" class="button" size="sm"
            :disabled="currentImage === (images.length - 1)">
            다음
        </b-button>
    </div>
    <div v-if="currentImage === (images.length - 1)" class="last">
        <b-button v-show="elementVisible && !elementVisible_2 && !elementVisible_3" variant="warning" class="button_2"
            size="md">
            <div class="wave" v-b-modal.modal-precious>
                <span style="--i: 1">소</span>
                <span style="--i: 2">중</span>
                <span style="--i: 3">한</span>
                <span style="--i: 4">&nbsp;</span>
                <span style="--i: 5">사</span>
                <span style="--i: 6">람</span>
                <span style="--i: 7">에</span>
                <span style="--i: 8">게</span>
                <span style="--i: 9">&nbsp;</span>
                <span style="--i: 10">편</span>
                <span style="--i: 11">지</span>
                <span style="--i: 12">&nbsp;</span>
                <span style="--i: 13">쓰</span>
                <span style="--i: 14">기</span>
                <span style="--i: 15">!</span>
            </div>
        </b-button>
    </div>
    <b-modal :no-close-on-backdrop="true" id="modal-precious" centered no-stacking hide-header hide-footer
        style="text-align: center; border-radius: 1vw;">
        <img data-bs-dismiss="modal" aria-label="Close" class="x_button" src="@/assets/xButton/x_happy.svg"
            style="cursor:pointer; float: right;" alt="" />
        <div style="font-size:1.3vw; margin-top: 5%; margin-bottom: 4%; font-weight: 400;">소중한 사람에게 짧은 편지를 남겨보세요!</div>
        <b-container ref="form" style="margin-bottom:3.8%">
            <b-row style="margin-bottom:3%">
                <b-col cols="1" style="padding:0; font-size: 1vw; color:#C6753E; text-align: right;">
                    To.
                </b-col>
                <b-col cols="11">
                    <b-form-input id="dear" placeholder=""
                        style="background-color: #FDFCFA; padding:2px 4px; border:none">
                    </b-form-input>
                </b-col>
            </b-row>
            <b-form-textarea id="content" placeholder="" rows="10" max-rows="15" required
                style="background-color: #FDFCFA; border:none;">
            </b-form-textarea>
        </b-container>
        <b-button text @click="submit()" data-bs-dismiss="modal" aria-label="Close"
            style="color: #ffffff; background-color: #C6753E; border: none; border-radius: 1vw;">저장
        </b-button>
    </b-modal>
    <img v-show="elementVisible_2" class="bubble" :src="images_2[currentImage_2]" alt="" />
    <div v-show="elementVisible_2" class="jump">
        <b-button @click="previousImage_2()" variant="warning" class="button" size="sm"
            :disabled="currentImage_2 === 0">
            뒤로
        </b-button>
        &nbsp;
        <b-button @click="nextImage_2()" variant="warning" class="button" size="sm"
            :disabled="currentImage_2 === (images_2.length - 1)">
            다음
        </b-button>
    </div>
    <div class="last">
        <b-button v-show="elementVisible_4" @click="trainLaunch" class="button_2" size="md">
            <div class="wave">
                <span style="--i: 1">넌</span>
                <span style="--i: 2">너</span>
                <span style="--i: 3">무</span>
                <span style="--i: 4">소</span>
                <span style="--i: 5">중</span>
                <span style="--i: 6">&nbsp;</span>
                <span style="--i: 7">행</span>
                <span style="--i: 8">성</span>
                <span style="--i: 9">&nbsp;</span>
                <span style="--i: 10">여</span>
                <span style="--i: 11">행</span>
                <span style="--i: 12">&nbsp;</span>
                <span style="--i: 13">마</span>
                <span style="--i: 14">치</span>
                <span style="--i: 15">기</span>
            </div>
        </b-button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            images: [
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_1.svg'),
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_2.svg'),
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_3.svg'),
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_4.svg'),
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_5.svg'),
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_6.svg'),
            ],
            images_2: [
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_7.svg'),
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_8.svg'),
            ],
            currentImage: 0,
            currentImage_2: 0,
            elementVisible: false,
            elementVisible_2: false,
            elementVisible_3: false,
            elementVisible_4: false
            // modalShow: false
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

        nextImage_2() {
            if (this.currentImage_2 !== (this.images_2.length - 1))
                this.currentImage_2++;
        },

        previousImage_2() {
            if (this.currentImage_2 !== 0)
                this.currentImage_2--;
        },

        gotoPage(link) {
            this.$router.push(link)
        },

        submit() {
            this.elementVisible = true
            this.elementVisible_3 = true
            setTimeout(() => this.elementVisible_2 = true, 800)
            // this.elementVisible_2 = true
        }

    },
    updated() {
        if (this.currentImage == (this.images.length - 1)) {
            setTimeout(() => this.elementVisible = true, 2000)
        }
        if (this.currentImage_2 == (this.images_2.length - 1)) {
            setTimeout(() => this.elementVisible_4 = true, 2000)
        }
    },
}
</script>
<script setup>
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()
const code = route.query.code;

// 클릭 시 열차 출발
const trainLaunch = () => {
    console.log(code)
    const train = document.querySelector('.trainman')
    document.getElementById('mix_precious').pause();
    document.getElementById('trainsound').play();
    train.classList.add('train-launch')
    train.addEventListener('animationend', (event) => {
        if (event.animationName.includes('launch')) {
            router.push({ name: 'planetlist' })
        }
    });
};
</script>

<style scoped>
.trainman {
    position: fixed;
    top: 51%;
    left: 78%;
}

body {
    margin: 0;

}

.bubble {
    position: absolute;
    /* top: 0;
    left: 0; */
    right: 2%;
    bottom: 45%;
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
    background: url("@/assets/PlanetBackground/precious.svg") no-repeat center center fixed;
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
    bottom: 50%;
    left: 45%;
    margin: auto;
}

.button {
    background-color: #9B3D54;
    color: #F4DFCA;
    border-radius: 0.8vw;
    border-color: #a28dc4;
}

.x_button {
    width: 4%;
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
    background-color: #C6753E;
    position: relative;
    margin: 300px auto 0;
    transition: all 0.3s ease-in-out 0s;
}

.button_2::before {
    content: '';
    border-radius: 1000px;
    min-width: calc(260px + 12px);
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

.train-launch {
    animation-name: launch;
    animation-duration: 4s;
    animation-timing-function: ease-in, ease-in-out;
    animation-fill-mode: forwards;
}

@keyframes launch {
    0% {
        transform: translateX(0px);
    }

    100% {
        transform: translate(-450px, 300px);
    }
}
</style>
<style>
#modal-precious .modal-content {
    background-color: #F5E2CE;
}

/* 아래 주석 해제 시, 백그라운드 클릭 시 모달 닫힘 */
/* #modal-precious .modal-backdrop {
    display: none;
    z-index: -1;
} */
.form-control {
    box-shadow: none !important;
    outline: none !important;
}
</style>