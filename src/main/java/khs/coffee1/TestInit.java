package khs.coffee1;

import jakarta.annotation.PostConstruct;
import khs.coffee1.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestInit {
    private final MenuService menuService;

//    @PostConstruct
    public void init() {
        menuService.saveMenu("아메리카노", 1000, "Coffee");
        menuService.saveMenu("cake", 10000, "Dessert");
        menuService.saveMenu("녹차", 5600, "Tea");
    }
}
