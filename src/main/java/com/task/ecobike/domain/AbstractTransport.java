package com.task.ecobike.domain;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AbstractTransport)) return false;
		AbstractTransport transport = (AbstractTransport) o;
		return brand.equals(transport.brand) &&
				color.equals(transport.color) &&
				price.equals(transport.price) &&
				lights.equals(transport.lights) &&
				weight.equals(transport.weight);
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, color, price, lights, weight);
	}
}
