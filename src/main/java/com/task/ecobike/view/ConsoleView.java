package com.task.ecobike.view;

import com.task.ecobike.contoller.EcoBikeController;
import com.task.ecobike.domain.AbstractTransport;
import com.task.ecobike.domain.nonpoweredtransport.FoldingBike;
import com.task.ecobike.domain.poweredtransport.EBike;
import com.task.ecobike.domain.poweredtransport.Speedelec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ConsoleView {
	private EcoBikeController controller;

	public void printResult(String resultString) {
		System.out.println(resultString);
	}
}
