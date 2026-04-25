package br.com.bifani.loginjavaprojetoia.controllers;

import br.com.bifani.loginjavaprojetoia.model.User;
import br.com.bifani.loginjavaprojetoia.model.dtos.AuthResponse;
import br.com.bifani.loginjavaprojetoia.model.dtos.UserLoginRequest;
import br.com.bifani.loginjavaprojetoia.model.dtos.UserRegisterRequest;
import br.com.bifani.loginjavaprojetoia.model.enums.Role;
import br.com.bifani.loginjavaprojetoia.repositories.IUserRepository;
import br.com.bifani.loginjavaprojetoia.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(IUserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRegisterRequest request) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        user.setName(request.name());
        user.setRole(Role.USER);

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(null, "Usuário criado com sucesso: " + user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
            String token = jwtUtil.generateToken(request.email());
            return ResponseEntity.ok(new AuthResponse(token, "Login realizado com sucesso"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, "Credenciais inválidas!"));
        }
    }
}
