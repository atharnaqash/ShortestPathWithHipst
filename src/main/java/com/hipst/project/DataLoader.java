package com.hipst.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataLoader {

	List<HashMap<String,String>> drones;
	List<HashMap<String,String>> stores;
	List<HashMap<String,String>> customers;
	
	//this could be later loaded from a json or some database.
	public DataLoader(){

		//DRONES LOCATIONS
		this.drones = new ArrayList<HashMap<String,String>>();	
		HashMap<String,String> drone = new HashMap<String, String>();
		drone.put(Constants.ADDRESS, "Metrostrasse 12, 40235 Düsseldorf");
		drone.put(Constants.LAT,"51.2346719");
		drone.put(Constants.LONG, "6.825635899999952");
		drone.put(Constants.SPEED, "60");
		drones.add(drone);
		
		drone = new HashMap<String, String>();
		drone.put(Constants.ADDRESS, "Am Albertussee 1, 40549 Düsseldorf, Germany");
		drone.put(Constants.LAT,"51.23648499999999");
		drone.put(Constants.LONG, "6.7239462");
		drone.put(Constants.SPEED, "60");
		drones.add(drone);
		
		//STORES LOCATIONS
		this.stores = new ArrayList<HashMap<String,String>>();	
		HashMap<String,String> store = new HashMap<String, String>();
		store.put(Constants.ADDRESS, "Schiessstraße 31, 40549 Düsseldorf");
		store.put(Constants.LAT,"51.2374784");
		store.put(Constants.LONG, "6.71953110000004");
		stores.add(store);
		store = new HashMap<String, String>();
		store.put(Constants.ADDRESS, "Friedrichstraße 152, 40217 Düsseldorf");
		store.put(Constants.LAT,"51.2092038");
		store.put(Constants.LONG, "6.778161599999976");
		stores.add(store);
		store = new HashMap<String, String>();
		store.put(Constants.ADDRESS, "Breslauer Str. 2, 41460 Neuss");
		store.put(Constants.LAT,"51.2019935");
		store.put(Constants.LONG, "6.718629400000054");
		stores.add(store);
		store = new HashMap<String, String>();
		store.put(Constants.ADDRESS, "Bataverstraße 93, 41462 Neuss");
		store.put(Constants.LAT,"51.2312339");
		store.put(Constants.LONG, "6.685699200000045");
		stores.add(store);
		store = new HashMap<String, String>();
		store.put(Constants.ADDRESS, "Am Sandbach 30, 40878 Ratingen");
		store.put(Constants.LAT,"51.2971708");
		store.put(Constants.LONG, "6.831175799999983");
		stores.add(store);
		
		//CUSTOMERS LOCATIONS
		customers = new ArrayList<HashMap<String,String>>();	
		HashMap<String,String> customer = new HashMap<String, String>();
		customer.put(Constants.ADDRESS, "Kronprinzenstraße 88, 40217 Düsseldorf");
		customer.put(Constants.LAT,"51.21171649999999");
		customer.put(Constants.LONG, "6.770084199999928");
		customers.add(customer);
		customer = new HashMap<String, String>();
		customer.put(Constants.ADDRESS, "Kaiserstraße 2, 40479 Düsseldorf");
		customer.put(Constants.LAT,"51.23516");
		customer.put(Constants.LONG, "6.778400000000033");
		customers.add(customer);
		customer = new HashMap<String, String>();
		customer.put(Constants.ADDRESS, "Wildenbruchstraße 2, 40545 Düsseldorf");
		customer.put(Constants.LAT,"51.22759989999999");
		customer.put(Constants.LONG, "6.75993840000001");
		customers.add(customer);
		customer = new HashMap<String, String>();
		customer.put(Constants.ADDRESS, "Schlesische Straße 5, 40231 Düsseldorf");
		customer.put(Constants.LAT,"51.2080472");
		customer.put(Constants.LONG, "6.8311332000000675");
		customers.add(customer);
		
	}

	public List<HashMap<String, String>> getDrones() {
		return drones;
	}

	public List<HashMap<String, String>> getStores() {
		return stores;
	}

	public List<HashMap<String, String>> getCustomers() {
		return customers;
	}
	
	
	
}
