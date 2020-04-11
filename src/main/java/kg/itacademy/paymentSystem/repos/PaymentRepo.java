package kg.itacademy.paymentSystem.repos;

import kg.itacademy.paymentSystem.enttities.Payment;
import kg.itacademy.paymentSystem.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    List<Payment> findAllByStatus(Status status);

    @Query("select p from Payment p where status =:status")
    List<Payment> findAllByStatusQuery(@Param("status") Status status);
}
