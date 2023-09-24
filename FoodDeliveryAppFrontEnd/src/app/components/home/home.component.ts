import {Component} from '@angular/core';
import Typed from 'typed.js';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent {
	present: boolean = true;

	ngAfterViewInit(): void {
		const autoTyped = new Typed('.autoType', {
			strings: ['Tired of Cooking and Delivery Fees?', 'Game Night?! Movie Marathon?! Fun Night In?!', "We'll Hook You Up With Amazing Meals!", "Your food is just one click away, Order Now!"],
			typeSpeed: 60,
			backSpeed: 10,
			loop: false,
		});
	}
}
