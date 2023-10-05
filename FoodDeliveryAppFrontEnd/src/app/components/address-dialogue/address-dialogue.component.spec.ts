import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AddressDialogueComponent} from './address-dialogue.component';

describe('AddressDialogueComponent', () => {
	let component: AddressDialogueComponent;
	let fixture: ComponentFixture<AddressDialogueComponent>;

	beforeEach(() => {
		TestBed.configureTestingModule({
			declarations: [AddressDialogueComponent]
		});
		fixture = TestBed.createComponent(AddressDialogueComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
