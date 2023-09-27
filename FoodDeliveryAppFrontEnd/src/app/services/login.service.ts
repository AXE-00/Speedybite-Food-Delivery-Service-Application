import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
	providedIn: 'root'
})
export class LoginService {

	isLoggedIn: boolean = false
	searchElement: EventEmitter<string> = new EventEmitter();

	//add eventEmitter for home and cart later
	constructor() {
	}

	loginSuccess() {
		this.isLoggedIn = true;
	}

	onFailure() {
		this.isLoggedIn = false;
	}

	getLoginStatus() {
		return this.isLoggedIn;
	}
}
