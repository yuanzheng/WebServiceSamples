import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../common/product';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'  // 默认在根注入器提供，通常无需在 `providers` 重复配置
})
export class ProductService {
  private http = inject(HttpClient); // 注入 HttpClient
  //constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8080/api/products';

  // 获取数据示例 (GET)
  getProductList(): Observable<Product[]> {
    return this.http.get<GetResponse>(this.baseUrl).pipe(
      map(response => response._embedded.products)
    );
  }
  
}

interface GetResponse {
  _embedded: {
    products: Product[];
  }
}
