/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addProduct } from '../fn/product-controller/add-product';
import { AddProduct$Params } from '../fn/product-controller/add-product';
import { AuthResponse } from '../models/auth-response';

@Injectable({ providedIn: 'root' })
export class ProductControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `addProduct()` */
  static readonly AddProductPath = '/api/v1/products/add-product';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addProduct()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addProduct$Response(params: AddProduct$Params, context?: HttpContext): Observable<StrictHttpResponse<AuthResponse>> {
    return addProduct(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addProduct$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addProduct(params: AddProduct$Params, context?: HttpContext): Observable<AuthResponse> {
    return this.addProduct$Response(params, context).pipe(
      map((r: StrictHttpResponse<AuthResponse>): AuthResponse => r.body)
    );
  }

}
