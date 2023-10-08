import {Component, OnInit} from '@angular/core';
import {Item} from "../../models/item.model";
import {SpeedybiteService} from "../../services/speedybite.service";
import {LoginService} from "../../services/login.service";
import {ToastrService} from "ngx-toastr";

@Component({
	selector: 'app-my-orders', templateUrl: './my-orders.component.html', styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {
	items: Item[] = [];
	itemsHistory: Item[] = [];

	constructor(private speedybiteService: SpeedybiteService, private loginService: LoginService, private toaster: ToastrService) {
	}

	ngOnInit(): void {
		this.items = [];
		this.itemsHistory = [];
		this.loginService.findCardCount();
		this.speedybiteService.getItems("ordered", localStorage.getItem('email')).subscribe(response => {
			console.log(response);
			this.items = this.items.concat(response);
		});

		this.speedybiteService.getItems("packed", localStorage.getItem('email')).subscribe(response => {
			console.log(response);
			this.items = this.items.concat(response);
		});

		this.speedybiteService.getItems("delivered", localStorage.getItem('email')).subscribe(response => {
			console.log(response);
			this.itemsHistory = this.itemsHistory.concat(response);
		});

		this.speedybiteService.getItems("cancelled", localStorage.getItem('email')).subscribe(response => {
			console.log(response);
			this.itemsHistory = this.itemsHistory.concat(response);
		});
	}

	calculateTotalPrice(itemName: string | undefined): number {
		let itemTotal = 0;
		this.items.map((item) => {
			if (item.itemName == itemName && item.count && item.itemPrice) {
				itemTotal = item.count * item.itemPrice;
			}
		});
		return itemTotal;
	}

	calculateTotalPriceForHistory(itemName: string | undefined): number {
		let itemTotal = 0;
		this.itemsHistory.map((item) => {
			if (item.itemName == itemName && item.count && item.itemPrice) {
				itemTotal = item.count * item.itemPrice;
			}
		});
		return itemTotal;
	}

	cancelOrder(itemName: string | undefined) {
		this.toaster.success("Dear Customer, Your Order is Cancelled. You will get the refund in 24 hours! Thank You")

		let items: Item[] = this.items.filter((item) => item.itemName == itemName);
		this.speedybiteService.cancelOrder(items, localStorage.getItem('email')).subscribe(response => {
			console.log(response);
			this.items = [];
			this.ngOnInit();
		});
	}
}
