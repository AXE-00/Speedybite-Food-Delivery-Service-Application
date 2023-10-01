import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterOutlet} from "@angular/router";
import {AppRoutingModule} from './app-routing.module';
import {HeaderComponent} from './components/header/header.component';
import {HomeComponent} from './components/home/home.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatToolbarModule} from "@angular/material/toolbar";
import {FooterComponent} from './components/footer/footer.component';
import {AboutUsComponent} from './components/about-us/about-us.component';
import {RegisterComponent} from './components/register/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {HttpClientModule} from "@angular/common/http";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {ToastrModule} from "ngx-toastr";
import {LoginComponent} from './components/login/login.component';
import {RestaurantViewComponent} from './components/restaurant-view/restaurant-view.component';

@NgModule({
  declarations: [
	  AppComponent,
	  HeaderComponent,
	  HomeComponent,
	  DashboardComponent,
	  FooterComponent,
	  AboutUsComponent,
	  RegisterComponent,
	  LoginComponent,
	  RestaurantViewComponent
  ],
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		RouterOutlet,
		AppRoutingModule,
		MatSidenavModule,
		MatToolbarModule,
		FormsModule,
		ReactiveFormsModule,
		MatButtonModule,
		MatIconModule,
		HttpClientModule,
		MatSnackBarModule,
		MatFormFieldModule,
		MatInputModule,
		MatCardModule,
		ToastrModule.forRoot(),
	],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
