package khs.coffee1.jwt;

import khs.coffee1.domain.User;
import khs.coffee1.domain.UserRole;
import khs.coffee1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String signup(SignupRequestDTO dto) {
        if (userRepository.existsByLoginId(dto.getLoginId())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        User user = User.builder()
                .loginId(dto.getLoginId())
                .password(passwordEncoder.encode(dto.getPassword())) // 암호화
                .username(dto.getUsername())
                .role(UserRole.USER)
                .build();

        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

    public String login(LoginRequestDTO dto) {
        User user = userRepository.findByLoginId(dto.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 JWT 발행 (아이디와 사용자 이름 탑재)
        return jwtTokenProvider.createToken(user.getLoginId(), user.getUsername(), user.getRole());
    }
}