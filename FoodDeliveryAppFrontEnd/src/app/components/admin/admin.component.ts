import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
	selector: 'app-admin',
	templateUrl: './admin.component.html',
	styleUrls: ['./admin.component.css']
})
export class AdminComponent {
	role: any = localStorage.getItem("email")

	constructor(private route: Router) {
	}

	addRestaurant() {
		this.route.navigateByUrl('/adminRestaurantView')
	}

	addFoodItem() {
		this.route.navigateByUrl('/adminFoodItem')
	}
}
