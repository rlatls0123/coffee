package khs.coffee1.controller;

import khs.coffee1.domain.Menu;
import khs.coffee1.repository.MenuRepository;
import khs.coffee1.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cafe")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MenuRepository menuRepository;

    // 1. 메뉴판 보기
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    // 2. 커피 주문 요청
    @PostMapping("/order")
    public String orderCoffee(@RequestParam Long userId,
                              @RequestParam Long menuId,
                              @RequestParam int count) {
        orderService.placeOrder(userId, menuId, count);
        return "주문이 완료되었습니다!";
    }
}
