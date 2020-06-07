package com.task.ecobike;

import java.io.IOException;

import com.task.ecobike.contoller.ProductController;
import com.task.ecobike.dao.ProductDao;
import com.task.ecobike.service.ProductService;
import com.task.ecobike.view.ConsoleView;


public class RaceApplication {
	
	public static void main(String[] args) throws IOException {
		ProductController controller = new ProductController(new ProductService(new ProductDao()), new ConsoleView());
		controller.startApplication();
	}
}