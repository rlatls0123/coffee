package khs.coffee1.jwt;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequestDTO {


//    @NotBlank
    private String loginId;
//    @NotBlank
    private String password;
//    @NotBlank
    private String username; // 실제 사용자 이름
//    @NotBlank
    private String phone;
}

