import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/login'  // 기본 주소 → 로그인으로 자동 이동
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/auth/LoginPage.vue')
    
  },
  {
    path: '/signup',
    name: 'Signup',
    component: () => import('@/views/auth/SignupPage.vue')
  },
  {
  path: '/mypage',
  name: 'MyPage',
  component: () => import('@/views/mypage/MyPage.vue')
},
{
  path: '/mypage/edit',
  name: 'ProfileEdit',
  component: () => import('@/views/mypage/ProfileEdit.vue')
}
  
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router
