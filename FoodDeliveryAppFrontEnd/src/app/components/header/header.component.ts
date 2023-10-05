import {Component, OnInit} from '@angular/core';
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {Item} from "../../models/item.model";
import {UserService} from "../../services/user.service";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {SpeedybiteService} from "../../services/speedybite.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
	selector: 'app-header', templateUrl: './header.component.html', styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
	imageSrc: SafeUrl = '';
	userType: string = 'login'
	isLoggedIn: boolean = true
	isNotLoggedIn: boolean = false
	isAdminLoggedIn: boolean = false
	userInfo: any;
	isProfileVisible: boolean = false
	cartItems: Item[] = [];
	cartCount: number = 0;

	constructor(private userService: UserService, private sanitizer: DomSanitizer, private loginService: LoginService, private router: Router, private speedybiteService: SpeedybiteService, private snackbar: MatSnackBar) {
	}

	ngOnInit(): void {
		if (this.loginService.isLoggedIn) {
			this.loginService.findCardCount()
		}
		if (this.loginService.getLoginStatus()) {
			if (this.userType === 'login') this.userType = 'logout'
			this.isLoggedIn = false
			this.isNotLoggedIn = true

			this.speedybiteService.getItems("inCart", localStorage.getItem('email')).subscribe(response => {
				this.cartItems = response;
			}, error => {
				console.error('Error retrieving items:', error)
			})
			this.loginService.cartCount.subscribe((number) => {
				this.cartCount = number
			})

			this.userService.getProfileImage().subscribe((data: any) => {
				if (data && data.imageData) {
					const imageData = data.imageData;
					const binaryData = atob(imageData)
					const arrayBuffer = new ArrayBuffer(binaryData.length);
					const uint8Array = new Uint8Array(arrayBuffer);
					for (let i = 0; i < binaryData.length; i++) {
						uint8Array[i] = binaryData.charCodeAt(i)
					}
					const blob = new Blob([uint8Array], {type: 'image/png'});
					this.imageSrc = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(blob))
				}
			})
			this.userService.getUserDetails().subscribe((data: any) => {
				this.userInfo = data;
			})
			if (localStorage.getItem('email') === 'ashutosh.k.work@gmail.com') {
				this.isAdminLoggedIn = false;
			} else {
				this.isAdminLoggedIn = true;
			}
		}

		document.addEventListener("DOMContentLoaded", function () {
			window.addEventListener("scroll", function () {
				if (this.scrollY > 120) {
					document.querySelector('#navbar')?.classList.add("sticky");
				} else {
					document.querySelector('#navbar')?.classList.remove("sticky");
				}
			});
		});
	}

	logout() {
		if (this.userType === 'logout') {
			this.loginService.onFailure()
			this.router.navigateByUrl('/login')
			localStorage.clear();
			this.userType = 'login'
			this.isNotLoggedIn = true
			this.isNotLoggedIn = false
		}
	}

	toggleProfileVisibility() {
		this.isProfileVisible = !this.isProfileVisible;
		// if (!this.isProfileVisible) {
		// 	this.isProfileVisible = true
		// } else {
		// 	this.isProfileVisible = false
		// }
	}

	onSearchTextChange(searchText: string) {
		this.loginService.searchElement.emit(searchText)
		if (searchText.length > 0) {
			this.loginService.homePresent.emit(false)
		} else {
			this.loginService.homePresent.emit(true);
		}
	}

	moveToHome() {
		this.ngOnInit();
		this.router.navigateByUrl("")
	}
}
