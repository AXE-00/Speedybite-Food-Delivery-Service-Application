import {Component} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
	selector: 'app-register', templateUrl: './register.component.html', styleUrls: ['./register.component.css']
})
export class RegisterComponent {
	responseData: any;
	public profileImage: any = File;
	registerForm: FormGroup = this.formBuilder.group({
		email: ["", [Validators.required, this.validateEmails]],
		password: ["", [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{7,}$/)]],
		confirmPassword: ["", [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{7,}$/)]],
		name: ["", [Validators.required, Validators.minLength(3), Validators.pattern('^[a-zA-Z]+$')]],
		phoneNumber: ["", [Validators.required, Validators.pattern('^[6-9]\\d{9}$')]]
	}, {validators: [this.validatePasswordMatch]})

	constructor(private formBuilder: FormBuilder,
				private userService: UserService,
				private loginStatus: LoginService,
				private router: Router,
				private toaster: ToastrService) {
	}

	get email() {
		return this.registerForm.get('email')
	}

	get password() {
		return this.registerForm.get('password');
	}

	get confirmPassword() {
		return this.registerForm.get('confirmPassword');
	}

	get name() {
		return this.registerForm.get('name');
	}

	get contact() {
		return this.registerForm.get('phoneNumber');
	}

	validateEmails(control: AbstractControl) {
		if (control.value !== '') {
			const emailString = control.value;
			const emails = emailString.split(',').map((email: string) => email.trim());
			const emailRegex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(gmail\.com|yahoo\.com)$/i;
			const isValid = emails.every((email: string) => email.match(emailRegex) !== null);
			if (!isValid) {
				return {validateEmails: false}
			}
		}
		return null;
	}

	validatePasswordMatch(formGroup: AbstractControl) {
		const password = formGroup.get('password')?.value
		const confirmPassword = formGroup.get('confirmPassword')?.value;
		if (!password || !confirmPassword) {
			return null;
		}
		if (password != confirmPassword) {
			return {passwordDoNotMatch: true}
		}
		return null;
	}

	public onProfileImageSelect(event: any) {

		const selectedImage = event.target.files[0];

		console.log(selectedImage);

		this.profileImage = selectedImage;

	}

	registerUser() {
		const userData = this.registerForm.value;

		const formData = new FormData();

		formData.append('userData', JSON.stringify(userData));

		formData.append('file', this.profileImage);

		this.userService.registerUser(formData).subscribe({

			next: data => {
				const userName: string = String(this.name?.value);
				localStorage.setItem('name', userName);
				this.toaster.success(`Welcome ${userName}, Thank you for signing up with SpeedyBite`)

				this.loginStatus.loginSuccess();
				this.router.navigateByUrl("/login")
			}
		})
	}
}
