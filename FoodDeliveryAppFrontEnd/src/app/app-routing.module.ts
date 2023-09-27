import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {HeaderComponent} from "./components/header/header.component";
import {AboutUsComponent} from "./components/about-us/about-us.component";
import {RegisterComponent} from "./components/register/register.component";

const routes: Routes = [
	{path: "", component: DashboardComponent},
	{path: "header", component: HeaderComponent},
	{path: "aboutUs", component: AboutUsComponent},
	{path: "register", component: RegisterComponent},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)], exports: [RouterModule]
})
export class AppRoutingModule {
}
