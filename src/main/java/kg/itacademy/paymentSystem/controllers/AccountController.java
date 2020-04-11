package kg.itacademy.paymentSystem.controllers;

import kg.itacademy.paymentSystem.enttities.Account;
import kg.itacademy.paymentSystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @PostMapping
    public Account save(@RequestBody Account payment) {
        return accountService.save(payment);
    }
    
    @GetMapping("/{id}")
    public Account getById(@PathVariable("id") Long id) {
        return accountService.getById(id);
    }
}
