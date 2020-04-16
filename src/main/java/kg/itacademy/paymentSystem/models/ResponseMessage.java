package kg.itacademy.paymentSystem.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseMessage {
    boolean success;
    String message;
    Object json;
}
