import {Component, OnInit} from '@angular/core';
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {UserService} from "../../services/user.service";
import {MatDialog} from "@angular/material/dialog";
import {LoginService} from "../../services/login.service";
import {UpdateUserComponent} from "../update-user/update-user.component";

@Component({
	selector: 'app-profile', templateUrl: './profile.component.html', styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
	imageSrc: SafeUrl = '';
	userInfo: any;
	visible: boolean = false;

	constructor(private userService: UserService, private loginService: LoginService, private sanitizer: DomSanitizer, private dialog: MatDialog) {
	}

	ngOnInit(): void {
		if (this.loginService.isLoggedIn) {
			if (localStorage.getItem('email') == "ashutosh.k.work@gmail.com") {
				this.visible = true
			}
		}
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
		});
		this.userService.getUserDetails().subscribe((data: any) => {
			this.userInfo = data;
		})
	}

	updateUser() {
		const dialogRef = this.dialog.open(UpdateUserComponent, {
			width: 'auto',
		});
	}
}
