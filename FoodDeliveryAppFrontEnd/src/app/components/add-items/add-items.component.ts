import {Component} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {RestaurantService} from "../../services/restaurant.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
	selector: 'app-add-items', templateUrl: './add-items.component.html', styleUrls: ['./add-items.component.css']
})
export class AddItemsComponent {
	userLoggedIn?: string;
	restaurantId: any;

	items: any = []
	addItemForm: any = this.formBuilder.group({
		"itemId": ['', Validators.required],
		"itemName": ['', Validators.required],
		"imageUrl": ['', Validators.required],
		"description": ['', Validators.required],
		"itemPrice": ['', Validators.required],
		"itemRating": ['', Validators.required],
	})

	constructor(private formBuilder: FormBuilder, private restaurantService: RestaurantService, private route: Router, private snackbar: MatSnackBar) {

	}

	addItem() {
		if (this.addItemForm.valid) {
			this.items.push(this.addItemForm.value)
			this.restaurantService.getId(this.restaurantId)
			this.restaurantService.addItem(this.items, this.restaurantId).subscribe(response => {
				this.snackbar.open("Item added successfully", 'OK', {
					duration: 3000
				})
				this.route.navigateByUrl('/restaurantView');
			}, error => {
				alert("all the details are required*")
			})
		}
	}

	view() {
		this.route.navigateByUrl('/viewAdminItem')
	}

	updateItem() {
		if (this.addItemForm.valid) {
			this.restaurantService.getId(this.restaurantId);
			this.restaurantService.updateItem(this.addItemForm.value, this.restaurantId).subscribe(response => {
				this.snackbar.open('Item have been updated')
			}, error => {
				alert('add all the items to update!')
			})
		}
	}
}
