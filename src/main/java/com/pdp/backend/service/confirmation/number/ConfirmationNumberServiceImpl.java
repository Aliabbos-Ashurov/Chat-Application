package com.pdp.backend.service.confirmation.number;

import com.pdp.backend.model.confirmation.ConfirmationNumber;
import java.util.List;
import java.util.UUID;

/**
 * The ConfirmationNumberServiceImpl class provides the implementation for the ConfirmationNumberService interface.
 * It handles operations related to confirmation numbers.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public class ConfirmationNumberServiceImpl implements ConfirmationNumberService{
    private static ConfirmationNumberServiceImpl instance;

    private ConfirmationNumberServiceImpl() {
    }

    public static ConfirmationNumberServiceImpl getInstance() {
        if (instance == null) {
            instance = new ConfirmationNumberServiceImpl();
        }
        return instance;
    }
    @Override
    public boolean add(ConfirmationNumber object) {
        repository.add(object);
        return true;
    }

    @Override
    public List<ConfirmationNumber> search(String query) {
        return null;
    }

    @Override
    public boolean update(ConfirmationNumber object) {
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public List<ConfirmationNumber> getAll() {
        return repository.getAll();
    }

    @Override
    public ConfirmationNumber getById(UUID id) {
        return repository.findById(id);
    }
}
