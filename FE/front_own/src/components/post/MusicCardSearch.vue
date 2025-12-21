<template>
  <div class="result-item" @click="$emit('select', music)">
    <img :src="music.albumImg" alt="album cover" width="50" />
    <div class="info">
      <p class="title">{{ music.musicTitle }}</p>
      <p class="artist">{{ music.artist }}</p>
    </div>
  </div>
</template>

<script setup>
defineProps({
  music: Object 
});
</script>

<style scoped>

</style>