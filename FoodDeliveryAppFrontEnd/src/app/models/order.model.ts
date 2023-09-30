import {Item} from "./item.model";

export class Order {
	customerId: string | undefined;
	billingAddress: string | undefined;
	items: Item[] | undefined;
}
