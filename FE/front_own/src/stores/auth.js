import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useAuthStore = defineStore('auth', {
  //상태 (데이터)
   state: () => ({
    user: null, //유저 정보 객체
    isLoggedIn: false
   }),

  getters: {
    // 유저 ID
    userId: (state) => state.user?.userId || null,
    
    // 이메일
    email: (state) => state.user?.email || '',
    
    // 이름
    name: (state) => state.user?.name || '',
    
    // 닉네임
    nickname: (state) => state.user?.nickname || '',
    
    // 프로필 이미지
    profileImg: (state) => state.user?.profileImg || '',
    
    // 등급 레벨
    tierLevel: (state) => state.user?.tierLevel || 0,
    
    // 게시물 수
    postCount: (state) => state.user?.postCount || 0,
    
    // 등급 레이블
    tierLabel: (state) => {
      const level = state.user?.tierLevel || 0;
      if (level === 3) return 'Pro';
      if (level === 2) return 'Amateur';
      return 'Newbie';
    },
    
    // 등급 CSS 클래스
    tierClass: (state) => {
      const level = state.user?.tierLevel || 0;
      if (level === 3) return 'tier-pro';
      if (level === 2) return 'tier-amateur';
      return 'tier-newbie';
    }
  },

  // Actions (메서드)
  actions: {
    /**
     * 로그인 - 유저 정보 저장
     */
    setUser(userData) {
      this.user = userData;
      this.isLoggedIn = true;
      
      // localStorage에도 저장 (새로고침 시 유지)
      localStorage.setItem('own_user', JSON.stringify(userData));
    },

    /**
     * 서버에서 유저 정보 가져오기 (세션 기반)
     */
    async fetchUser() {
      try {
        const response = await fetch('http://localhost:8080/api/user/me', {
          credentials: 'include'  // 세션 쿠키 포함
        });

        if (response.ok) {
          const userData = await response.json();
          this.setUser(userData);
          return true;
        } else {
          this.clearUser();
          return false;
        }
      } catch (error) {
        console.error('유저 정보 조회 실패:', error);
        this.clearUser();
        return false;
      }
    },

    /**
     * 로그아웃 - 유저 정보 삭제
     */
    clearUser() {
      this.user = null;
      this.isLoggedIn = false;
      localStorage.removeItem('own_user');
    },

    /**
     * 유저 정보 일부 업데이트
     */
    updateUser(updates) {
      if (this.user) {
        this.user = { ...this.user, ...updates };
        localStorage.setItem('own_user', JSON.stringify(this.user));
      }
    },

    /**
     * localStorage에서 유저 정보 복원 (새로고침 시)
     */
    restoreUser() {
      try {
        const savedUser = localStorage.getItem('own_user');
        if (savedUser) {
          this.user = JSON.parse(savedUser);
          this.isLoggedIn = true;
        }
      } catch (error) {
        console.error('유저 정보 복원 실패:', error);
        this.clearUser();
      }
    }
  }
})