package kg.itacademy.paymentSystem.services;

import kg.itacademy.paymentSystem.enttities.Payment;
import kg.itacademy.paymentSystem.enums.Status;

import java.util.List;

public interface PaymentService extends BaseService<Payment> {
    Payment createPayment(Payment payment);

    List<Payment> getByStatus(Status status);
}
