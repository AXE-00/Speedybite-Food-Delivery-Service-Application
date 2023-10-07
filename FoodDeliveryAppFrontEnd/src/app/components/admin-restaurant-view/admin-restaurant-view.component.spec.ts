import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AdminRestaurantViewComponent} from './admin-restaurant-view.component';

describe('AdminRestaurantViewComponent', () => {
	let component: AdminRestaurantViewComponent;
	let fixture: ComponentFixture<AdminRestaurantViewComponent>;

	beforeEach(() => {
		TestBed.configureTestingModule({
			declarations: [AdminRestaurantViewComponent]
		});
		fixture = TestBed.createComponent(AdminRestaurantViewComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
