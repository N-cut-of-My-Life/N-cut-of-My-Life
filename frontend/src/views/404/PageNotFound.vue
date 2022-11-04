<template>
  <div class="jumbotron">
    <div class="oops"></div>
    <div class="buttons">
      <button class="button-go-back" @click="goBack">Back</button>
      <button class="button-go-home" @click="goHome">Intro</button>
    </div>
  </div>
</template>

<script>
export default {
  methods:{
    goBack(){
      this.$router.go(-1);
    },
    goHome(){
      this.$router.push('/');
    }
  }

}
</script>

<style>
body {
  margin:0;
  background-color: #141414;
}
.buttons{
  display : flex;
  width: 393px;
  justify-content: center;
}
.jumbotron {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: url("@/assets/404/404.svg") no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  height: 100vh;
  width: 100%;
  margin: 0;
  padding: 0;
}
.oops {
  background: url("@/assets/404/oops.svg") no-repeat center center;
  height: 472px;
  width: 393px;
}
.button-go-back {
  border: 0.5px solid white;
  background: transparent;
  color: white;
  border-radius: 10%;
  margin: 28px 8px;
  padding: 6px 40px 8px ;
  font-size: 20px;
  font-family: Exo;
}
.button-go-back:hover{
  background-color: white;
  color: #141414;
}
.button-go-home {
  border: 0.5px solid white;
  background: transparent;
  color: white;
  border-radius: 10%;
  margin: 28px 8px;
  padding: 6px 40px 8px ;
  font-size: 20px;
  font-family: Exo;
}
.button-go-home:hover{
  background-color: white;
  color: #141414;
}
</style>