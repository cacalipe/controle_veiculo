package com.projeto.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class Car {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String brand;
	@Column(nullable = false)
	private String model;
	@Column(nullable = false)
	private int year;
	private DayOfWeek rotation_day;
	private boolean rotation_active = false;
	
	@ManyToMany
	@JoinTable(name = "user_car",
	uniqueConstraints = @UniqueConstraint(columnNames = {"id_user", "id_car"}),
	joinColumns = @JoinColumn(name = "id_car"),
	inverseJoinColumns = @JoinColumn(name = "id_user"))
	private List<User> user;
	
	public Car() {
	}
	public Car(String brand, String model, int year) {
		this.brand = brand;
		this.model = model;
		this.year = year;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public DayOfWeek getRotation_day() {
		return rotation_day;
	}
	public void setRotation_day(DayOfWeek rotation_day) {
		this.rotation_day = rotation_day;
	}
	public boolean isRotation_active() {
		return rotation_active;
	}
	public void setRotation_active(boolean rotation_active) {
		this.rotation_active = rotation_active;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	
	LocalDate today = LocalDate.now();
	
	@Override
	public String toString() {
		return "\n	Carro id: " + id +"\n		Hoje é "+ today.getDayOfWeek() + ", o carro é da marca " + brand + ", modelo " + model + " do ano " + year
				+ ", ou seja, seu rodizio será às " + rotation_day + " e o atributo de rodizio ativo será " + rotation_active + "\n";
	}
	
	
	
}
