package greivance.project.services;

import greivance.project.entity.User;
import greivance.project.entity.enums.Role;
import greivance.project.exceptions.UsernameAlreadyExistsException;
import greivance.project.repos.UserRepo;
import greivance.project.requests.LoginRequest;
import greivance.project.requests.RegisterRequest;
import greivance.project.responses.AuthResponse;
import greivance.project.responses.UserResponse;
import greivance.project.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServices(UserRepo repo,
                        PasswordEncoder passwordEncoder,
                        AuthenticationManager authenticationManager,
                        JwtService jwtService) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (repo.findByUsername(request.getUsername()) != null) {
            throw new UsernameAlreadyExistsException(request.getUsername());
        }

        // self-registration cannot grant itself OFFICIAL or ADMIN, an admin promotes instead
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CITIZEN)
                .build();

        User saved = repo.save(user);

        return buildAuthResponse(saved);
    }

    public AuthResponse login(LoginRequest request) {
        // throws BadCredentialsException when the username or password is wrong
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = repo.findByUsername(request.getUsername());

        return buildAuthResponse(user);
    }

    private AuthResponse buildAuthResponse(User user) {
        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());

        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .expiresInMs(jwtService.getExpirationMillis())
                .user(UserResponse.from(user))
                .build();
    }
}
