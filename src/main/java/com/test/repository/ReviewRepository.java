package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Car;
import com.test.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	public List<Review> findReviewByCar(Car car);

}
