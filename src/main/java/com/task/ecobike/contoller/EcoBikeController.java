package com.task.ecobike.contoller;

import com.task.ecobike.domain.AbstractTransport;
import com.task.ecobike.domain.nonpoweredtransport.FoldingBike;
import com.task.ecobike.domain.poweredtransport.EBike;
import com.task.ecobike.domain.poweredtransport.Speedelec;
import com.task.ecobike.dto.Filter;
import com.task.ecobike.dto.ProductsDTO;
import com.task.ecobike.service.EcoBikeService;
import com.task.ecobike.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcoBikeController {
    private EcoBikeService service;
    private ConsoleView console;

    public EcoBikeController(EcoBikeService service, ConsoleView console) {
        this.service = service;
        this.console = console;
    }

    public void startApplication() {
        boolean isStart = true;
        boolean isFileRead = false;
        String askDataFile = "Please enter a data file name";
        String title = "Please make your choice:\r\n" +
                "1 - Show the entire EcoBike catalog\r\n" +
                "2 – Add a new folding bike\r\n" +
                "3 – Add a new speedelec\r\n" +
                "4 – Add a new e-bike\r\n" +
                "5 – Find all items of a particular brand\r\n" +
                "6 – Write to file\r\n" +
                "7 – Stop the program;";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!isFileRead) {
                console.printResult(askDataFile);
                isFileRead = service.
                        readData(reader.readLine());
            }
            while (isStart) {
                console.printResult(title);
                switch (reader.readLine()) {
                    case "1":
                        console.printResult(showCatalog());
                        break;
                    case "2":
                        Map<String, String> foldingBikeParam = createObject(reader, FoldingBike.class);
                        List<AbstractTransport> foldingBike = Collections.singletonList(
                                service.createObject(foldingBikeParam, FoldingBike.class));
                        console.printResult(listToString(foldingBike));
                        break;
                    case "3":
                        Map<String, String> speedelecParam = createObject(reader, Speedelec.class);
                        List<AbstractTransport> speedelec = Collections.singletonList(
                                service.createObject(speedelecParam, Speedelec.class));
                        console.printResult(listToString(speedelec));
                        break;
                    case "4":
                        Map<String, String> eBikeParam = createObject(reader, EBike.class);
                        List<AbstractTransport> eBike = Collections.singletonList(
                                service.createObject(eBikeParam, EBike.class));
                        console.printResult(listToString(eBike));
                        break;
                    case "5":
                        console.printResult("Use multithreading search (Y/N)");
                        boolean isMultithreading = false;
                        try {
                            if ("Y".equals(reader.readLine().toUpperCase())) {
                                isMultithreading = true;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        console.printResult(listToString(service.findProduct(getFilter(reader), isMultithreading)));
                        break;
                    case "6":
                        service.saveToFile();
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

    private Filter getFilter(BufferedReader reader) {
        console.printResult("please enter: filter parameters, " +
                "if you don't know some bike parameters don't enter it");
        return service.createObject(createObject(reader, Filter.class), Filter.class);
    }

    private String showCatalog() {
        ProductsDTO dto = service.getAllProduct();

        return listToString(dto.geteBikes()) +
                listToString(dto.getSpeedelec()) +
                listToString(dto.getFoldingBikes());
    }

    private <T> String listToString(List<T> list) {
        String brand = "%s with";
        String gear = " %d gear(s)";
        String withLights = " and head/tail light";
        String withoutLights = " and no head/tail light";
        String battery = " %d mAh battery";
        String price = "Price: %d euros.";

        StringBuilder result = new StringBuilder();
        for (T transport : list) {
            if (transport instanceof AbstractTransport) {
                result.append(String.format(brand, ((AbstractTransport) transport).getBrand()));
                if (transport instanceof FoldingBike) {
                    result.append(String.format(gear, ((FoldingBike) transport).getGear()));
                } else if (transport instanceof EBike) {
                    result.append(String.format(battery, ((EBike) transport).getBatteryCapacity()));
                } else if (transport instanceof Speedelec) {
                    result.append(String.format(battery, ((Speedelec) transport).getBatteryCapacity()));
                }
                if (((AbstractTransport) transport).getLights()) {
                    result.append(withLights);
                } else {
                    result.append(withoutLights);
                }
                result.append(System.lineSeparator());
                result.append(String.format(price, ((AbstractTransport) transport).getPrice()));
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }

    private Map<String, String> createObject(BufferedReader reader, Class aClass) {
        Map<String, String> parameters = new HashMap<>();
        try {
            console.printResult("please enter: brand type");
            String brand = reader.readLine();
            if (brand != null && !brand.isEmpty()) {
                parameters.put("brand", brand);
            }
            console.printResult("please enter: color");
            String color = reader.readLine();
            if (color != null && !color.isEmpty()) {
                parameters.put("color", color);
            }
            console.printResult("please enter: price");
            String price = reader.readLine();
            if (price != null && !price.isEmpty()) {
                parameters.put("price", price);
            }
            console.printResult("please enter: did bike has lights? (Y/N)");
            String lights = reader.readLine();
            if (lights != null && !lights.isEmpty()) {
                parameters.put("lights", lights);
            }
            console.printResult("please enter: weight");
            String weight = reader.readLine();
            if (weight != null && !weight.isEmpty()) {
                parameters.put("weight", weight);
            }

            if (aClass == FoldingBike.class || brand != null && brand.contains("Folding Bike".toUpperCase())) {
                console.printResult("please enter: wheels size");
                String wheelsSize = reader.readLine();
                if (wheelsSize != null && !wheelsSize.isEmpty()) {
                    parameters.put("wheelsSize", wheelsSize);
                }
                console.printResult("please enter: number of gear(s)");
                String gear = reader.readLine();
                if (gear != null && !gear.isEmpty()) {
                    parameters.put("gear", gear);
                }
            } else {
                console.printResult("please enter: max speed");
                String maxSpeed = reader.readLine();
                if (maxSpeed != null && !maxSpeed.isEmpty()) {
                    parameters.put("maxSpeed", maxSpeed);
                }
                console.printResult("please enter: battery capacity");
                String batteryCapacity = reader.readLine();
                if (batteryCapacity != null && !batteryCapacity.isEmpty()) {
                    parameters.put("batteryCapacity", batteryCapacity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parameters;
    }
}
