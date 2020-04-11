package kg.itacademy.paymentSystem.controllers;

import kg.itacademy.paymentSystem.enttities.Payment;
import kg.itacademy.paymentSystem.enums.Status;
import kg.itacademy.paymentSystem.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAll() {
        return paymentService.getAll();
    }

    @PostMapping
    public Payment save(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable("id") Long id) {
        return paymentService.getById(id);
    }
    @GetMapping("/getByStatus/{status}")
    public List<Payment> getByStatus(@PathVariable("status") Status status) {
        return paymentService.getByStatus(status);
    }
}
