package MRC.InternApp.Service;

import MRC.InternApp.Entity.Users;

import java.util.List;

public interface userService {
    List<Users> getAllUsers();
    Users getUserById(Long id);
    Users createUser(Users user);
    Users updateUser(Long id, Users updatedUser);
    void deleteUser(Long id);
}
