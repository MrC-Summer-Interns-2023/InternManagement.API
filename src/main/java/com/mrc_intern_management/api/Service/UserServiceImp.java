package MRC.InternApp.Service;

import MRC.InternApp.Entity.Users;
import MRC.InternApp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImp implements userService{

        @Autowired
        private UsersRepository userRepository;

        @Override
        public List<Users> getAllUsers() {
            return userRepository.findAll();
        }

        @Override
        public Users getUserById(Long id) {
            return userRepository.findById(id).orElse(null);
        }

        @Override
        public Users createUser(Users user) {
            return userRepository.save(user);
        }

        @Override
        public Users updateUser(Long id, Users updatedUser) {
            Users existingUser = userRepository.findById(id).orElse(null);
            if (existingUser != null) {
                // Update user properties here
                existingUser.setName(updatedUser.getName());
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setRole(updatedUser.getRole());
                // Save the updated user
                return userRepository.save(existingUser);
            }
            return null;
        }

        @Override
        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }
    }
