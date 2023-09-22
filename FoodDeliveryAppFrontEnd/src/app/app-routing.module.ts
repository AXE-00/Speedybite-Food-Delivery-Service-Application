import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {HeaderComponent} from "./components/header/header.component";

const routes: Routes = [
	{path: "", component: DashboardComponent},
	{path: "header", component: HeaderComponent},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)], exports: [RouterModule]
})
export class AppRoutingModule {
}
