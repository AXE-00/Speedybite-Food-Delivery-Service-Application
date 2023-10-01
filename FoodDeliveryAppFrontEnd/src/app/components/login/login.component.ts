import {Component} from '@angular/core';
import {AbstractControl, FormBuilder, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";

@Component({
	selector: 'app-login', templateUrl: './login.component.html', styleUrls: ['./login.component.css']
})
export class LoginComponent {
	responseData: any;
	userRole: any
	loginForm = this.formBuilder.group({
		email: ["", [Validators.required, this.validateEmails]],
		password: ["", [Validators.required, Validators.minLength(8)]]
	})

	constructor(private formBuilder: FormBuilder,
				private userService: UserService,
				private snackbar: MatSnackBar,
				private route: Router,
				private loginStatus: LoginService) {
	}

	get email() {
		return this.loginForm.get('email')
	}

	get password() {
		return this.loginForm.get('password')
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

	showHidePassword() {
		const password = document.getElementById('password') as HTMLInputElement;
		const toggle = document.getElementById('toggle') as HTMLElement;

		if (password.type === 'password') {
			password.setAttribute('type', 'text')
			toggle.classList.add('fa-eye-slash')
		} else {
			toggle.classList.remove('fa=eye-slash')
			password.setAttribute('type', 'password')
		}
	}

	loginUser() {
		this.userService.login(this.loginForm.value).subscribe({
			next: data => {
				localStorage.setItem('Token', this.responseData.Token);
				localStorage.setItem('email', this.responseData.email);

				this.userRole === localStorage.getItem('email');

				if (this.userRole === 'ashutosh.k.work@gmail.com') {
					this.route.navigateByUrl('/admin');
					this.loginStatus.loginSuccess();
					this.loginStatus.getLoginStatus();
				} else {
					this.loginStatus.loginSuccess();
					this.loginStatus.getLoginStatus();
					this.route.navigateByUrl("");
				}

				this.snackbar.open('Logged In Successfully', 'action', {
					duration: 3000, panelClass: ['mat-toolbar', 'mat-primary']
				});
			}, error: () => {
				this.loginStatus.onFailure()
				this.snackbar.open("Authorization failed, Please check the provided details and try again", 'OK', {
					duration: 4000, panelClass: ['mat-toolbar', 'mat-warn']
				})
			}
		})
	}
}
