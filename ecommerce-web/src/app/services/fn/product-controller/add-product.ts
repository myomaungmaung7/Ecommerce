/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { AuthResponse } from '../../models/auth-response';
import { ProductDto } from '../../models/product-dto';

export interface AddProduct$Params {
      body: ProductDto
}

export function addProduct(http: HttpClient, rootUrl: string, params: AddProduct$Params, context?: HttpContext): Observable<StrictHttpResponse<AuthResponse>> {
  const rb = new RequestBuilder(rootUrl, addProduct.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<AuthResponse>;
    })
  );
}

addProduct.PATH = '/api/v1/products/add-product';
