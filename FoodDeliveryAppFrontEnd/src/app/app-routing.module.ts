import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {HeaderComponent} from "./components/header/header.component";
import {AboutUsComponent} from "./components/about-us/about-us.component";
import {RegisterComponent} from "./components/register/register.component";
import {LoginComponent} from "./components/login/login.component";
import {RestaurantViewComponent} from "./components/restaurant-view/restaurant-view.component";

const routes: Routes = [
	{path: "", component: DashboardComponent},
	{path: "header", component: HeaderComponent},
	{path: "aboutUs", component: AboutUsComponent},
	{path: "register", component: RegisterComponent},
	{path: "login", component: LoginComponent},
	{path: "restaurantView", component: RestaurantViewComponent},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)], exports: [RouterModule]
})
export class AppRoutingModule {
}
