<template>
  <aside class="lightbox">
    <input type="checkbox" class="state" id="lightbox-1" />
    <article class="content">
      <header class="header">
        <h3 class="h h3">Dear OOO</h3>
        <label class="button" for="lightbox-1">&times;</label>
      </header>
      <main class="main">
        <p>
          Dear OOO.
          <br />
          멀어져만 가는 그대 you're the only one 내가 사랑했던 것만큼 you're the
          only one 아프고 아프지만 바보 같지만 good bye 다시 널 못 본다 해도
          you're the only one, only one <br /><br />
          어색하게 마주앉아 사소한 얘기로 안부를 묻고 가끔 대화가 끊기는
          순간에는 차가운 정적 우릴 얼게 만들어 (만들어)<br /><br />
          지금 이 자리에서 우리는 남이 되겠지 어느 누군가는 눈물 흘리며 남겠지만
          상처주지 않으려고 자꾸 애를 써가면서 눈치 보는 니 모습 싫어 so I'll
          let you go<br /><br />
          내사랑 이제는 안녕 you're the only one (you're the only one) 이별하는
          이순간에도 you're the only one 아프고 아프지만 바보 같지만 good bye
          다시 널 못 본다 해도 you're the only one, only one You're the only
          one, only one<br /><br />
          갑작스런 나의 말에 왠지 모르게 넌 안심한듯해 어디서부터 우린 이렇게
          잘못된 걸까 오래 전부터, 다른 곳만 기대한 건 아닌지<br /><br />
          너무 다른 시작과 끝의 그 날카로움이 내 심장을 찌르는 아픔은 왜
          똑같은지 벅찬 가슴이 한 순간에 공허하게 무너져서 이런 내 모습 어떻게
          일어설까<br /><br />
          내사랑 이제는 안녕 you're the only one (only one) 이별하는 이순간에도
          you're the only one 아프고 아프지만 바보 같지만 good bye (good bye)
          다시 널 못 본다 해도 you're the only one (you're the only one)<br /><br />
          내 머릿속은 언제쯤 너를 지울까 (I will let you go) 하루 이틀 한달
          멀게는 아마 몇 년쯤 (my baby can't forget) 그리고 언젠가 너의 기억
          속에는 나란 사람은 더 이상 살지 않겠지 지우겠지 Only one, only one
          You're the only one, only one<br />
          Sincerely, OOO
        </p>
      </main>
      <footer class="footer">
        <button class="button" type="button">수정하기</button>
        <label class="button" for="lightbox-1">Close</label>
      </footer>
    </article>
    <label class="backdrop" for="lightbox-1"></label>
  </aside>
</template>

<script></script>

<style scoped>
* {
  box-sizing: border-box;
}
html,
body {
  font-family: "Helvetica Neue", Helvetica, Arial;
  font-size: 15px;
  margin: 0;
  padding: 0;
  background: #5ec792;
}
.h {
  margin-top: 0;
}
.button {
  display: inline-block;
  margin: 0;
  padding: 10px 15px;
  background-color: #5ec792;
  border: none;
  color: #fff;
  text-align: center;
  font-size: 14px;
  font-weight: bold;
  -webkit-transition: 0.3s all ease-in-out;
  transition: 0.3s all ease-in-out;
}
.button:hover {
  box-shadow: inset 0 -2.5px rgba(0, 0, 0, 0.4);
}
/* .hero {
  position: absolute;
  top: 50%;
  left: 50%;
  color: rgba(0, 0, 0, 0.4);
  border: 1px solid rgba(0, 0, 0, 0.4);
  -webkit-transform: translate3d(-50%, -50%, 0);
          transform: translate3d(-50%, -50%, 0);
} */
.state {
  position: absolute;
  top: 0;
  left: -100vw;
}
.state:checked ~ .content {
  -webkit-transform: none;
  transform: none;
}
.state:checked ~ .backdrop {
  bottom: 0;
  opacity: 1;
  z-index: 1;
}
.lightbox {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  height: 0;
  padding: 0 20px;
}
.lightbox .content {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -ms-flex-direction: column;
  flex-direction: column;
  overflow: hidden;
  position: relative;
  z-index: 2;
  max-width: 500px;
  max-height: 95vh;
  margin: 20px auto;
  padding: 20px;
  background: #fff;
  -webkit-transform: translateY(-200%);
  transform: translateY(-200%);
  -webkit-transition: 0.3s -webkit-transform ease-in-out;
  transition: 0.3s -webkit-transform ease-in-out;
  transition: 0.3s transform ease-in-out;
  transition: 0.3s transform ease-in-out, 0.3s -webkit-transform ease-in-out;
  border: 1px solid rgba(0, 0, 0, 0.1);
}
.lightbox .header,
.lightbox .footer {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-negative: 0;
  flex-shrink: 0;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
}
.lightbox .header .h,
.lightbox .footer .h {
  margin: 0;
}
.lightbox .header .button:not(:first-child),
.lightbox .footer .button:not(:first-child) {
  margin-left: auto;
}
.lightbox .header {
  padding-bottom: 10px;
}
.lightbox .footer {
  padding-top: 20px;
}
.lightbox .main {
  -webkit-box-flex: 1;
  -ms-flex-positive: 1;
  flex-grow: 1;
  overflow: scroll;
}
.lightbox .backdrop {
  position: fixed;
  z-index: -1;
  top: 0;
  right: 0;
  bottom: 100%;
  left: 0;
  opacity: 0;
  background: rgba(0, 0, 0, 0.3);
  -webkit-transition: 0.3s opacity ease-in-out;
  transition: 0.3s opacity ease-in-out;
}
</style>
