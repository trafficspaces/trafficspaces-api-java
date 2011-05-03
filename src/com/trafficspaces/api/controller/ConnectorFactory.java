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

public class ConnectorFactory {
	private EndPoint adStoreEndPoint;
	private EndPoint adServerEndPoint;
	
	public ConnectorFactory(String subDomain) {
		this(subDomain, null, null);
	}
	public ConnectorFactory(String subDomain, String username, String password) {
		this.adStoreEndPoint  = new EndPoint("https://" + subDomain + ".trafficspaces.com", username, password);
		this.adServerEndPoint = new EndPoint("http://ads.trafficspaces.net");
	}
		
	public Connector getUserConnector() {
		return new Connector(adStoreEndPoint, "/resources/users", User.class.getName());
	}
	
	public Connector getContactConnector() {
		return new Connector(adStoreEndPoint, "/resources/contacts", Contact.class.getName());
	}
	
	public Connector getZoneConnector() {
		return new Connector(adStoreEndPoint, "/resources/zones", Zone.class.getName());
	}
	
	public Connector getZoneStatisticsConnector() {
		return new Connector(adStoreEndPoint, "/resources/zones/statistics", ZoneStatistic.class.getName());
	}
	
	public Connector getAdConnector() {
		return new Connector(adStoreEndPoint, "/resources/ads", Ad.class.getName());
	}
	
	public Connector getCampaignConnector() {
		return new Connector(adStoreEndPoint, "/resources/campaigns", Campaign.class.getName());
	}
	
	public Connector getTargetingPlanConnector() {
		return new Connector(adStoreEndPoint, "/resources/targetingplans", TargetingPlan.class.getName());
	}
	
	public Connector getFeedConnector() {
		return new Connector(adStoreEndPoint, "/resources/feeds", Feed.class.getName());
	}
	
	public Connector getOrderConnector() {
		return new Connector(adStoreEndPoint, "/resources/orders", Order.class.getName());
	}
	
	public Connector getOrderStatisticsConnector() {
		return new Connector(adStoreEndPoint, "/resources/orders/statistics", OrderStatistic.class.getName());
	}
	
	public Connector getCouponConnector() {
		return new Connector(adStoreEndPoint, "/resources/coupons", Coupon.class.getName());
	}
	
	public Connector getPlacementConnector() {
		return new Connector(adServerEndPoint, "/resources/placements", Placement.class.getName());
	}
}
