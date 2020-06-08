package com.task.ecobike.dto;

public class Filter {

    private String brand;
    private String color;
    private Integer price;
    private Boolean lights;
    private Integer weight;
    private Integer wheelsSize;
    private Integer gear;
    private Integer maxSpeed;
    private Integer batteryCapacity;

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

    public Integer getWheelsSize() {
        return wheelsSize;
    }
    public void setWheelsSize(Integer wheelsSize) {
        this.wheelsSize = wheelsSize;
    }

    public Integer getGear() {
        return gear;
    }
    public void setGear(Integer gear) {
        this.gear = gear;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }
    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }
    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
