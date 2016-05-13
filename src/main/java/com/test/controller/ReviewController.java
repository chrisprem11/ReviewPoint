package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.DTO.ReviewDTO;
import com.test.model.Review;
import com.test.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public ModelAndView getReview(ReviewDTO reviewDTO, Authentication authentication, RedirectAttributes attributes) {
		Review review = reviewService.getReviews(reviewDTO, authentication);
		if (null == review) {

			return new ModelAndView("carDetails", "error","Opeartion Failed !");
		}
		attributes.addFlashAttribute("success", "Review Submitted Successfully !");
		
		return new ModelAndView("redirect:/viewCars");
	}

}
