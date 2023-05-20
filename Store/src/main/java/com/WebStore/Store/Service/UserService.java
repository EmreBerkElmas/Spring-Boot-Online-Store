package com.WebStore.Store.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

import com.WebStore.Store.Model.Users;
import com.WebStore.Store.Web.Dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

   
	Users save(UserRegistrationDto registrationDto);

	List<Users> getAll();
}
