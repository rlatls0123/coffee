package khs.coffee1;

import jakarta.transaction.Transactional;
import khs.coffee1.domain.User;
import khs.coffee1.domain.UserRole;
import khs.coffee1.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback
public class SignupAndLogin {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void signup() {


        User user = User.builder()
                .username("test")
                .password(bCryptPasswordEncoder.encode("test1"))
                .loginId("signupTest@tests")
                .phone("010testtest")
                .role(UserRole.USER)
                .build();

        User save = userRepository.save(user);

        User user1 = userRepository.findByLoginId("signupTest@tests").get();

        Assertions.assertThat(user).isEqualTo(user1);

    }


    void login() {

    }

}
