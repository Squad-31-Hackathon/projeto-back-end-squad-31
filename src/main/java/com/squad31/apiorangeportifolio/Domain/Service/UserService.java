package com.squad31.apiorangeportifolio.Domain.Service;

import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Repository.UserRepository;
import com.squad31.apiorangeportifolio.Exceptions.BadRequestException;
import com.squad31.apiorangeportifolio.Exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public User create(UserRequestDTO userData) {

        Optional<User> userExists = userRepository.findByEmail(userData.email());

        if (userExists.isPresent()) {
            throw new BadRequestException("Usuário já cadastrado com este e-mail");
        }

       // TODO: Criptografar senha antes de salvar

        User newUser = new User();

        BeanUtils.copyProperties(userData, newUser);

        return userRepository.save(newUser);

    }

    public User findById(String uuid) {
        Optional<User> user = userRepository.findById(UUID.fromString(uuid));

        if (user.isEmpty()) {
            throw new NotFoundException("Usuário com este ID não encontrado");
        }

        return user.get();

    }
}
