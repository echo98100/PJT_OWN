<template>
    <div class="step-container">
        <h2>오늘의 운동을 마음껏 표현해 주세요</h2>

        <div class="caption-area">
            <textarea
              v-model="createStore.caption"
              placeholder="새로운 동작을 해냈다.  아이유의 가을 아침과 함께 운동한 덕분 :) "
              maxlength="400"
            ></textarea>
        </div>

        <div class="nav-btn">
            <button @click="handleSubmit"
            :disabled="!createStore.caption.trim()"
            class="submit-btn"> 완료 </button>
        </div>
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    import { useCreateStore } from '@/stores/create';
    import { createPost } from '@/api/post';
    import { saveMusicToDb } from '@/api/music';

    const createStore = useCreateStore();

    const handleSubmit = async () => {

        try {
        // 1단계: 음악 정보를 DB에 먼저 저장
        const musicPayload = {
            musicId: null, // 새로 저장하는 경우 null
            spotifyTrackId: createStore.selectedMusic.spotifyTrackId,
            musicTitle: createStore.selectedMusic.musicTitle,
            artist: createStore.selectedMusic.artist,
            albumImg: createStore.selectedMusic.albumImg,
            duration: parseInt(createStore.selectedMusic.duration) || 0, // int로 변환
            previewUrl: createStore.selectedMusic.previewUrl || null
        };

        console.log("1. 음악 저장 요청:", JSON.stringify(musicPayload, null, 2));
        const musicResponse = await saveMusicToDb(musicPayload);
        console.log("1. 음악 저장 응답:", musicResponse.data);
        
        // musicId 확인
        if (!musicResponse.data || !musicResponse.data.musicId) {
            throw new Error("음악 저장 실패: musicId를 받지 못했습니다.");
        }

        // 2단계: 포스트 생성 (musicId는 서버에서 받은 값 사용)
        const postPayload = {
            workoutTag: createStore.workoutTag.workoutTypeId, // Integer로 전송
            emotionTags: createStore.emotionTag.map(tag => {
                // emotionTag가 객체 배열인 경우 ID만 추출
                return typeof tag === 'object' ? tag.id : tag;
            }),
            musicId: musicResponse.data.musicId, // 서버에서 반환받은 musicId
            caption: createStore.caption.trim(),
            spotifyTrackUrl: `https://open.spotify.com/track/${createStore.selectedMusic.spotifyTrackId}`
        };

        console.log("2. 포스트 생성 요청:", postPayload);
        const postResponse = await createPost(postPayload);
        console.log("2. 포스트 생성 응답:", postResponse.data);
        
        alert("운동일지가 등록되었습니다!");
        createStore.resetData();
        
        // 필요시 메인 페이지로 이동
        // router.push('/');
        
    } catch (error) {
        console.error("저장 실패:", error);
        
        if (error.response) {
            console.error("응답 데이터:", error.response.data);
            console.error("응답 상태:", error.response.status);
            console.error("응답 헤더:", error.response.headers);
            
            const errorMsg = error.response.data.message || '서버 오류가 발생했습니다.';
            alert(`오류: ${errorMsg}`);
        } else if (error.request) {
            console.error("요청 실패:", error.request);
            alert("서버에 연결할 수 없습니다.");
        } else {
            console.error("에러:", error.message);
            alert("요청 중 오류가 발생했습니다.");
        }
    }
    }
</script>

<style scoped>
    .caption-area { position: relative; margin-bottom: 30px; }
textarea { 
  width: 100%; height: 180px; padding: 15px; 
  border: 1px solid #ddd; border-radius: 12px; 
  resize: none; font-size: 1rem; line-height: 1.5;
}
.char-count { position: absolute; bottom: 10px; right: 15px; color: #aaa; font-size: 0.8rem; }

.submit-btn { 
  flex: 2; background: #42b883; color: white; 
  padding: 15px; border-radius: 8px; border: none; font-weight: bold; font-size: 1rem;
}
.submit-btn:disabled { background: #ccc; }
.prev-btn { flex: 1; background: #eee; padding: 15px; border-radius: 8px; border: none; }

</style>