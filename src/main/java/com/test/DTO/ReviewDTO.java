package com.test.DTO;

public class ReviewDTO {

	private String review;
	private Integer rating;
	private Long carId;

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Long getCarId() {
		return carId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

}
