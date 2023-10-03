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
	addToCart!: Order;
	@Input('restaurants') restaurants: any = [];

	constructor(private restaurantService: RestaurantService, private route: Router, private login: LoginService, private userService: UserService, private speedybiteService: SpeedybiteService, private snackbar: MatSnackBar) {
	}

	ngOnInit(): void {
		this.restaurantService.getAllRestaurant().subscribe(response => {
			this.restaurants = response;
			this.isLoading = false;
		})
	}

	getItems(id: number) {
		this.restaurantService.getId(id)
		this.route.navigateByUrl('/viewItem')
	}

	getLocation(location: any) {
		this.getLocation(location);
		this.restaurantService.getRestaurantByLocation(location).subscribe((response: Object) => {
			const restaurantArray = response as any[];
			if (location && location !== '') {
				this.restaurants = restaurantArray.filter((restaurant: any) => restaurant.location === location);
			} else {
				this.restaurants = restaurantArray
			}
		}, error => {
			alert("Oops!! Something went wrong, Please try again after some time")
		})
	}

}
