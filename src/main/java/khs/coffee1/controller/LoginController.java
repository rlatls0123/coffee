package khs.coffee1.controller;

import jakarta.validation.Valid;
import khs.coffee1.domain.User;
import khs.coffee1.domain.UserRole;
import khs.coffee1.jwt.AuthService;
import khs.coffee1.jwt.LoginRequestDTO;
import khs.coffee1.jwt.SignupRequestDTO;
import khs.coffee1.repository.UserRepository;
import khs.coffee1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/login")
    public String login(@ModelAttribute("dto") LoginRequestDTO dto) {

        return "login";
    }

    @PostMapping("/login")
    public String logini(@Valid @ModelAttribute("dto") LoginRequestDTO dto, BindingResult bindingResult) {
        log.info("id={},pw={}",dto.getLoginId(),dto.getPassword());
        if (bindingResult.hasErrors()) {
            return "login";
        }

        try {
            String token = authService.login(dto);
        } catch (Exception e) {
            bindingResult.reject("loginFail", e.getMessage());
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup(@ModelAttribute("dto") SignupRequestDTO dto) {

        return "signup";
    }

    @PostMapping("/signup")
    public String addUser(@Valid @ModelAttribute("dto") SignupRequestDTO dto,
                          BindingResult bindingResult,
                          @RequestParam("action") String action,
                          Model model) {

        // 1. [중복 확인] 버튼을 눌렀을 때의 로직
        if ("check".equals(action)) {
            boolean isDuplicated = userService.isIdDuplicated(dto.getLoginId()); // DB 조회 로직


            if (isDuplicated) {
                model.addAttribute("message", "이미 사용 중인 아이디입니다.");
                model.addAttribute("msgColor", "red");
                model.addAttribute("isIdChecked", false);
            } else {
                model.addAttribute("message", "사용 가능한 아이디입니다. 아래 확인창을 수락해주세요.");
                model.addAttribute("msgColor", "green");
                model.addAttribute("isIdChecked", true); // 자바스크립트 변수로 넘어가 confirm창을 띄움
            }
            return "signup"; // 다시 회원가입 페이지 렌더링
        }

//        if (bindingResult.hasErrors()) {
//            return "signup";
//        }

        if ("register".equals(action)) {
            User user = User.builder()
                    .username(dto.getUsername())
                    .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                    .loginId(dto.getLoginId())
                    .phone(dto.getPhone())
                    .role(UserRole.USER)
                    .build();

            userRepository.save(user);
        }
        return "redirect:/";
    }
}
