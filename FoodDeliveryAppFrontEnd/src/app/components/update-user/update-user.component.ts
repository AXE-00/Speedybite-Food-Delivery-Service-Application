import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {ToastrService} from "ngx-toastr";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";

@Component({
	selector: 'app-update-user', templateUrl: './update-user.component.html', styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
	public uploadedImage: any = File;
	userName: string = "";
	userPhone: any;
	fileName: string = "";
	updateForm = this.formBuilder.group({
		name: ['', [Validators.minLength(3)]], contactNumber: [null, [Validators.pattern(/^[6789]\d{9,9}$/)]]
	})

	constructor(private formBuilder: FormBuilder, private userService: UserService, private toaster: ToastrService, private dialogRef: MatDialogRef<UpdateUserComponent>, private loginStatus: LoginService, @Inject(MAT_DIALOG_DATA) public dataX: any, private router: Router) {
	}

	get name() {
		return this.updateForm.get('name');
	}

	get phone() {
		return this.updateForm.get('contactNumber');
	}

	ngOnInit(): void {
		this.userService.getUserDetails().subscribe((data: any) => {
			this.userName = data.name;
			this.userPhone = data.contactNumber;

			this.updateForm.patchValue({
				name: this.userName, contactNumber: this.userPhone
			})
		})
	}

	public onImageUpload(event: any) {
		const userImg = event.target.files[0]
		if (userImg) {
			this.uploadedImage = userImg
			this.fileName = userImg.name
		} else {
			this.uploadedImage = null;
			this.fileName = "";
		}
	}

	updateUser() {
		const userData = this.updateForm.value;
		const formData = new FormData();
		formData.append('userInfo', JSON.stringify(userData))
		formData.append('file', this.uploadedImage)

		this.userService.updateUser(formData).subscribe({
			next: data => {
				this.router.navigateByUrl("")
				this.dialogRef.close();
				this.toaster.success("Updated successfully!", 'Profile')
			}, error(error) {
				alert("Oops! Profile not updated.")
			}
		})
	}

	clear() {
		this.dialogRef.close();
	}
}
