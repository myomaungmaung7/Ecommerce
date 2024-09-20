import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProductDto } from '../../../../services/models';
import { ProductControllerService } from '../../../../services/services';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {
  productRequest: ProductDto = {
    name: '',
    category: '',
    model: '',
    brand: '',
    price: undefined,
    stockQuantity: undefined,
    storage: '',
    description: '',
    releaseDate: '',
    colors: [
      {
        colorName: '',
        availableStock: undefined
      }
    ],
  };

  constructor(
    private router: Router,
    private productService: ProductControllerService,
  ) {}

  dropdownOpen: boolean[] = [false, false]; 

  toggleDropdown(index: number) {
    this.dropdownOpen[index] = !this.dropdownOpen[index]; 
  }

  onAddProduct() {
    this.productService.addProduct({
      body: this.productRequest
    }).subscribe({
      next: () => {
        this.router.navigate(['admin-home']);
      }
    })
  }

  addColor() {
    this.productRequest.colors?.push({
      colorName: '',
      availableStock: 0
    });
  }
  removeColor(index: number) {
    if (this.productRequest.colors && this.productRequest.colors.length > 1) {
      this.productRequest.colors.splice(index, 1); 
    }
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
