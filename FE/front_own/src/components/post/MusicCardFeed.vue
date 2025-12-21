<template>
  <div class="music-card-feed">
    <img :src="music.albumImg" alt="Album Art" class="album-img" />
    <div class="music-info">
      <h4 class="title">{{ music.title }}</h4>
      <p class="artist">{{ music.artist }}</p>
    </div>
    <MusicPlayer :previewUrl="music.previewUrl" />
  </div>
</template>

<script setup>
import MusicPlayer from './MusicPlayer.vue';

defineProps({
  music: Object // { title, artist, albumImg, previewUrl } 구성
});
</script>

<style scoped>
.music-card-feed {
  display: flex;
  align-items: center;
  padding: 12px;
  background: #1e1e1e; /* 어두운 테마 */
  border-radius: 8px;
  gap: 12px;
}
.album-img {
  width: 50px;
  height: 50px;
  border-radius: 4px;
}
.music-info {
  flex: 1;
}
.title {
  color: white;
  margin: 0;
  font-size: 0.9rem;
}
.artist {
  color: #aaa;
  margin: 0;
  font-size: 0.8rem;
}
</style>