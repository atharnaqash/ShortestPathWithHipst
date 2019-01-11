package com.hipst.project;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {

		if (args.length > 0) {
			String customerDistination = args[0].trim();
			if (customerDistination.length() > 0) {
				System.out.println("Current Delivery: " + customerDistination);
				DeliveryHandler dHandler = new DeliveryHandler();
				HashMap<String, Object> fastestPath = dHandler.calculateShortestPath(customerDistination);
				dHandler.showResults(fastestPath);
			} else {
				System.out.println(
						"Got an empty command line argument, please provide the distination path e.g. \"Kaiserstraße 2, 40479 Düsseldorf\"");
			}
		} else {
			System.out.println(
					"No command line arguments found, please provide the distination path e.g. \"Kaiserstraße 2, 40479 Düsseldorf\"");
		}

	}

}
