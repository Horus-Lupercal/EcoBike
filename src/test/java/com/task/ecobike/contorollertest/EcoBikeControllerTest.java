package com.task.ecobike.contorollertest;

import com.task.ecobike.contoller.EcoBikeController;
import com.task.ecobike.dao.EcoBikeDao;
import com.task.ecobike.service.EcoBikeService;
import com.task.ecobike.service.TranslationService;
import com.task.ecobike.view.ConsoleView;
import org.junit.Test;

public class EcoBikeControllerTest {
    private EcoBikeController controller = new EcoBikeController(
            new EcoBikeService(new TranslationService(), new EcoBikeDao()), new ConsoleView());

    @Test
    public void testStartApplication() {
        controller.startApplication();
    }
}
