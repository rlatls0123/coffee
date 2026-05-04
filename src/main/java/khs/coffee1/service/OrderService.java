package khs.coffee1.service;

import khs.coffee1.domain.Menu;
import khs.coffee1.domain.Order;
import khs.coffee1.domain.User;
import khs.coffee1.repository.MenuRepository;
import khs.coffee1.repository.OrderRepository;
import khs.coffee1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;

    // 커피 주문하기
    @Transactional
    public Long placeOrder(Long userId, Long menuId, int count) {
        User user = userRepository.findById(userId).orElseThrow();
        Menu menu = menuRepository.findById(menuId).orElseThrow();

        Order order = new Order(user, menu, count);
        return orderRepository.save(order).getId();
    }
}