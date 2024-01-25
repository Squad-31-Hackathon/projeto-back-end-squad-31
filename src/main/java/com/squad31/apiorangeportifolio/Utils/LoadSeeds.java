package com.squad31.apiorangeportifolio.Utils;

import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadSeeds {
    @Autowired
    private UserService userService;

    public void loadUsers() {
        UserRequestDTO adminUser = new UserRequestDTO("user", "admin", "admin@email.com", "admin123");
        userService.create(adminUser);
    }
}
