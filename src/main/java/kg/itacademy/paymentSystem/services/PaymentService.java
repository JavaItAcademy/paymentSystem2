package kg.itacademy.paymentSystem.services;

import kg.itacademy.paymentSystem.enttities.Payment;
import kg.itacademy.paymentSystem.enums.Status;
import kg.itacademy.paymentSystem.models.ConfirmationModel;

import java.util.List;

public interface PaymentService extends BaseService<Payment> {
    Payment createPayment(Payment payment);

    List<Payment> getByStatus(Status status);

    List<Payment> getByClientId(Long clientId);

    List<Payment> getByClientIdNative(Long clientId);

    Payment confirmPayment(ConfirmationModel confirmationModel, String clientKeyWord) throws Exception;
}
