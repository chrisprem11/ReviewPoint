package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.Car;
import com.test.model.Person;
import com.test.service.CarService;
import com.test.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CarService carService;

	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public String getUserList(Model model) {
		List<Person> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@RequestMapping(value = "viewCars", method = RequestMethod.GET)
	public ModelAndView viewCars(Model model) {
		List<Car> cars = carService.getAllCars();
		model.addAttribute("cars", cars);
		return new ModelAndView("viewCars");
	}
	
	@RequestMapping(value = "viewCars/{id}",method =RequestMethod.GET)
	public ModelAndView getDetails(@PathVariable("id") Long carId,Model model){
		List<Car> details= carService.getCarDetails(carId);
		model.addAttribute("details", details);
		return new ModelAndView("carDetails");
	}

}
