import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Favourites} from "../models/favourites.model";
import {Address} from "../models/address.model";

@Injectable({
	providedIn: 'root'
})
export class UserService {

	authorizationUrl: String = "http://localhost:9000/api/v1/auth";
	userUrl: String = "http://localhost:9000/api/v1/userService"
	addressChanged: EventEmitter<boolean> = new EventEmitter();
	public userInformation: any = {};

	constructor(private httpClient: HttpClient) {
	}

	login(loginData: any): Observable<Object> {
		console.log(loginData);
		return this.httpClient.post(`${this.authorizationUrl}/login`, loginData);
	}

	registerUser(formData: FormData): Observable<any> {
		return this.httpClient.post(`${this.userUrl}/register/user`, formData);
	}

	updateUser(formData: FormData): Observable<Object> {
		let httpHeader: HttpHeaders = new HttpHeaders({
			"Authorization": "Bearer " + localStorage.getItem('token')
		})
		let requestOption = {headers: httpHeader}
		console.log(requestOption)
		console.log(formData)
		return this.httpClient.put(`${this.userUrl}/update/user`, formData, requestOption)
	}

	getProfileImage() {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.get(`${this.userUrl}/get/profile`, requestOption)
	}

	getUserDetails() {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.get(`${this.userUrl}/getName`, requestOption)
	}

	addItemToFavourites(itemData: any) {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.post(`${this.userUrl}/add/item`, itemData, requestOption)
	}

	getListOfFavourites(): Observable<Array<Favourites>> {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.get<Array<Favourites>>(`${this.userUrl}/get/user/favourite`, requestOption)
	}

	removeFavourites(itemRemoved: any) {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.delete(`${this.userUrl}/remove/favourite?itemId=${itemRemoved}`, requestOption)
	}

	checkList(itemId: number): Observable<boolean> {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.get<boolean>(`${this.userUrl}/check/list?itemId=${itemId}`, requestOption)
	}

	changeAddress(address: Address) {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.post(`${this.userUrl}/add/address`, address, requestOption)
	}

	getAddress(): Observable<Address | any> {
		let httpHeader = new HttpHeaders({
			'Authorization': 'Bearer ' + localStorage.getItem("token")
		})
		let requestOption = {headers: httpHeader}
		return this.httpClient.get(`${this.userUrl}/get/address`, requestOption)
	}
}

