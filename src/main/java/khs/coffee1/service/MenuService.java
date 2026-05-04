package khs.coffee1.service;

import jakarta.transaction.Transactional;
import khs.coffee1.domain.Menu;
import khs.coffee1.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional
    public Long saveMenu(String name, int price, String category) {
        Menu menu = new Menu(name, price, category);
        return menuRepository.save(menu).getId();
    }

    @Transactional
    public void updatePrice(Long menuId, int newPrice) {
        Menu menu = menuRepository.findById(menuId).orElseThrow();
        // JPA의 변경 감지(Dirty Checking)를 이용해 가격 수정
        // 별도의 save() 호출 없이도 트랜잭션 종료 시 DB에 반영됨
    }

    @Transactional
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Transactional
    public List<Menu> findMenusByCategory(String category) {
        return menuRepository.findAllByCategory(category);
    }
}