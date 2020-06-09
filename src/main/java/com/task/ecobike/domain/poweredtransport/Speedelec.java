package com.task.ecobike.domain.poweredtransport;

import com.task.ecobike.domain.AbstractTransport;

import java.util.Objects;

public class Speedelec extends AbstractTransport {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Speedelec)) return false;
        if (!super.equals(o)) return false;
        Speedelec speedelec = (Speedelec) o;
        return maxSpeed.equals(speedelec.maxSpeed) &&
                batteryCapacity.equals(speedelec.batteryCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed, batteryCapacity);
    }
}
