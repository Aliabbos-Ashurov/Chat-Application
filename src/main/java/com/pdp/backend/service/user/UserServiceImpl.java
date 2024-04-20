package com.pdp.backend.service.user;

import com.pdp.backend.DTO.LoginDTO;
import com.pdp.backend.model.user.User;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * The UserServiceImpl class provides implementations for managing user entities.
 *
 * @see com.pdp.backend.service.user.UserService
 * @see com.pdp.backend.service.BaseService
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public class UserServiceImpl implements UserService{
    private static UserServiceImpl instance;
    private UserServiceImpl() {}
    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean add(User object) {
        List<User> users = repository.getAll();
        boolean noneExistUsername = users.stream().noneMatch(user -> Objects.equals(user.getUsername(), object.getUsername()));
        if (noneExistUsername) repository.add(object);
        return noneExistUsername;
    }

    /**
     * Checks if a user exists in the database based on the login credentials provided.
     *
     * @param dto The LoginDTO object containing login credentials
     * @return True if the user exists, false otherwise
     */
    @Override
    public boolean checkUserFromDB(LoginDTO dto) {
        List<User> users = repository.getAll();
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(dto.username()) && user.getPassword().equals(dto.password()));
    }

    /**
     * Retrieves all users whose usernames match the provided query.
     *
     * @param query The username query to search for
     * @return The list of users matching the query
     */
    @Override
    public List<User> search(String query) {
        List<User> users = repository.getAll();
        return users.stream()
                .filter((user -> userConfig(query,user.getUsername())))
                .collect(Collectors.toList());
    }
    private boolean userConfig(String query,String username) {
        boolean check1 = query.equalsIgnoreCase(username);
        boolean check2 = username.toUpperCase().startsWith(query.toUpperCase());
        return check1 || check2;
    }

    /**
     * Updates the information of a user in the database.
     *
     * @param object The user object with updated information
     * @return True if the user information is successfully updated, false otherwise
     */
    @Override
    public boolean update(User object) {
        User user = repository.findById(object.getId());
        if (Objects.nonNull(user)) {
            user.setUsername(object.getUsername());
            user.setEmail(object.getEmail());
            user.setPassword(object.getPassword());
            user.setFullname(object.getFullname());
            user.setPhoneNumber(object.getPhoneNumber());
            return true;
        }
        return false;
    }

    /**
     * Retrieves a user from the database based on the username.
     *
     * @param username The username of the user to retrieve
     * @return The user object if found, null otherwise
     */
    @Override
    public User getUserByName(String username) {
        var users = repository.getAll();
        return users.stream()
                .filter((user -> user.getUsername().equals(username)))
                .findFirst().orElse(null);
    }

    /**
     * Deletes a user from the database.
     *
     * @param id The UUID of the user to delete
     * @return True if the user is successfully deleted, false otherwise
     */
    @Override
    public boolean delete(UUID id) {
        return repository.remove(id);
    }

    /**
     * Retrieves all users from the database.
     *
     * @return The list of all users
     */
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    /**
     * Retrieves a user from the database based on the provided UUID.
     *
     * @param id The UUID of the user to retrieve
     * @return The user object if found, null otherwise
     */
    @Override
    public User getById(UUID id) {
        return repository.findById(id);
    }
}
