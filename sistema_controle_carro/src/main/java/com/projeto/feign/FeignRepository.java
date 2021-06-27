package com.projeto.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carros", url = "https://parallelum.com.br/fipe/api/v1")
public interface FeignRepository {
	
	@GetMapping(value = "/carros/marcas")
	List<TempData> getBrand();
	
	@GetMapping(value = "/carros/marcas/{brand_code}/modelos")
	ListTempData getModel(@PathVariable String brand_code);
	
	@GetMapping(value = "/carros/marcas/{brand_code}/modelos/{model_code}/anos")
	List<TempData> getYear(@PathVariable String brand_code, @PathVariable String model_code);
	
	@GetMapping(value = "carros/marcas/{brand_code}/modelos/{model_code}/anos/{year_code}")
	TempSpecification getAllSpecification(@PathVariable String brand_code, @PathVariable String model_code, @PathVariable String year_code);
}
