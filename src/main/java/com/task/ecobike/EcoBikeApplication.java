package com.task.ecobike;

import com.task.ecobike.contoller.EcoBikeController;
import com.task.ecobike.dao.EcoBikeDao;
import com.task.ecobike.service.EcoBikeService;
import com.task.ecobike.service.TranslationService;
import com.task.ecobike.view.ConsoleView;


public class EcoBikeApplication {
	
	public static void main(String[] args) {
		EcoBikeController controller = new EcoBikeController(
				new EcoBikeService(new TranslationService(), new EcoBikeDao()), new ConsoleView());
		controller.startApplication();
	}
}