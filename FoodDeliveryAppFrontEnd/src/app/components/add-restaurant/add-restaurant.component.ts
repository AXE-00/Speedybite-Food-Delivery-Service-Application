import {Component} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {RestaurantService} from "../../services/restaurant.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
	selector: 'app-add-restaurant',
	templateUrl: './add-restaurant.component.html',
	styleUrls: ['./add-restaurant.component.css']
})
export class AddRestaurantComponent {
	role = localStorage.getItem("email")
	userLoggedIn?: string;
	restaurants: any = []
	restaurantForm = this.formBuilder.group({
		restaurantId: ['', Validators.required],
		restaurantName: ['', Validators.required],
		imageUrl: ['', Validators.required],
		location: ['', Validators.required],
		rating: ['', Validators.required],
	});

	constructor(private formBuilder: FormBuilder, private snackbar: MatSnackBar, private restaurantService: RestaurantService, private route: Router) {
	}

	addRestaurant() {
		if (this.role === 'ashutosh.k.work@gmail.com') {
			if (this.restaurantForm.valid) {
				this.restaurantService.addRestaurant(this.restaurantForm.value).subscribe(response => {
					this.snackbar.open(`Restaurant added successfully`)
					this.restaurantService.restaurantUpdated.emit(true)
					this.restaurantForm.reset();
				}, error => {
					alert('add all the particulars')
				})
			} else {
				alert('You are not authorized to add or delete')
			}
		}
	}

	update() {
		if (this.restaurantForm.valid) {
			this.restaurantService.updateRestaurant(this.restaurantForm.value, this.restaurantForm.value.restaurantId).subscribe(response => {
				this.snackbar.open(`Restaurant has been updated successfully!`)
				this.restaurantService.restaurantUpdated.emit(true)
				this.restaurantForm.reset()
			}, error => {
				alert('update is not possible')
			})
		}
	}

	view() {
		this.route.navigateByUrl('/restaurantView')
	}

	addItem() {
		const id = 1
		this.restaurantService.getId(id);
		this.restaurantForm.reset();
		this.route.navigateByUrl('/adminAddItem')
	}
}
