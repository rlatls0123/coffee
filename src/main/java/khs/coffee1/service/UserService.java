package khs.coffee1.service;

import jakarta.validation.constraints.NotBlank;
import khs.coffee1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;



    public boolean isIdDuplicated(@NotBlank String loginId) {
        return userRepository.existsByLoginId(loginId);
    }
}
