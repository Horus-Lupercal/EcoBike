package com.task.ecobike.contoller;

import com.task.ecobike.domain.AbstractTransport;
import com.task.ecobike.domain.nonpoweredtransport.FoldingBike;
import com.task.ecobike.domain.poweredtransport.EBike;
import com.task.ecobike.domain.poweredtransport.Speedelec;
import com.task.ecobike.dto.Filter;
import com.task.ecobike.dto.ProductsDTO;
import com.task.ecobike.service.ProductService;
import com.task.ecobike.service.TranslationService;
import com.task.ecobike.view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductController {
    private ProductService service;
    private ConsoleView console;
    private TranslationService translationService;

    public ProductController(ProductService service, ConsoleView console, TranslationService translationService) {
        this.service = service;
        this.console = console;
        this.translationService = translationService;
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
                        AbstractTransport foldingBike = service.save(createObject(reader, new FoldingBike()));
                        console.printResult(listToString(Collections.singletonList(foldingBike)));
                        break;
                    case "3":
                        AbstractTransport speedelec = service.save(createObject(reader, new Speedelec()));
                        console.printResult(listToString(Collections.singletonList(speedelec)));
                        break;
                    case "4":
                        AbstractTransport eBike = service.save(createObject(reader, new EBike()));
                        console.printResult(listToString(Collections.singletonList(eBike)));
                        break;
                    case "5":
                        console.printResult(listToString(service.findProduct(getFilter(reader))));
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
        Filter filter = new Filter();
        console.printResult("please enter: filter parameters, " +
                "if you don't know some bike parameters or bike don't have such parameters don't enter it");
        createObject(reader, filter);
        return filter;
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

    private <T> T createObject(BufferedReader reader, T object) {
        try {
            List<Field> fields = translationService.getAllFields(new ArrayList<>(), object.getClass());

            for (Field field : fields) {
                console.printResult(String.format("please enter: %s", field.getName()));
                field.setAccessible(true);
                if (field.getType() == String.class) {
                    String string = reader.readLine();

                    if (string != null && !string.isEmpty()) {
                        if (field.getName().equals("brand")) {

                            if (EBike.class == object.getClass()) {
                                field.set(object, "E-BIKE " + string);
                            } else if (Speedelec.class == object.getClass()) {
                                field.set(object, "SPEEDELEC " + string);
                            } else if (FoldingBike.class == object.getClass()) {
                                field.set(object, "FOLDING BIKE " + string);
                            } else {
                                field.set(object, string);
                            }

                        } else {
                            field.set(object, string);
                        }
                    }

                } else if (field.getType() == Integer.class) {
                    String integer = reader.readLine();
                    if (integer != null && !integer.isEmpty()) {
                        field.set(object, Integer.parseInt(integer));
                    }

                } else if (field.getType() == Boolean.class) {
                    console.printResult("enter: (Y/N)");
                    String light = reader.readLine();
                    if (light != null && !light.isEmpty()) {
                        if ("Y".equals(light.toUpperCase())) {
                            field.set(object, true);
                        } else {
                            field.set(object, false);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Something Wrong with given parameters");
        }
        return object;
    }
}
