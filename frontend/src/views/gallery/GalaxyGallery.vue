<template>
    <div class="jumbotron">
        <div class="title">은 하 갤 러 리</div>
    </div>
</template>

<script setup>

</script>

<style scoped>
body {
    margin: 0;
}
.title {
    text-align: center;
    padding-top: 3%;
    color: aliceblue;
    font-size: 2vw;
    font-weight: 600;
}

.jumbotron {
    background: url("@/assets/galaxy_gallery.svg") no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
    height: 100vh;
    width: 100%;
    margin:0;
    padding:0;
}
</style>