package com.projeto.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.dto.CarDTO;
import com.projeto.entity.Car;
import com.projeto.entity.User;
import com.projeto.feign.FeignRepository;
import com.projeto.feign.TempData;
import com.projeto.feign.TempSpecification;
import com.projeto.repository.CarRepository;
import com.projeto.repository.UserRepository;

@Service
public class CarService {

	private final CarRepository carRepository;
	private final UserRepository userRepository;
	private final FeignRepository feignRepository;
	
	@Autowired
	public CarService(CarRepository carRepository, UserRepository userRepository, FeignRepository feignRepository) {
		this.carRepository = carRepository;
		this.userRepository = userRepository;
		this.feignRepository = feignRepository;
	}
	
	public List<TempData> loadingBrand(){
		return feignRepository.getBrand();
	}
	
	public List<TempData> loadingModel(String brand_code) {
		return feignRepository.getModel(brand_code).getModelos();
	}
	
	public List<TempData> loadingYear(String brand_code, String model_code){
		return feignRepository.getYear(brand_code, model_code);
	}
	
	
	public void registerCarForUser(String brand_code, String model_code, String year_code, Long id) {
		User user = userRepository.findById(id).get();
		CarDTO carDTO = new CarDTO();
		TempSpecification temp = feignRepository.getAllSpecification(brand_code, model_code, year_code);
		
		carDTO.setBrand(temp.Marca);
		carDTO.setModel(temp.Modelo);
		carDTO.setYear(temp.AnoModelo);
		Car car = carDTO.transformInObj();
		
		car = rotationDay(car);
		carRepository.save(car);
		user.getCar().add(car);
		userRepository.save(user);
	}
	
	public Car rotationDay(Car car) {
		LocalDate today = LocalDate.now();
		String verYear = Integer.toString(car.getYear());
		String last = verYear.substring(verYear.length()-1);
		
		if (last.equals("0") || last.equals("1")) {
			car.setRotation_day(today.getDayOfWeek().MONDAY);
		}else if (last.equals("2") || last.equals("3")) {
			car.setRotation_day(today.getDayOfWeek().TUESDAY);
		}else if (last.equals("4") || last.equals("5")) {
			car.setRotation_day(today.getDayOfWeek().WEDNESDAY);
		}else if (last.equals("6") || last.equals("7")) {
			car.setRotation_day(today.getDayOfWeek().THURSDAY);
		}else if (last.equals("8") || last.equals("9")) {
			car.setRotation_day(today.getDayOfWeek().FRIDAY);
		}
		
		return car;
	}
}



