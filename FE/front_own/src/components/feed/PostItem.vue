<template>
  <div class="post-item">
    <div class="post-header">
      <div class="user-info">
        <img :src="post.userProfile" class="user-avatar" />
        <div class="user-meta">
          <span class="user-nickname">{{ post.nickname || '익명' }}</span>
          <span class="tier-badge" v-if="post.tierLevel">{{ post.tierLevel }}</span>
          <span class="post-date">{{ formatDate(post.createdAt) }}</span>
        </div>
      </div>
    </div>

    <MusicCardFeed :music="{
      musicTitle: post.musicTitle,
      artist: post.artist,
      albumImg: post.albumImg,
      previewUrl: post.previewUrl
    }" />

    <div class="post-content">
      <div class="tags">
        <span class="tag workout-tag" v-if="workoutName">{{ workoutName }}</span>
        <span v-for="id in post.emotionTag" :key="id" class="tag emotion-tag" >{{ getEmotionName(id) }}</span>
      </div>
      <p class="caption">{{ post.caption }}</p>
    </div>

    <div class="post-actions">
      <button class="action-btn">❤️ {{ post.likeCount }}</button>
      <button class="action-btn">🔖 {{ post.bookmarkCount }}</button>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import MusicCardFeed from '@/components/post/MusicCardFeed.vue';
import { useCreateStore } from '@/stores/create';

const props = defineProps({
  post: Object
});

const createStore = useCreateStore();

const formatDate = (dateArray) => {
  if(!dateArray) return '방금 전';
  return new Date(dateArray).toLocaleDateString();
};

const workoutName = computed(() => {
  const found = createStore.workoutTags.find(t => t.workoutTypeId === props.post.workoutTag);
  return found.workoutName; 
});

const getEmotionName = (id) => {
  const found = createStore.emotionTags.find(t => t.emotionTypeId === id);
  return found.emotionName;
}

</script>

<style scoped>
.post-item {
  background: white;
  border-radius: 12px;
  padding: 16px;
  margin-bottom: 20px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}
.post-header { margin-bottom: 12px; }
.user-avatar { width: 32px; height: 32px; border-radius: 50%; margin-right: 8px; }
.user-nickname { font-weight: bold; margin-right: 8px; }
.post-date { font-size: 0.8rem; color: #888; }
.tags { margin: 12px 0; display: flex; gap: 8px; }
.tag { font-size: 0.85rem; padding: 4px 8px; border-radius: 4px; }
.workout-tag { background: #e3f2fd; color: #1976d2; }
.emotion-tag { background: #f3e5f5; color: #7b1fa2; }
.caption { line-height: 1.5; color: #333; }
.post-actions { border-top: 1px solid #eee; pt: 12px; display: flex; gap: 16px; }
.action-btn { background: none; border: none; cursor: pointer; color: #666; }
</style>