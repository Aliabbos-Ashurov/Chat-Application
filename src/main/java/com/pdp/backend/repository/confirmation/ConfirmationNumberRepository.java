package com.pdp.backend.repository.confirmation;

import com.pdp.backend.model.confirmation.ConfirmationNumber;
import com.pdp.backend.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  13:33
 **/
public class ConfirmationNumberRepository implements BaseRepository<ConfirmationNumber> {
    List<ConfirmationNumber> confirmationNumbers;

    public ConfirmationNumberRepository() {
        this.confirmationNumbers = new ArrayList<>();
    }

    @Override
    public boolean add(ConfirmationNumber object) {
        confirmationNumbers.add(object);
        return true;
    }

    @Override
    public boolean remove(UUID id) {
        return confirmationNumbers.removeIf((confirmationNumber -> Objects.equals(confirmationNumber.getId(),id)));
    }

    @Override
    public ConfirmationNumber findById(UUID id) {
        return confirmationNumbers.stream()
                .filter((confirmationNumber -> Objects.equals(confirmationNumber.getId(),id)))
                .findFirst().orElse(null);
    }
    @Override
    public List<ConfirmationNumber> getAll() {
        return confirmationNumbers;
    }
}
