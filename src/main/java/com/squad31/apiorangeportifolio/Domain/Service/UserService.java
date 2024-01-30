package com.squad31.apiorangeportifolio.Domain.Service;

import com.squad31.apiorangeportifolio.Domain.DTOs.User.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Repository.ProjectRepository;
import com.squad31.apiorangeportifolio.Domain.Repository.UserRepository;
import com.squad31.apiorangeportifolio.Exceptions.BadRequestException;
import com.squad31.apiorangeportifolio.Exceptions.ProjectNotFoundException;
import com.squad31.apiorangeportifolio.Exceptions.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    public User create(UserRequestDTO userData) {

        if (userRepository.findByEmail(userData.email()) != null) {
            log.warn("Usuário com este email já cadastrado!");
            throw new BadRequestException("Usuário com este email já cadastrado!");
        }

        String hashedPassword = new BCryptPasswordEncoder().encode(userData.password());

        User newUser = new User();

        BeanUtils.copyProperties(userData, newUser, "password");

        newUser.setPassword(hashedPassword);


        return userRepository.save(newUser);

    }

    public User findById(String uuid) {
        Optional<User> user = userRepository.findById(UUID.fromString(uuid));

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        return user.get();

    }

    public List<Project>  getAuthenticatedUserProjects() {
        //Pega os dados do usuário autenticado
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return projectRepository.findByUser(user);
    }

    public void deleteUser(UUID uuid){
        userRepository.deleteById(uuid);
    }
}
