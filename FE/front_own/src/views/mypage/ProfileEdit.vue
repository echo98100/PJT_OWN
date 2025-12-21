<template>
  <div class="profile-edit-page">
    <div class="edit-container">
      <div class="form-section">
        <h1>프로필 수정</h1>

        <div class="edit-form">
          <!-- 프로필 아바타 선택 -->
          <div class="profile-image">
            <div
              v-for="img in profileImages"
              :key="img"
              class="image-placeholder"
              :class="{ selected: profileImg === img }"
              @click="selectProfile(img)"
            >
              <img :src="img" />
            </div>
          </div>
          <p v-if="imageError" class="field-error">{{ imageError }}</p>

          <!-- 기존 비밀번호 -->
          <input
            v-model="oldPassword"
            type="password"
            placeholder="기존 비밀번호 (비밀번호 변경 시 입력)"
            @blur="validateOldPassword"
          />
          <p v-if="oldPasswordError" class="field-error">{{ oldPasswordError }}</p>

          <!-- 새 비밀번호 -->
          <input
            v-model="newPassword"
            type="password"
            placeholder="새 비밀번호 (영문, 숫자 조합 8~13자)"
            @blur="validatePassword"
          />
          <p v-if="passwordError" class="field-error">{{ passwordError }}</p>

          <!-- 새 비밀번호 확인 -->
          <input
            v-model="newPasswordConfirm"
            type="password"
            placeholder="새 비밀번호 확인"
            @blur="validatePasswordConfirm"
          />
          <p v-if="passwordConfirmError" class="field-error">{{ passwordConfirmError }}</p>

          <button @click="handleUpdate">수정하기</button>

          <!-- 전체 에러 메시지 -->
          <p v-if="errorMessage" class="error">
            {{ errorMessage }}
          </p>

          <!-- 마이페이지로 돌아가기 -->
          <p class="back-link">
            <router-link to="/mypage">마이페이지로 돌아가기</router-link>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { updateUserProfile } from '@/api/user.js';

export default {
  name: 'ProfileEdit',

  data() {
    return {
      profileImg: '',
      oldPassword: '',
      newPassword: '',
      newPasswordConfirm: '',
      
      profileImages: [
        '/images/avatar1.png',
        '/images/avatar2.png',
        '/images/avatar3.png',
        '/images/avatar4.png',
      ],
      
      imageError: '',
      oldPasswordError: '',
      passwordError: '',
      passwordConfirmError: '',
      errorMessage: '',
      
      userId: null,
      originalProfileImg: '' // 원래 프로필 이미지 저장
    }
  },

  methods: {
    // 프로필 이미지 선택
    selectProfile(imgUrl) {
      this.profileImg = imgUrl;
      this.imageError = '';
    },

    // 기존 비밀번호 검증
    validateOldPassword() {
      if (this.newPassword && !this.oldPassword) {
        this.oldPasswordError = '기존 비밀번호를 입력하세요';
        return false;
      }
      this.oldPasswordError = '';
      return true;
    },

    // 비밀번호 유효성 검사
    validatePassword() {
      if (!this.newPassword) {
        this.passwordError = '';
        return true;
      }

      // 영문, 숫자 조합 8-13자
      const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,13}$/;
      
      if (!passwordRegex.test(this.newPassword)) {
        this.passwordError = '영문, 숫자 조합 8~13자';
        return false;
      }

      this.passwordError = '';
      return true;
    },

    // 비밀번호 확인 검사
    validatePasswordConfirm() {
      if (!this.newPassword && !this.newPasswordConfirm) {
        this.passwordConfirmError = '';
        return true;
      }

      if (this.newPassword !== this.newPasswordConfirm) {
        this.passwordConfirmError = '비밀번호가 일치하지 않습니다';
        return false;
      }

      this.passwordConfirmError = '';
      return true;
    },

    // 폼 제출
    async handleUpdate() {
      this.errorMessage = '';

      // 아무것도 변경하지 않은 경우
      const isProfileImgChanged = this.profileImg !== this.originalProfileImg;
      const isPasswordChanging = this.newPassword || this.newPasswordConfirm || this.oldPassword;

      if (!isProfileImgChanged && !isPasswordChanging) {
        this.errorMessage = '변경할 내용이 없습니다';
        return;
      }

      // 비밀번호 변경하려는 경우 검증
      if (isPasswordChanging) {
        if (!this.validateOldPassword()) {
          return;
        }

        if (!this.validatePassword()) {
          return;
        }

        if (!this.validatePasswordConfirm()) {
          return;
        }
      }

      try {
        const updateData = {
          profileImage: this.profileImg,
          oldPassword: this.oldPassword || null,
          newPassword: this.newPassword || null,
          newPasswordConfirm: this.newPasswordConfirm || null
        };

        const response = await updateUserProfile(this.userId, updateData);

        if (response.data) {
          alert('프로필이 수정되었습니다');
          this.$router.push('/mypage');
        }
      } catch (error) {
        console.error('프로필 수정 실패:', error);
        this.errorMessage = '프로필 수정에 실패했습니다. 기존 비밀번호를 확인해주세요.';
      }
    },

    // 현재 프로필 정보 가져오기
    async fetchCurrentProfile() {
      try {
        const response = await fetch('http://localhost:8080/api/user/me', {
          credentials: 'include'
        });

        if (response.ok) {
          const data = await response.json();
          this.userId = data.id || data.userId;
          this.profileImg = data.profileImg;
          this.originalProfileImg = data.profileImg; // 원래 이미지 저장
        }
      } catch (error) {
        console.error('프로필 조회 실패:', error);
      }
    }
  },

  mounted() {
    this.fetchCurrentProfile();
  }
}
</script>

<style scoped>
.profile-edit-page {
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #ffd89b 0%, #19547b 100%);
}

.edit-container {
  width: 450px;
  background: rgba(255, 255, 255, 0.9);
  border-radius: 20px;
  padding: 40px;
  backdrop-filter: blur(10px);
}

.form-section h1 {
  text-align: center;
  margin-bottom: 30px;
  font-size: 28px;
}

/* 프로필 이미지 선택 (회원가입과 동일) */
.profile-image {
  display: flex;
  justify-content: center;
  gap: 10px;
  margin-bottom: 20px;
}

.image-placeholder {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background-color: #f0f0f0;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  overflow: hidden;
  border: 3px solid transparent;
  transition: all 0.2s;
}

.image-placeholder img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.image-placeholder:hover {
  background-color: #e0e0e0;
  border-color: #ccc;
}

.image-placeholder.selected {
  border-color: #333;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.edit-form input {
  width: 100%;
  padding: 14px;
  margin: 8px 0 4px 0;
  box-sizing: border-box;
  border: none;
  border-bottom: 2px solid rgba(255, 255, 255, 0.5);
  background: transparent;
  font-size: 14px;
  outline: none;
}

.edit-form input::placeholder {
  color: rgba(0, 0, 0, 0.5);
}

.edit-form input:focus {
  border-bottom-color: #333;
}

/* 필드별 에러 메시지 */
.field-error {
  color: #d32f2f;
  font-size: 12px;
  margin: 2px 0 8px 0;
  min-height: 16px;
}

.edit-form button {
  width: 100%;
  padding: 14px;
  margin: 20px 0 10px 0;
  background-color: #333;
  color: white;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
}

.edit-form button:hover {
  background-color: #555;
}

/* 전체 에러 메시지 */
.error {
  color: #d32f2f;
  font-size: 13px;
  margin-top: 10px;
  text-align: center;
}

.back-link {
  text-align: center;
  margin-top: 20px;
  font-size: 13px;
  color: #666;
}

.back-link a {
  color: #333;
  text-decoration: none;
  font-weight: bold;
}

.back-link a:hover {
  text-decoration: underline;
}
</style>