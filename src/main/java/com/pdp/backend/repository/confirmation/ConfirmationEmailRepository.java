package com.pdp.backend.repository.confirmation;

import com.pdp.backend.model.confirmation.ConfirmationEmail;
import com.pdp.backend.nio.ListFileHandler;
import com.pdp.backend.nio.pathConfig.ChildPath;
import com.pdp.backend.nio.pathConfig.DirectoryPath;
import com.pdp.backend.repository.BaseRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Aliabbos Ashurov
 * Date: 12/April/2024  13:31
 **/
public class ConfirmationEmailRepository implements BaseRepository<ConfirmationEmail> {
    private final ListFileHandler<ConfirmationEmail> listFileHandler = new ListFileHandler<>(DirectoryPath.DB, ChildPath.EMAILS);
    private final List<ConfirmationEmail> confirmationEmails;

    public ConfirmationEmailRepository() {
        this.confirmationEmails = getAll();
    }
    @Override
    public boolean add(ConfirmationEmail object) {
        confirmationEmails.add(object);
        listFileHandler.writeList(confirmationEmails);
        return true;
    }
    @Override
    public boolean remove(UUID id) {
        boolean removeIf = confirmationEmails.removeIf(confirmationEmail -> Objects.equals(confirmationEmail.getId(), id));
        if (removeIf) listFileHandler.writeList(confirmationEmails);
        return removeIf;
    }
    @Override
    public ConfirmationEmail findById(UUID id) {
        return confirmationEmails.stream()
                .filter(confirmationEmail -> Objects.equals(confirmationEmail.getId(),id))
                .findFirst().orElse(null);
    }
    @Override
    public List<ConfirmationEmail> getAll() {
        return listFileHandler.readList();
    }
}
