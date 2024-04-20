package com.pdp.backend.service.confirmation.number;

import com.pdp.backend.model.confirmation.ConfirmationNumber;
import com.pdp.backend.repository.confirmation.ConfirmationNumberRepository;
import com.pdp.backend.service.BaseService;
/**
 * The ConfirmationNumberService interface defines operations related to confirmation numbers.
 * It extends the BaseService interface to provide basic CRUD operations for confirmation numbers.
 * Implementing classes should provide concrete implementations for these methods to handle confirmation number operations.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public interface ConfirmationNumberService extends BaseService<ConfirmationNumber> {
    ConfirmationNumberRepository repository = new ConfirmationNumberRepository();
}
