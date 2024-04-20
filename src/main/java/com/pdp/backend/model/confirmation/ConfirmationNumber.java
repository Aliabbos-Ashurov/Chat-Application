package com.pdp.backend.model.confirmation;

import com.pdp.backend.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
/**
 * The ConfirmationNumber class represents a confirmation number sent via SMS for verification purposes in a system.
 * It extends the BaseModel class.
 * Each ConfirmationNumber instance is associated with a user ID, phone number, and confirmation code.
 *
 * This class provides a structured representation of confirmation numbers sent via SMS.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class ConfirmationNumber extends BaseModel  {
    private UUID userID;
    private String phoneNumber;
    private String code;
}
