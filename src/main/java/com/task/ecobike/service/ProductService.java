package com.task.ecobike.service;

import java.util.List;
import java.util.stream.Collectors;

import com.task.ecobike.dao.ProductDao;
import com.task.ecobike.domain.nonpoweredtransport.FoldingBike;
import com.task.ecobike.domain.poweredtransport.EBike;
import com.task.ecobike.domain.poweredtransport.Speedelec;
import com.task.ecobike.dto.ProductsDTO;

public class ProductService {

	private ProductDao dao;

	public ProductService (ProductDao dao) {
		this.dao = dao;
	}

	public ProductsDTO readData() {
		ProductsDTO dto = new ProductsDTO();
		List<String> lines = dao.readDataResources();

		dto.seteBikes(lines.stream().filter(line -> line.startsWith("E-BIKE"))
				.map(this::toEBike).collect(Collectors.toList()));
		
		dto.setSpeedelec(lines.stream().filter(line -> line.startsWith("SPEEDELEC"))
				.map(this::toSpidelec).collect(Collectors.toList()));
		
		dto.setFoldingBikes(lines.stream().filter(line -> line.startsWith("FOLDING BIKE"))
				.map(this::toFoldingBike).collect(Collectors.toList()));
				
		return dto;
	}

	private EBike toEBike(String line) {
		String[] arr = line.split("; ");
		EBike eBike = new EBike();
		eBike.setBrand(arr[0]);
		eBike.setMaxSpeed(Integer.parseInt(arr[1]));
		eBike.setWeight(Integer.parseInt(arr[2]));
		eBike.setLights(Boolean.parseBoolean(arr[3]));
		eBike.setBatteryCapacity(Integer.parseInt(arr[4]));
		eBike.setColor(arr[5]);
		eBike.setPrice(Integer.parseInt(arr[6]));
		return eBike;
	}
	
	private Speedelec toSpidelec(String line) {
		String[] arr = line.split("; ");
		Speedelec speedelec = new Speedelec();
		speedelec.setBrand(arr[0]);
		speedelec.setMaxSpeed(Integer.parseInt(arr[1]));
		speedelec.setWeight(Integer.parseInt(arr[2]));
		speedelec.setLights(Boolean.parseBoolean(arr[3]));
		speedelec.setBatteryCapacity(Integer.parseInt(arr[4]));
		speedelec.setColor(arr[5]);
		speedelec.setPrice(Integer.parseInt(arr[6]));
		return speedelec;
	}
	
	private FoldingBike toFoldingBike(String line) {
		String[] arr = line.split("; ");
		FoldingBike foldingBike = new FoldingBike();
		foldingBike.setBrand(arr[0]);
		foldingBike.setWheelsSize(Integer.parseInt(arr[1]));
		foldingBike.setGear(Integer.parseInt(arr[2]));
		foldingBike.setWeight(Integer.parseInt(arr[3]));
		foldingBike.setLights(Boolean.parseBoolean(arr[4]));
		foldingBike.setColor(arr[5]);
		foldingBike.setPrice(Integer.parseInt(arr[6]));
		return foldingBike;
	}
}
