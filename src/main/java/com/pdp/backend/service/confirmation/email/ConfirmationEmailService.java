package com.pdp.backend.service.confirmation.email;

import com.pdp.backend.enums.MailType;
import com.pdp.backend.model.confirmation.ConfirmationEmail;
import com.pdp.backend.repository.confirmation.ConfirmationEmailRepository;
import com.pdp.backend.service.BaseService;
import java.util.UUID;

/**
 * The ConfirmationEmailService interface defines operations related to confirmation emails.
 * It extends the BaseService interface and specifies additional methods for confirmation email functionality.
 *
 * Implementing classes should provide concrete implementations for these methods to handle confirmation email operations.
 *
 * @see BaseService
 * @see com.pdp.backend.model.confirmation.ConfirmationEmail
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public interface ConfirmationEmailService extends BaseService<ConfirmationEmail> {
    ConfirmationEmailRepository repository = new ConfirmationEmailRepository();

    /**
     * Sends a confirmation email to the user with the specified user ID and email address.
     *
     * @param userID   The UUID of the user.
     * @param email    The email address of the user.
     * @param mailType The type of email to be sent (e.g., account confirmation, password reset).
     * @return True if the email is successfully sent, false otherwise.
     */
    boolean emailSender(UUID userID, String email, MailType mailType);

    /**
     * Retrieves an existing confirmation email for the user with the specified user ID and email address,
     * or creates a new one if it doesn't exist.
     *
     * @param userID   The UUID of the user.
     * @param email    The email address of the user.
     * @param mailType The type of email (e.g., account confirmation, password reset).
     * @return The existing confirmation email or a newly created one.
     */
    ConfirmationEmail getOrCreate(UUID userID, String email, MailType mailType);

    /**
     * Retrieves the confirmation code associated with the user for the specified email type.
     *
     * @param userID   The UUID of the user.
     * @param mailType The type of email (e.g., account confirmation, password reset).
     * @return The confirmation code, or null if not found.
     */
    Integer getConfirmationCodeByUser(UUID userID,MailType mailType);
}
