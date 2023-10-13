import {Component, Inject} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
	selector: 'app-address-dialogue',
	templateUrl: './address-dialogue.component.html',
	styleUrls: ['./address-dialogue.component.css']
})
export class AddressDialogueComponent {
	addressForm: FormGroup;

	constructor(
		public dialogRef: MatDialogRef<AddressDialogueComponent>,
		@Inject(MAT_DIALOG_DATA) public data: any,
		private formBuilder: FormBuilder,
		private userService: UserService,
		private router: Router
	) {
		this.addressForm = this.formBuilder.group({
			houseNo: ['', Validators.required],
			landmark: ['', Validators.required],
			street: ['', Validators.required],
			city: ['', [Validators.required, Validators.pattern('^[a-zA-Z]+$')]],
			pin: ['', [Validators.required, Validators.pattern('^[0-9]+$')]]
		});
	}

	saveAddress() {
		if (this.addressForm.valid) {
			const newAddress = this.addressForm.value;
			this.userService.changeAddress(newAddress).subscribe(
				(response) => {
					this.userService.addressChanged.emit(true);
					this.dialogRef.close(newAddress);
				},
				(error) => {
					console.log('Something went wrong');
				}
			);
		}
	}

	cancel() {
		this.dialogRef.close();
	}
}
