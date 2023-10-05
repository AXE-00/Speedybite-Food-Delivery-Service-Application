import {Component} from '@angular/core';
import {RestaurantService} from "../../services/restaurant.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
	selector: 'app-admin-food-items-view',
	templateUrl: './admin-food-items-view.component.html',
	styleUrls: ['./admin-food-items-view.component.css']
})
export class AdminFoodItemsViewComponent {
	data: number | undefined;
	restaurants: any = [];
	restaurantId!: number;

	constructor(private restaurantService: RestaurantService, private route: Router, private snackbar: MatSnackBar) {
	}

	ngOnInit(): void {
		this.data = 51
		this.restaurantId = this.restaurantService.id;
		this.restaurantService.getAllItems(+this.restaurantId).subscribe(data => {
			this.restaurants = data;
			console.log(this.restaurants)
		})
	}

	getItems() {
		this.restaurantService.getAllItems(+this.restaurantId).subscribe(data => {
			this.restaurants = data;
		})
	}

	deleteItem(item: any) {
		this.restaurantService.deleteItem(item, this.restaurantId).subscribe(data => {
			this.snackbar.open(`item ${item} is deleted successfully`, 'OK', {
				duration: 3000
			})
			this.getItems()
		}, error => {
			alert("Oops! only admin is authorized to delete items")
		})
		this.restaurants = this.restaurants.filter((restaurant: { restaurantId: any }) => {
			return restaurant.restaurantId != item;
		})
	}

	update() {
		this.route.navigateByUrl('/adminFoodItem')
	}
}
