package com.squad31.apiorangeportifolio.Domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table (name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @NotNull
    private UUID uuid;

    @Column (name = "name")
    @NotBlank
    private String name;

    @Column (name = "last_name")
    @NotBlank
    private String lastName;

    @Column (name = "email")
    @NotBlank
    private String email;

    @Column (name = "password")
    @NotBlank
    private String password;

    @Column (name = "profile_image", length = 1000)

    private byte[] profileImage;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO: Definir se teremos niveis de usuário na aplicação,
        // por enquanto todos os usuários terão todos os acessos
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

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
}
