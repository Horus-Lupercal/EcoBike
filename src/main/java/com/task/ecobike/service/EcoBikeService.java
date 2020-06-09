package com.task.ecobike.service;

import com.task.ecobike.dao.EcoBikeDao;
import com.task.ecobike.domain.AbstractTransport;
import com.task.ecobike.domain.nonpoweredtransport.FoldingBike;
import com.task.ecobike.domain.poweredtransport.EBike;
import com.task.ecobike.domain.poweredtransport.Speedelec;
import com.task.ecobike.dto.Filter;
import com.task.ecobike.dto.ProductsDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EcoBikeService {
	private TranslationService translationService;
	private EcoBikeDao dao;
	private ProductsDTO dto;
	private Boolean isSavedToFile = true;

	public EcoBikeService(TranslationService translationService, EcoBikeDao dao) {
		this.translationService = translationService;
		this.dao = dao;
	}

	public boolean readData(String fileName) {
		ProductsDTO dto = new ProductsDTO();
		List<String> lines = dao.readDataResources(fileName);
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

	public <T> T save(T savedObject) {
		if (savedObject.getClass() == EBike.class) {
			dto.geteBikes().add((EBike) savedObject);

		} else if (savedObject.getClass() == Speedelec.class) {
			dto.getSpeedelec().add((Speedelec) savedObject);

		} else if (savedObject.getClass() == FoldingBike.class) {
			dto.getFoldingBikes().add((FoldingBike) savedObject);
		}
		System.out.println("Save was completed!");
		isSavedToFile = false;
		return savedObject;
	}

	public void saveToFile() {
		dao.saveToFile(isSavedToFile, dto);
		isSavedToFile = true;
	}

	public <T> T createObject(Map<String, String> parameters, Class<T> aClass) {
		return save(translationService.createObject(parameters,  aClass));
	}

	public List<AbstractTransport> findProduct(Filter filter, Boolean isMultithreading) {
		SearchingService searchingThread = new SearchingService(dto, filter);

		if (!isMultithreading) {
			return searchingThread.findProduct();
		}
		searchingThread.run();
		return searchingThread.getResult();
	}
}
