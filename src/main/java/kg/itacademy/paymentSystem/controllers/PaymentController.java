package kg.itacademy.paymentSystem.controllers;

import kg.itacademy.paymentSystem.enttities.Payment;
import kg.itacademy.paymentSystem.enums.Status;
import kg.itacademy.paymentSystem.models.ConfirmationModel;
import kg.itacademy.paymentSystem.models.ResponseMessage;
import kg.itacademy.paymentSystem.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseMessage save(@RequestBody Payment payment) {
        try {
            return ResponseMessage.builder()
                    .success(true)
                    .json(paymentService.createPayment(payment))
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable("id") Long id) {
        return paymentService.getById(id);
    }

    @GetMapping("/getByStatus/{status}")
    public List<Payment> getByStatus(@PathVariable("status") Status status) {
        return paymentService.getByStatus(status);
    }

    @GetMapping("/getByClientId/{clientId}")
    public List<Payment> getByClientId(@PathVariable("clientId") Long clientId) {
        return paymentService.getByClientId(clientId);
    }

    @GetMapping("/getByClientIdNative/{clientId}")
    public List<Payment> getByClientIdNative(@PathVariable("clientId") Long clientId) {
        return paymentService.getByClientIdNative(clientId);
    }

    @PostMapping("/confirm")
    public ResponseMessage confirm(@RequestBody ConfirmationModel confirmationModel, @RequestHeader String codeWord) {
        try {
            return ResponseMessage.builder()
                    .success(true)
                    .json(paymentService.confirmPayment(confirmationModel, codeWord))
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }
}
