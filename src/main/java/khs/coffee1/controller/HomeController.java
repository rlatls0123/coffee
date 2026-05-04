package khs.coffee1.controller;

import khs.coffee1.domain.Menu;
import khs.coffee1.repository.MenuRepository;
import khs.coffee1.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MenuService menuService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

//    @GetMapping("/shop")
    public String getShopPage(Model model) {
        // DB에서 카테고리별 또는 전체 상품 리스트를 가져왔다고 가정
        List<Menu> menus = menuService.findAll();
        model.addAttribute("menus", menus);
        return "shop";
    }

    @GetMapping("/shop")
    public String getShopPage(@RequestParam(value = "category", required = false) String category,
                              Model model) {
        List<Menu> menus;

        if (category != null && !category.isEmpty()) {
            // 특정 카테고리만 가져오기
            menus = menuService.findMenusByCategory(category);
        } else {
            // 전체 상품 가져오기
            menus = menuService.findAll();
        }

        model.addAttribute("menus", menus);
        model.addAttribute("currentCategory", category); // 현재 선택된 탭 강조용

        return "shop";
    }
}
