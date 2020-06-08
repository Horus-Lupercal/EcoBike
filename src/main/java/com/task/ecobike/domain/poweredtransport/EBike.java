package com.task.ecobike.domain.poweredtransport;

import com.task.ecobike.domain.AbstractTransport;
import com.task.ecobike.dto.Filter;

import java.util.Objects;

public class EBike extends AbstractTransport {
    private Integer maxSpeed;
    private Integer batteryCapacity;

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

    @Override
    public String toString() {
        return super.getBrand() + "; " + maxSpeed + "; " + super.getWeight() +
                "; " + super.getLights() + "; " + batteryCapacity +
                "; " + super.getColor() + "; " + super.getPrice();
    }
}


