<template>
  <div class="feed-page">
    <header class="feed-header">
      <h2>최신 피드</h2>
    </header>

    <section class="recommend-section">
      <h3>🔥 오늘의 추천 조합</h3>
      <div class="recommend-container">
        <div class="recommend-card">등 운동 + 신나는 Rock</div>
        <div class="recommend-card">요가 + 차분한 Lo-fi</div>
      </div>
    </section>

    <PostList />
  </div>
</template>

<script setup>
import PostList from '@/components/feed/PostList.vue';
</script>

<style scoped>
.feed-page { max-width: 600px; margin: 0 auto; }
.feed-header { margin-bottom: 24px; }
.recommend-section { margin-bottom: 30px; }
.recommend-container { display: flex; gap: 12px; overflow-x: auto; padding-bottom: 10px; }
.recommend-card {
  min-width: 200px;
  padding: 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border-radius: 12px;
  font-weight: bold;
}
</style>