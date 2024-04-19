package com.pdp.backend.service.confirmation.email;

import com.pdp.backend.enums.MailType;
import com.pdp.backend.model.confirmation.ConfirmationEmail;
import com.pdp.backend.repository.confirmation.ConfirmationEmailRepository;
import com.pdp.backend.service.BaseService;

import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  11:26
 **/
public interface ConfirmationEmailService extends BaseService<ConfirmationEmail> {
    ConfirmationEmailRepository repository = new ConfirmationEmailRepository();
    boolean emailSender(UUID userID, String email, MailType mailType);

    ConfirmationEmail getOrCreate(UUID userID, String email, MailType mailType);
    Integer getConfirmationCodeByUser(UUID userID,MailType mailType);
}
