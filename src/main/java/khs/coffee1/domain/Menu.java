package khs.coffee1.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Menu.java (커피 메뉴)
    @Entity
    @Getter
    @NoArgsConstructor
    public class Menu {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        private String name;    // 아메리카노, 카페라떼 등
        private int price;     // 가격
        private String category; // Coffee, Dessert, Tea 등



        @Builder
        public Menu(String name, int price, String category) {
            this.name = name;
            this.price = price;
            this.category = category;
        }
    }

