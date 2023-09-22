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

@NgModule({
  declarations: [
	  AppComponent,
	  HeaderComponent,
	  HomeComponent,
	  DashboardComponent
  ],
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		RouterOutlet,
		AppRoutingModule,
		MatSidenavModule,
		MatToolbarModule,
	],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}