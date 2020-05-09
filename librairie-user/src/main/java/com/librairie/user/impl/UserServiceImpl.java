package com.librairie.user.impl;

import com.librairie.user.dto.SignInDto;
import com.librairie.user.exceptions.EmailExistsExceptionThrowable;
import com.librairie.user.exceptions.UsernameExistsExceptionThrowable;
import com.librairie.user.model.User;
import com.librairie.user.repositories.UserRepository;
import com.librairie.user.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService, UserDetailsService {
    private static final Logger         logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private              UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findbyUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User signIn(SignInDto signInDto) throws EmailExistsExceptionThrowable, UsernameExistsExceptionThrowable,
            Exception {
        if (emailExist(signInDto.getEmail())) {
            logger.error("email deja présent");
            throw new EmailExistsExceptionThrowable("L'email existe déjà !!!");
        } else if (usernameExist(signInDto.getUsername())) {
            logger.error("username déjà présent");
            throw new UsernameExistsExceptionThrowable("Le nom d'utilisateur est déjà pris");
        } else if (signInDto.getEmail().isEmpty() || signInDto.getUsername().isEmpty() || signInDto.getPassword().isEmpty()) {
            logger.error("Informations incomplètes");
            throw new Exception("Impossible de récupérer toutes les informations de l'utilsateur");
        } else {
            User user = new User();
            user.setUsername(signInDto.getUsername());
            user.setEmail(signInDto.getEmail());
            user.setPassword(passwordEncoder.encode(signInDto.getPassword()));
            return userRepository.save(user);
        }
    }

    public boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean usernameExist(String username) {
        return userRepository.findByEmail(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error(String.format("user not found : %s", username));
                    return new UsernameNotFoundException("Il n'existe pas d'utilisateurs avec le nom " + "d" +
                                                                 "'utilisateur " + username);
                });
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
}
