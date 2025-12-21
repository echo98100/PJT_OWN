<template>
  <div class="mini-profile">
    <div class="profile-image">
      <img :src="userInfo.profileImg || '/default-profile.png'" alt="프로필" />
    </div>
    <p class="nickname">{{ userInfo.nickname }}</p>
    <div class="tier-badge" :class="tierClass">
      {{ tierLabel }}
    </div>
  </div>
</template>

<script>
export default {
  name: 'MiniProfile',

  data() {
    return {
      userInfo: {
        nickname: '',
        profileImg: '',
        tierLevel: 0
      }
    }
  },

  computed: {
    tierLabel() {
      if (this.userInfo.tierLevel === 3) return 'Pro';
      if (this.userInfo.tierLevel === 2) return 'Amateur';
      return 'Newbie';
    },

    tierClass() {
      if (this.userInfo.tierLevel === 3) return 'tier-pro';
      if (this.userInfo.tierLevel === 2) return 'tier-amateur';
      return 'tier-newbie';
    }
  },

  methods: {
    async fetchUserInfo() {
      try {
        const response = await fetch('http://localhost:8080/api/user/me', {
          credentials: 'include'
        });

        if (response.ok) {
          const data = await response.json();
          this.userInfo = {
            nickname: data.nickname,
            profileImg: data.profileImg,
            tierLevel: data.tierLevel
          };
        }
      } catch (error) {
        console.error('사용자 정보 조회 실패:', error);
      }
    }
  },

  mounted() {
    this.fetchUserInfo();
  }
}
</script>

<style scoped>
.mini-profile {
  background-color: #2a2a2a;
  border-radius: 12px;
  padding: 20px;
  text-align: center;
  margin-bottom: 30px;
}

.profile-image {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  overflow: hidden;
  margin: 0 auto 12px;
  background-color: #333;
  border: 3px solid #444;
}

.profile-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.nickname {
  color: white;
  font-size: 16px;
  font-weight: 600;
  margin: 0 0 10px 0;
}

.tier-badge {
  display: inline-block;
  padding: 6px 20px;
  border-radius: 6px;
  color: white;
  font-size: 13px;
  font-weight: bold;
}

.tier-pro {
  background-color: #4169E1;
}

.tier-amateur {
  background-color: #FF6B6B;
}

.tier-newbie {
  background-color: #51CF66;
}
</style>