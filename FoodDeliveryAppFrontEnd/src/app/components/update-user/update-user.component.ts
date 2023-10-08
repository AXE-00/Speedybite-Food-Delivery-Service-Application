import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {ToastrService} from "ngx-toastr";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";

@Component({
	selector: 'app-update-user',
	templateUrl: './update-user.component.html',
	styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
	public uploadedImage: any = File;
	userName: string = "";
	userPhone: any;
	fileName: string = "";
	updateForm = this.formBuilder.group({
		name: ['', [Validators.minLength(3)]],
		phoneNo: [null, [Validators.pattern(/^[6789]\d{9,9}$/)]]
	})

	constructor(private formBuilder: FormBuilder,
				private userService: UserService,
				private toaster: ToastrService,
				private dialogRef: MatDialogRef<UpdateUserComponent>,
				private loginStatus: LoginService, @Inject(MAT_DIALOG_DATA) public dataX: any,
				private router: Router) {
	}

	get name() {
		return this.updateForm.get('name');
	}

	get phone() {
		return this.updateForm.get('phoneNo');
	}

	ngOnInit(): void {
		this.userService.getUserDetails().subscribe((data: any) => {
			this.userName = data.name;
			this.userPhone = data.phoneNo;

			this.updateForm.patchValue({
				name: this.userName,
				phoneNo: this.userPhone
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


}
