package greivance.project.controllers;

import greivance.project.requests.LoginRequest;
import greivance.project.requests.RegisterRequest;
import greivance.project.responses.AuthResponse;
import greivance.project.responses.UserResponse;
import greivance.project.services.AuthServices;
import greivance.project.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServices authServices;
    private final UserServices userServices;

    public AuthController(AuthServices authServices, UserServices userServices) {
        this.authServices = authServices;
        this.userServices = userServices;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authServices.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authServices.login(request));
    }

    /** Who the current token belongs to, used by the frontend to restore a session. */
    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(Authentication authentication) {
        return ResponseEntity.ok(
                UserResponse.from(userServices.getByUsername(authentication.getName())));
    }

    /**
     * Stateless JWTs cannot be revoked server-side without a denylist, so logout is
     * the client dropping its token. Kept so the frontend has a real endpoint to call.
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent().build();
    }
}
