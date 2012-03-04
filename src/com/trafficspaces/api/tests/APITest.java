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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Unit tests for the Trafficspaces API
 * 
 * Usage: java APITest <realm> <apikey>
 * 
 * Alternatives
 * 1. Curl
 * 		curl -u test:d91936b7c8fa83dfe9330b3a144839a2e43e189f9ffd00f5d8d15d20ac0a2136 -H "accept: application/json" "https://test.trafficspaces.com/resources/zones?pagesize=1"
 * 2. Browser
 * 		https://test:d91936b7c8fa83dfe9330b3a144839a2e43e189f9ffd00f5d8d15d20ac0a2136@test.trafficspaces.com/resources/users"
 *
 */
public class APITest {	
	
	private static ConnectorFactory factory;
	
	private static Properties defaults;
	
	public static final DateFormat dateTimeFormat  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	public APITest(String subDomain, String apiKey) {
		factory = new ConnectorFactory(subDomain, apiKey);
		
		defaults = new Properties();
		defaults.put("page", "1");
		defaults.put("pagesize", "50");
	}

	public static void main(String[] args) throws IOException, TrafficspacesAPIException {

		if (args == null || args.length != 2) {
			System.out.println("Usage: java " + APITest.class.getName() +" <subdomain> <apikey>");
			System.exit(-1);
		}
		
		APITest apiTest = new APITest(args[0], args[1]);
		
		apiTest.testUserAPI();
		apiTest.testContactAPI(); if (true) return;
		apiTest.testZoneAPI();
		apiTest.testAdAPI();
		apiTest.testCampaignAPI();
		apiTest.testTargetingPlanAPI();
		apiTest.testFeedAPI();
		apiTest.testOrderAPI();
		apiTest.testCouponAPI();
		apiTest.testPlacementsAPI();
	}

	
	private void testUserAPI() throws IOException, TrafficspacesAPIException {

		System.out.println("--- Testing User API ---");
		
		Properties props = new Properties(defaults);
		
		// List
		List users = factory.getUserConnector().find(props);
		System.out.println("List: Found " + users.size() + " users");		
	}
	
	private void testContactAPI() throws IOException, TrafficspacesAPIException {

		System.out.println("--- Testing Contact API ---");
		
		Properties props = new Properties(defaults);
		Connector connector = factory.getContactConnector();
		
		// 1. List
		List contacts = connector.find(props);
		System.out.println("List: Found " + contacts.size() + " contacts");
		
		// 2. Create
		Contact contact = Contact.createContact("John Doe",
				new Contact.Profile("john@test.com", "Test Company", Contact.Profile.TYPE_ADVERTISER),
				null);
		contact = (Contact) connector.create(contact);
		System.out.println("Create: " + (contact != null ? "Succesful" : "Failed") + (contact != null ? ". The new ID is " + contact.id : ""));
		
		// 3. Update
		contact.name = "Jane Smith";
		contact.profile.email = "jane@test.com";
		contact.profile.company_name = "Test Ad Agency";
		contact.profile.contact_details.street = "1 Madison Avenue";
		contact.profile.contact_details.city = "New York";
		contact.profile.contact_details.state = "NY";
		contact.profile.contact_details.country = "us";
		Contact updatedContact = (Contact) connector.update(contact);
		System.out.println("Update: " + (updatedContact != null && updatedContact.id.equals(contact.id) && updatedContact.name.equals(contact.name) ? "Successful" : "Failed"));
		
		// 4. Delete
		if (!connector.delete(contact.id) || connector.read(contact.id) != null) {
			System.out.println("Delete: Failed");
		} else {
			System.out.println("Delete: Successful");
		}
	}
	
	private void testZoneAPI() throws IOException, TrafficspacesAPIException {
		
		System.out.println("--- Testing Zone API ---");
		
		Properties props = new Properties(defaults);
		Connector connector = factory.getZoneConnector();
		
		// 1. List 
		List zones = connector.find(props);
		System.out.println("List: Found " + zones.size() + " zones");
		
		// 2. Create
		Zone zone = Zone.createZone("Test Zone", 300, 250, "text,image,flash", new Zone.Pricing("cpm", 5.0));
		zone = (Zone) connector.create(zone);
		System.out.println("Create: " + (zone != null ? "Successful" : "Failed") + (zone != null ? ". The new ID is " + zone.id : ""));
		
		// 3. Update
		zone.name = "Test Zone 2";
		zone.formats = "text,image";
		zone.description = "Just another test zone";
		zone.default_ad_tag = "<!-- Insert Google Adsense Tag -->";
		zone.position = "anywhere";
		zone.channel = "blog";
		Zone updatedZone = (Zone) connector.update(zone);
		System.out.println("Update: " + (updatedZone != null && updatedZone.id.equals(zone.id) && updatedZone.name.equals(zone.name) ? "Successful" : "Failed"));
		
		// 4. Delete
		if (!connector.delete(zone.id) || connector.read(zone.id) != null) {
			System.out.println("Delete: Failed");
		} else {
			System.out.println("Delete: Successful");
		}
	}

	private void testAdAPI() throws IOException, TrafficspacesAPIException {
		
		System.out.println("--- Testing Ad API ---");
		
		Properties props = new Properties(defaults);
		Connector connector = factory.getAdConnector();
		
		// 1. List
		List ads = connector.find(props);
		System.out.println("List: Found " + ads.size() + " ads");

		// 2. Create
		Ad ad = Ad.createAd("Test Ad", 300, 250, "text",
				Ad.Creative.createTextCreative("My Ad Title", "My Ad Caption", "TestAd.com", null, "http://www.testad.com"));
		ad = (Ad) connector.create(ad);
		System.out.println("Create: " + (ad != null ? "Successful" : "Failed") + (ad != null ? ". The new ID is " + ad.id : ""));
		
		// 3. Update
		ad.name = "Test Ad 2";
		ad.status = "approved";
		ad.creative.title = "Another Ad Title";
		ad.creative.caption = "Yet another caption";
		ad.creative.target_url = "http://www.testads.com/landing_page/";
		Ad updatedAd = (Ad) connector.update(ad);
		System.out.println("Update: " + (updatedAd!= null && updatedAd.id.equals(ad.id) && updatedAd.name.equals(ad.name) ? "Successful" : "Failed"));
		
		// 4. Delete
		if (!connector.delete(ad.id) || connector.read(ad.id) != null) {
			System.out.println("Delete: Failed");
		} else {
			System.out.println("Delete: Successful");
		}
	}

	private void testCampaignAPI() throws IOException, TrafficspacesAPIException {
		
		System.out.println("--- Testing Campaign API ---");
		
		Properties props = new Properties(defaults);
		Connector connector = factory.getCampaignConnector();
		
		// 1. List
		List campaigns = connector.find(props);
		System.out.println("List: Found " + campaigns.size() + " campaigns");

		// 2. Create
		Campaign campaign = Campaign.createCampaign("Test Campaign", null);
		campaign = (Campaign) connector.create(campaign);
		System.out.println("Create: " + (campaign != null ? "Successful" : "Failed") + (campaign != null ? ". The new ID is " + campaign.id : ""));
		
		// 3. Update
		Ad ad = Ad.createAd("Test Ad", 300, 250, "text",
				Ad.Creative.createTextCreative("My Ad Title", "My Ad Caption", "TestAd.com", null, "http://www.testad.com"));
		ad = (Ad) factory.getAdConnector().create(ad);
		
		campaign.name = "Test Campaign 2";
		campaign.linked_ads = new LinkedResource[] { new LinkedResource(ad.id, ad.name) };
		Campaign updatedCampaign = (Campaign) connector.update(campaign);
		System.out.println("Update: " + (updatedCampaign != null && updatedCampaign.id.equals(campaign.id) && updatedCampaign.name.equals(campaign.name) &&
				updatedCampaign.linked_ads != null && updatedCampaign.linked_ads.length == 1 ? "Successful" : "Failed"));
		
		// 4. Delete
		factory.getAdConnector().delete(ad.id);
		if (!connector.delete(campaign.id) || connector.read(campaign.id) != null) {
			System.out.println("Delete: Failed");
		} else {
			System.out.println("Delete: Successful");
		}
	}

	private void testTargetingPlanAPI() throws IOException, TrafficspacesAPIException {
		
		System.out.println("--- Testing Targeting Plan API ---");
		
		Properties props = new Properties(defaults);
		Connector connector = factory.getTargetingPlanConnector();
		
		// 1. List
		List targetingPlans = connector.find(props);
		System.out.println("List: Found " + targetingPlans.size() + " targeting plans");

		// 2. Create
		TargetingPlan targetingPlan = new TargetingPlan("Test Targeting Plan", null);
		targetingPlan = (TargetingPlan) connector.create(targetingPlan);
		System.out.println("Create: " + (targetingPlan != null ? "Successful" : "Failed") + (targetingPlan != null ? ". The new ID is " + targetingPlan.id : ""));
		
		// 3. Update
		targetingPlan.name = "Test Targeting Plan 2";
		targetingPlan.targets = new TargetingPlan.Targets();
		targetingPlan.targets.geographics = "us,ca";
		targetingPlan.targets.keywords = "football,basketball,baseball,hockey";
		TargetingPlan updatedTargetingPlan = (TargetingPlan) connector.update(targetingPlan);
		System.out.println("Update: " + (updatedTargetingPlan != null && updatedTargetingPlan.id.equals(targetingPlan.id) && updatedTargetingPlan.name.equals(targetingPlan.name) &&
				updatedTargetingPlan.targets != null && 
				csv2Set(updatedTargetingPlan.targets.geographics).equals(csv2Set(targetingPlan.targets.geographics)) && 
				csv2Set(updatedTargetingPlan.targets.keywords).equals(csv2Set(targetingPlan.targets.keywords)) ? "Successful" : "Failed"));
		
		// 4. Delete
		if (!connector.delete(targetingPlan.id) || connector.read(targetingPlan.id) != null) {
			System.out.println("Delete: Failed");
		} else {
			System.out.println("Delete: Successful");
		}
	}

	private void testFeedAPI() throws IOException, TrafficspacesAPIException {
		
		System.out.println("--- Testing Feed API ---");
		
		Properties props = new Properties(defaults);
		Connector connector = factory.getFeedConnector();
		
		// 1. List
		List feeds = connector.find(props);
		System.out.println("List: Found " + feeds.size() + " feeds");

		// 2. Create
		Feed feed = Feed.createFeed("Test Feed", 728, 90, 100.0, "<!-- Google AdSense Backfill -->");
		feed = (Feed) connector.create(feed);
		System.out.println("Create: " + (feed != null ? "Successful" : "Failed") + (feed != null ? ". The new ID is " + feed.id : ""));
		
		// 3. Update
		feed.name = "Test Feed 2";
		feed.weight = 20.0;
		feed.ad_tag = "<!-- Another 3rd party Ad Tag-->";
		Feed updatedFeed = (Feed) connector.update(feed);
		System.out.println("Update: " + (updatedFeed != null && updatedFeed.id.equals(feed.id) && updatedFeed.name.equals(feed.name) &&
				updatedFeed.weight == feed.weight && updatedFeed.ad_tag.equals(feed.ad_tag) ? "Successful" : "Failed"));

		// 4. Delete
		if (!connector.delete(feed.id) || connector.read(feed.id) != null) {
			System.out.println("Delete: Failed");
		} else {
			System.out.println("Delete: Successful");
		}
	}

	private void testOrderAPI() throws IOException, TrafficspacesAPIException {
		
		System.out.println("--- Testing Order API ---");
		
		Properties props = new Properties(defaults);
		Connector connector = factory.getOrderConnector();
		
		// 1. List
		List orders = connector.find(props);
		System.out.println("List: Found " + orders.size() + " orders");

		// 2. Create
		Zone zone = Zone.createZone("Test Zone", 300, 250, "text", new Zone.Pricing("cpc", 0.5));
		zone = (Zone) factory.getZoneConnector().create(zone);
		
		Ad ad = Ad.createAd("Test Ad", 300, 250, "text", Ad.Creative.createTextCreative("My Ad Title", "My Ad Caption", "TestAd.com", null, "http://www.testad.com"));
		ad = (Ad) factory.getAdConnector().create(ad);
		
		Campaign campaign = Campaign.createCampaign("Test Campaign", null);
		campaign.linked_ads = new LinkedResource[] { new LinkedResource(ad.id, ad.name) };
		campaign = (Campaign) factory.getCampaignConnector().create(campaign);
				
		Order order = Order.createOrder(0.5, 100000, 1000, dateTimeFormat.format(new Date()), null, 
				new LinkedResource(zone.id, zone.name), new LinkedResource(campaign.id, campaign.name));
		order = (Order) connector.create(order);
		System.out.println("Create: " + (order != null ? "Successful" : "Failed") + (order != null ? ". The new ID is " + order.id : ""));

		// 3. Update
		order.maximum_bid_price = 0.75;
		order.daily_volume = 5000;
		Order updatedOrder = (Order) connector.update(order);
		System.out.println("Update: " + (updatedOrder != null && updatedOrder.id.equals(order.id) && updatedOrder.name.equals(order.name) &&
				updatedOrder.maximum_bid_price == order.maximum_bid_price && 
				updatedOrder.daily_volume == order.daily_volume ? "Successful" : "Failed"));
		
		// 4. Process
		connector.sendRequest("/resources/orders/process/?action=pause&orderid=" + order.id, "application/x-www-form-urlencoded", "POST", "");
		updatedOrder = (Order) connector.read(order.id);
		System.out.println("Process: " + (updatedOrder.id.equals(order.id) && updatedOrder.name.equals(order.name) &&
				updatedOrder.status != null && (updatedOrder.status.equals("pausing") || updatedOrder.status.equals("paused")) ? "Successful" : "Failed"));
		
		// 4. Delete
		factory.getAdConnector().delete(ad.id);
		factory.getCampaignConnector().delete(campaign.id);
		factory.getZoneConnector().delete(zone.id);
		if (!connector.delete(order.id) || connector.read(order.id) != null) {
			System.out.println("Delete: Failed");
		} else {
			System.out.println("Delete: Successful");
		}
	}

	private void testCouponAPI() throws IOException, TrafficspacesAPIException {
		
		System.out.println("--- Testing Coupon API ---");
		
		Properties props = new Properties(defaults);
		Connector connector = factory.getCouponConnector();
		
		// 1. List
		List coupons = connector.find(props);
		System.out.println("List: Found " + coupons.size() + " coupons");

		// 2. Create
		String couponCode = "HALFPRICE";
		// Remove the coupon if it already exists 
		Coupon coupon = readCouponByCode(connector, couponCode);
		if (coupon != null) {
			System.out.println("Coupon already exists. Deleting..");
			connector.delete(coupon.id);
		}
		coupon = Coupon.createRelativeCoupon("Test Coupon", "HALFPRICE", 0, 50.0);
		coupon = (Coupon) connector.create(coupon);
		System.out.println("Create: " + (coupon != null ? "Successful" : "Failed") + (coupon != null ? ". The new ID is " + coupon.id : ""));
		
		// 3. Update
		coupon.name = "Test Coupon 2";
		coupon.base_value = 100.0;
		coupon.maximum_cumulative_discount = 1000.0;
		coupon.maximum_cumulative_uses = 10;
		Coupon updatedCoupon = (Coupon) connector.update(coupon);
		System.out.println("Update: " + (updatedCoupon != null && updatedCoupon.id.equals(coupon.id) && updatedCoupon.name.equals(coupon.name) &&
				updatedCoupon.base_value == coupon.base_value && 
				updatedCoupon.maximum_cumulative_discount == coupon.maximum_cumulative_discount &&
				updatedCoupon.maximum_cumulative_uses == coupon.maximum_cumulative_uses ? "Successful" : "Failed"));
		
		// 4. Process
		double discount = 50;
		connector.sendRequest("/resources/coupons/process/?action=use&couponcode=" + coupon.code +"&discount="+discount, "application/x-www-form-urlencoded", "POST", "");
		updatedCoupon = (Coupon) connector.read(coupon.id);
		System.out.println("Process: " + (updatedCoupon.id.equals(coupon.id) && updatedCoupon.name.equals(coupon.name) &&
				updatedCoupon.cumulative_discount == (coupon.cumulative_discount + discount) &&
				updatedCoupon.cumulative_uses == (coupon.cumulative_uses + 1) ? "Successful" : "Failed"));
		
		// 5. Delete
		if (!connector.delete(coupon.id) || connector.read(coupon.id) != null) {
			System.out.println("Delete: Failed");
		} else {
			System.out.println("Delete: Successful");
		}
	}


	private void testPlacementsAPI() throws IOException, TrafficspacesAPIException {
		
		System.out.println("--- Testing Placements API ---");
		
		Properties props = new Properties(defaults);
		PlacementConnector connector = (PlacementConnector) factory.getPlacementConnector();
		
		// 1. List
		props.put("pagesize", "1");
		props.put("status", "playing");
		
		System.out.println("Fetching a live insertion order");
		List orders = factory.getOrderConnector().find(props);
		if (orders.isEmpty()) {
			System.out.println("There are no available insertion orders");
			return;
		}
		Order order = (Order) orders.get(0);		
		
		Zone zone = (Zone) factory.getZoneConnector().read(order.linked_zone.id);

		System.out.println("Fetching live ads");
		
		long startTime = System.currentTimeMillis();
		List placements = connector.find(new Placement[] { Placement.createPlacement(zone.handle) }, null);

		int adCount = 0;
		if (placements != null) {
			Iterator itr = placements.iterator();
			while (itr.hasNext()) {
				Placement placement = (Placement) itr.next();
				adCount += (placement.ads != null)  ? placement.ads.length : 0;
			}
		}
		System.out.println("Got ads in =" + (System.currentTimeMillis() - startTime) + " (msecs)");
		System.out.println("Found  "+ placements.size() + " placements");
		System.out.println("Found  "+ adCount+ " ads");
	}


	private Coupon readCouponByCode(Connector connector, String couponCode) throws IOException, TrafficspacesAPIException {
		Coupon coupon = null;
		try {
			Properties params = new Properties();
			params.put("couponcode", couponCode);
			List coupons = connector.find(params);
			if (coupons != null && coupons.size() == 1) {
				coupon = (Coupon) coupons.get(0);
			}
		} catch (Exception e) {
	    	e.printStackTrace();
	    }	
		return coupon;
	}


	private Set csv2Set(String csv) {
		HashSet set = new HashSet();
    	if (csv != null && (csv = csv.trim()).length() != 0) {
	        if (csv.indexOf(",") == -1) {
	            set.add(csv);
	        } else {
	            StringTokenizer tokenizer = new StringTokenizer(csv, ",");
	            while (tokenizer.hasMoreTokens()) {
	                String value = tokenizer.nextToken().trim();
	                if (value.length() != 0) {
	                	set.add(value);
	                }
	            }
	        }
    	}
    	return set;
	}
}