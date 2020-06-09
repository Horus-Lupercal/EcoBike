package com.task.ecobike.service;

import com.task.ecobike.domain.AbstractTransport;
import com.task.ecobike.domain.nonpoweredtransport.FoldingBike;
import com.task.ecobike.domain.poweredtransport.EBike;
import com.task.ecobike.domain.poweredtransport.Speedelec;
import com.task.ecobike.dto.Filter;
import com.task.ecobike.dto.ProductsDTO;

import java.util.ArrayList;
import java.util.List;

public class SearchingService implements Runnable {
    private ProductsDTO dto;
    private Filter filter;
    private List<AbstractTransport> result = new ArrayList<>();

    public SearchingService(ProductsDTO dto, Filter filter) {
        this.dto = dto;
        this.filter = filter;
    }

    public List<AbstractTransport> getResult() {
        return result;
    }

    @Override
    public void run() {
       findProduct();
    }

    public List<AbstractTransport> findProduct() {
        for (FoldingBike foldingBike : dto.getFoldingBikes()) {
            if (compareByField(foldingBike, filter)) {
                result.add(foldingBike);
            }
        }
        for (EBike eBike : dto.geteBikes()) {
            if (compareByField(eBike, filter)) {
                result.add(eBike);
            }
        }
        for (Speedelec speedelec : dto.getSpeedelec()) {
            if (compareByField(speedelec, filter)) {
                result.add(speedelec);
            }
        }
        return result;
    }

    private boolean compareByField(AbstractTransport transport, Filter filter) {
        if (filter.getBrand() != null) {
            if (!transport.getBrand().equals(filter.getBrand()) &&
                    !transport.getBrand().startsWith(filter.getBrand())) {
                return false;
            }
        }
        if (filter.getWeight() != null) {
            if (!transport.getWeight().equals(filter.getWeight())) {
                return false;
            }
        }
        if (filter.getLights() != null) {
            if (!transport.getLights().equals(filter.getLights())) {
                return false;
            }
        }
        if (filter.getPrice() != null) {
            if (!transport.getPrice().equals(filter.getPrice())) {
                return false;
            }
        }
        if (filter.getColor() != null) {
            if (!transport.getColor().equals(filter.getColor())) {
                return false;
            }
        }

        if (transport.getClass() == FoldingBike.class) {

            if (filter.getGear() != null) {
                if (!((FoldingBike) transport).getGear().equals(filter.getGear())) {
                    return false;
                }
            }
            if (filter.getWheelsSize() != null) {
                if (!((FoldingBike) transport).getWheelsSize().equals(filter.getWheelsSize())) {
                    return false;
                }
            }

        } else if (transport.getClass() == EBike.class) {

            if (filter.getBatteryCapacity() != null) {
                if (!((EBike) transport).getBatteryCapacity().equals(filter.getBatteryCapacity())) {
                    return false;
                }
            }
            if (filter.getMaxSpeed() != null) {

                if (!((EBike) transport).getMaxSpeed().equals(filter.getMaxSpeed())) {
                    return false;
                }
            }
        } else if (transport.getClass() == Speedelec.class) {

            if (filter.getBatteryCapacity() != null) {
                if (!((Speedelec) transport).getBatteryCapacity().equals(filter.getBatteryCapacity())) {
                    return false;
                }
            }
            if (filter.getMaxSpeed() != null) {
                if (!((Speedelec) transport).getMaxSpeed().equals(filter.getMaxSpeed())) {
                    return false;
                }
            }
        }
        return true;
    }
}
