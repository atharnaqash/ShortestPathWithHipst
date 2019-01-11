package com.hipst.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.algorithm.Algorithm.SearchResult;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.impl.WeightedNode;
import es.usc.citius.hipster.model.problem.SearchProblem;

public class Helper {

	  ArrayList<HashMap<String, Object>> getPathFromSourceToDistination(HipsterDirectedGraph<String, Double> graph,
			ArrayList<HashMap<String, String>> drones, String customerDistination) {
			
		ArrayList<HashMap<String, Object>> paths = new ArrayList<HashMap<String, Object>>();
		
		
		//this task could be parallelized later.
		for (HashMap<String, String> drone : drones) {
			SearchProblem p = GraphSearchProblem.startingFrom(drone.get(Constants.ADDRESS)).in(graph).takeCostsFromEdges().build();
					
			SearchResult hipster = Hipster.createDijkstra(p).search(customerDistination.trim());

			WeightedNode goalNode = (WeightedNode) hipster.getGoalNode();
			double distance = Double.parseDouble(goalNode.getScore().toString());

			double droneSpeed = Double.parseDouble(drone.get(Constants.SPEED));
			HashMap<String, Object> path = getShortestPathForCurrentDrone(hipster, distance, droneSpeed);
			paths.add(path);
		}
		
		return paths;
	}

	
	  HashMap<String, Object> getFastestPath(ArrayList<HashMap<String, Object>> paths) {
		 
		 HashMap<String, Object> previousMap = null;
		 for(HashMap<String,Object> path : paths)
		 {
			 if(previousMap == null) {
				 previousMap = path;
			 }
			 else {
				 
				 double prevTimeInSec = (Double) previousMap.get(Constants.TIME_IN_SEC);
				 double currTimeInSecond = (Double) path.get(Constants.TIME_IN_SEC);
				 if(currTimeInSecond<prevTimeInSec) {
					 previousMap = path;
				 }
				 
			 }
		 }
		 
		 return previousMap;
	}

	  HashMap<String, Object> getShortestPathForCurrentDrone(SearchResult hipster, double distance, double droneSpeed) {

		List distination = (List) hipster.getOptimalPaths().get(0);
		String droneLoc = distination.get(0).toString();
		String storeLoc = distination.get(1).toString();
		String customerLoc = distination.get(2).toString();

		double timeInSec = distance * Constants.SEC_IN_HOUR / droneSpeed;
		double timeInMin = timeInSec / Constants.MIN_IN_HOUR;

		HashMap<String, Object> path = new HashMap<String, Object>();
		path.put(Constants.DRONE_LOC, droneLoc);

		path.put(Constants.STORE_LOC, storeLoc);
		path.put(Constants.CUSTOMER_LOC, customerLoc);
		path.put(Constants.DISTANCE, distance);
		path.put(Constants.TIME_IN_SEC, timeInSec);
		path.put(Constants.TIME_IN_MIN, timeInMin);

		return path;

	}

	  

	 void createGraphOfDronesAndStores(GraphBuilder<String, Double> graphBuilder,
			ArrayList<HashMap<String, String>> drones, ArrayList<HashMap<String, String>> stores) {

		for (HashMap<String, String> drone : drones) {

			String droneAddress = drone.get(Constants.ADDRESS);
			double droneLat = Double.parseDouble(drone.get(Constants.LAT));
			double droneLong = Double.parseDouble(drone.get(Constants.LONG));
			double droneSpeed = Double.parseDouble(drone.get(Constants.SPEED));

			for (HashMap<String, String> store : stores) {

				String storeAddress = store.get(Constants.ADDRESS);
				double storeLat = Double.parseDouble(store.get(Constants.LAT));
				double storeLong = Double.parseDouble(store.get(Constants.LONG));
				double edgeDistance = distance(droneLat, storeLat, droneLong, storeLong, 0, 0);
				graphBuilder.connect(droneAddress).to(storeAddress).withEdge(edgeDistance);
				//System.out.println(droneAddress + ","+storeAddress+","+edgeDistance);
				
			}

		}

	}

	 void createGraphOfStoresAndCustomers(GraphBuilder<String, Double> graphBuilder,
				ArrayList<HashMap<String, String>> stores, ArrayList<HashMap<String, String>> customers) {

			for (HashMap<String, String> store : stores) {

				String storeAddress = store.get(Constants.ADDRESS);
				double storeLat = Double.parseDouble(store.get(Constants.LAT));
				double storeLong = Double.parseDouble(store.get(Constants.LONG));

				for (HashMap<String, String> customer : customers) {

					String customerAddress = customer.get(Constants.ADDRESS);
					double customerLat = Double.parseDouble(customer.get(Constants.LAT));
					double customerLong = Double.parseDouble(customer.get(Constants.LONG));
					double edgeDistance = distance(storeLat, customerLat, storeLong, customerLong, 0, 0);
					graphBuilder.connect(storeAddress).to(customerAddress).withEdge(edgeDistance);

				}
			}

		}
	 
	 
	 double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

		final int R = 6371; // Radius of the earth
		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		// double distance = R * c * 1000; // convert to meters
		double distance = R * c; //

		double height = el1 - el2;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);
	}
	
}
