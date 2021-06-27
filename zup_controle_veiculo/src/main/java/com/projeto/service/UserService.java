package com.projeto.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.entity.Car;
import com.projeto.entity.User;
import com.projeto.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void registerUser(User user) {
		userRepository.save(user);
	}
	
	public User searchById(Long id) {
		User user = userRepository.findById(id).get();
		List<Car> car = rotationActiveDay(user.getCar());
		for(Car x : car) {
			x.getUser().add(user);
		}
		return user;
	}
	
	public List<Car> rotationActiveDay(List<Car> car){
		LocalDate today = LocalDate.now();
		for(Car x : car) {
			DayOfWeek ver = today.getDayOfWeek();
			if(ver.equals(x.getRotation_day())) {
				x.setRotation_active(true);;
			}else {
				x.setRotation_active(false);
			}
		}
		return car;
	}
	
	
}
