package com.hipst.project;

import java.util.ArrayList;
import java.util.HashMap;

import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;

public class Main {

	public static void main(String[] args) {

		String customerDistination = args[0].trim();
		System.out.println("Current Delivery: "+ customerDistination);
		
		Helper helper = new Helper();
		GraphBuilder<String, Double> graphBuilder = GraphBuilder.<String, Double>create();

		DataLoader data = new DataLoader();
		ArrayList<HashMap<String, String>> drones = data.getDrones();
		ArrayList<HashMap<String, String>> stores = data.getStores();
		ArrayList<HashMap<String, String>> customers = data.getCustomers();

		helper.createGraphOfDronesAndStores(graphBuilder, drones, stores);
		helper.createGraphOfStoresAndCustomers(graphBuilder, stores, customers);

		HipsterDirectedGraph<String, Double> graph = graphBuilder.createDirectedGraph();

		ArrayList<HashMap<String, Object>> shortestPaths = helper.getPathFromSourceToDistination(graph,drones,customerDistination);
		
		HashMap<String, Object> fastestPath =  helper.getFastestPath(shortestPaths);

		showResults(fastestPath);
		
		
	}

	private static void showResults(HashMap<String, Object> fastestPath) {
		
		System.out.println("--------------------------------------------------");
		System.out.println("Drone Loc:	"+fastestPath.get(Constants.DRONE_LOC));
		System.out.println("Store Loc:	"+fastestPath.get(Constants.STORE_LOC));
		System.out.println("Customer Loc:	"+fastestPath.get(Constants.CUSTOMER_LOC));
		System.out.println("Distance:	"+fastestPath.get(Constants.DISTANCE));
		System.out.println("Time (sec):	"+fastestPath.get(Constants.TIME_IN_SEC));
		System.out.println("Time (Min):	"+fastestPath.get(Constants.TIME_IN_MIN));
	}

	

}
