<template>
  <div class="app-container">
    <!--사이드바 있는 defaultLayout-->
    <template v-if="isDefaultLayout">
      <sidebar-left />

      <main class="main-content">
        <router-view />
      </main>

      <sidebarRight />
    </template>

    <!--사이드바 없는 auth-content-->
    <template v-else>
      <main class="auth-content">
        <router-view />
      </main>
    </template>

  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import SidebarLeft from './components/layout/SidebarLeft.vue';
import SidebarRight from './components/layout/SidebarRight.vue';

const route = useRoute();

const isDefaultLayout = computed(() => route.meta.layout === 'DefaultLayout');
</script>

<style scoped>
.app-container {
  display: flex;
  width: 100%;
  min-height: 100vh;
  background-color: #000; /* 다크모드 배경 */
  color: #fff;
}

.main-content {
  flex: 1; /* 중간 영역이 남은 공간을 다 가짐 */
  border-left: 1px solid #2a2a2a;
  border-right: 1px solid #2a2a2a;
  height: 100vh;
  overflow-y: auto; /* 본문만 스크롤 가능하게 */
}

.auth-content {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>