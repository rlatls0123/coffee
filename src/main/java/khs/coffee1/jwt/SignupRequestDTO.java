package khs.coffee1.jwt;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequestDTO {
    private String loginId;
    private String password;
    private String username; // 실제 사용자 이름
    private String phone;
}

