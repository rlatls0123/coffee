package khs.coffee1.service;

import khs.coffee1.domain.Menu;
import khs.coffee1.repository.MenuRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class MenuServiceTest {

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuService menuService;
    @Test
    void save() {

        Long saved = menuService.saveMenu("아메리카노", 1000, "Coffee");
        Long saved1 = menuService.saveMenu("cake", 10000, "Dessert");
        Long saved2 = menuService.saveMenu("녹차", 5600, "Tea");

        Optional<Menu> id = menuRepository.findById(saved);
        assertEquals(saved,id.get().getId());
    }
}