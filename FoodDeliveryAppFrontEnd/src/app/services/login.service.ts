import {EventEmitter, Injectable} from '@angular/core';
import {SpeedybiteService} from "./speedybite.service";
import {Item} from "../models/item.model";

@Injectable({
	providedIn: 'root'
})
export class LoginService {

	isLoggedIn: boolean = false
	searchElement: EventEmitter<string> = new EventEmitter();
	cartCount: EventEmitter<number> = new EventEmitter();
	homePresent: EventEmitter<boolean> = new EventEmitter();

	//add eventEmitter for home and cart later
	constructor(private speedybite: SpeedybiteService) {
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

	findCardCount() {
		this.speedybite.getItems("inCart", localStorage.getItem('email')).subscribe((response: Item[]) => {
			let items: Item[] = response;
			let count = 0
			items.map(item => {
				if (item.count !== undefined) {
					count += item.count;
				}
			})
			this.cartCount.emit(count)
		}, (error) => {
			this.cartCount.emit(0)
		})
	}
}
