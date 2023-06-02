package com.WebStore.Store.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.WebStore.Store.Model.Role;
import com.WebStore.Store.Model.Users;
import com.WebStore.Store.Repository.UserRepository;
import com.WebStore.Store.Web.Dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Users save(UserRegistrationDto registrationDto) {
		Users user = new Users(registrationDto.getFirstName(), registrationDto.getLastName(),
				registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()),
				Arrays.asList(new Role("USER")));

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.getUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		return new UserDetails() {

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return user.getRoles().stream()
						.map(role -> new SimpleGrantedAuthority(role.getName()))
						.collect(Collectors.toList());
			}

			@Override
			public String getPassword() {
				return user.getPassword();
			}

			@Override
			public String getUsername() {
				return user.getEmail();
			}

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isEnabled() {
				return true;
			}
		};
	}

	@Override
	public List<Users> getAll() {
		return userRepository.findAll();
	}

}
