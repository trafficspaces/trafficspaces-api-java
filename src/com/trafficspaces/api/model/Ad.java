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

import com.trafficspaces.api.model.Zone.Pricing;

public class Ad extends Resource {
	//******************************
	//** INPUT & OUTPUT VARIABLES **
	//******************************
	public String name;
	public int width;
	public int height;
	public String status;
	public String format;
	public Creative creative;
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

	public static Ad createAd(String name, int width, int height, String format, Creative creative) {
		return Ad.createAd(name, width, height, format, creative, null, null, null);
	}
	
	public static Ad createAd(String name, int width, int height, String format, Creative creative, 
			LinkedResource linked_user, LinkedResource linked_contact, LinkedResource linked_targeting_plan) {
		Ad ad = new Ad();
		ad.name = name;
		ad.width = width;
		ad.height = height;
		ad.format = format;
		ad.creative = creative;
		ad.linked_user = linked_user;
		ad.linked_contact = linked_contact;
		ad.linked_targeting_plan = linked_targeting_plan;
		return ad;
	}

	public String getName() {
  		return "ad";
  	}
	
	public static class Creative extends Resource {
		//******************************
		//** INPUT & OUTPUT VARIABLES **
		//******************************
		public String flash_url;
		public String image_url;
		public String audio_url;
		public String video_url;
		public String title;
		public String caption;
		public String anchor;
		public String raw;
		public String target_url;
		
		public Creative() {}
		
		public Creative(JSONObject jsonObject) { super(jsonObject); }

		public String getName() {
	  		return "creative";
	  	}

		public static Creative createTextCreative(String title, String caption, String anchor, String image_url, String target_url) {
			Creative creative = new Creative();
			creative.title = title;
			creative.caption = caption;
			creative.anchor = anchor;
			creative.image_url = image_url;
			creative.target_url = target_url;
			return creative;
		}

		public static Creative createImageCreative(String image_url, String target_url) {
			Creative creative = new Creative();
			creative.image_url = image_url;
			creative.target_url = target_url;
			return creative;
		}
		
		public static Creative createFlashCreative(String flash_url, String target_url) {
			Creative creative = new Creative();
			creative.flash_url = flash_url;
			creative.target_url = target_url;
			return creative;
		}
		
		public static Creative createAudioCreative(String audio_url, String target_url) {
			Creative creative = new Creative();
			creative.audio_url = audio_url;
			creative.target_url = target_url;
			return creative;
		}

		public static Creative createVideoCreative(String video_url, String target_url) {
			Creative creative = new Creative();
			creative.video_url = video_url;
			creative.target_url = target_url;
			return creative;
		}

		public static Creative createRawCreative(String raw, String target_url) {
			Creative creative = new Creative();
			creative.raw = raw;
			creative.target_url = target_url;
			return creative;
		}
	}
}