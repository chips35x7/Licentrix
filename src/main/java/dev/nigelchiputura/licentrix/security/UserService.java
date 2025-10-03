package dev.nigelchiputura.licentrix.security;

import dev.nigelchiputura.licentrix.dto.RegisterRequest;
import dev.nigelchiputura.licentrix.model.User;
import dev.nigelchiputura.licentrix.model.UserRole;
import dev.nigelchiputura.licentrix.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        String role = request.getRole() != null ? request.getRole().toUpperCase() : "USER";
        user.setRole(UserRole.valueOf(role));

        return userRepository.save(user);
    }
}
