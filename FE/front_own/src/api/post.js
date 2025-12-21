import instance from './index';

export const createPost = (postData) => {
    return instance.post('/post',postData);
}