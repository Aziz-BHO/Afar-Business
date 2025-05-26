import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from './product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productUrl = 'http://127.0.0.1:8080/product';
  private uploadUrl = 'http://127.0.0.1:8080/uploadproductimage/';

  constructor(private http: HttpClient) { }

  getProducts() {
    return this.http.get<any>(this.productUrl);
  }

  addProduct(idEntreprise: number, product: Product) {
    return this.http.post<any>(this.productUrl + '/entreprise/' + idEntreprise, product);
  }

  deleteProduct(idPublicity) {
    return this.http.delete<any>(this.productUrl + '/' + idPublicity);
  }
  
  updateProduct(product: Product) {
    return this.http.put<any>(this.productUrl + '/', product);
  }

  uploadFile(file: File, idPublicity) {
    const formdata: FormData = new FormData();

    formdata.append('file', file);

    const req = new HttpRequest('POST', this.uploadUrl + idPublicity, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }
}
