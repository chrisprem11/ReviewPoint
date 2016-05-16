package com.test.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.DTO.ReviewDTO;
import com.test.model.Car;
import com.test.model.Review;
import com.test.repository.ReviewRepository;
import com.test.security.CurrentUser;

@Service
@Transactional
public class ReviewService {

	@Autowired
	private CarService carService;
	@Autowired
	private ReviewRepository reviewRepository;

	private static final int PAGE_SIZE = 4;

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
		Car cars = carService.getCar(carId);
		List<Review> allReviews = reviewRepository.findReviewByCar(cars);
		return allReviews;
	}

	public Page<Review> getReviewLog(Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "reviewedOn");
		return reviewRepository.findAll(request);
	}

}
