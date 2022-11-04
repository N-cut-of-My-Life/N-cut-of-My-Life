<template>
    <div class="jumbotron">
        <div class="title">지니 행성</div>
    </div>
    <div class="img-box">
        <img src="@/assets/genie_charac.png" class="img-genie genie-bounce" />
    </div>
    <img class="bubble" :src="images[currentImage]" />
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
            <div class="wave" v-b-modal.modal-genie>
                <span style="--i: 1">소</span>
                <span style="--i: 2">원</span>
                <span style="--i: 3">&nbsp;</span>
                <span style="--i: 4">적</span>
                <span style="--i: 5">으</span>
                <span style="--i: 6">러</span>
                <span style="--i: 7">&nbsp;</span>
                <span style="--i: 8">가</span>
                <span style="--i: 9">기</span>
                <span style="--i: 10">!</span>
            </div>
        </b-button>
    </div>

    <b-modal centered no-stacking id="modal-genie" hide-header hide-footer style="text-align: center; border-radius: 1vw;">
        <img data-bs-dismiss="modal" aria-label="Close" class="x_button" src="@/assets/xButton/x_genie.svg" style="cursor:pointer; float: right;"/>
        <div style="font-size:1.3vw; margin-top: 5%; margin-bottom: 3%; font-weight: 400;">당신의 소원을 적어주세요!</div>
        <b-container ref="form" style="margin-bottom:3.8%">
            <b-form-textarea id="content" placeholder="" rows="10" max-rows="15" required style="border-radius: 1vw; background-color:#e3ecfc">
            </b-form-textarea>
        </b-container>
        <b-button text @click="submit" style="color: #ffffff; background-color: #9985c6; border: none; border-radius: 1vw;">저장
        </b-button>
    </b-modal>
</template>

<script>
export default {
    data() {
        return {
            images: [
                require('@/assets/PlanetSpeech/GenieSpeech/genie_bubble_1.svg'),
                require('@/assets/PlanetSpeech/GenieSpeech/genie_bubble_2.svg'),
                require('@/assets/PlanetSpeech/GenieSpeech/genie_bubble_3.svg'),
                require('@/assets/PlanetSpeech/GenieSpeech/genie_bubble_4.svg'),
                require('@/assets/PlanetSpeech/GenieSpeech/genie_bubble_5.svg'),
            ],
            currentImage: 0,
            elementVisible: false
        }
    },
    updated() {
        if(this.currentImage==(this.images.length-1)){
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
        }
    }
}
</script>

<style scoped>
.img-box {
    text-align: center;
}

.img-genie {
    width: 20%;
    position: absolute;
    /* top: 0;
    left: 0; */
    right: 15%;
    bottom: 3%;
    /* height: 50%; */
    margin: auto;
}

.genie-bounce {
    animation: bounce .75s infinite alternate;
    -webkit-animation: bounce .75s infinite alternate;

}

@keyframes bounce {
    from {
        transform: translateY(0px);
    }

    to {
        transform: translateY(-5px);
    }
}

@-webkit-keyframes bounce {
    from {
        transform: translateY(0px);
    }

    to {
        transform: translateY(-5px);
    }
}

body {
    margin: 0;
}

.bubble {
    position: absolute;
    right: 26%;
    top: 20%;
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
    background: url("@/assets/PlanetBackground/genie.svg") no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
    height: 100vh;
    width: 100%;
    margin: 0;
    padding: 0;
    opacity: 0.8;
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
    bottom: 12%;
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
    background-color: #ffffff;
    color: #141414;
    border-radius: 0.8vw;
    border-color: #ffffff;
}

.button_2 {
    border-radius: 0.8vw;
    /* border-color: #81c6e8; */
    border:none;
    background-color: #9cb4cc;
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
    min-width: calc(200px + 12px);
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

<style>
#modal-genie .modal-content {
    background-color: #b1afff;
    ;
}

#modal-genie .modal-header {
    /* border-bottom: #1f1f1f; */
}

#modal-genie .modal-header .btn-close {
    color: white;
}
</style>