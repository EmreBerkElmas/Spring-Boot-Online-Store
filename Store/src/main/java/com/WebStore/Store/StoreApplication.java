package com.WebStore.Store;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.WebStore.Store.Model.Item;
import com.WebStore.Store.Model.Role;
import com.WebStore.Store.Model.Users;
import com.WebStore.Store.Repository.ItemRepository;
import com.WebStore.Store.Repository.RoleRepository;
import com.WebStore.Store.Repository.UserRepository;
import com.WebStore.Store.Model.DummyData;




@SpringBootApplication
public class StoreApplication implements CommandLineRunner{
    

	@Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    
    
    @Autowired
    private DummyData dummyData;


	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
        
	}

    

	@Override
    public void run(String... args) throws Exception {
      
    

        Item kraker = new Item("Kraker", 5.0,10);
        itemRepository.save(kraker);
        Item kek = new Item("kek", 6.0,20);
        itemRepository.save(kek);


        dummyData = new DummyData(userRepository);
        Users dummyUser = dummyData.createDummyUser();
        userRepository.save(dummyUser);
    }
		
    
}
