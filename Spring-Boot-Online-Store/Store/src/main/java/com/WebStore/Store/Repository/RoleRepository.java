package com.WebStore.Store.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WebStore.Store.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
