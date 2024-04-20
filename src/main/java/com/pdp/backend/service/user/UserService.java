package com.pdp.backend.service.user;

import com.pdp.backend.DTO.LoginDTO;
import com.pdp.backend.model.user.User;
import com.pdp.backend.repository.user.UserRepository;
import com.pdp.backend.service.BaseService;
/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  10:31
 **/
public interface UserService extends BaseService<User> {
    UserRepository repository = new UserRepository();
    boolean checkUserFromDB(LoginDTO dto);
    User getUserByName(String username);

}
