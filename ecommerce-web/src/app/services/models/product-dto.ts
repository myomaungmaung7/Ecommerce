/* tslint:disable */
/* eslint-disable */
import { ProductColorDto } from '../models/product-color-dto';
export interface ProductDto {
  active?: boolean;
  brand?: string;
  category?: string;
  colors?: Array<ProductColorDto>;
  createdAt?: number;
  description?: string;
  discount?: number;
  id?: number;
  image?: Blob;
  imagePath?: string;
  model?: string;
  name?: string;
  price?: number;
  releaseDate?: string;
  stockQuantity?: number;
  storage?: string;
  updatedAt?: number;
}
