import {Component} from '@angular/core';
import {Address} from 'src/app/models/address.model';
import {Item} from 'src/app/models/item.model';
import {SpeedybiteService} from "../../services/speedybite.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {UserService} from "../../services/user.service";
import {LoginService} from "../../services/login.service";
import {DatePipe} from "@angular/common";
import {AddressDialogueComponent} from "../address-dialogue/address-dialogue.component";
import {PaymentOrder} from "../../models/payment-order.model";
import {RazorpayResponse} from "../../models/razorpay.model";
import {ToastrService} from "ngx-toastr";

declare var Razorpay: any;

@Component({
	selector: 'app-cart', templateUrl: './cart.component.html', styleUrls: ['./cart.component.css']
})
export class CartComponent {
	items: Item[] = [];
	total: number = 0;
	subtotal: number = 0;
	delivery: number = 0;
	userAddress: Address | undefined;
	currentTime1!: string;
	currentTime2!: string;

	constructor(private speedybiteService: SpeedybiteService, private router: Router, private dialog: MatDialog, private userService: UserService, private loginService: LoginService, private toaster: ToastrService) {
	}

	ngOnInit(): void {
		this.loginService.findCardCount();
		this.updateCurrentTime();
		this.userService.getAddress().subscribe((response: Address) => {
			this.userAddress = response;
		});

		this.userService.addressChanged.subscribe(() => {
			this.ngOnInit();
		});

		this.speedybiteService.getItems("inCart", localStorage.getItem('email')).subscribe(response => {
			console.log(response);
			this.items = response;
			this.calculateTotalPrice();
		}, error => {
			this.items = [];
			this.calculateTotalPrice();
		});
	}

	updateCurrentTime() {
		const currentDate = new Date();
		currentDate.setMinutes(currentDate.getMinutes() + 15);
		const datePipe = new DatePipe('en-US');
		this.currentTime1 = datePipe.transform(currentDate, 'h:mm:ss a') || '';
		currentDate.setMinutes(currentDate.getMinutes() + 15);
		this.currentTime2 = datePipe.transform(currentDate, 'h:mm:ss a') || '';
	}

	removeCartItem(itemName?: string) {
		if (itemName != null) {
			const itemToRemove = this.items.find(item => item.itemName === itemName);
			console.log(itemToRemove);
			if (itemToRemove != undefined) {
				this.speedybiteService.removeItem(localStorage.getItem('email'), itemToRemove).subscribe(response => {
					this.ngOnInit();
					this.calculateTotalPrice();
				});
			}
		}
	}

	addCartItem(itemName?: string) {
		if (itemName != null) {
			const itemToAdd = this.items.find(item => item.itemName === itemName);
			console.log(itemToAdd);
			if (itemToAdd != undefined) {
				this.speedybiteService.addItem(localStorage.getItem('email'), itemToAdd).subscribe(response => {
					this.ngOnInit();
					this.calculateTotalPrice();
				});
			}
		}
	}

	calculateTotalPrice() {
		this.total = this.items.map(item => {
			if (item.itemPrice && item.count) {
				return item.itemPrice * item.count;
			}
			return 0;
		})
			.reduce((acc, val) => acc + val, 0);

		this.subtotal = this.total;

		if (this.total > 199) {
			this.delivery = 0;
		}

		if (this.total < 199 && this.total > 0) {
			this.delivery = 50;
		}

		if (this.total < 199 && this.total > 0) {
			this.subtotal = this.total + this.delivery;
		}
	}

	calculateCartItemTotalPrice(itemName: string | undefined): number {
		let itemTotal = 0;
		this.items.map((item) => {
			if (item.itemName === itemName && item.count && item.itemPrice) {
				itemTotal = item.count * item.itemPrice;
			}
		});
		return itemTotal;
	}

	changeAddress() {
		const dialogRef = this.dialog.open(AddressDialogueComponent, {
			width: '700px',
		});
	}

	goToPayment = () => {
		const paymentOrder: PaymentOrder = {
			orderAmount: this.subtotal, orderInformation: "order_request"
		};

		this.speedybiteService.createPaymentOrder(paymentOrder).subscribe((response: any) => {
			console.log(response);
			if (response.status === 'created') {
				const options = {
					key: 'rzp_test_Nv2JwuXBqlVpsN',
					amount: response.amount,
					currency: 'INR',
					name: 'Speedy Bite',
					description: 'Order Total Amount',
					image: "data:image/png;base64,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",
					order_id: response.id,
					handler: (response: RazorpayResponse) => {
						console.log(response.razorpayPaymentId);
						console.log(response.razorpayOrderId);
						console.log(response.razorpaySignature);
						this.paymentSuccess();
					},
					prefill: {
						name: "", email: "", contact: ""
					},
					notes: {
						address: "Razorpay Corporate Office"
					},
					theme: {
						color: "#3399cc"
					}
				};
				const razorpay = new Razorpay(options) as any;

				razorpay.on('payment.failed', function (response: any) {
					console.log(response.error.code);
					console.log(response.error.description);
					console.log(response.error.source);
					console.log(response.error.step);
					console.log(response.error.reason);
					console.log(response.error.metadata.order_id);
					console.log(response.error.metadata.payment_id);
				});

				razorpay.open();
			}
		}, failure => {
			console.log(failure);
			alert("Oops! Something went wrong");
		});
	}

	paymentSuccess() {
		this.speedybiteService.placeOrder(this.items, localStorage.getItem("email")).subscribe(response => {
			this.toaster.success('Your Order Was Placed successfully', "Yay!")
			setTimeout(() => {
				this.router.navigateByUrl("/myOrders");
			}, 2500);
		}, (error) => {
			console.error("Payment completed but order is not placed? , contact customer care support: ", error);
		});

	}
}
