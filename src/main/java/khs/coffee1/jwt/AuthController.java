package khs.coffee1.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDTO dto) {
        String result = authService.signup(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO dto) {
        String token = authService.login(dto);
        // 클라이언트에게 토큰을 Body나 Header(Authorization)에 담아 전달
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + token)
                .body(token);
    }
}