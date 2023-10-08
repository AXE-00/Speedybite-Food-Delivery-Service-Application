import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ViewItemsComponent} from './view-items.component';

describe('ViewItemsComponent', () => {
	let component: ViewItemsComponent;
	let fixture: ComponentFixture<ViewItemsComponent>;

	beforeEach(() => {
		TestBed.configureTestingModule({
			declarations: [ViewItemsComponent]
		});
		fixture = TestBed.createComponent(ViewItemsComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
