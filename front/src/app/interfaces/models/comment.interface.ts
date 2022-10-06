export interface Comment {
  id?: number;
  comment: string;
  post_id: number;
  user_id: number;
  createdAt?: Date;
  updatedAt?: Date;
}
