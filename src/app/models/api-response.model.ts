export interface ApiResponse<T> {
  statusCode: number;
  message: string;
  data: T | null;
  dataList: T[] | null;
}
