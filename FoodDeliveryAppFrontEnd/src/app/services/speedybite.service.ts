import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Item} from "../models/item.model";
import {Order} from "../models/order.model";

@Injectable({
	providedIn: 'root'
})
export class SpeedybiteService {
	private orderServiceUrl: String;
	private paymentServiceUrl: String;


	constructor(private httpClient: HttpClient) {
		this.orderServiceUrl = 'http://localhost:9000/api/v1/order/'
		this.paymentServiceUrl = 'http://localhost:9000/api/v1/payment/'
	}

	public getItems(status: String, email: String | null): Observable<Item[]> {
		const url: string = `${this.orderServiceUrl}getItems/${status}/${email}`;
		return this.httpClient.get<Item[]>(url);
	}

	public addItem(email: string | null, item: Item): Observable<any> {
		let httpHeader: HttpHeaders = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem('Token')
		})
		let requestOptions = {headers: httpHeader}
		const url = `${this.orderServiceUrl}addItem/${email}`;
		return this.httpClient.post<Item>(url, item, requestOptions);
	}

	public removeItem(email: string | null, item: Item): Observable<any> {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem('Token')
		})
		let requestOptions = {headers: httpHeader}
		const url = `${this.orderServiceUrl}removeItem/${email}`;
		return this.httpClient.post<Item>(url, item, requestOptions)
	}

	public insertOrder(order: Order): Observable<any> {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem('Token')
		})
		let requestOptions = {headers: httpHeader}
		const url = `${this.orderServiceUrl}insertOrder`
		return this.httpClient.post<Order>(url, order, requestOptions)
	}

}
