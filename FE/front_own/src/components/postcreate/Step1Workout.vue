<template>
    <div class="step-container">
        <h2>오늘 한 운동은 무엇인가요?</h2>

        <div v-if="createStore.fetchWorkoutTags.length === 0">로딩 중</div>

        <div class="tag-grid">
            <button
              v-for="tag in createStore.workoutTags"
              :key="tag.workoutTypeId"
              :class="['tag-btn', { active: createStore.workoutTag?.workoutTypeId === tag.workoutTypeId }]"
              @click="selectTag(tag)">
              {{ tag.workoutName }}
            </button>
        </div>

        <button @click="next" :disabled="!createStore.workoutTag" class="next-btn">
            다음
        </button>

    </div>
</template>

<script setup>
import { useCreateStore } from '@/stores/create';
import { onMounted } from 'vue';

const createStore = useCreateStore();

onMounted(() => {
    createStore.fetchWorkoutTags();
});

const selectTag = (tag) => {
    createStore.workoutTag = {
        workoutTypeId: tag.workoutTypeId,
        workoutName: tag.workoutName
    }
};

const next = () => {
    createStore.setStep(2);
};

</script>

<style scoped>
.tag-grid { display: grid; grid-template-columns: repeat(2, 1fr); gap: 10px; margin: 20px 0; }
.tag-btn { padding: 15px; border: 1px solid #ddd; border-radius: 8px; background: white; cursor: pointer; }
.tag-btn.active { background: #42b883; color: white; border-color: #42b883; }
.next-btn { width: 100%; padding: 15px; background: #333; color: white; border: none; border-radius: 8px; cursor: pointer; }
.next-btn:disabled { background: #ccc; }

</style>