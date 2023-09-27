import {TestBed} from '@angular/core/testing';

import {SpeedybiteService} from './speedybite.service';

describe('SpeedybiteService', () => {
	let service: SpeedybiteService;

	beforeEach(() => {
		TestBed.configureTestingModule({});
		service = TestBed.inject(SpeedybiteService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});
});
