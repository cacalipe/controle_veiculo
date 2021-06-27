package com.projeto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.feign.TempData;
import com.projeto.service.CarService;

@RestController
@RequestMapping("car")
public class CarController {

	private final CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping(value = "/brand")
	public List<TempData> getBrand(){
		return carService.loadingBrand();
	}
	
	@GetMapping(value = "/brand/{brand_code}")
	public List<TempData> getModel(@PathVariable String brand_code){
		return carService.loadingModel(brand_code);
	}
	
	@GetMapping(value = "/brand/{brand_code}/model/{model_code}")
	public List<TempData> getYear(@PathVariable String brand_code, @PathVariable String model_code){
		return carService.loadingYear(brand_code, model_code);
	}
	
	@PostMapping(value = "/brand/{brand_code}/model/{model_code}/year/{year_code}/user/{id}")
	public ResponseEntity<String> registerCarForUser(@PathVariable String brand_code, @PathVariable String model_code, @PathVariable String year_code, @PathVariable Long id){
		try {
			carService.registerCarForUser(brand_code, model_code, year_code, id);
			return new ResponseEntity<String>("Criado com sucesso!", HttpStatus.CREATED);
		} catch (Exception e) {
			String erro = "erro: " + e;
			return new ResponseEntity<String>(erro, HttpStatus.BAD_REQUEST);
		}
	}
}
