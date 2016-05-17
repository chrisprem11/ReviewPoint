package com.test.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.DTO.UserDTO;
import com.test.model.Car;
import com.test.model.Person;
import com.test.model.Review;
import com.test.service.CarService;
import com.test.service.ReviewService;
import com.test.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CarService carService;

	@Autowired
	private ReviewService reviewService;

	

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

	@RequestMapping(value = "viewCars/{id}", method = RequestMethod.GET)
	public ModelAndView getDetails(@PathVariable("id") Long carId, Model model, Authentication authentication,
			Car car) {
		List<Car> details = carService.getCarDetails(carId);
		List<Review> allReviews = reviewService.fetchReviews(car, carId);

		model.addAttribute("reviews", allReviews);
		model.addAttribute("details", details);
		return new ModelAndView("carDetails");
	}

	  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity<?> uploadFile(
	      @RequestParam("uploadfile") MultipartFile uploadfile,UserDTO userDTO) {
	    
	    try {
	      // Get the filename and build the local file path
	      String filename = uploadfile.getOriginalFilename();
	      String directory = "src/main/resources/documents";
	      String filepath = Paths.get(directory,filename).toString();
	      System.out.println(filepath);
	     
	      
	      // Save the file locally
	      BufferedOutputStream stream =
	          new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	      stream.write(uploadfile.getBytes());
	      userService.saveDetails(userDTO, filepath);
	      stream.close();
	    }
	    catch (Exception e) {
	      System.out.println(e.getMessage());
	      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	    
	    return new ResponseEntity<>(HttpStatus.OK);
	  } // method uploadFile
	  
	  @RequestMapping(value="/user/images",method=RequestMethod.GET)
	  public ModelAndView userDetails(Model model){
		  List<Person> details=userService.getAllUsers();
		  model.addAttribute("details", details);
		  return new ModelAndView("userDetails");
	  }

}
