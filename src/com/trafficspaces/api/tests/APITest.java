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
package com.trafficspaces.api.tests;

import com.trafficspaces.api.model.*;
import com.trafficspaces.api.controller.*;

import java.util.List;
import java.util.Iterator;
import java.util.Properties;
import java.io.IOException;

/**
 * Unit tests for the Trafficspaces API
 * 
 * Usage: java APITest <realm> <username> <password>
 * 
 * Alternatives
 * 1. Curl
 * 		curl -u stbadvertising:e767e0c60e33b489a2fdcf8165705d317f3109173967ff13effd9c8527a6878a -H "accept: application/json" "https://stbadvertising.trafficspaces.com/resources/zones?pagesize=1"
 * 2. Browser
 * 		https://stbadvertising:e767e0c60e33b489a2fdcf8165705d317f3109173967ff13effd9c8527a6878a@stbadvertising.trafficspaces.com/resources/users"
 *
 */
public class APITest {	

	public static void main(String[] args) throws IOException, TrafficspacesAPIException {
		if (args == null || args.length != 2) {
			System.out.println("Usage: java " + APITest.class.getName() +" <subdomain> <apikey>");
			System.exit(-1);
		}
		ConnectorFactory factory = new ConnectorFactory(args[0], args[1]);
		
		Properties props = null;/*
		List users = factory.getUserConnector().find(props);
		System.out.println("Found " + users.size() + " users");
		Iterator itr = users.iterator();
		while (itr.hasNext()) {
			User user = (User) itr.next();
			System.out.println("id="+user.id+ ", name="+user.name + ", role="+user.role + 
					", first_name=" + user.profile.first_name + ", last_name=" + user.profile.last_name + 
					", email=" + user.profile.email + ", dob=" + user.profile.date_of_birth);
		}
		*/
		props = new java.util.Properties();
		props.put("page", "1");
		props.put("pagesize", "50");
		List zones = factory.getZoneConnector().find(props);
		System.out.println("Found " + zones.size() + " zones");
		
		//props.put("page", "2");
		//props.put("pagesize", "15");
		List ads = factory.getAdConnector().find(props);
		System.out.println("Found " + ads.size() + " ads");
	}
}