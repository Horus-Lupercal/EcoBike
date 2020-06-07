package com.task.ecobike.dao;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductDao {

	public List<String> readDataResources() {
		List<String> items = null;
		String dataFile = "ecobike.txt";
		Path path = null;

		try {

			path = Paths.get(getClass().getClassLoader().getResource("application.properties\\" + dataFile).toURI());
			items = fillList(path);

		} catch (URISyntaxException e1) {
			e1.printStackTrace();
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
