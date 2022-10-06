import { Topic } from './topic.interface';

export interface User {
  id: number;
  username: string;
  email: string;
  password?: string;
  topics?: Topic[];
  createdAt?: any;
  updatedAt: Date;
}
