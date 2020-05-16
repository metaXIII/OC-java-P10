package com.librairie.user.impl;

import com.librairie.user.dto.SignInDto;
import com.librairie.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SignInDto signInDto;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void findbyUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> userService.findbyUsername("aze"));
    }

    @Test
    void signIn() {
        signInDto = new SignInDto();
        signInDto.setEmail("aze@aze");
        signInDto.setPassword("aze");
        signInDto.setUsername("aze");
        when(passwordEncoder.encode(anyString())).thenReturn("aze");
        assertDoesNotThrow(() -> userService.signIn(signInDto));
    }

    @Test
    void emailExist() {
        assertDoesNotThrow(() -> userService.emailExist("aze"));
    }

    @Test
    void usernameExist() {
        assertDoesNotThrow(() -> userService.usernameExist("aze"));
    }

    @Test
    void loadUserByUsername() {
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("aze"));
    }

    @Test
    void findById() {
        assertDoesNotThrow(() -> userService.findById(1));
    }
}