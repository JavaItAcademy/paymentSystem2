package kg.itacademy.paymentSystem.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfirmationModel {
    Long paymentId;
    String confirmationCode;
}
