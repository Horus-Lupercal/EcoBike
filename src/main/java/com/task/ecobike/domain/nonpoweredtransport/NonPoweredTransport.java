package com.task.ecobike.domain.nonpoweredtransport;

import com.task.ecobike.domain.AbstractTransport;

public abstract class NonPoweredTransport extends AbstractTransport {

    private Integer wheelsSize;
    private Integer gear;

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
}
