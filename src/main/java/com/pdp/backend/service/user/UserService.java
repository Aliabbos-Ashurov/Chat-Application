package com.pdp.backend.service.user;

import com.pdp.backend.DTO.LoginDTO;
import com.pdp.backend.model.user.User;
import com.pdp.backend.repository.user.UserRepository;
import com.pdp.backend.service.BaseService;
/**
 * The UserService interface provides methods for managing user entities.
 *
 * @see com.pdp.backend.service.BaseService
 * @see com.pdp.backend.service.user.UserServiceImpl
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 14/April/2024
 */
public interface UserService extends BaseService<User> {
    UserRepository repository = new UserRepository();

    /**
     * Checks if a user exists in the database based on the login credentials provided.
     *
     * @param dto The LoginDTO object containing login credentials
     * @return True if the user exists, false otherwise
     */
    boolean checkUserFromDB(LoginDTO dto);

    /**
     * Retrieves a user from the database based on the username.
     *
     * @param username The username of the user to retrieve
     * @return The user object if found, null otherwise
     */
    User getUserByName(String username);
}
