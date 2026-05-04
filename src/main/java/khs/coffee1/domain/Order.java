package khs.coffee1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


    // Order.java (주문 기록)
    @Entity
    @Table(name = "orders") // order는 SQL 예약어이므로 테이블명 지정
    @Getter
    @NoArgsConstructor
    public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "menu_id")
        private Menu menu;

        private int count; // 주문 수량
        private LocalDateTime orderDate;

        public Order(User user, Menu menu, int count) {
            this.user = user;
            this.menu = menu;
            this.count = count;
            this.orderDate = LocalDateTime.now();
        }
    }
