import { Comment } from './comment.interface';

export interface Post {
  id?: number;
  title: string;
  content: string;
  user_id: number;
  topic_id: number;
  createdAt?: any;
  updatedAt?: Date;
}
