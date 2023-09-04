package com.WebStore.Store.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.WebStore.Store.Model.Users;



@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    
    @Query("SELECT u from Users u Where u.email = :email")
	public Users getUserByEmail(@Param("email") String email);
}
