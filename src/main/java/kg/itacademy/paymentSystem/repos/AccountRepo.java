package kg.itacademy.paymentSystem.repos;

import kg.itacademy.paymentSystem.enttities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
}
