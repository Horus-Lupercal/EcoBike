package com.task.ecobike.contoller;

import com.task.ecobike.domain.AbstractTransport;
import com.task.ecobike.domain.nonpoweredtransport.FoldingBike;
import com.task.ecobike.domain.poweredtransport.EBike;
import com.task.ecobike.domain.poweredtransport.Speedelec;
import com.task.ecobike.dto.ProductsDTO;
import com.task.ecobike.service.ProductService;
import com.task.ecobike.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ProductController {
    private ProductService service;
    private ConsoleView console;

    public ProductController(ProductService service, ConsoleView console) {
        this.service = service;
        this.console = console;
    }

    public void startApplication() throws IOException {
        boolean isStart = true;
        ProductsDTO dto = service.readData();

        String title = "Please make your choice:\r\n" +
                "1 - Show the entire EcoBike catalog\r\n" +
                "2 – Add a new folding bike\r\n" +
                "3 – Add a new speedelec\r\n" +
                "4 – Add a new e-bike\r\n" +
                "5 – Find the first item of a particular brand\r\n" +
                "6 – Write to file\r\n" +
                "7 – Stop the program;";

        console.printResult(title);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (isStart) {
                switch (reader.readLine()) {
                    case "1":
                        console.printResult(dtoToString(dto));
                        break;
                    case "2":
                        dto.getFoldingBikes().add(createFoldingBike(reader));
                        break;
                    case "3":
                        dto.getSpeedelec().add(createSpeedelec(reader));
                        break;
                    case "4":
                        dto.geteBikes().add(createEBike(reader));
                        break;
                    case "5":
                        findItem();
                        break;
                    case "6":
                        writeFoFile();
                        break;
                    case "7":
                        isStart = false;
                        console.printResult("Program stopped working");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AbstractTransport findItem() {
        return null;
    }

    private void writeFoFile() {

    }

    private String dtoToString(ProductsDTO dto) {

        String gear = " %d gear(s)";

        return poweredTransportToString(dto.geteBikes());
    }

    private String poweredTransportToString(List<EBike> list) {
        StringBuilder result = new StringBuilder();
        String brand = "%s with";
        String battery = " %d mAh battery";
        String withLights = " and head/tail light";
        String withoutLights = " and no head/tail light";
        String price = "Price: %d euros.";
        for (EBike ebike : list) {
            result.append(String.format(brand, ebike.getBrand()));
            result.append(String.format(battery, ebike.getBatteryCapacity()));
            if (ebike.getLights()) {
                result.append(withLights);
            } else {
                result.append(withoutLights);
            }
            result.append(System.lineSeparator());
            result.append(String.format(price, ebike.getPrice()));
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    private FoldingBike createFoldingBike(BufferedReader reader) throws IOException {
        FoldingBike foldingBike = new FoldingBike();

        console.printResult("please enter: a brand");
        foldingBike.setBrand("FOLDING BIKE ".concat(reader.readLine()));

        console.printResult("please enter: count of gears");
        foldingBike.setGear(Integer.parseInt(reader.readLine()));

        console.printResult("please enter: wheel size");
        foldingBike.setGear(Integer.parseInt(reader.readLine()));

        console.printResult("please enter: weight of the bike");
        foldingBike.setWeight(Integer.parseInt(reader.readLine()));

        console.printResult("please enter: did a bike has lights? Y/N");
        String lights = reader.readLine();
        if ("Y".equals(lights.toUpperCase())) {
            foldingBike.setLights(true);
        } else {
            foldingBike.setLights(false);
        }

        console.printResult("please enter: a color");
        foldingBike.setColor(reader.readLine());

        console.printResult("please enter: the price");
        foldingBike.setPrice(Integer.parseInt(reader.readLine()));

        return foldingBike;
    }

    private EBike createEBike(BufferedReader reader) throws IOException {
        EBike eBike = new EBike();

        console.printResult("please enter: a brand");
        eBike.setBrand("E-BIKE ".concat(reader.readLine()));

        console.printResult("please enter: battery capacity");
        eBike.setBatteryCapacity(Integer.parseInt(reader.readLine()));

        console.printResult("please enter: max speed");
        eBike.setMaxSpeed(Integer.parseInt(reader.readLine()));

        console.printResult("please enter: weight of the bike");
        eBike.setWeight(Integer.parseInt(reader.readLine()));

        console.printResult("please enter: did a bike has lights? Y/N");
        String lights = reader.readLine();
        if ("Y".equals(lights.toUpperCase())) {
            eBike.setLights(true);
        } else {
            eBike.setLights(false);
        }
        console.printResult("please enter: a color");
        eBike.setColor(reader.readLine());

        console.printResult("please enter: the price");
        eBike.setPrice(Integer.parseInt(reader.readLine()));
        return eBike;
    }

    private Speedelec createSpeedelec(BufferedReader reader) throws IOException {
        Speedelec speedelec = new Speedelec();

        console.printResult("please enter: a brand");
        speedelec.setBrand("SPEEDELEC ".concat(reader.readLine()));

        console.printResult("please enter: battery capacity");
        speedelec.setBatteryCapacity(Integer.parseInt(reader.readLine()));

        console.printResult("please enter: max speed");
        speedelec.setMaxSpeed(Integer.parseInt(reader.readLine()));

        console.printResult("please enter: weight of the bike");
        speedelec.setWeight(Integer.parseInt(reader.readLine()));

        console.printResult("please enter: did a bike has lights? Y/N");
        String lights = reader.readLine();
        if ("Y".equals(lights.toUpperCase())) {
            speedelec.setLights(true);
        } else {
            speedelec.setLights(false);
        }
        console.printResult("please enter: a color");
        speedelec.setColor(reader.readLine());

        console.printResult("please enter: the price");
        speedelec.setPrice(Integer.parseInt(reader.readLine()));
        return speedelec;
    }
}
