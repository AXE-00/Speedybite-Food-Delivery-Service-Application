import {Component, OnInit} from '@angular/core';
import {RestaurantService} from "../../services/restaurant.service";
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";
import {SpeedybiteService} from "../../services/speedybite.service";
import {UserService} from "../../services/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Order} from "../../models/order.model";
import {Item} from "../../models/item.model";

@Component({
	selector: 'app-view-items', templateUrl: './view-items.component.html', styleUrls: ['./view-items.component.css']
})
export class ViewItemsComponent implements OnInit {
	addToCartOrder: Order = new Order();
	itemId!: boolean;
	itemCount: number | undefined;
	restaurantItems: any[] = [];
	cartItems: any[] = [];
	favoriteItems: any[] = [];
	isLoading: boolean = false;
	restaurantId!: number;

	constructor(private restaurantService: RestaurantService, private router: Router, private loginService: LoginService, private speedybiteService: SpeedybiteService, private userService: UserService, private snackBar: MatSnackBar) {
	}

	ngOnInit(): void {
		this.loginService.findCardCount();
		this.itemCount = 51;
		this.restaurantId = this.restaurantService.id;

		if (this.loginService.isLoggedIn) {
			const cartItems = this.initializeCartItems();
			const favoriteItems = this.initializeFavoriteItems();

			Promise.all([cartItems, favoriteItems]).then(() => {
				this.restaurantService.getAllItems(+this.restaurantId).subscribe((data: any) => {
					this.restaurantItems = data.map((item: any) => {
						const cartItem = this.cartItems?.find((cartItem: any) => cartItem.itemName === item.itemName);
						const inCart = !!cartItem;
						const favoriteItem = this.favoriteItems?.find((favoriteItem: any) => favoriteItem.itemName === item.itemName);
						const inFavorites = !!favoriteItem;
						const count = cartItem ? cartItem.count : 0;
						return {item, inCart, inFavorites, count};
					});
				});
			}).catch((error) => {
				console.error("Error retrieving favoriteItems or cartItems:", error);
			});
		} else {
			this.restaurantService.getAllItems(+this.restaurantId).subscribe((data: any) => {
				this.restaurantItems = data.map((item: any) => {
					const inCart = false;
					const inFavorites = false;
					return {item, inCart, inFavorites};
				});
			});
		}
	}

	remove(item?: Item) {
		if (item != null) {
			item.status = "inCart";
			item.count = 1;
			this.speedybiteService.removeItem(localStorage.getItem('email'), item).subscribe(response => {
				this.ngOnInit();
			});
		}
	}

	add(item?: Item) {
		if (item != null) {
			item.status = "inCart";
			item.count = 1;
			this.speedybiteService.addItem(localStorage.getItem('email'), item).subscribe(response => {
				this.ngOnInit();
			})
		}
	}

	addToCart(theItem: any) {
		if (this.loginService.isLoggedIn) {
			this.addToCartOrder = new Order();
			this.addToCartOrder.customerId = localStorage.getItem('email') ?? '';
			this.addToCartOrder.billingAddress = localStorage.getItem("address") ?? '';
			if (theItem) {
				theItem.status = "inCart";
				theItem.count = 1;
				if (!this.addToCartOrder.items) {
					this.addToCartOrder.items = [];
				}
				this.addToCartOrder.items = this.addToCartOrder.items.concat(theItem);

				this.speedybiteService.insertOrder(this.addToCartOrder).subscribe(data => {
					this.ngOnInit();
					this.router.navigateByUrl("/viewItem");
					this.snackBar.open(`Item Added to Cart`, 'success', {
						duration: 3000, panelClass: ['mat-toolbar', 'mat-primary']
					});
				});
			} else {
				this.router.navigateByUrl("/login");
			}
		} else {
			this.router.navigateByUrl("/login");
		}
	}

	addToFavourites(itemData: any) {
		if (this.loginService.isLoggedIn) {
			if (itemData.inFavorites) {
				this.userService.removeFavourites(itemData.item.itemId).subscribe({
					next: (data) => {
						this.ngOnInit();
						this.snackBar.open(`${itemData.item.itemName} is removed from Favorites`, 'success', {
							duration: 3000, panelClass: ['mat-toolbar', 'mat-primary']
						});
					}, error(err) {
						alert("Not removed from favorites!");
					},
				});
			} else {
				this.userService.addItemToFavourites(itemData.item).subscribe({
					next: (data) => {
						this.ngOnInit();
						this.snackBar.open(`${itemData.item.itemName} is added to your Favorites`, 'success', {
							duration: 3000, panelClass: ['mat-toolbar', 'mat-primary']
						});
					}, error(err) {
						alert("Not added to favorites!");
					},
				});
			}
		} else {
			this.router.navigateByUrl("/login");
		}
	}

	private initializeCartItems(): Promise<void> {
		return new Promise((resolve, reject) => {
			this.speedybiteService.getItems("inCart", localStorage.getItem('email')).subscribe(response => {
				this.cartItems = response;
				console.log(this.cartItems);
				resolve();
			}, (error) => {
				this.cartItems = [];
				resolve();
			});
		});
	}

	private initializeFavoriteItems(): Promise<void> {
		return new Promise((resolve, reject) => {
			this.userService.getListOfFavourites().subscribe((data: any) => {
				this.favoriteItems = data;
				resolve();
			}, (error) => {
				this.favoriteItems = [];
				resolve();
			})
		});
	}
}
