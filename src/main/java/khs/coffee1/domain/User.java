package khs.coffee1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String loginId;
    private String password;
    private String phone;
    private UserRole role;


    @OneToMany(mappedBy = "user")
    private final List<Order> orders = new ArrayList<>();



}


