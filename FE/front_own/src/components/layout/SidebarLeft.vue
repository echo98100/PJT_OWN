<template>
  <div class="sidebar-left">
    <!-- 로고 -->
    <div class="logo">
      <router-link to="/">
        <img src="@/assets/logo/grey.png" alt="OWN LOGO" class="logo-img" />
      </router-link>
      <!--<h1>OWN</h1> -->
    </div>

    <!-- 미니 프로필 컴포넌트 -->
    <MiniProfile />

    <!-- 메뉴 -->
    <nav class="menu">
      <router-link to="/" class="menu-item">
        <span class="menu-label">홈</span>
      </router-link>

      <router-link to="/mypage" class="menu-item">
        <span class="menu-label">마이 로그</span>
      </router-link>
    </nav>

    <!-- 로그아웃 버튼 -->
    <button class="logout-btn" @click="handleLogout">
      로그아웃
    </button>
  </div>
</template>

<script>
import MiniProfile from '@/components/layout/MiniProfile.vue';
import { useAuthStore } from '@/stores/auth';

export default {
  name: 'SidebarLeft',

  components: {
    MiniProfile
  },

  methods: {
    async handleLogout() {
      try {
        const response = await fetch('http://localhost:8080/api/user/logout', {
          method: 'POST',
          credentials: 'include'
        });

        if (response.ok) {
          // authStore에서 사용자 정보 삭제
          const authStore = useAuthStore();
          authStore.clearUser();
          
          console.log('로그아웃 성공');
          alert('로그아웃되었습니다');
          
          //로그인 페이지로 이동
          this.$router.push('/login');
        } else {
          console.error('로그아웃 실패:', response.status);
          alert('로그아웃에 실패했습니다.');
        }
      } catch (error) {
        console.error('로그아웃 에러:', error);
        alert('로그아웃 중 오류가 발생했습니다.');
      }
    }
  }
}
</script>

<style scoped>
.sidebar-left {
  width: 220px;
  height: 100vh;
  background-color: #212121;
  padding: 24px 16px;
  display: flex;
  flex-direction: column;
  position: sticky;
  top: 0;
}

/* 로고 */
.logo {
  text-align: center;
  margin-top: 20px;
  margin-bottom: 40px; /* 미니 프로필과의 간격 */
  padding: 0 10px;
}

.logo-img {
  width: 80%;       /* 사이드바 너비에 맞춰 꽉 채우기 */
  max-width: 100px;  /* 너무 크면 최대 수치 제한 */
  height: auto;      /* 비율 유지 */
  object-fit: contain;
  transition: transform 0.2s;
}

.logo-img:hover {
  transform: scale(1.05); /* 로고 호버 시 살짝 커지는 효과 */
}

/* 메뉴 */
.menu {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.menu-item {
  padding: 12px 16px;
  color: #999;
  text-decoration: none;
  border-radius: 8px;
  transition: all 0.2s;
  text-align: left;
  border-left: 3px solid transparent;
}

.menu-item:hover {
  background-color: #2a2a2a;
  color: white;
}

.menu-item.router-link-active {
  background-color: #2a2a2a;
  color: white;
  border-left-color: #4169E1;
  font-weight: 600;
}

.menu-label {
  font-size: 15px;
}

/* 로그아웃 버튼 */
.logout-btn {
  width: 100%;
  padding: 12px;
  background-color: #2a2a2a;
  color: #999;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.2s;
  margin-top: auto;
}

.logout-btn:hover {
  background-color: #333;
  color: white;
}
</style>