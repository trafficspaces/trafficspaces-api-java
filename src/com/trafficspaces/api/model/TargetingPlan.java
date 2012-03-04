/**
 ** Copyright (c) 2011 Trafficspaces Inc.
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
 ** Reference Documentation: http://support.trafficspaces.com/kb/api/api-targeting-plans
 **/
package com.trafficspaces.api.model;

import org.json.JSONObject;

public class TargetingPlan extends Resource {
	//******************************
	//** INPUT & OUTPUT VARIABLES **
	//******************************
	public String name;
	public Targets targets;
	public LinkedResource linked_user;

	//******************************
	//*** OUTPUT ONLY VARIABLES ****
	//******************************
	public String realm;
	public String creation_date;
	public String last_modified_date;

	public TargetingPlan() {}
	
	public TargetingPlan(JSONObject jsonObject) { super(jsonObject); }
	
	public TargetingPlan(String name, Targets targets) {
		this.name = name;
		this.targets = targets;
	}

	public String getName() {
  		return "targeting_plan";
  	}

	public static class Targets extends Resource {
		//******************************
		//** INPUT & OUTPUT VARIABLES **
		//******************************
		public String geographics;
		public String keywords;
		public String interests;
		public String coordinates;
		public String genders;
		public String ageranges;
		public String incomeranges;
		public String ethnicities;
		public String relationships;
		public String qualifications;
		public String jobs;
		public String industries;
		public String religions;
		public String politics;
		public String urls;
		public String ipaddresses;
		public String daysofweek;
		public String hoursofday;
		
		public Targets() {}
		
		public Targets(JSONObject jsonObject) { super(jsonObject); }
	
		public String getName() {
	  		return "targets";
	  	}
	}
}
