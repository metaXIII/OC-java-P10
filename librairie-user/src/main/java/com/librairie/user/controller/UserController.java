package com.librairie.user.controller;

import com.librairie.user.dto.SignInDto;
import com.librairie.user.exceptions.EmailExistsException;
import com.librairie.user.exceptions.UsernameExistsException;
import com.librairie.user.model.User;
import com.librairie.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping(value = "/signIn")
    public ResponseEntity signIn(@RequestBody SignInDto signInDto) throws EmailExistsException,
            UsernameExistsException {
        User user = userService.signIn(signInDto);
        if (user == null)
            System.out.println("une erreur est survenue");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("info")
    public ResponseEntity info() {
        return new ResponseEntity(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), HttpStatus.ACCEPTED);
    }

    @GetMapping("me/{name}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("name") String username) {
        return new ResponseEntity<>(userService.findbyUsername(username), HttpStatus.ACCEPTED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.ACCEPTED);
    }
}
