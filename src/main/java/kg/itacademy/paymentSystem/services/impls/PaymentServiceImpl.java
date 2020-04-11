package kg.itacademy.paymentSystem.services.impls;

import kg.itacademy.paymentSystem.enttities.Account;
import kg.itacademy.paymentSystem.enttities.Payment;
import kg.itacademy.paymentSystem.enums.Status;
import kg.itacademy.paymentSystem.repos.PaymentRepo;
import kg.itacademy.paymentSystem.services.AccountService;
import kg.itacademy.paymentSystem.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private AccountService accountService;

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
        payment.setStatus(payment.getAmount().intValue()%2 == 0 ? Status.SUCCESS : Status.FAILED);
        if (payment.getStatus().equals(Status.SUCCESS)) {
            //Get money from
            Account from = accountService.getById(payment.getAccountFrom().getId());
            from.setBalance(from.getBalance().subtract(payment.getAmount()));
            Account to = accountService.getById(payment.getAccountTo().getId());
            to.setBalance(to.getBalance().add(payment.getAmount()));
            accountService.save(from);
            accountService.save(to);
            //Put money to
        }
        return save(payment);
    }

    @Override
    public List<Payment> getByStatus(Status status) {
        return paymentRepo.findAllByStatusQuery(status);
//        return paymentRepo.findAllByStatus(status);
    }
}
