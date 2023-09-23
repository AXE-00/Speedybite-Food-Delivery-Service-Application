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
			strings: ['Tired of Cooking and Delivery Fees?', 'Movie Night? Date Night?', "Don't worry we got you covered!", "Your food is just one click away, Order Now!"],
			typeSpeed: 100,
			backSpeed: 10,
			loop: false,
		});
	}
}
