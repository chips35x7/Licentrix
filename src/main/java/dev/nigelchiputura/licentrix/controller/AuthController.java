package dev.nigelchiputura.licentrix.controller;

import dev.nigelchiputura.licentrix.dto.AuthRequest;
import dev.nigelchiputura.licentrix.dto.AuthResponse;
import dev.nigelchiputura.licentrix.dto.RegisterRequest;
import dev.nigelchiputura.licentrix.dto.UpdateUserCompanyRequest;
import dev.nigelchiputura.licentrix.model.Company;
import dev.nigelchiputura.licentrix.model.User;
import dev.nigelchiputura.licentrix.repository.CompanyRepository;
import dev.nigelchiputura.licentrix.repository.UserRepository;
import dev.nigelchiputura.licentrix.security.UserService;
import dev.nigelchiputura.licentrix.security.jwt.JwtUtil;
import dev.nigelchiputura.licentrix.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(), authRequest.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new AuthResponse("Invalid credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return ResponseEntity.ok("User registered successfully with ID: " + user.getId());
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/me")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        return ResponseEntity.ok(user);
    }

    @Autowired
    private CompanyRepository companyRepository;
    private CompanyService companyService;


    @PutMapping("/users/{username}")
    public ResponseEntity<User> updateUser(
            @PathVariable String username,
            @RequestBody User updatedUser) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setCompany(updatedUser.getCompany());
        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}


