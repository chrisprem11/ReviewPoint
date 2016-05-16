package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.DTO.ReviewDTO;
import com.test.model.Review;
import com.test.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public String getReview(ReviewDTO reviewDTO, Authentication authentication, RedirectAttributes attributes) {
		Review review = reviewService.getReviews(reviewDTO, authentication);
		if (null == review) {

			return "Opeartion Failed !";
		}
		attributes.addFlashAttribute("success", "Review Submitted Successfully !");
		
		return ("redirect:/viewCars/"+reviewDTO.getCarId());
	}
	
	@RequestMapping(value = "reviews/pages/{pageNumber}", method = RequestMethod.GET)
	public String getReviewPage(@PathVariable Integer pageNumber, Model model) {
	    Page<Review> page = reviewService.getReviewLog(pageNumber);
	    List<Review> reviews =page.getContent();
	    int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());
	    int totalPageCount=page.getTotalPages();

	    model.addAttribute("reviews", reviews);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    model.addAttribute("totalPageCount",totalPageCount);

	    return "users";
	}

}
