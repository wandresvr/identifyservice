package com.flowlite.identifyservice.infrastructure.security.jwt;


import com.flowlite.identifyservice.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class SecurityUserDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Adaptamos el Role de dominio a un GrantedAuthority
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword().getValue();
    }

    @Override
    public String getUsername() {
        return user.getUsername().getValue();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // puedes mapearlo a algún campo en dominio si quieres
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; 
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

    public User getDomainUser() {
        return this.user;
    }
}
