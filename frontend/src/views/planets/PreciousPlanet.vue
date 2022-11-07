<template>
    <div class="jumbotron">
        <div class="title">넌너무소중 행성</div>
    </div>
    <img class="bubble" :src="images[currentImage]" />
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
    <div v-if="currentImage === (images.length - 1)" class="last">
        <b-button v-show="elementVisible" variant="warning" class="button_2" size="md">
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
    <b-modal :no-close-on-backdrop="true" id="modal-precious" centered no-stacking hide-header hide-footer style="text-align: center; border-radius: 1vw;">
      <b-popover target="addon" placement="left">
        <input type="file" accept="image/*" @change="onUpload" />
        <div>
            <img v-if="item.imageUrl" :src="item.imageUrl" style="max-width: 16vw; height: auto;"> 
            <div v-else>
                <div><strong>소중한 순간을 담아주세요!</strong></div>
                <img src="@/assets/v-pose.svg">
            </div>
        </div>
      </b-popover>
      <img data-bs-dismiss="modal" aria-label="Close" class="x_button" src="@/assets/xButton/x_happy.svg" style="cursor:pointer; float: right;"/>
      <div style="font-size:1.3vw; margin-top: 5%; margin-bottom: 3%; font-weight: 400;">소중한 사람과의 추억을 기록해보세요!</div>
      <b-container ref="form" style="margin-bottom:3.8%">
          <b-form-textarea id="content" placeholder="" rows="10" max-rows="15" required style="border-radius: 1vw; background-color: #FDFCFA;">
          </b-form-textarea>
      </b-container>
      <b-button text @click="submit" style="color: #ffffff; background-color: #C6753E; border: none; border-radius: 1vw;">저장
      </b-button>
      <b-button class='img-btn' id="addon"><img class="upload" src="@\assets\enter-image-upload.svg"></b-button>
    </b-modal>
</template>

<script>
export default {
    data() {
        return {
            item:{
                image : null,
                imageUrl : null,
            },
            images: [
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_1.svg'),
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_2.svg'),
                require('@/assets/PlanetSpeech/PreciousSpeech/precious_bubble_3.svg'),
            ],
            currentImage: 0,
            elementVisible: false
        }
    },
    methods: {
        onUpload(e) {
            const file = e.target.files[0]
            this.image = file
            this.item.imageUrl = URL.createObjectURL(file)
        },
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
    },
    updated() {
        if(this.currentImage==(this.images.length-1)){
            setTimeout(() => this.elementVisible = true, 2000)
        }
    },
}
</script>

<style scoped>
body {
    margin: 0;
}

.bubble {
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

.x_button {
    width: 4%;
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

.img-btn {
  left: 0%;
  bottom:0%;
  background-color: transparent;
  border: none;
  position:absolute;
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
        transform: translateY(-3px);
    }
}
</style>
<style>
#modal-precious .modal-content {
    background-color: #F5E2CE;
}
</style>