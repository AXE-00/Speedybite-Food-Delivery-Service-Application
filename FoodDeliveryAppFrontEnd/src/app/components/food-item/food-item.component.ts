import {Component, Input} from '@angular/core';
import {RestaurantService} from "../../services/restaurant.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {LoginService} from "../../services/login.service";

@Component({
	selector: 'app-food-item', templateUrl: './food-item.component.html', styleUrls: ['./food-item.component.css']
})
export class FoodItemComponent {

	@Input('restaurants') restaurants: any = [];

	constructor(private restaurantService: RestaurantService, private loginService: LoginService, private route: Router, private activatedRoute: ActivatedRoute, private snackbar: MatSnackBar) {
	}

	ngOnInit(): void {
		this.restaurantService.getAllRestaurant().subscribe(response => {
			this.restaurants = response;
		}, error => {
			alert("Error!")
		})
	}

	getItems(id: number) {
		this.loginService.isLoggedIn;
		this.restaurantService.getId(id);
		this.route.navigateByUrl('/adminFoodItemsView')
	}
}
