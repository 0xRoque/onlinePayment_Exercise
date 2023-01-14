package services;

public class PaypalService implements OnlinePaymentService {

	@Override
	public Double paymentFee(double amount) {
		return amount * 0.2;
	}

	@Override
	public Double interest(double amount, int months) {
		return amount * 0.1 * months;
	}

}
