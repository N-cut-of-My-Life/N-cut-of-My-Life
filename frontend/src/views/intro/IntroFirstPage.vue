<template>
  <video muted autoplay loop playbackRate="1.0">
    <source src="@/assets/intro_video.mp4" type="video/mp4">
  </video>
  <div style="color: #ffffff;  text-align: right;  padding-right:66px; margin-top: 3.5%;">어서오세요, <span style="font-weight:400">최재현</span>님</div>
  <b-navbar toggleable="lg" style="float: right; margin-right: 50px;">
    <b-navbar-nav>
      <b-nav-item>
        <router-link :to="{ name: 'traveldiary' }" class="nav-link">
          나의 여행 일지
        </router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link :to="{ name: 'galaxygallery' }" class="nav-link">
          은하갤러리
        </router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link :to="{ name: 'planetlist' }" class="nav-link">
          행성 목록
        </router-link>
      </b-nav-item>
    </b-navbar-nav>
  </b-navbar>
  <br/><br/><br/><br/><br/>
  <div style="text-align:center;">
    <img src="@/assets/astronaut_riding.png" />
  </div>
  <br/><br/><br/><br/>
  <div class="box">
    <div class="start-btn">
      <router-link :to="{ name: 'introstory1' }" class="nav-link" style="text-decoration: none; color: inherit;">
        여행 시작하기
      </router-link>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.navbar-expand-lg .navbar-nav .nav-link a:hover {
  text-decoration: none;
  color: #ffffff;
  font-weight: 600;
}

.navbar-expand-lg .navbar-nav .nav-link {
  color: #ffffff;
}

body {
  margin: 0;
}

img{
  width:40%;
  animation: bounce .75s infinite alternate;
  -webkit-animation: bounce .75s infinite alternate;
}
@keyframes bounce{
  from{
    transform: translateY(0px);
  }
  to{
    transform: translateY(-15px);
  }
}
@-webkit-keyframes bounce{
  from{
    transform: translateY(0px);
  }
  to{
    transform: translateY(-15px);
  }
}

video {
  position: fixed;
  right: 0;
  bottom: 0;
  min-width: 100%;
  min-height: 100%;
  width: auto;
  height: auto;
  z-index: -100;
  background-size: cover;
}

.nav-link {
  text-decoration: none;
  color: inherit;
  font-size: x-large;
  padding: 0;
}

.start-btn {
  text-align: center;
  background-color: #ffffff;
  border-radius: 10px;
  font-family: 'kakao';
  font-size: 1.5rem;
  width: 10%;
  height: auto;
  cursor: pointer;
}

.box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.container,
.container-fluid,
.container-xxl,
.container-xl,
.container-lg,
.container-md,
.container-sm {
  padding: 0
}
</style>