package com.pdp.backend.model.confirmation;

import com.pdp.backend.enums.MailType;
import com.pdp.backend.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;
/**
 * The ConfirmationEmail class represents an email used for confirmation purposes in a system.
 * It extends the BaseModel class.
 * Each confirmation email instance is associated with a user ID, email address, confirmation code, and mail type.
 *
 * This class provides a structured representation of confirmation emails.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 12/April/2024
 */
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class ConfirmationEmail extends BaseModel{
    private UUID userID;
    private String email;
    private Integer code;
    private MailType mailType;
}
