import {Component, Input} from '@angular/core';
import {RestaurantService} from "../../services/restaurant.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
	selector: 'app-admin-restaurant-view',
	templateUrl: './admin-restaurant-view.component.html',
	styleUrls: ['./admin-restaurant-view.component.css']
})
export class AdminRestaurantViewComponent {

	@Input('restaurants') restaurants: any = [];

	constructor(private restaurantService: RestaurantService, private route: Router, private activatedRoute: ActivatedRoute) {
	}

	ngOnInit() {
		this.restaurantService.getAllRestaurant().subscribe(restaurants => {
			this.restaurants = restaurants;
		}, error => {
			alert("error!!!!");
		})
		this.restaurantService.restaurantUpdated.subscribe(updated => {
			if (updated == true) {
				this.ngOnInit();
			}
		})
	}

	getItems(id: number) {
		this.restaurantService.getId(id);
		this.route.navigateByUrl('/adminFoodItem');
	}

	add() {
		this.route.navigateByUrl('/addRestaurant');
	}

	deleteRestaurant(id: any) {
		this.restaurantService.deleteRestaurant(id).subscribe(response => {
			alert("restaurant is deleted");
		}, error => {
			alert("OOPS! only admin can delete a restaurant");
		})
		let arr = this.restaurants.filter((res: { restaurantId: any }) => {
			if (res.restaurantId == id) {
				return false;
			} else {
				return true;
			}
		})
		this.restaurants = arr;
	}
}
