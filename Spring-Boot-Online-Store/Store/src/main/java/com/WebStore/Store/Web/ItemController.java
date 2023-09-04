package com.WebStore.Store.Web;

import com.WebStore.Store.Model.Item;
import com.WebStore.Store.Repository.ItemRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class ItemController {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @GetMapping("/new")
    public String showNewItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "newItemForm";
    }
    
    @PostMapping("/save")
    public String saveNewItem(Item item) {
        itemRepository.save(item);
        return "redirect:/marketplace";
    }
    
    @PostMapping("/remove/{id}")
    public String removeItem(@RequestParam("id") Long itemId) {
        itemRepository.deleteById(itemId);
        return "redirect:/marketplace";
    }
    
    
    
    @GetMapping("/update")
    public String updateItemd(@RequestParam("id") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item ID: " + itemId));
    
        model.addAttribute("item", item);
    
        return "updatePage";
    }
}
