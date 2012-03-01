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
 ** Reference Documentation: http://support.trafficspaces.com/kb/api/api-ads
 **/
package com.trafficspaces.api.model;

import org.json.JSONObject;

public class Ad extends Resource {
	//******************************
	//** INPUT & OUTPUT VARIABLES **
	//******************************
	public String name;
	public int width;
	public int height;
	public String status;
	public String format;
	public String creative;
	public LinkedResource linked_user;
	public LinkedResource linked_contact;
	public LinkedResource linked_targeting_plan;

	//******************************
	//*** OUTPUT ONLY VARIABLES ****
	//******************************
	public String realm;
	public String creation_date;
	public String last_modified_date;

	public Ad() {}
	
	public Ad(JSONObject jsonObject) { super(jsonObject); }

	public String getName() {
  		return "ad";
  	}
	
	static class Creative extends Resource {
		//******************************
		//** INPUT & OUTPUT VARIABLES **
		//******************************
		public String flash_url;
		public String video_url;
		public String audio_url;
		public String image_url;
		public String title;
		public String caption;
		public String anchor;
		public String target_url;
		
		public Creative() {}
		
		public Creative(JSONObject jsonObject) { super(jsonObject); }

		public String getName() {
	  		return "creative";
	  	}
	}
}