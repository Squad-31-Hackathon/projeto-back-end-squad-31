package com.squad31.apiorangeportifolio.Domain.Service;

import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Repository.ProjectRepository;
import com.squad31.apiorangeportifolio.Domain.Repository.UserRepository;
import com.squad31.apiorangeportifolio.Exceptions.BadRequestException;
import com.squad31.apiorangeportifolio.Exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    public User create(UserRequestDTO userData) {

        if (userRepository.findByEmail(userData.email()) != null) {
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
            throw new NotFoundException("Usuário com este ID não encontrado");
        }

        return user.get();

    }

    public List<Project>  getAuthenticatedUserProjects() {
        //Pega os dados do usuário autenticado
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return projectRepository.findByUser(user);
    }
}
