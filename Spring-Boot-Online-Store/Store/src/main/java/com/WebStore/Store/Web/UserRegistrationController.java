package com.WebStore.Store.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.WebStore.Store.Repository.UserRepository;
import com.WebStore.Store.Service.UserService;
import com.WebStore.Store.Web.Dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private UserRepository userRepository;
	private UserService userService;

	public UserRegistrationController(UserService userService,UserRepository userRepository) {
		super();
		this.userService = userService;
        this.userRepository= userRepository;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, Model model) {
    if (userRepository.getUserByEmail(registrationDto.getEmail()) != null) {
        model.addAttribute("emailExist", true);
        return "redirect:/registration?fail";
    }

    userService.save(registrationDto);
    return "redirect:/registration?success";
}

}
