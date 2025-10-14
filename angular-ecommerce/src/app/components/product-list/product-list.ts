import { Component, DestroyRef, inject, OnInit } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { takeUntilDestroyed } from "@angular/core/rxjs-interop";
import { Product } from '../../common/product';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.html',
  styleUrls: ['./product-list.css'],
  imports: [CommonModule, CurrencyPipe]
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];
  destroyRef = inject(DestroyRef);

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.listProducts();
  }

  listProducts() {
    this.productService.getProductList()
      .pipe(takeUntilDestroyed(this.destroyRef)) // 自动管理订阅生命周期
      .subscribe({
        next: (data) => {
          this.products = data; 
        },
        error: (error) => {
          console.error('Failed to load products:', error);
          // 这里可以添加用户友好的错误提示
        }
    });
  }

}
