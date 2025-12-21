<template>
  <div class="signup-page">
    <div class="signup-container">
      <div class="form-section">
        <h1>회원가입</h1>

        <div class="signup-form">
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

          <!-- 이메일 -->
          <input
            v-model="email"
            type="email"
            placeholder="이메일"
            @blur="validateEmail"
          />
          <p v-if="emailError" class="field-error">{{ emailError }}</p>

          <!-- 비밀번호 -->
          <input
            v-model="password"
            type="password"
            placeholder="비밀번호"
            @blur="validatePassword"
          />
          <p v-if="passwordError" class="field-error">{{ passwordError }}</p>

          <!-- 비밀번호 확인 -->
          <input
            v-model="passwordConfirm"
            type="password"
            placeholder="비밀번호 확인"
            @blur="validatePasswordConfirm"
          />
          <p v-if="passwordConfirmError" class="field-error">
            {{ passwordConfirmError }}
          </p>

          <!-- 이름 -->
          <input
            v-model="name"
            type="text"
            placeholder="이름"
            @blur="validateName"
          />
          <p v-if="nameError" class="field-error">{{ nameError }}</p>

          <!-- 닉네임 -->
          <input
            v-model="nickname"
            type="text"
            placeholder="닉네임"
            @blur="validateNickname"
          />
          <p v-if="nicknameError" class="field-error">{{ nicknameError }}</p>

          <button @click="handleSignup">확인</button>

          <!-- 전체 에러 메시지 -->
          <p v-if="errorMessage" class="error">
            {{ errorMessage }}
          </p>

          <!-- 로그인 페이지로 이동 -->
          <p class="login-link">
            이미 계정이 있으신가요?
            <router-link to="/login">로그인하기</router-link>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  signup,
  checkEmailDuplicate,
  checkNicknameDuplicate,
} from "@/api/user.js";

export default {
  name: "SignupPage",
  data() {
    return {
      email: "",
      password: "",
      passwordConfirm: "",
      name: "",
      nickname: "",
      profileImg: "",

      profileImages: [
        "/images/avatar1.png",
        "/images/avatar2.png",
        "/images/avatar3.png",
        "/images/avatar4.png",
      ],

      // 각 필드별 에러 메시지
      emailError: "",
      passwordError: "",
      passwordConfirmError: "",
      nameError: "",
      nicknameError: "",
      imageError: "",

      // 전체 에러 메시지
      errorMessage: "",
    };
  },
  methods: {
    selectProfile(imgUrl) {
      this.profileImg = imgUrl;
      this.imageError = "";
    },

    // 이메일 검증
    async validateEmail() {
      if (!this.email) {
        this.emailError = "";
        return false;
      }

      if (!this.email.includes("@")) {
        this.emailError = "이메일 형식이 올바르지 않습니다";
        return false;
      }

      try {
        const res = await checkEmailDuplicate(this.email);
        if (res.data === true) {
          this.emailError = "이미 사용 중인 이메일입니다";
          return false;
        }
      } catch (e) {
        this.emailError = "이메일 확인 중 오류가 발생했습니다";
        return false;
      }

      this.emailError = "";
      return true;
    },

    // 비밀번호 검증
    validatePassword() {
      const pattern = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,13}$/;
      if (!pattern.test(this.password)) {
        this.passwordError = "영문, 숫자 조합 8~13자";
        return false;
      }
      this.passwordError = "";
      return true;
    },

    validatePasswordConfirm() {
      if (this.password !== this.passwordConfirm) {
        this.passwordConfirmError = "비밀번호가 일치하지 않습니다";
        return false;
      }
      this.passwordConfirmError = "";
      return true;
    },

    // 이름 검증
    validateName() {
      if (!this.name) {
        this.nameError = "";
        return false;
      }

      // 이름: 3~20자, 한글/영어/숫자만 허용
      const namePattern = /^[가-힣a-zA-Z0-9]{3,20}$/;

      if (!namePattern.test(this.name)) {
        this.nameError = "한글, 영어 대/소문자를 이용해 3~20자 입력해주세요";
        return false;
      }

      // 특수문자 제한
      if (/[^가-힣a-zA-Z0-9]/.test(this.name)) {
        this.nameError = "특수문자 금지";
        return false;
      }

      this.nameError = "";
      return true;
    },

    // 닉네임 검증
    async validateNickname() {
      if (!this.nickname) {
        this.nicknameError = "";
        return false;
      }

      try {
        const res = await checkNicknameDuplicate(this.nickname);
        if (res.data === true) {
          this.nicknameError = "이미 사용 중인 닉네임입니다";
          return false;
        }
      } catch (e) {
        this.nicknameError = "닉네임 확인 중 오류가 발생했습니다";
        return false;
      }

      this.nicknameError = "";
      return true;
    },

     // 이메일 중복 체크
  async checkEmailDuplicate(email) {
    try {
      const response = await fetch(`http://localhost:8080/check-email?email=${email}`);
      const isDuplicate = await response.json();
      return { data: isDuplicate }; // true면 중복, false면 사용 가능
    } catch (error) {
      console.error('이메일 중복 체크 실패:', error);
      throw error;
    }
  },

  // 닉네임 중복 체크
  async checkNicknameDuplicate(nickname) {
    try {
      const response = await fetch(`http://localhost:8080/check-nickname?nickname=${nickname}`);
      const isDuplicate = await response.json();
      return { data: isDuplicate }; // true면 중복, false면 사용 가능
    } catch (error) {
      console.error('닉네임 중복 체크 실패:', error);
      throw error;
    }
  },


    async handleSignup() {
      if (!this.profileImg) {
        this.imageError = "프로필 이미지를 선택해주세요";
        this.errorMessage = "입력 정보를 확인해주세요";
        return;
      }

      const isEmailValid = await this.validateEmail();
      const isPasswordValid = this.validatePassword();
      const isPasswordConfirmValid = this.validatePasswordConfirm();
      const isNameValid = this.validateName();
      const isNicknameValid = await this.validateNickname();

      if (
        !isEmailValid ||
        !isPasswordValid ||
        !isPasswordConfirmValid ||
        !isNameValid ||
        !isNicknameValid
      ) {
        this.errorMessage = "입력 정보를 확인해주세요";
        return;
      }

      const payload = {
        email: this.email,
        password: this.password,
        name: this.name,
        nickname: this.nickname,
        profileImg: this.profileImg,
      };

      await signup(payload);
      alert("회원가입이 완료되었습니다!");
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
.signup-page {
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #ffd89b 0%, #19547b 100%);
}

.signup-container {
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

.profile-image {
  display: flex;
  justify-content: center;
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
  font-size: 32px;
  cursor: pointer;
  overflow: hidden;
  position: relative;
}

.image-placeholder img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.image-placeholder:hover {
  background-color: #e0e0e0;
}
.signup-form input {
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

.signup-form input::placeholder {
  color: rgba(0, 0, 0, 0.5);
}

.signup-form input:focus {
  border-bottom-color: #333;
}

/* 필드별 에러 메시지 (빨간색) */
.field-error {
  color: #d32f2f;
  font-size: 12px;
  margin: 2px 0 8px 0;
  min-height: 16px;
}

.signup-form button {
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

.signup-form button:hover {
  background-color: #555;
}

/* 전체 에러 메시지 */
.error {
  color: #d32f2f;
  font-size: 13px;
  margin-top: 10px;
  text-align: center;
}

.login-link {
  text-align: center;
  margin-top: 20px;
  font-size: 13px;
  color: #666;
}

.login-link a {
  color: #333;
  text-decoration: none;
  font-weight: bold;
}

.login-link a:hover {
  text-decoration: underline;
}
</style>
