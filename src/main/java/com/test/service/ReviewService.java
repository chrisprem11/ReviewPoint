package com.test.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.test.DTO.ReviewDTO;
import com.test.model.Car;
import com.test.model.Review;
import com.test.repository.ReviewRepository;
import com.test.security.CurrentUser;

@Service
public class ReviewService {

	@Autowired
	private CarService carService;
	@Autowired
	private ReviewRepository reviewRepository;

	public Review getReviews(ReviewDTO reviewDTO, Authentication authentication) {
		Review addReview = new Review();
		CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
		addReview.setRating(reviewDTO.getRating());
		addReview.setReview(reviewDTO.getReview());
		addReview.setReviewedOn(new Timestamp(System.currentTimeMillis()));
		addReview.setPerson(currentUser.getUser());
		addReview.setCar(carService.getCar(reviewDTO.getCarId()));
		return reviewRepository.save(addReview);

	}

	public List<Review> fetchReviews(Car car, Long carId) {
		Car cars =carService.getCar(carId);
		List<Review> allReviews= reviewRepository.findReviewByCar( cars);
		return allReviews;
	}

	
}
