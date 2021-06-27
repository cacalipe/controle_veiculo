package com.projeto.dto;

import java.util.List;

import com.projeto.entity.Car;
import com.projeto.entity.User;

public class UserResponseDTO {

	private String name;
	private List<Car> car;
	
	public UserResponseDTO(String name, List<Car> car) {
		this.name = name;
		this.car = car;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Car> getCar() {
		return car;
	}
	public void setCar(List<Car> car) {
		this.car = car;
	}
	
	public static UserResponseDTO transformInDTO(User user) {
		return new UserResponseDTO(user.getName(), user.getCar());
	}
	
	@Override
	public String toString() {
		return "Nome: " + name + "\n"+ car.toString() ;
	}

}
