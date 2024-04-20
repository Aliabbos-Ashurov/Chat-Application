package com.pdp.backend.service.user;

import com.pdp.backend.DTO.LoginDTO;
import com.pdp.backend.model.user.User;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 * @author Aliabbos Ashurov
 * Date: 14/April/2024  10:30
 **/
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
    @Override
    public boolean checkUserFromDB(LoginDTO dto) {
        List<User> users = repository.getAll();
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(dto.username()) && user.getPassword().equals(dto.password()));
    }
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

    @Override
    public User getUserByName(String username) {
        var users = repository.getAll();
        return users.stream()
                .filter((user -> user.getUsername().equals(username)))
                .findFirst().orElse(null);
    }

    @Override
    public boolean delete(UUID id) {
        return repository.remove(id);
    }
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
    @Override
    public User getById(UUID id) {
        return repository.findById(id);
    }
}
