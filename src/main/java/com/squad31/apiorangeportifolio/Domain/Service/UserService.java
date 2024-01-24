package com.squad31.apiorangeportifolio.Domain.Service;

import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.user.UserResponseDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Domain.Mapper.UserMapper;
import com.squad31.apiorangeportifolio.Domain.Repository.UserRepository;
import com.squad31.apiorangeportifolio.Exceptions.UserException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public User createUser(UserRequestDTO userData) {

        Optional<User> userExists = userRepository.findByEmail(userData.email());

        if (userExists.isPresent()) {
            throw new UserException("Usuário já cadastrado com este e-mail");
        }

       // TODO: Criptografar senha antes de salvar

        User newUser = new User();

        BeanUtils.copyProperties(userData, newUser);

        return userRepository.save(newUser);

    }
}
