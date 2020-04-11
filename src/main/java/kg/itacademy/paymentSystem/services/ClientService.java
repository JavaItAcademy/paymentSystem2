package kg.itacademy.paymentSystem.services;

import kg.itacademy.paymentSystem.enttities.Client;

import java.util.List;

public interface ClientService extends BaseService<Client> {
    List<Client> findByName(String name);
}
