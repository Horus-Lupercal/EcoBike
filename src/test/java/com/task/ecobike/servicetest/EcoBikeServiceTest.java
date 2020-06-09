package com.task.ecobike.servicetest;

import com.task.ecobike.dao.EcoBikeDao;
import com.task.ecobike.domain.AbstractTransport;
import com.task.ecobike.domain.nonpoweredtransport.FoldingBike;
import com.task.ecobike.domain.poweredtransport.EBike;
import com.task.ecobike.domain.poweredtransport.Speedelec;
import com.task.ecobike.dto.Filter;
import com.task.ecobike.dto.ProductsDTO;
import com.task.ecobike.service.EcoBikeService;
import com.task.ecobike.service.TranslationService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EcoBikeServiceTest {

    private EcoBikeService service = new EcoBikeService(new TranslationService(), new EcoBikeDao());

    @BeforeEach
    public void beforeEach() {
        service.readData("ecobike.txt");
    }

    @Test
    public void testReadDataExceptedStatusIsOK() {
        Assert.assertTrue( service.readData("ecobike.txt"));
    }

    @Test
    public void testGetAllProductExceptedDTO() {

        ProductsDTO dto = service.getAllProduct();

        Assert.assertNotNull(dto);
        Assert.assertNotNull(dto.getSpeedelec());
        Assert.assertNotNull(dto.getFoldingBikes());
        Assert.assertNotNull(dto.geteBikes());

        Assert.assertEquals(dto.geteBikes().get(0), getEBike());
        Assert.assertEquals(dto.getFoldingBikes().get(0), getFoldingBike());
        Assert.assertEquals(dto.getSpeedelec().get(0), getSpeedelec());
    }

    @Test
    public void testSaveObjectExceptedSameObject() {
        EBike eBike = getEBike();
        Assert.assertEquals(eBike, service.save(eBike));

        Speedelec speedelec = getSpeedelec();
        Assert.assertEquals(speedelec, service.save(speedelec));

        FoldingBike foldingBike = getFoldingBike();
        Assert.assertEquals(foldingBike, service.save(foldingBike));
    }

    @Test
    public void testFindProductWithoutFilterParametersExceptedAllEntity() {
        Filter filter = new Filter();
        List<AbstractTransport> list = service.findProduct(filter, false);
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void testFindProductWithFilterParametersExceptedOneEntity() {
        Filter filter = new Filter();
        filter.setBrand("SPEEDELEC Booster");
        filter.setMaxSpeed(35);
        filter.setBatteryCapacity(13200);
        filter.setColor("green");
        filter.setLights(false);
        filter.setPrice(1279);
        filter.setWeight(10900);
        List<AbstractTransport> list = service.findProduct(filter, false);

        Assertions.assertThat(list.get(0)).isEqualToComparingFieldByFieldRecursively(filter);
    }

    private EBike getEBike() {
        EBike eBike = new EBike();
        eBike.setBrand("E-BIKE Lankeleisi");
        eBike.setMaxSpeed(50);
        eBike.setBatteryCapacity(30000);
        eBike.setColor("flame");
        eBike.setLights(false);
        eBike.setPrice(859);
        eBike.setWeight(21600);
        return eBike;
    }

    private Speedelec getSpeedelec() {
        Speedelec speedelec = new Speedelec();
        speedelec.setBrand("SPEEDELEC Booster");
        speedelec.setMaxSpeed(35);
        speedelec.setBatteryCapacity(13200);
        speedelec.setColor("green");
        speedelec.setLights(false);
        speedelec.setPrice(1279);
        speedelec.setWeight(10900);
        return speedelec;
    }

    private FoldingBike getFoldingBike() {
        FoldingBike foldingBike = new FoldingBike();
        foldingBike.setBrand("FOLDING BIKE Benetti");
        foldingBike.setGear(27);
        foldingBike.setWheelsSize(24);
        foldingBike.setColor("rose");
        foldingBike.setLights(false);
        foldingBike.setPrice(1009);
        foldingBike.setWeight(11400);
        return foldingBike;
    }
}
