import instance from './index';

export const getEmotionTags = () => {
    return instance.get('/emotion');
}