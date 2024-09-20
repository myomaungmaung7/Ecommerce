import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent {
  constructor(
    private router: Router,
  ) {}

  dropdownOpen: boolean[] = [false, false]; // Array to handle multiple dropdowns

  toggleDropdown(index: number) {
    this.dropdownOpen[index] = !this.dropdownOpen[index]; // Toggle dropdown based on index
  }

  dashboard() {
    this.router.navigate(['admin-home'])
  }

  addProduct() {
    this.router.navigate(['add-product'])
  }
}
