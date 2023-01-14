package services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	//Delcar o PaymentOnlineService
	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	
	public void processContract(Contract contract, int months) {
		double parcelaBasica = contract.getTotalValue() / months;
		//For para gerar parcelas
		for(int i=1; i<months; i++) {
			//Data de vencimento de cada parcela
			LocalDate dataDeVencimento = contract.getDate().plusMonths(i);
			//Calcular as parcelas(juro(interest) e taxa(fee) para cada um dos meses)
			double juro = onlinePaymentService.interest(parcelaBasica, i);
			double tax = onlinePaymentService.paymentFee(parcelaBasica + juro);
			double quota = parcelaBasica + juro + tax;
			//Add os dados como sendo um obj installment
			contract.getInstalments().add(new Installment(dataDeVencimento, quota));
		}

	}
	
}
