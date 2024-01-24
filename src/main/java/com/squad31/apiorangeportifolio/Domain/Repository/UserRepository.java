package com.squad31.apiorangeportifolio.Domain.Repository;

import com.squad31.apiorangeportifolio.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
