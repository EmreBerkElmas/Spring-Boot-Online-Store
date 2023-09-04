package com.WebStore.Store.Service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.WebStore.Store.Model.Item;

@Component
public class Basket {

    private Map<Item, Integer> items;

    public Basket() {
        this.items = new HashMap<>();
    }

   
    public void addItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
        } else {
            items.put(item, quantity);
        }
    }
    public void updateItem(Item item, int newQuantity) {
        if (items.containsKey(item)) {
            items.put(item, newQuantity);
        }
    }

    public void removeItem(Item item) {
        if (items.containsKey(item)) {
            items.remove(item);
        }
        else{
            System.out.println("DEBuuuuugggggggggg");
        }
        
    }

    public void clear() {
        items.clear();
    }

    public List<Item> getItems() {
        return new ArrayList<>(items.keySet());
    }

    public int getQuantity(Item item) {
        return items.getOrDefault(item, 0);
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    

}
