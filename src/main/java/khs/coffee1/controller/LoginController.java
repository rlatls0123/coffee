package khs.coffee1.controller;

import khs.coffee1.domain.User;
import khs.coffee1.domain.UserRole;
import khs.coffee1.jwt.AuthService;
import khs.coffee1.jwt.LoginRequestDTO;
import khs.coffee1.jwt.SignupRequestDTO;
import khs.coffee1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthService authService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login(@ModelAttribute("dto") LoginRequestDTO dto) {

        return "login";
    }

    @PostMapping("/login")
    public String logini(@ModelAttribute("dto") LoginRequestDTO dto) {
        log.info("id={},pw={}",dto.getLoginId(),dto.getPassword());
        String token = authService.login(dto);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup(@ModelAttribute("dto") SignupRequestDTO dto) {

        return "signup";
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute("dto") SignupRequestDTO dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .loginId(dto.getLoginId())
                .phone(dto.getPhone())
                .role(UserRole.USER)
                .build();

        userRepository.save(user);
        return "redirect:/";
    }
}
