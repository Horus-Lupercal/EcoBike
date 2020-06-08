package com.task.ecobike.servicetest;

import com.task.ecobike.dto.ProductsDTO;
import com.task.ecobike.service.ProductService;
import com.task.ecobike.service.TranslationService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {

    private ProductService service = new ProductService(new TranslationService());

    @Test
    public void testToEntity() {
        service.readData("test");

        ProductsDTO dto = service.getAllProduct();
        Assert.assertNotNull(dto);

        Assert.assertNotNull(dto.geteBikes().get(0));
        System.out.println(dto.getSpeedelec().get(0).getLights());
        System.out.println(dto.getSpeedelec().get(0).getMaxSpeed());
        System.out.println(dto.getSpeedelec().get(0).getBrand());
        System.out.println(dto.getSpeedelec().get(0).getWeight());
        System.out.println(dto.getSpeedelec().get(0).getColor());
        System.out.println(dto.getSpeedelec().get(0).getPrice());
        System.out.println(dto.getSpeedelec().get(0).getBatteryCapacity());
    }
}
