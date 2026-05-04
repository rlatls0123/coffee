package khs.coffee1.repository;

import khs.coffee1.domain.Menu;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class JpaMenuRepository {
    private final MenuRepository menuRepository;

    public List<Menu> findMenusByCategory(String category) {
        return menuRepository.findAllByCategory(category);
    }
}
