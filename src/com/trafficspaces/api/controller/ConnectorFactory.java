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

import com.trafficspaces.api.model.User;
import com.trafficspaces.api.model.Contact;
import com.trafficspaces.api.model.Zone;
import com.trafficspaces.api.model.Ad;
import com.trafficspaces.api.model.Campaign;
import com.trafficspaces.api.model.TargetingPlan;
import com.trafficspaces.api.model.Feed;
import com.trafficspaces.api.model.Order;
import com.trafficspaces.api.model.Coupon;
import com.trafficspaces.api.model.Placement;

public class ConnectorFactory {
	private EndPoint adStoreEndPoint;
	private EndPoint adServerEndPoint;
	
	public ConnectorFactory(String subDomain, String apiKey) {
		this.adStoreEndPoint  = new EndPoint("https://" + subDomain + ".trafficspaces.com", subDomain, apiKey);
		this.adServerEndPoint = new EndPoint("http://ads.trafficspaces.net");
	}
		
	public Connector getUserConnector() throws TrafficspacesAPIException {
		return new Connector(adStoreEndPoint, "/resources/users", User.class.getName());
	}
	
	public Connector getContactConnector() throws TrafficspacesAPIException {
		return new Connector(adStoreEndPoint, "/resources/contacts", Contact.class.getName());
	}
	
	public Connector getZoneConnector() throws TrafficspacesAPIException {
		return new Connector(adStoreEndPoint, "/resources/zones", Zone.class.getName());
	}
	
	public Connector getZoneStatisticsConnector() throws TrafficspacesAPIException {
		return new Connector(adStoreEndPoint, "/resources/zones/statistics", Zone.Statistic.class.getName());
	}
	
	public Connector getAdConnector() throws TrafficspacesAPIException {
		return new Connector(adStoreEndPoint, "/resources/ads", Ad.class.getName());
	}
	
	public Connector getCampaignConnector() throws TrafficspacesAPIException {
		return new Connector(adStoreEndPoint, "/resources/campaigns", Campaign.class.getName());
	}
	
	public Connector getTargetingPlanConnector() throws TrafficspacesAPIException {
		return new Connector(adStoreEndPoint, "/resources/targetingplans", TargetingPlan.class.getName());
	}
	
	public Connector getFeedConnector() throws TrafficspacesAPIException {
		return new Connector(adStoreEndPoint, "/resources/feeds", Feed.class.getName());
	}
	
	public Connector getOrderConnector() throws TrafficspacesAPIException {
		return new Connector(adStoreEndPoint, "/resources/orders", Order.class.getName());
	}
	
	public Connector getOrderStatisticsConnector() throws TrafficspacesAPIException {
		return new Connector(adStoreEndPoint, "/resources/orders/statistics", Order.Statistic.class.getName());
	}
	
	public Connector getCouponConnector() throws TrafficspacesAPIException {
		return new Connector(adStoreEndPoint, "/resources/coupons", Coupon.class.getName());
	}
	
	public Connector getPlacementConnector() throws TrafficspacesAPIException {
		return new PlacementConnector(adServerEndPoint, "/resources/placements.json", Placement.class.getName());
	}
}
