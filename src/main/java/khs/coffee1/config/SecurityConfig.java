//package khs.coffee1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // 테스트를 위해 CSRF 잠시 해제
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/api/v1/cafe/menus").permitAll() // 메뉴판은 누구나
//                        .requestMatchers("/admin/**").hasRole("ADMIN")        // 관리자만 접속 가능
//                        .anyRequest().authenticated()                         // 나머지는 로그인 필수
//                )
//                .formLogin(form -> form.defaultSuccessUrl("/api/v1/cafe/menus")); // 로그인 성공 시 이동
//
//        return http.build();
//    }
//}