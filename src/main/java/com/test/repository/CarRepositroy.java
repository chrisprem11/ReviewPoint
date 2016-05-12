package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Car;

@Repository
public interface CarRepositroy extends JpaRepository<Car, Long> {
	
	public List<Car> findAllByCarId(Long id);

	
}
