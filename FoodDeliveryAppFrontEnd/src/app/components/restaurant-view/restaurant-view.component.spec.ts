import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RestaurantViewComponent} from './restaurant-view.component';

describe('RestaurantViewComponent', () => {
	let component: RestaurantViewComponent;
	let fixture: ComponentFixture<RestaurantViewComponent>;

	beforeEach(() => {
		TestBed.configureTestingModule({
			declarations: [RestaurantViewComponent]
		});
		fixture = TestBed.createComponent(RestaurantViewComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
