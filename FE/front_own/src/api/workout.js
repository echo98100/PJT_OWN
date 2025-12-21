import instance from './index';

export const getWorkoutTags = () => {
    return instance.get('/workout');
}