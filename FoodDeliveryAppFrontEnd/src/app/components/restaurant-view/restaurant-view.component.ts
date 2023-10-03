import {Component, Input, OnInit} from '@angular/core';
import {Order} from "../../models/order.model";
import {RestaurantService} from "../../services/restaurant.service";
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";
import {UserService} from "../../services/user.service";
import {SpeedybiteService} from "../../services/speedybite.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
	selector: 'app-restaurant-view',
	templateUrl: './restaurant-view.component.html',
	styleUrls: ['./restaurant-view.component.css']
})
export class RestaurantViewComponent implements OnInit {
	isLoading: boolean = true;
	searchedItems: any = [];
	searchedRestaurants: any = [];
	present: boolean = false;
	order!: Order;
	@Input('restaurants') restaurants: any = [];

	constructor(private restaurantService: RestaurantService, private route: Router, private login: LoginService, private userService: UserService, private speedybiteService: SpeedybiteService, private snackbar: MatSnackBar) {
	}

	ngOnInit(): void {
		this.restaurantService.getAllRestaurant().subscribe(response => {
			this.restaurants = response;
			this.isLoading = false;
		})
		this.login.searchElement.subscribe(searchElement => {
			this.onSearchTextChanged(searchElement)
		})
	}

	getItems(id: number) {
		this.restaurantService.getId(id)
		this.route.navigateByUrl('/viewItem')
	}

	// getLocation(location: any) {
	// 	this.getLocation(location);
	// 	this.restaurantService.getRestaurantByLocation(location).subscribe((response: Object) => {
	// 		const restaurantArray = response as any[];
	// 		if (location && location !== '') {
	// 			this.restaurants = restaurantArray.filter((restaurant: any) => restaurant.location === location);
	// 		} else {
	// 			this.restaurants = restaurantArray
	// 		}
	// 	}, error => {
	// 		alert("Oops!! Something went wrong, Please try again after some time")
	// 	})
	// }

	onSearchTextChanged(searchText: string) {
		this.route.navigateByUrl("");
		if (searchText && searchText.trim() !== '') {
			this.present = true;
			this.searchedItems = [];
			this.restaurants.forEach((restaurant: any) => {
				restaurant.items.forEach((item: any) => {
					if (item.itemName.toLowerCase().includes(searchText.toLowerCase())) {
						this.searchedItems.push(item);
					}
				})
			})
		} else {
			this.ngOnInit();
			this.present = false;
			this.searchedItems = []
		}
		if (searchText || searchText !== '') {
			this.present = true;
			this.searchedRestaurants = this.restaurants.filter((restaurant: any) => {
				return restaurant.restaurantName.toLowerCase().toLowerCase().includes(searchText.toLowerCase())
			})
		} else {
			this.present = false;
			this.searchedRestaurants = []
			this.ngOnInit();
		}
	}

	add() {
		this.route.navigateByUrl('/addRestaurant');
	}

	addToFavourites(itemData: any) {
		this.userService.addItemToFavourites(itemData).subscribe({
			next: data => {
				this.snackbar.open(`${itemData.itemName} is added to your favourites♥`, 'Ok', {
					duration: 3000
				})
			}, error(err) {
				alert("Item not added to favourites!")
			}
		})
	}

	addToCart(item: any) {
		if (this.login.isLoggedIn) {
			this.order = new Order();
			this.order.customerId = localStorage.getItem('email') ?? ''
			this.order.billingAddress = localStorage.getItem("address") ?? ''
			if (item) {
				item.status = "inCart";
				item.count = 1;
				if (!this.order.items) {
					this.order.items = []
				}
				this.order.items = this.order.items.concat(item)
				this.speedybiteService.insertOrder(this.order).subscribe(data => {
					this.ngOnInit()
					this.route.navigateByUrl("/cart")
					this.snackbar.open(`Item added to your cart`, 'OK', {
						duration: 3000
					})
				})
			} else this.route.navigateByUrl("/login");
		} else {
			this.route.navigateByUrl("/login")
		}
	}
}
