<template>
  <div class="feed-page">
    <header class="feed-header">
    </header>
    
    <RankingSection></RankingSection>
    <section class="recommend-section">
      
    </section>

    <PostList />
  </div>
</template>

<script setup>
import PostList from '@/components/feed/PostList.vue';
import RankingSection from '@/components/feed/RankingSection.vue';
</script>

<style scoped>
.feed-page { max-width: 620px; margin: 0 auto; }
.feed-header { margin-bottom: 24px; }
.recommend-section { margin-bottom: 30px; }
.recommend-container { display: flex; gap: 12px; overflow-x: auto; padding-bottom: 10px; }

</style>