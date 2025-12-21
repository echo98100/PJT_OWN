<template>
    <div class="Progress-wrapper">
        <div class="progress-bg">
            <div class="progress-fill" :style="{ width: `${(step / 4) * 100}%`}"></div>
        </div>
    </div>
</template>

<script setup>
defineProps({
    step: Number
});
</script>

<style scoped>
.progress-wrapper { width: 100%; padding: 20px 0; }
.progress-bg {
  width: 100%; height: 8px; background-color: #eee;
  border-radius: 4px; overflow: hidden;
}
.progress-fill {
  height: 100%; background-color: #42b883; /* 포인트 컬러 */
  transition: width 0.3s ease; /* 부드럽게 차오르는 효과 */
}
.step-text { font-size: 0.8rem; text-align: right; margin-top: 5px; color: #888; }


</style>