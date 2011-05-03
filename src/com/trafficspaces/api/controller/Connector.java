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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Connector {

	private EndPoint endPoint;
	private String resourcePath;
	private Constructor resourceConstructor;
	
	public Connector(EndPoint endPoint, String resourcePath, String resourceClassName) {
		this.endPoint		= endPoint;
		this.resourcePath	= resourcePath;
		this.resourceConstructor = Class.forName(resourceClassName).getConstructor(new Class[] {JSONObject.class});
	}

	/****************************************************
	 ***********       CRUD FUNCTIONS       *************
	 ****************************************************/

	public List list(Properties params) throws IOException, TrafficspacesAPIException {
	    String jsonStr = sendRequest(resourcePath + "?" + toQueryString(params), "JSON");
  		JSONArray jsonArray = new JSONArray(jsonStr);
	    
  		Object[] args = new Object[1];
	    ArrayList resourceList = new ArrayList();
	    for (int i = 0; i < all_resources.length(); i++) {
	    	args[0] = jsonArray.getJSONObject(i);
	    	resourceList.add(resourceConstructor.newInstance(args));
	    }
	    return resourceList;
	}

	public Resource find(String id) throws IOException, TrafficspacesAPIException {
		String jsonStr = sendRequest(resourcePath + "/" + id + ".json", "JSON");
		return resourceConstructor.newInstance(new Object[] {resourceConstructor.newInstance(new JSONObject(jsonStr))});
	}

	public Resource create(Resource resource) throws IOException, TrafficspacesAPIException {
		String jsonStr = sendRequest(resourcePath, "JSON", "POST", resource.getJSON());
		return resourceConstructor.newInstance(new Object[] {resourceConstructor.newInstance(new JSONObject(jsonStr))});
	}

	public Resource update(Resource resource) throws IOException, TrafficspacesAPIException {
		String jsonStr = sendRequest(resourcePath + "/" + resource.id + ".json", "JSON", "PUT", resource->getJSON());
		return resourceConstructor.newInstance(new Object[] {resourceConstructor.newInstance(new JSONObject(jsonStr))});
	}
	
	public boolean delete(String id) throws IOException, TrafficspacesAPIException {
		sendRequest(resourcePath + "/" + id + ".json", "JSON", "DELETE");
		return true;
	}

	/****************************************************
	 **********       UTILITY FUNCTIONS       ***********
	 ****************************************************/
	
	protected String sendRequest(String path, String format, String method, String data)
	 		throws IOException, TrafficspacesAPIException {
		
		URL url = new URL(endPoint.baseURI + path);
		
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod(method.toUpperCase());
		
		String basicAuth = "Basic " + Base64.encode((endPoint.username + ":" + endPoint.password).getBytes());
		httpCon.setRequestProperty ("Authorization", basicAuth);
		
		String contentType = format.equalsIgnoreCase("JSON") ? "application/json" : "application/xml";
		httpCon.setRequestProperty("Content-Type", contentType + "; charset=UTF-8");
		httpCon.setRequestProperty("Accept", contentType);
		
		if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
			httpCon.setRequestProperty("Content-Length", data.length()); 
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			out.write(data);
			out.close();
		} else {
			httpCon.connect();
		}
		
		String responseData = readResponseData(httpCon.getInputStream());
		
		int responseCode = httpCon.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_CREATED) {
			throw new TrafficspacesAPIException(responseCode, responseData);
		}
		return responseData;
	}
	
	private char[] readResponseData(InputStream stream, String encoding) {
        BufferedReader in = null;
        char[] data = null;
    	try {
    		StringBuffer buf = new StringBuffer();
    		data = new char[1024];

            in = new BufferedReader(new InputStreamReader(stream, encoding));
            int charsRead;
            while ((charsRead = in.read(data)) != -1) {
            	buf.append(data, 0, charsRead);
            }
            data = new char[buf.length()];
            buf.getChars(0, data.length, data, 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data != null ? new String(data) : null;
    }
	    		
	private String toQueryString(Properties params) {
		StringBuffer queryString = new StringBuffer();
		
		Iterator itr = params.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry entry = (Map.Entry) itr.next();
			queryString.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
			queryString.append("=");
			queryString.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
			queryString.append("&");
		}
		return queryString.toString();
	}	
}
