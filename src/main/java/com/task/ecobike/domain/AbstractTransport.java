package com.task.ecobike.domain;

public abstract class AbstractTransport {
	
	private String brand;
	private String color;
	private Integer price;
	private Boolean lights;
	private Integer weight;
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Boolean getLights() {
		return lights;
	}
	public void setLights(Boolean lights) {
		this.lights = lights;
	}
	
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
