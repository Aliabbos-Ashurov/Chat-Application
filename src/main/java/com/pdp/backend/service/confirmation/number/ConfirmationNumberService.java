package com.pdp.backend.service.confirmation.number;

import com.pdp.backend.model.confirmation.ConfirmationEmail;
import com.pdp.backend.model.confirmation.ConfirmationNumber;
import com.pdp.backend.repository.confirmation.ConfirmationEmailRepository;
import com.pdp.backend.repository.confirmation.ConfirmationNumberRepository;
import com.pdp.backend.service.BaseService;

/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  11:27
 **/
public interface ConfirmationNumberService extends BaseService<ConfirmationNumber> {
    ConfirmationNumberRepository repository = new ConfirmationNumberRepository();
}
