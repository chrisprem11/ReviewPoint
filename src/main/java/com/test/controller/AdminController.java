package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.test.DTO.CarDTO;
import com.test.model.Car;
import com.test.security.CurrentUser;
import com.test.service.CarService;

@Controller
public class AdminController {
	
	@Autowired
	private CarService carService;
	
	@RequestMapping(value="addCar", method = RequestMethod.GET)
	public ModelAndView getCars(){
		return new ModelAndView("addCar");
	}
	
	@RequestMapping(value="addCar", method = RequestMethod.POST)
	public ModelAndView addCars(CarDTO carDTO){
		Car saveCar= carService.SaveDetails(carDTO);
		if(null==saveCar){
			return new ModelAndView("addCar","error", "Operation Failed !");
		}
		return new ModelAndView("success", "success", "Successfully Added !");
	}

	@RequestMapping(value="/admin/users", method =RequestMethod.GET)
	public ModelAndView getUsers(Authentication auth,Model model){
		CurrentUser currentUser= (CurrentUser) auth.getPrincipal();
		if(currentUser.getRole().getUserRole().equals("ADMIN")){
			return new ModelAndView("userList");
		} else {
			model.addAttribute("user", "You cannot view this page !");
			return new ModelAndView("error");
		}
		
	}
}
