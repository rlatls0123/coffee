package khs.coffee1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String loginId;
    private String password;
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    @OneToMany(mappedBy = "user")
    private final List<Order> orders = new ArrayList<>();



}


