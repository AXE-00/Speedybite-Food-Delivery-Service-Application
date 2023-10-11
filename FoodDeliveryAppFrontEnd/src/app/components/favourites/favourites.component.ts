import {Component, OnInit} from '@angular/core';
import {Order} from "../../models/order.model";
import {UserService} from "../../services/user.service";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {SpeedybiteService} from "../../services/speedybite.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
	selector: 'app-favourites', templateUrl: './favourites.component.html', styleUrls: ['./favourites.component.css']
})
export class FavouritesComponent implements OnInit {

	favouriteList: any;
	addFavouriteToCart!: Order;
	isLoading: boolean = true;
	dataPresent?: boolean;

	constructor(private userService: UserService, private loginService: LoginService, private router: Router, private speedybiteService: SpeedybiteService, private snackbar: MatSnackBar) {
	}

	ngOnInit(): void {
		this.userService.getListOfFavourites().subscribe({
			next: data => {
				if (data != null && data.length > 0) {
					this.favouriteList = data;
					this.isLoading = false
					this.dataPresent = true
				} else {
					this.isLoading = false
					this.dataPresent = false
				}
			}
		})
	}

	removeItemFromFavourite(itemData: any) {
		this.userService.removeFavourites(itemData).subscribe({
			next: data => {
				this.snackbar.open(`Item Removed`, 'Ok', {
					duration: 2000
				})
				this.ngOnInit();
				this.router.navigateByUrl("/favourites")
			}
		})
	}

	addToCart(theItem: any) {
		if (this.loginService.isLoggedIn) {
			this.addFavouriteToCart = new Order();
			this.addFavouriteToCart.customerId = localStorage.getItem('email') ?? ''
			this.addFavouriteToCart.billingAddress = localStorage.getItem('address') ?? '';
			if (theItem) {
				theItem.status = "inCart"
				theItem.count = 1;
				if (!this.addFavouriteToCart.items) {
					this.addFavouriteToCart.items = [];
				}
				this.addFavouriteToCart.items = this.addFavouriteToCart.items.concat(theItem)
				this.speedybiteService.insertOrder(this.addFavouriteToCart).subscribe(data => {
					this.snackbar.open(`Favourite Item Added To Cart`, 'OK', {
						duration: 2500
					})
				})
				this.router.navigateByUrl("/cart")
			}
		}
	}
}
