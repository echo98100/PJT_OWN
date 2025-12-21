import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { getWorkoutTags } from '@/api/workout';
import { getEmotionTags } from '@/api/emotion';

export const useCreateStore = defineStore('create', () => {
  
  const currentStep = ref(1);
  const workoutTags = ref([]);
  const workoutTag = ref('');
  const emotionTags = ref([]);
  const emotionTag = ref([]);
  const selectedMusic = ref(null);
  const caption = ref('');

  const setStep = (step) => { currentStep.value = step; };

  const fetchWorkoutTags = async () => {
    try {
      const response = await getWorkoutTags();
      workoutTags.value = response.data;
    } catch (error) {
      console.error("태그를 불러오는데 실패했습니다", error);
    }
  }

  const fetchEmotionTags = async () => {
    try {
      const response = await getEmotionTags();
      emotionTags.value = response.data;
    } catch(error) {
      console.error("감정 태그 로드 실패", error);
    }
  }

  const toggleEmotion = (tagName) => {
    const index = emotionTag.value.indexOf(tagName);
    if(index > -1) {
      emotionTag.value.splice(index,1);
    } else {
      if(emotionTag.value.length < 5) {
        emotionTag.value.push(tagName);
      } else {
        alert("감정 태그는 최대 5개까지 선택 가능합니다");
      }
    }
  }


  const resetData = () => {
    currentStep.value = 1;
    workoutTag.value = '';
    emotionTag.value = '';
    selectedMusic.value = null;
    caption.value = '';
  };

  return { currentStep, workoutTag, emotionTag, selectedMusic, caption, setStep, resetData, workoutTags, fetchWorkoutTags, emotionTags, fetchEmotionTags, toggleEmotion };
});
