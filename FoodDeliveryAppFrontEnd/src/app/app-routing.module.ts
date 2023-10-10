import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {HeaderComponent} from "./components/header/header.component";
import {AboutUsComponent} from "./components/about-us/about-us.component";
import {RegisterComponent} from "./components/register/register.component";
import {LoginComponent} from "./components/login/login.component";
import {RestaurantViewComponent} from "./components/restaurant-view/restaurant-view.component";
import {AddItemsComponent} from "./components/add-items/add-items.component";
import {AdminComponent} from "./components/admin/admin.component";
import {ContactComponent} from "./components/contact/contact.component";
import {CartComponent} from "./components/cart/cart.component";
import {AddRestaurantComponent} from "./components/add-restaurant/add-restaurant.component";
import {AdminRestaurantViewComponent} from "./components/admin-restaurant-view/admin-restaurant-view.component";
import {AdminFoodItemsViewComponent} from "./components/admin-food-items-view/admin-food-items-view.component";
import {FoodItemComponent} from "./components/food-item/food-item.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {UpdateUserComponent} from "./components/update-user/update-user.component";
import {PageNotFoundComponent} from "./components/page-not-found/page-not-found.component";
import {ViewItemsComponent} from "./components/view-items/view-items.component";
import {MyOrdersComponent} from "./components/my-orders/my-orders.component";
import {FavouritesComponent} from "./components/favourites/favourites.component";

const routes: Routes = [
	{path: "register", component: RegisterComponent},
	{path: "login", component: LoginComponent},
	{path: "aboutUs", component: AboutUsComponent},
	{path: "header", component: HeaderComponent},
	{path: "myOrders", component: MyOrdersComponent},
	{path: "cart", component: CartComponent},
	{path: "profile", component: ProfileComponent},
	{path: "update", component: UpdateUserComponent},
	{path: "", component: DashboardComponent},
	{path: "viewItem", component: ViewItemsComponent},
	{path: "favourites", component: FavouritesComponent},
	{path: "restaurantView", component: RestaurantViewComponent},
	{path: "admin", component: AdminComponent},
	{path: "adminFoodItemsView", component: AdminFoodItemsViewComponent},
	{path: "adminRestaurantView", component: AdminRestaurantViewComponent},
	{path: "adminAddItem", component: AddItemsComponent},
	{path: "adminAddRestaurant", component: AddRestaurantComponent},
	{path: "adminFoodItem", component: FoodItemComponent},
	{path: "contact", component: ContactComponent},
	{path: "**", component: PageNotFoundComponent},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)], exports: [RouterModule]
})
export class AppRoutingModule {
}
