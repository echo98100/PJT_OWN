<template>
    <div class="create-page">
        <Step1Workout v-if="createStore.currentStep === 1" />
        <Step2Emotion v-if="createStore.currentStep === 2" />
        <Step3Music v-if="createStore.currentStep === 3" />
        <Step4Caption v-if="createStore.currentStep === 4" />

        <ProgressBar :step="createStore.currentStep"/>
    </div>
</template>

<script setup>
import ProgressBar from '@/components/postcreate/ProgressBar.vue';
import Step1Workout from '@/components/postcreate/Step1Workout.vue';
import Step2Emotion from '@/components/postcreate/Step2Emotion.vue';
import Step3Music from '@/components/postcreate/Step3Music.vue';
import Step4Caption from '@/components/postcreate/Step4Caption.vue';
import { useCreateStore } from '@/stores/create';

const createStore = useCreateStore();

</script>

<style scoped>

.create-page {
    max-width: 500px;
    margin: 0 auto;
    padding-bottom: 80px;
}

</style>