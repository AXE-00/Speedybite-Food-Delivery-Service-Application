import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AdminFoodItemsViewComponent} from './admin-food-items-view.component';

describe('AdminFoodItemsViewComponent', () => {
	let component: AdminFoodItemsViewComponent;
	let fixture: ComponentFixture<AdminFoodItemsViewComponent>;

	beforeEach(() => {
		TestBed.configureTestingModule({
			declarations: [AdminFoodItemsViewComponent]
		});
		fixture = TestBed.createComponent(AdminFoodItemsViewComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
