<template>
  <div v-if="isOpen" class="modal-overlay" @click="closeModal">
    <div class="modal-content" @click.stop>
      <h2 class="modal-title">프로필 캐릭터를 선택하세요</h2>

      <div class="image-grid">
        <button
          v-for="(image, index) in images"
          :key="index"
          class="image-item"
          :class="{ selected: selectedImage === image }"
          @click="selectAndConfirm(image)"
        >
          <div class="img-container">
            <img :src="image" :alt="`프로필 ${index + 1}`" />
            <div v-if="selectedImage === image" class="selected-overlay"></div>
          </div>
        </button>
      </div>

      <div class="modal-buttons">
        <button @click="closeModal" class="cancel-btn">닫기</button>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: 'ProfileImageModal',
  props: {
    isOpen: Boolean,
    images: Array,
    currentImage: String
  },
  data() {
    return {
      selectedImage: this.currentImage
    }
  },
  methods: {
    selectAndConfirm(image) {
      this.selectedImage = image;
      this.$emit('select', image);
      this.closeModal();
    },
    closeModal() {
      this.$emit('close');
    }
  }
}
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  inset: 0;
  background-color: rgba(0, 0, 0, 0.6);
  backdrop-filter: blur(4px);
  z-index: 2000;
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-content {
  background: white;
  padding: 40px;
  border-radius: 24px;
  width: 500px;
  max-width: 90%;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);
  text-align: center;
}

.modal-title {
  font-size: 1.4rem;
  font-weight: 700;
  color: #222;
  margin-bottom: 8px;
}

.image-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  margin-bottom: 30px;
}

.image-item {
  background: none;
  border: none;
  padding: 0;
  cursor: pointer;
  outline: none;
}

.img-container {
  position: relative;
  width: 100%;
  aspect-ratio: 1 / 1;
  border-radius: 50%;
  overflow: hidden;
  border: 3px solid #f0f0f0; /* 기본 테두리 */
  transition: all 0.2s ease;
}

.image-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* 호버 시 */
.image-item:not(.selected):hover .img-container {
  transform: translateY(-3px);
  border-color: #ccc;
}

.image-item.selected .img-container {
  border-color: #333; /* 검정색 테두리로 강조 */
  transform: scale(1.05);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
}

.selected-overlay {
  position: absolute;
  inset: 0;
  background: rgba(0, 0, 0, 0.35); /* 이미지를 차분하게 눌러주는 효과 */
  transition: opacity 0.2s ease;
}

.cancel-btn {
  padding: 12px 40px;
  background: #f5f5f5;
  border: none;
  border-radius: 12px;
  color: #666;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
}

.check {
  position: absolute;
  top: 6px;
  right: 6px;
  background: #4CAF50;
  color: white;
  font-size: 14px;
  width: 22px;
  height: 22px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>