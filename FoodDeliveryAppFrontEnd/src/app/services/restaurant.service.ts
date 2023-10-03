import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
	providedIn: 'root'
})
export class RestaurantService {

	restaurantId: EventEmitter<number> = new EventEmitter();
	id: number = 0;
	location: string = '';
	apiBaseUrl: string = 'http://localhost:9000/api/v1/restaurant'
	restaurantUpdated: EventEmitter<boolean> = new EventEmitter();

	constructor(private httpClient: HttpClient) {
	}

	getAllRestaurant() {
		return this.httpClient.get(this.apiBaseUrl + "/getRestaurant")
	}

	getAllItems(id: number) {
		return this.httpClient.get(`${this.apiBaseUrl}/getItems/${id}`)
	}

	getId(id: number) {
		if (id) {
			this.id = id;
			this.restaurantId.emit(id)
		}
	}

	getLocation(location: string) {
		this.location = location;
	}

	getRestaurantByLocation(location: string) {
		return this.httpClient.get(`${this.apiBaseUrl}/getLocation/${location}`)
	}

	addRestaurant(addRestaurant: any) {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("Token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.post(`${this.apiBaseUrl}/addRestaurant`, addRestaurant, requestOption);
	}

	updateRestaurant(updateRestaurant: any, id: any) {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("Token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.delete(`${this.apiBaseUrl}/delete/${id}`, requestOption)
	}

	deleteRestaurant(id: number) {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("Token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.post(`${this.apiBaseUrl}/addItem/${id}`, requestOption);
	}

	addItem(addItem: any, id: number) {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("Token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.post(`${this.apiBaseUrl}/addItem/${id}`, addItem, requestOption)
	}

	updateItem(updateItem: any, id: number) {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("Token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.put(`${this.apiBaseUrl}/updateItem/${id}`, updateItem, requestOption)
	}

	deleteItem(deleteItem: any, id: number) {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("Token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.post(`${this.apiBaseUrl}/deleteItem/${id}`, deleteItem, requestOption)
	}
}
