package com.WebStore.Store.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WebStore.Store.Model.Item;



@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    void deleteById(long id);

    Optional<Item> findById(long id);
}