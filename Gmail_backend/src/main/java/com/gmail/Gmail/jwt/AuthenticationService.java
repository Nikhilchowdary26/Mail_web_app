package com.gmail.Gmail.jwt;

import com.gmail.Gmail.Repository.UserRepository;
import com.gmail.Gmail.model.LoginUserDTO;
import com.gmail.Gmail.model.RegisterUserDTO;
import com.gmail.Gmail.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signUp(RegisterUserDTO registerUserDTO) {
        Optional<User> existingUser = userRepository.findById(registerUserDTO.getMailid());
        if(existingUser.isPresent()) {
            throw new RuntimeException("User with email " + registerUserDTO.getMailid() + "already exsts");
        }
        User user = new User();
        user.setMailid(registerUserDTO.getMailid());
        user.setFirstName(registerUserDTO.getFirstName());
        user.setLastName(registerUserDTO.getLastName());
        user.setGender(registerUserDTO.getGender());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setMobileNo(registerUserDTO.getMobileNo());

        return userRepository.save(user);
    }

    public User signIn(LoginUserDTO loginUserDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.getMailid(),
                        loginUserDTO.getPassword()
                )
        );
        return userRepository.findById(loginUserDTO.getMailid()).orElseThrow();
    }
}
