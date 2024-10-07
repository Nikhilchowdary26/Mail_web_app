package com.gmail.Gmail;

import com.gmail.Gmail.jwt.AuthenticationService;
import com.gmail.Gmail.jwt.JWTService;
import com.gmail.Gmail.model.LoginResponse;
import com.gmail.Gmail.model.LoginUserDTO;
import com.gmail.Gmail.model.RegisterUserDTO;
import com.gmail.Gmail.model.User;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final JWTService jwtService;
    private final AuthenticationService authenticationService;

    public AuthController ( JWTService jwtService,
                            AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",useReturnTypeSchema = true,description = "Successfully Registered",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = User.class)))
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegisterUserDTO registerUserDTO) {
        User signUpUser = authenticationService.signUp(registerUserDTO);
        return ResponseEntity.ok(signUpUser);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully Logged in",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = LoginResponse.class)))
    })
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginUserDTO loginUserDTO) {
        User authUser = authenticationService.signIn(loginUserDTO);
        String jwtToken = jwtService.generateToken(authUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
