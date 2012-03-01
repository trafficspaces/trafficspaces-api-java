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

public class Placement extends Resource {

	//******************************
	//** INPUT & OUTPUT VARIABLES **
	//******************************
	public String handle;
	
	//******************************
	//*** INPUT ONLY VARIABLES  ****
	//******************************
	public String medium;
	public int count;
	public boolean useiframe;
	public String frame;
	public String title;

	//******************************
	//*** OUTPUT ONLY VARIABLES ****
	//******************************
	public Ad[] ads;

	public Placement() {}
	
	public Placement(JSONObject jsonObject) { super(jsonObject); }

	public String getName() {
  		return "placement";
  	}

	static class Ad extends Resource {
	
		//******************************
		//*** OUTPUT ONLY VARIABLES ****
		//******************************
		public String medium;
		public int width;
		public int height;
		public Creative creative;
	
		public Ad() {}
		
		public Ad(JSONObject jsonObject) { super(jsonObject); }
	
		public String getName() {
	  		return "ad";
	  	}
		
		static class Creative extends Resource {
			//******************************
			//*** OUTPUT ONLY VARIABLES ****
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
}