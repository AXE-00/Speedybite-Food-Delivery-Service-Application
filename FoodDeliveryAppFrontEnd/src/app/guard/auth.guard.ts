import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {LoginService} from "../services/login.service";
import {Observable} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";


@Injectable({
	providedIn: "root"
})
export class AuthGuard implements CanActivate {
	constructor(private loginService: LoginService,
				private route: Router,
				private snackbar: MatSnackBar) {
	}

	canActivate(route: ActivatedRouteSnapshot,
				state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
		if (this.loginService.getLoginStatus()) {
			return true;
		} else {
			this.snackbar.open("You will need to login to access this!", "Failure", {
				duration: 5000
			});
			return false
		}
	}

}
