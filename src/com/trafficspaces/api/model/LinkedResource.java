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
 ** Reference Documentation: http://support.trafficspaces.com/kb/api/api-introduction
 **/
package com.trafficspaces.api.model;

import org.json.JSONObject;

public class LinkedResource extends Resource {

	//******************************
	//****** OUTPUT VARIABLES ******
	//******************************
	public String name;

	//******************************
	//****** PRIVATE VARIABLES *****
	//******************************
	private String resourceName;
	
	public LinkedResource() {}
	
	public LinkedResource(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public LinkedResource(String id, String name) {
		super.id = id;
		this.name = name;
	}
	
	public LinkedResource(JSONObject jsonObject, String resourceName) { 
		super(jsonObject);
		this.resourceName = resourceName;
	}
	
	public String getName() {
  		return resourceName;
  	}
}