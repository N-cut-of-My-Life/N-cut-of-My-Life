<template>
    <div class="jumbotron">
        <div class="title">넌너무소중 행성</div>
    </div>
    <img :src="images[currentImage]" />
    <div class="other">
        <b-button @click="gotoPage({ name: 'planetlist' })" variant="warning" class="button_prev" size="sm"><strong>&lt;</strong>&nbsp;&nbsp;다른 행성 가기</b-button>
    </div>
    <div class="jump">
        <b-button @click="previousImage()" variant="warning" class="button" size="sm" :disabled="currentImage === 0">
            뒤로
        </b-button>
        &nbsp;
        <b-button @click="nextImage()" variant="warning" class="button" size="sm" :disabled="currentImage === (images.length - 1)">
            다음
        </b-button>
    </div>
    <div class="last" data-bs-dismiss="modal" aria-label="Close">
        <b-button v-if="currentImage === (images.length - 1)" variant="warning" class="button_2" size="md">
            <div class="wave" v-b-modal.modal-precious>
                <span style="--i: 1">소</span>
                <span style="--i: 2">중</span>
                <span style="--i: 3">한</span>
                <span style="--i: 4">&nbsp;</span>
                <span style="--i: 5">사</span>
                <span style="--i: 6">람</span>
                <span style="--i: 7">&nbsp;</span>
                <span style="--i: 8">기</span>
                <span style="--i: 9">록</span>
                <span style="--i: 10">하</span>
                <span style="--i: 11">기</span>
                <span style="--i: 12">!</span>
            </div>
        </b-button>
    </div>
    <b-modal id="modal-precious" hide-header hide-footer style="text-align: center; border-radius: 1vw;">
      <b-popover target="addon">
        사진을 올려주세요!
      </b-popover>
      <div style="font-size:1.3vw; margin-top: 2%; font-weight: 400;">소중한 사람과의 추억을 기록해보세요!</div><br />
      <b-container ref="form">
          <b-form-textarea id="content" placeholder="" rows="10" max-rows="15" required style="border-radius: 1vw; background-color: #FDFCFA;">
          </b-form-textarea>
      </b-container><br/>
      <b-button data-bs-dismiss="modal" aria-label="Close"
          style="color: #ffffff; background-color: #a1a1a1; border: none; border-radius: 1vw;">취소</b-button>&nbsp;
      <b-button text @click="submit" style="color: #ffffff; background-color: #C6753E; border: none; border-radius: 1vw;">저장
      </b-button>
      <b-button class='img-btn' id="addon"><img src="@\assets\enter-image-upload.svg"></b-button>
    </b-modal>
</template>

<script>
export default {
    data() {
        return {
            images: [
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_1.svg'),
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_2.svg'),
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_3.svg'),
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
    right: 15%;
    bottom: 20%;
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

.button_prev {
    background-color:#ffffff;
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

.img-btn {
  right: 50%;
  top:1rem;
  background-color: transparent;
  border: none;
}
.img-btn:active {
  background-color: transparent;
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
#modal-precious .modal-content {
    background-color: #F5E2CE;
}
</style>