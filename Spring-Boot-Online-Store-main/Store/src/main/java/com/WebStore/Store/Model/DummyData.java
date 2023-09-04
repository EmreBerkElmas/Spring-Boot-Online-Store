package com.WebStore.Store.Model;

import com.WebStore.Store.Model.Role;
import com.WebStore.Store.Model.Users;
import com.WebStore.Store.Repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class DummyData {

    private UserRepository userRepository;

    public DummyData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createDummyData() {
        createDummyUser();
    }

    public Users createDummyUser() {
        Role role = new Role("ADMIN");

        Users user = new Users();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");
        user.setPassword(new BCryptPasswordEncoder().encode("a"));
        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
        return user;
    }

}
