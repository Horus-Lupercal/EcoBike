package com.task.ecobike.dao;

import com.task.ecobike.domain.nonpoweredtransport.FoldingBike;
import com.task.ecobike.domain.poweredtransport.EBike;
import com.task.ecobike.domain.poweredtransport.Speedelec;
import com.task.ecobike.dto.ProductsDTO;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EcoBikeDao {

    public boolean saveToFile(Boolean isSaveToFile, ProductsDTO dto) {
        if (!isSaveToFile) {
            File file = new File(".\\src\\main\\resources\\application.properties");
            try (FileWriter writer = new FileWriter(file + "\\ecobike.txt")) {
                for (EBike eBike : dto.geteBikes()) {
                    writer.write(eBike.toString() + System.lineSeparator());
                }
                for (Speedelec speedelec : dto.getSpeedelec()) {
                    writer.write(speedelec.toString() + System.lineSeparator());
                }
                for (FoldingBike foldingBike : dto.getFoldingBikes()) {
                    writer.write(foldingBike.toString() + System.lineSeparator());
                }
                System.out.println("write is complete");
                Desktop.getDesktop().open(new File(file + "\\ecobike.txt"));
                System.out.println(file.getAbsolutePath());
                return true;
            } catch (Exception e) {
                System.err.println("Something wrong with write to file");
                e.printStackTrace();
            }

        }
        System.out.println("Nothing to save, zero changes on catalog");

        return false;
    }

    public List<String> readDataResources(String dataFile) {
       List<String> items = null;
        Path path = null;
        try {

            path = Paths.get(getClass().getClassLoader()
                    .getResource("application.properties\\" + dataFile).toURI());
            items = fillList(path);

        } catch (Exception e) {
            System.err.println("Not valid file name, pls check, " +
                    "file should locate in application.properties resources directory");
        }
        return items;
    }

    private List<String> fillList(Path path) {
        List<String> list = null;

        try (Stream<String> streamFromFile = Files.lines(path)) {

            list = streamFromFile.collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
