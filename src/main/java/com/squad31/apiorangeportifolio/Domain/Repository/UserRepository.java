package com.squad31.apiorangeportifolio.Domain.Repository;

import com.squad31.apiorangeportifolio.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByEmail(String email);

    Boolean existsByEmail(String email);


}
