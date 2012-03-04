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
package com.trafficspaces.api.controller;

import com.trafficspaces.api.model.Flags;
import com.trafficspaces.api.model.Placement;

import java.io.IOException;

import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlacementConnector extends Connector {

	public PlacementConnector(EndPoint endPoint, String resourcePath, String resourceClassName) 
			throws TrafficspacesAPIException {
		super(endPoint, resourcePath, resourceClassName);
	}

	/****************************************************
	 ***********       CRUD FUNCTIONS       *************
	 ****************************************************/

	public List find(Placement[] placements, Flags flags) throws IOException, TrafficspacesAPIException {
		return find(placements, flags, null, null, null, true);
	}
	
	public List find(Placement[] placements, Flags flags, String medium, String frame, String title, boolean useIframe) throws IOException, TrafficspacesAPIException {

		Properties params = new Properties();
		params.put("request", getRequestJSONObject(placements, flags, medium, frame, title, useIframe).toString());
		return find(params);
	}

	private JSONObject getRequestJSONObject(Placement[] placements, Flags flags, String medium, String frame, String title, boolean useIframe) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (placements != null && placements.length > 0) {
				JSONArray placementsJSONArray = new JSONArray();
				for (int i = 0; i < placements.length; i++) {
					placementsJSONArray.put(placements[i].getJSONObject());
				}
				jsonObject.put("placements", placementsJSONArray);
			}
			if (flags != null) {
				jsonObject.put("flags", flags.getJSONObject());
			}
			if (medium != null) {
				jsonObject.put("medium", medium);
			}
			if (frame != null) {
				jsonObject.put("frame", frame);
			}
			if (title != null) {
				jsonObject.put("title", title);
			}
			jsonObject.put("useiframe", useIframe);
		} catch (Exception e) {
			
		}
		return jsonObject;
	}

}
