<template>
  <div class="login-page">

    <!-- 배경화면-->
    <div class="bg-animation"></div>

    <div class="login-container">
        <!-- 로그인 폼 -->
          <div class="form-section">

            <div class="logo-wrapper">
              <img src="@/assets/logo/black.png" alt="OWN LOGO" class="login-logo" />
             </div>
            
          <p class="welcome-text">OWN과 함께 나만의 운동 일지를 기록해보세요</p>

        <!-- 입력 폼-->
        <div class="login-form">
          <div class="input-group">
            <div class="input-wrapper">
              <label>EMAIL</label>
              <input 
                v-model="email" 
                type="email" 
                placeholder="이메일을 입력하세요" 
                @keyup.enter="handleLogin"
              />
            </div>

            <div class="input-wrapper">
              <label>PASSWORD</label>
              <input 
                v-model="password" 
                type="password" 
                placeholder="비밀번호를 입력하세요" 
                @keyup.enter="handleLogin"
              />
            </div>
          </div>

          <p v-if="errorMessage" class="error-global">
            {{ errorMessage }}
          </p>

          <button class="submit-btn" @click="handleLogin">로그인</button>

          <p class="register-link">
            계정이 없으신가요?
            <router-link to="/signup">가입하기</router-link>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useAuthStore } from '@/stores/auth.js';
import { login } from "@/api/user.js";

export default {
  name: "LoginPage",

  setup() {
    const authStore = useAuthStore();
    return { authStore };
  },

  data() {
    return {
      email: "",
      password: "", 
      errorMessage: "",
    };
  },
  methods: {
    async handleLogin() {
       if (!this.email) {
        this.errorMessage = "이메일을 입력해주세요";
        return;
      }
       if (!this.password) {
        this.errorMessage = "비밀번호를 입력해주세요";
        return;
      }
        // 에러 메시지 초기화
      this.errorMessage = "";
      
      try {
        // 백엔드에 로그인 요청
        const response = await login(this.email, this.password);

        // 성공 (200)
        this.authStore.setUser(response.data);

        sessionStorage.setItem('isLogin', 'true');

         alert('로그인 성공!');
        // 메인 페이지로 이동
        this.$router.push("/"); //메인 피드 경로 이게 맞나

      } catch (error) {
        // 실패 처리

        if (error.response) {
          // 백엔드에서 응답은 왔는데 에러인 경우
          if (error.response.status === 401) {
            // 인증 실패
            this.errorMessage = "아이디 또는 비밀번호가 일치하지 않습니다";
          } else if (error.response.status === 400) {
            // 잘못된 요청
            this.errorMessage = "입력 정보를 확인해주세요";
          } else {
            this.errorMessage = "로그인에 실패했습니다";
          }
        } else {
          // 네트워크 에러 (서버가 꺼져있거나)
          this.errorMessage = "서버와 연결할 수 없습니다";
        }
      }
    },
  },
};
</script>
<style scoped>
.login-page {
  width: 100vw;
  height: 100vh;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  margin: 0;
  padding: 0;
}

.bg-animation {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 0; /* 배경이므로 가장 뒤로 */
  overflow: hidden;
  background: radial-gradient(
    circle,
    rgba(255, 255, 255, 0.2),
    rgba(0, 0, 0, 0.1)
  );
}

.bg-animation::before,
.bg-animation::after {
  content: "";
  position: absolute;
  top: 50%;
  left: 50%;
  width: 200%;
  height: 200%;
  background: conic-gradient(
    from 0deg,
    #E1603F,
    #2E3781,
    #E1603F,
    #F3DBC8,
    #2E3781
  );
  transform: translate(-50%, -50%);
  animation: rotate 8s linear infinite;
  filter: blur(50px);
  opacity: 0.8;
}

.bg-animation::after {
  width: 180%;
  height: 180%;
  animation: rotate-reverse 10s linear infinite;
  opacity: 0.6;
}

@keyframes rotate {
  0% { transform: translate(-50%, -50%) rotate(0deg); }
  100% { transform: translate(-50%, -50%) rotate(360deg); }
}

@keyframes rotate-reverse {
  0% { transform: translate(-50%, -50%) rotate(0deg); }
  100% { transform: translate(-50%, -50%) rotate(-360deg); }
}

.login-container {
  z-index: 1;
  width: 420px;
  padding: 50px 40px;
  border-radius: 30px;
  background: rgba(255, 255, 255, 0.25);
  box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.2);
  backdrop-filter: blur(12px);
  -webkit-backdrop-filter: blur(12px);
  border: 1px solid rgba(255, 255, 255, 0.2);
  text-align: center;
}

.logo-wrapper {
  margin-bottom: 15px;
}

.login-logo {
  width: 120px;
  height: auto;
  object-fit: contain;
}

.welcome-text {
  font-size: 14px;
  color: #333;
  margin-bottom: 40px;
  font-weight: 500;
  letter-spacing: -0.5px;
}

/* 입력 필드 그룹 */
.input-group {
  display: flex;
  flex-direction: column;
  gap: 24px;
  margin-bottom: 30px;
  text-align: left;
}

.input-wrapper {
  display: flex;
  flex-direction: column;
}

.input-wrapper label {
  font-size: 11px;
  color: #333;
  margin-bottom: 6px;
  font-weight: 700;
  letter-spacing: 1px;
}

/* 밑줄 입력 스타일 */
.login-form input {
  width: 100%;
  padding: 10px 0;
  background: transparent;
  border: none;
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
  color: #222;
  font-size: 15px;
  outline: none;
  transition: border-color 0.3s;
}

.login-form input::placeholder {
  color: rgba(0, 0, 0, 0.3);
  font-size: 13px;
}

.login-form input:focus {
  border-bottom-color: #222;
}

/* 에러 메시지 */
.error-global {
  color: #ff4d4d;
  font-size: 13px;
  margin-bottom: 20px;
  font-weight: 600;
}

/* 로그인 버튼 */
.submit-btn {
  width: 100%;
  padding: 16px;
  background-color: #222;
  color: white;
  border: none;
  border-radius: 35px;
  font-size: 15px;
  font-weight: 700;
  cursor: pointer;
  transition: all 0.3s;
  letter-spacing: 1px;
  margin-bottom: 20px;
}

.submit-btn:hover {
  background-color: #000;
  transform: translateY(-2px);
  box-shadow: 0 5px 15px rgba(0,0,0,0.2);
}

/* 회원가입 링크 */
.register-link {
  font-size: 13px;
  color: #444;
}

.register-link a {
  color: #222;
  text-decoration: none;
  font-weight: 700;
  margin-left: 6px;
  border-bottom: 1px solid #222;
}

.register-link a:hover {
  color: #000;
}
</style>
