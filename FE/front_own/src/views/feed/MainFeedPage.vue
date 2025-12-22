<template>
  <div class="feed-page">
    <header class="feed-header">
      <h2>최신 피드</h2>
    </header>
    
    <RankingSection></RankingSection>
    <section class="recommend-section">
      <h3>🔥 오늘의 추천 조합</h3>
      
    </section>

    <PostList />
  </div>
</template>

<script setup>
import PostList from '@/components/feed/PostList.vue';
import RankingSection from '@/components/feed/RankingSection.vue';
</script>

<style scoped>
.feed-page { max-width: 600px; margin: 0 auto; }
.feed-header { margin-bottom: 24px; }
.recommend-section { margin-bottom: 30px; }
.recommend-container { display: flex; gap: 12px; overflow-x: auto; padding-bottom: 10px; }

</style>