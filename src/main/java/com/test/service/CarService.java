package com.test.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.DTO.CarDTO;
import com.test.model.Car;
import com.test.repository.CarRepositroy;

@Service
public class CarService {

	@Autowired
	private CarRepositroy carRepository;

	public Car SaveDetails(CarDTO carDTO) {
		Car newCar = new Car();
		newCar.setBrand(carDTO.getBrand());
		newCar.setDateAdded(new Timestamp(System.currentTimeMillis()));
		newCar.setDetails(carDTO.getDetails());
		newCar.setModel(carDTO.getModel());
		newCar.setPrice(carDTO.getPrice());
		newCar.setVariant(carDTO.getVariant());
		return carRepository.save(newCar);

	}

	public List<Car> getAllCars() {
		List<Car> carList = carRepository.findAll();
		return carList;

	}

	public List<Car> getCarDetails(Long id) {
		List<Car> carDetails = carRepository.findAllByCarId(id);
		return carDetails;
	}

	public Car getCar(Long id) {
		Car carId = carRepository.findCarByCarId(id);
		return carId;

	}

}
