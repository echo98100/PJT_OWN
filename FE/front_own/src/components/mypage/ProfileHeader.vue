<template>
  <div class="profile-header">
    <div class="profile-left">
      <!-- 프로필 이미지 -->
      <div class="profile-image">
        <img :src="userInfo.profileImg || '/default-profile.png'" alt="프로필" />
      </div>
      
      <!-- 등급 배지 -->
      <div class="tier-badge" :class="tierClass">
        {{ tierLabel }}
      </div>
    </div>

    <div class="profile-right">
      <!-- 닉네임 & 등급 태그 -->
      <div class="profile-top">
        <h2 class="nickname">{{ userInfo.nickname }}</h2>
        <span class="tier-tag" :class="tierClass">{{ tierLabel }}</span>
      </div>

      <!-- 게시물 수 -->
      <p class="post-count">{{ userInfo.postCount }} posts</p>

      <!-- 탭 메뉴 -->
      <div class="tab-menu">
        <button 
          v-for="tab in tabs" 
          :key="tab.id"
          :class="['tab-btn', { active: currentTab === tab.id }]"
          @click="changeTab(tab.id)"
        >
          {{ tab.label }}
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ProfileHeader',
  
  data() {
    return {
      userInfo: {
        name: '',
        nickname: '',
        email: '',
        profileImg: '',
        tierLevel: 0,
        postCount: 0
      },
      currentTab: 'journal',
      tabs: [
        { id: 'journal', label: '나의 운동일지' },
        { id: 'likes', label: '좋아요 게시물' },
        { id: 'bookmarks', label: '북마크' }
      ]
    }
  },

  computed: {
    // 등급에 따른 라벨
    tierLabel() {
      if (this.userInfo.tierLevel === 3) return 'Pro';
      if (this.userInfo.tierLevel === 2) return 'Amateur';
      return 'Newbie';
    },
    
    // 등급에 따른 CSS 클래스
    tierClass() {
      if (this.userInfo.tierLevel === 3) return 'tier-pro';
      if (this.userInfo.tierLevel === 2) return 'tier-amateur';
      return 'tier-newbie';
    }
  },

  methods: {
    async fetchUserProfile(userId) {
      const url = userId 
        ? `http://localhost:8080/api/user/${userId}`
        : `http://localhost:8080/api/user/me`;

      try {
        const response = await fetch(url, {
          credentials: 'include' // 세션 쿠키 포함
        });
        
        if (!response.ok) {
          throw new Error('프로필 조회 실패');
        }
        
        const data = await response.json();
        
        this.userInfo = {
          name: data.name,
          nickname: data.nickname,
          email: data.email,
          profileImg: data.profileImg,
          tierLevel: data.tierLevel,
          postCount: data.postCount
        };
      } catch (error) {
        console.error('프로필 조회 실패:', error);
      }
    },

    changeTab(tabId) {
      this.currentTab = tabId;
      // 부모 컴포넌트에 탭 변경 알림
      this.$emit('tab-changed', tabId);
    }
  },

  mounted() {
    // 내 프로필 정보 가져오기
    this.fetchUserProfile();
  }
}
</script>

<style scoped>
.profile-header {
  display: flex;
  gap: 30px;
  padding: 24px;
  background-color: #1a1a1a;
  border-radius: 12px;
  margin-bottom: 20px;
}

.profile-left {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
}

.profile-image {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  overflow: hidden;
  background-color: #333;
  border: 3px solid #444;
}

.profile-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.tier-badge {
  padding: 6px 20px;
  color: white;
  border-radius: 6px;
  font-weight: bold;
  font-size: 14px;
}

.tier-pro {
  background-color: #2E3781;
}

.tier-amateur {
  background-color: #E1603F;
}

.tier-newbie {
  background-color: #F3DBC8;
}

.profile-right {
  flex: 1;
}

.profile-top {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 10px;
}

.nickname {
  color: white;
  font-size: 28px;
  margin: 0;
  font-weight: 600;
}

.tier-tag {
  padding: 4px 14px;
  color: white;
  border-radius: 4px;
  font-size: 13px;
  font-weight: bold;
}

.post-count {
  color: #999;
  margin: 0 0 24px 0;
  font-size: 15px;
}

.tab-menu {
  display: flex;
  gap: 0;
  border-bottom: 1px solid #333;
}

.tab-btn {
  padding: 14px 24px;
  background-color: transparent;
  color: #999;
  border: none;
  cursor: pointer;
  border-bottom: 3px solid transparent;
  font-size: 15px;
  font-weight: 500;
  transition: all 0.2s;
}

.tab-btn:hover {
  color: #ccc;
}

.tab-btn.active {
  color: white;
  border-bottom-color: #4169E1;
}
</style>