package kg.itacademy.paymentSystem.services.impls;

import kg.itacademy.paymentSystem.enttities.Account;
import kg.itacademy.paymentSystem.enttities.Payment;
import kg.itacademy.paymentSystem.enums.Currency;
import kg.itacademy.paymentSystem.enums.Status;
import kg.itacademy.paymentSystem.exceptions.WrongConfirmationCodeException;
import kg.itacademy.paymentSystem.exceptions.WrongKeyWordException;
import kg.itacademy.paymentSystem.models.ConfirmationModel;
import kg.itacademy.paymentSystem.repos.PaymentRepo;
import kg.itacademy.paymentSystem.services.AccountService;
import kg.itacademy.paymentSystem.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private AccountService accountService;

    private Random random = new Random();

    @Override
    public Payment getById(Long id) {
        Optional<Payment> optionalPayment = paymentRepo.findById(id);
        return optionalPayment.get();
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepo.findAll();
    }

    @Override
    public Payment save(Payment item) {
        return paymentRepo.save(item);
    }

    @Override
    public Payment createPayment(Payment payment) {
        payment.setStatus(Status.AWAITING_CONFIRMATION);
        int confirmationCode = random.nextInt(9999 - 1000 + 1) + 1000;
        payment.setConfirmationCode(confirmationCode + "");
        return save(payment);
    }

    @Override
    public Payment confirmPayment(ConfirmationModel confirmationModel, String clientKeyWord ) throws Exception {
        Payment payment = getById(confirmationModel.getPaymentId());
        if (!payment.getAccountFrom().getClient().getCodeWord().equals(clientKeyWord)) throw new WrongKeyWordException();
        if (!payment.getConfirmationCode().equals(confirmationModel.getConfirmationCode())) throw new WrongConfirmationCodeException();
        processPayment(payment);
        return payment;
    }

    private void processPayment(Payment payment) {
        payment.setStatus(payment.getAmount().intValue()%2 == 0 ? Status.SUCCESS : Status.FAILED);
        if (payment.getStatus().equals(Status.SUCCESS)) {
            //Get money from
            Account from = accountService.getById(payment.getAccountFrom().getId());
            from.setBalance(from.getBalance().subtract(payment.getAmount()));
            Account to = accountService.getById(payment.getAccountTo().getId());
            to.setBalance(to.getBalance().add(payment.getAmount()));
            accountService.save(from);
            accountService.save(to);
            payment.setAccountFrom(from);
            payment.setAccountTo(to);
            //
            // Put money to
        }
        save(payment);
    }

    @Override
    public List<Payment> getByStatus(Status status) {
        return paymentRepo.findAllByStatusQuery(status);
//        return paymentRepo.findAllByStatus(status);
    }

    @Override
    public List<Payment> getByClientId(Long clientId) {
        return paymentRepo.findAllByAccountFrom_Client_Id(clientId);
    }

    @Override
    public List<Payment> getByClientIdNative(Long clientId) {
        return paymentRepo.findAllByClientId(clientId);
    }

}
