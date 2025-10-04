package dev.nigelchiputura.licentrix.security;

import dev.nigelchiputura.licentrix.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Map DB role (e.g., "USER") -> Spring authority ("ROLE_USER")
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();  // stored hashed password
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // ✅ Account status flags (customize if you add fields for locking, disabling etc.)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Optional: Expose your full User entity if you need it in controllers
    public User getUser() {
        return this.user;
    }
}