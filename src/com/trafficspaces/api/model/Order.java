/**
 ** Copyright (c) 2011  Inc.
 ** 
 ** Permission is hereby granted, free of charge, to any person obtaining a copy
 ** of this software and associated documentation files (the "Software"), to deal
 ** in the Software without restriction, including without limitation the rights
 ** to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 ** copies of the Software, and to permit persons to whom the Software is
 ** furnished to do so, subject to the following conditions:
 ** 
 ** The above copyright notice and this permission notice shall be included in
 ** all copies or substantial portions of the Software.
 ** 
 ** THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 ** IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 ** FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 ** AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 ** LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 ** OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 ** THE SOFTWARE.
 ** 
 ** Reference Documentation: http://support.trafficspaces.com/kb/api/api-orders
 **/
package com.trafficspaces.api.model;

import org.json.JSONObject;

public class Order extends Resource {
	//******************************
	//** INPUT & OUTPUT VARIABLES **
	//******************************
	public String priority;
	public double price;
	public double maximum_bid_price;
	public int total_volume;
	public int daily_volume;	
	public String start_date;
	public String end_date;
	public LinkedResource linked_user;
	public LinkedResource linked_zone;
	public LinkedResource linked_campaign;
	public Statistic order_statistic;

	//******************************
	//*** OUTPUT ONLY VARIABLES ****
	//******************************
	public String name;
	public String model;
	public String status;
	public int filled_volume;
	public String realm;
	public String last_modified_date;

	public Order() {}
	
	public Order(JSONObject jsonObject) { super(jsonObject); }
	
	public static Order createOrder(double price, int total_volume, int daily_volume, String start_date, String end_date, 
			LinkedResource linked_zone, LinkedResource linked_campaign) {
		Order order = new Order();
		order.price = price;
		order.total_volume = total_volume;
		order.daily_volume = daily_volume;
		order.start_date = start_date;
		order.end_date = end_date;
		order.linked_zone = linked_zone;
		order.linked_campaign = linked_campaign;
		return order;
	}
	
	public String getName() {
  		return "order";
  	}

	public static class Statistic extends Resource {
		//******************************
		//**** OUTPUT ONLY VARIABLES ***
		//******************************
		public double hits;
		public double uniques;
		public double clicks;
		public double conversions;
		public double views;
		public double duration;
		public double average_conversion_amount;
		public double days;
		public String date;
		public LinkedResource linked_order;

		public Statistic() {}
		
		public Statistic(JSONObject jsonObject) { super(jsonObject); }
		
		public String getName() {
	  		return "order_statistic";
	  	}
	}
}