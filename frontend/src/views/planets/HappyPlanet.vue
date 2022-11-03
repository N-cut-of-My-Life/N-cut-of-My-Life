<template>
    <div class="jumbotron">
        <div class="title">하하호호 행성</div>
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
    <div v-if="currentImage === (images.length - 1)" class="last" data-bs-dismiss="modal" aria-label="Close">
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

    <b-modal id="modal-happy" hide-header hide-footer style="text-align: center; border-radius: 1vw;">
        <div style="font-size:1.3vw; margin-top: 2%; font-weight: 400;">가장 행복했던 순간을 이 곳에 적어주세요!</div><br />
        <b-container ref="form">
            <b-form-textarea id="content" placeholder="" rows="10" max-rows="15" required
                style="border-radius: 1vw; background-color: #f7eadb;">
            </b-form-textarea>
        </b-container><br />
        <b-button data-bs-dismiss="modal" aria-label="Close"
            style="color: #ffffff; background-color: #a1a1a1; border: none; border-radius: 1vw;">취소</b-button>&nbsp;
        <b-button text @click="submit"
            style="color: #ffffff; background-color: #bb9f7f; border: none; border-radius: 1vw;">저장
        </b-button>
    </b-modal>
</template>

<script>
export default {
    data() {
        return {
            images: [
                require('@/assets/PlanetSpeech/HappySpeech/happy_bubble_1.svg'),
                require('@/assets/PlanetSpeech/HappySpeech/happy_bubble_2.svg'),
                require('@/assets/PlanetSpeech/HappySpeech/happy_bubble_3.svg'),
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
body {
    margin: 0;
}

img {
    position: absolute;
    /* top: 0;
    left: 0; */
    right: 40%;
    bottom: 15%;
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