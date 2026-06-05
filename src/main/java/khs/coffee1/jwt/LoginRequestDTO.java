package khs.coffee1.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequestDTO {
    private String loginId;
    private String password;
}
