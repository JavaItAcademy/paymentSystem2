#Postman запросы:
POST, на создание клиента
```
http://localhost:8080/clients
```
Тело:
```
{
	"name": "NameTest",
	"phoneNumber": "321"
}
```
-------------------------------------------------------------
POST, на создание счета
```
http://localhost:8080/accounts
```

Тело, вместо `client` использовать клиента созданного выше:
```
{
    "accountNumber": 1,
    "balance": 1000,
    "currency": "USD",
    "client": {
        "id": 20,
        "name": "NameTest",
        "phoneNumber": "123"
    }
}
```
-------------------------------------------------------------
POST, на создание платежа
```
http://localhost:8080/payments
```

Тело, заменить `id` для `accountFrom` и `accountTo` использовать id клиетов созданных выше:
```
{
{
    "amount": 100,
    "accountFrom": {
        "id": 23
    },
    "accountTo": {
        "id": 21
    },
    "currency": "USD"
}
```
-------------------------------------------------------------
GET, на получение платежей по статусам
```
http://localhost:8080/payments/getByStatus/SUCCESS
```
