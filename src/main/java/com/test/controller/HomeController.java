package com.test.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.DTO.UserDTO;
import com.test.event.SignupSuccessEvent;
import com.test.model.Person;
import com.test.model.Review;
import com.test.security.CurrentUser;
import com.test.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@RequestMapping(value = {"/","/index"})
	public String getHome() {
		return "index";
	}

	@RequestMapping(value = "registration", method = RequestMethod.GET)
	public ModelAndView doRegistration() {
		return new ModelAndView("registration");

	}

	@RequestMapping(value = "userRegistration", method = RequestMethod.POST)
	public ModelAndView saveDetails(UserDTO userDTO, Model model, WebRequest request) {
		Person person = userService.saveDetails(userDTO);
		try {
			eventPublisher.publishEvent(new SignupSuccessEvent(request.getLocale(), person));
		} catch (Exception e) {
			e.getMessage();
		}
		model.addAttribute("register", "Email has been sent succesfully.Verify email");
		return new ModelAndView("success");

	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView getUserLoginPage(@RequestParam("error") Optional<String> error, Model model,
			Authentication authentication) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("index");
		} else {

			if (error.isPresent()) {
			if (error.get().equals("badUser")) {
					model.addAttribute("loginError", "Invalid Email or Password");
				} else if (error.get().equals("disabled")) {
					model.addAttribute("loginError", "Your Account is Disabled. Please contact nearest Pickup Point.");
				}
			}

			return new ModelAndView("login", "error", error);
		}
	}

	@RequestMapping(value = { "admin", "admin/adminHome" })
	public ModelAndView getAdminHome(Model model, Authentication authentication) {
		CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
		if (currentUser.getRole().getUserRole().equals("ADMIN")) {
			System.out.println(currentUser.getRole().toString());
			return new ModelAndView("adminHome");
		}
		return new ModelAndView("adminHome");

	}
}
