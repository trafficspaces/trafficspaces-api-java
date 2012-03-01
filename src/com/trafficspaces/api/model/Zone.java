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
 ** Reference Documentation: http://support.trafficspaces.com/kb/api/api-introduction
 **/
package com.trafficspaces.api.model;

import org.json.JSONObject;

public class Zone extends Resource {
	//******************************
	//** INPUT & OUTPUT VARIABLES **
	//******************************
	public String name;
	public int width;
	public int height;
	public String formats;
	public String language;
	public String channel;
	public String position;
	public String scope;
	public String info_url;
	public String preview_url;
	public String default_ad_tag;
	public String description;
	public LinkedResource linked_user;
	public Pricing pricing;
	public Statistic zone_statistic;

	//******************************
	//*** OUTPUT ONLY VARIABLES ****
	//******************************
	public String realm;
	public String creation_date;
	public String last_modified_date;

	public Zone() {}
	
	public Zone(JSONObject jsonObject) { super(jsonObject); }

	public String getName() {
  		return "zone";
  	}

	static class Pricing extends Resource {
		//******************************
		//** INPUT & OUTPUT VARIABLES **
		//******************************
		public String model;
		public boolean accept_bids;
		public double price;
		public int volume_minimum;
		public int volume_maximum;
		public int volume_increment;
		public int order_concurrency;
		public Discount[] discounts;
	
		public Pricing() {}
		
		public Pricing(JSONObject jsonObject) { super(jsonObject); }

		public String getName() {
	  		return "pricing";
	  	}
	}
	
	static class Discount extends Resource {
		//******************************
		//** INPUT & OUTPUT VARIABLES **
		//******************************
		public double discount_rate;
		public int volume_minimum;
			
		public Discount() {}
		
		public Discount(JSONObject jsonObject) { super(jsonObject); }

		public String getName() {
	  		return "discount";
	  	}
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
		public double very_short_stay_uniques;
		public double short_stay_uniques;
		public double medium_stay_uniques;
		public double long_stay_uniques;
		public double very_long_stay_uniques;
		public String date;
		public LinkedResource linked_zone;
	
		public Statistic() {}
		
		public Statistic(JSONObject jsonObject) { super(jsonObject); }

		public String getName() {
	  		return "zone_statistic";
	  	}
	}
}