package kg.itacademy.paymentSystem.services.impls;

import kg.itacademy.paymentSystem.enttities.Account;
import kg.itacademy.paymentSystem.repos.AccountRepo;
import kg.itacademy.paymentSystem.repos.PaymentRepo;
import kg.itacademy.paymentSystem.services.AccountService;
import kg.itacademy.paymentSystem.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Account getById(Long id) {
        Optional<Account> optionalPayment = accountRepo.findById(id);
        return optionalPayment.get();
    }

    @Override
    public List<Account> getAll() {
        return accountRepo.findAll();
    }

    @Override
    public Account save(Account item) {
        return accountRepo.save(item);
    }
}
