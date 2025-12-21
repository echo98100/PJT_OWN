<template>
  <div class="post-list">
    <PostItem v-for="post in posts" :key="post.id" :post="post" />

    <div ref="loadMoreTrigger" class="loading-trigger">
      <p v-if="loading">데이터를 불러오는 중...</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import PostItem from './PostItem.vue';

const posts = ref([]); // 게시글 데이터 리스트
const loading = ref(false);
const loadMoreTrigger = ref(null);

// 임시 데이터 로드 함수 (나중에 API 연결)
const fetchPosts = async () => {
  if (loading.value) return;
  loading.value = true;
  
  // API 호출 시뮬레이션
  setTimeout(() => {
    const newPosts = Array.from({ length: 5 }, (_, i) => ({
      id: posts.value.length + i,
      nickname: '운동매니아',
      userProfile: 'https://via.placeholder.com/32',
      createdAt: '2시간 전',
      workoutTag: '하체',
      emotionTag: '뿌듯함',
      caption: '오늘 스쿼트 100개 완료! 음악이 좋아서 더 잘됐네요.',
      music: { title: 'DYNAMITE', artist: 'BTS', albumImg: 'https://via.placeholder.com/50' },
      likeCount: 12,
      bookmarkCount: 5
    }));
    posts.value.push(...newPosts);
    loading.value = false;
  }, 1000);
};

// Intersection Observer 설정 -> 무한 스크롤
onMounted(() => {
  const observer = new IntersectionObserver((entries) => {
    if (entries[0].isIntersecting) {
      fetchPosts();
    }
  }, { threshold: 1.0 });

  if (loadMoreTrigger.value) {
    observer.observe(loadMoreTrigger.value);
  }
});
</script>

<style scoped>
.loading-trigger { height: 50px; display: flex; justify-content: center; align-items: center; }
</style>