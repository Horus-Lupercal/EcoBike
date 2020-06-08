package com.task.ecobike.service;

import com.task.ecobike.domain.AbstractTransport;
import com.task.ecobike.domain.nonpoweredtransport.FoldingBike;
import com.task.ecobike.domain.poweredtransport.EBike;
import com.task.ecobike.domain.poweredtransport.Speedelec;
import com.task.ecobike.dto.Filter;
import com.task.ecobike.dto.ProductsDTO;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductService {
	private TranslationService translationService;
	private ProductsDTO dto;
	private Boolean isSaveToFile = true;

	public ProductService(TranslationService translationService) {
		this.translationService = translationService;
	}

	public boolean readData(String fileName) {
		ProductsDTO dto = new ProductsDTO();
		List<String> lines = readDataResources(fileName);
		if (lines == null) {
			return false;
		}

		dto.seteBikes(lines.stream().filter(line -> line.startsWith("E-BIKE"))
				.map(line -> (EBike) translationService.toEntity(line, new EBike())).collect(Collectors.toList()));

		dto.setSpeedelec(lines.stream().filter(line -> line.startsWith("SPEEDELEC"))
				.map(line -> (Speedelec) translationService.toEntity(line, new Speedelec())).collect(Collectors.toList()));

		dto.setFoldingBikes(lines.stream().filter(line -> line.startsWith("FOLDING BIKE"))
				.map(line -> (FoldingBike) translationService.toEntity(line, new FoldingBike())).collect(Collectors.toList()));

		this.dto = dto;

		return true;
	}

	public ProductsDTO getAllProduct() {
		return dto;
	}

	public AbstractTransport save(AbstractTransport savedObject) {
		if (savedObject.getClass() == EBike.class) {
			dto.geteBikes().add((EBike) savedObject);

		} else if (savedObject.getClass() == Speedelec.class) {
			dto.getSpeedelec().add((Speedelec) savedObject);

		} else if (savedObject.getClass() == FoldingBike.class) {
			dto.getFoldingBikes().add((FoldingBike) savedObject);
		}
		System.out.println("Save was completed!");
		isSaveToFile = false;
		return savedObject;
	}

	public void saveToFile() {
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
			} catch (Exception e) {
				System.err.println("Something wrong with write to file");
				e.printStackTrace();
			}
		} else {
			System.out.println("Nothing to save, zero changes on catalog");
		}
	}

	public List<AbstractTransport> findProduct(Filter filter) {
		List<AbstractTransport> result = new ArrayList<>();
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

	private List<String> readDataResources(String dataFile) {
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
