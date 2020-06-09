package com.task.ecobike.domain.nonpoweredtransport;

import com.task.ecobike.domain.AbstractTransport;

import java.util.Objects;

public class FoldingBike extends AbstractTransport {

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

    @Override
    public String toString() {
        return super.getBrand() + "; " + wheelsSize + "; " + gear +
                "; " + super.getWeight() + "; " + super.getLights() +
                "; " + super.getColor() + "; " + super.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoldingBike)) return false;
        if (!super.equals(o)) return false;
        FoldingBike that = (FoldingBike) o;
        return wheelsSize.equals(that.wheelsSize) &&
                gear.equals(that.gear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wheelsSize, gear);
    }
}
