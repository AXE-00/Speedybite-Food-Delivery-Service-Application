import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterOutlet} from "@angular/router";
import {AppRoutingModule} from './app-routing.module';
import {HeaderComponent} from './components/header/header.component';

@NgModule({
  declarations: [
	  AppComponent,
	  HeaderComponent
  ],
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		RouterOutlet,
		AppRoutingModule,
	],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
