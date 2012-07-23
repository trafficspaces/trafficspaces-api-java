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
 ** Reference Documentation: http://support.trafficspaces.com/kb/api/api-campaigns
 **/
package com.trafficspaces.api.model;

import org.json.JSONObject;

public class Campaign extends Resource {
	//******************************
	//** INPUT & OUTPUT VARIABLES **
	//******************************
	public String name;
	public LinkedResource[] linked_ads;
	public LinkedResource linked_user;
	public LinkedResource linked_contact;
	
	//******************************
	//*** OUTPUT ONLY VARIABLES ****
	//******************************
	public String realm;
	public String creation_date;
	public String last_modified_date;

	public Campaign() {}
	
	public Campaign(JSONObject jsonObject) { super(jsonObject); }
	
	public static Campaign createCampaign(String name, LinkedResource[] linked_ads) {
		return Campaign.createCampaign(name, linked_ads, null);
	}
	
	public static Campaign createCampaign(String name, LinkedResource[] linked_ads, LinkedResource linked_contact) {
		Campaign campaign = new Campaign();
		campaign.name = name;
		campaign.linked_ads = linked_ads;
		campaign.linked_contact = linked_contact;
		return campaign;
	}
	
	public String getName() {
  		return "campaign";
  	}
}
