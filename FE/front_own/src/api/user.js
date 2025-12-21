import axios from 'axios'

const BASE_URL = 'http://localhost:8080'

//로그인
export function login(email, password) {
  return axios.post(`${BASE_URL}/api/users/login`, {
    email: email,
    password: password
  });
}

export function signup(userData){
    return axios.post(`${BASE_URL}/api/users/signup`, formData,{
        headers: {
      'Content-Type': 'multipart/form-data'
        }
    });
}
// 이메일 중복 체크
export function checkEmailDuplicate(email) {
  return axios.get(`${BASE_URL}/check-email`, {
    params: { email: email }
  });
}

// 닉네임 중복 체크 
export function checkNicknameDuplicate(nickname) {
  return axios.get(`${BASE_URL}/check-nickname`, {
    params: { nickname: nickname }
  });
}

// 프로필 조회
export function getUserProfile(userId) {
  return axios.get(`${BASE_URL}/api/user/${userId}`);
}

// 내 정보 조회 (테스트용? 백엔드에 있어서 일단 넣어놓음)
export function getMyProfile() {
  return axios.get(`${BASE_URL}/api/user/me`);
}
// 프로필 수정
export function updateUserProfile(userId, userData) {
  return axios.put(`${BASE_URL}/api/user/${userId}`, userData);
}

// 회원 탈퇴
export function deleteUser(userId) {
  return axios.delete(`${BASE_URL}/api/user/${userId}`);
}

// 로그아웃
export function logout() {
  return axios.post(`${BASE_URL}/api/user/logout`);
}