import {Component, Input, OnInit} from '@angular/core';
import Typed from 'typed.js';
import {RestaurantService} from "../../services/restaurant.service";
import {LoginService} from "../../services/login.service";

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
	present: boolean = true;
	@Input('restaurants') restaurants: any[] = [];

	constructor(private restaurantService: RestaurantService, private loginService: LoginService) {
	}

	ngAfterViewInit(): void {
		const autoTyped = new Typed('.autoType', {
			strings: ['Tired of Cooking and Delivery Fees?', 'Game Night?! Movie Marathon?! Fun Night In?!', "We'll Hook You Up With Amazing Meals!", "Your food is just one click away, Order Now!"],
			typeSpeed: 60,
			backSpeed: 10,
			loop: false,
		});
	}

	ngOnInit(): void {
		this.loginService.findCardCount();
		this.loginService.homePresent.subscribe(data => {
			this.present = data;
			console.log(data)
		})
	}

}
