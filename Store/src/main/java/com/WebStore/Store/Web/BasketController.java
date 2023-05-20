package com.WebStore.Store.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.WebStore.Store.Model.Item;
import com.WebStore.Store.Repository.ItemRepository;
import com.WebStore.Store.Service.Basket;



@Controller
public class BasketController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private Basket basket;

    @GetMapping("/basket")
    public String basket(Model model) {
        model.addAttribute("basket", basket);
        return "basket";
    }

    @PostMapping("/basket/add")
    public String addToBasket(@RequestParam Long id, @RequestParam int quantity) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid item ID: " + id));
        basket.addItem(item, quantity);
        return "redirect:/basket";
    }

    @PostMapping("/basket/update")
    public String updateBasketItem(@RequestParam Long id, @RequestParam int quantity) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid item ID: " + id));
        basket.updateItem(item, quantity);
        return "redirect:/basket";
    }

    @PostMapping("/basket/remove")
    public String removeFromBasket(@RequestParam Long id, @RequestParam int quantity) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid item ID: " + id));
        basket.removeItem(item,quantity);
        return "redirect:/basket";
    }

    @PostMapping("/basket/clear")
    public String clearBasket() {
        basket.clear();
        return "redirect:/basket";
    }

    @GetMapping("/marketplace")
    public String marketplace(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "marketplace";
    }

   
    
}
