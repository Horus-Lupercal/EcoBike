package com.task.ecobike.domain.poweredtransport;

import com.task.ecobike.domain.AbstractTransport;

public abstract class PoweredTransport extends AbstractTransport {

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
}
