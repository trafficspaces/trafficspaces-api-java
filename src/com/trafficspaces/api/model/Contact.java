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
 ** Reference Documentation: http://support.trafficspaces.com/kb/api/api-contacts
 **/
package com.trafficspaces.api.model;

import org.json.JSONObject;

public class Contact extends Resource {
	//******************************
	//** INPUT & OUTPUT VARIABLES **
	//******************************
	public String name;
	public Profile profile;
	public LinkedResource linked_user;

	//******************************
	//*** OUTPUT ONLY VARIABLES ****
	//******************************
	public String realm;
	public String creation_date;
	public String last_modified_date;

	public Contact() {}
	
	public Contact(JSONObject jsonObject) { super(jsonObject); }
	
	public static Contact createContact(String name, Profile profile, LinkedResource linked_user) {
		Contact contact = new Contact();
		contact.name = name;
		contact.profile = profile;
		contact.linked_user = linked_user;
		return contact;
	}
	
	public String getName() {
  		return "contact";
  	}

	public static class Profile extends Resource {
		
		public static final int TYPE_ADVERTISER = 0;
		
		public static final int TYPE_PUBLISHER = 1;
		
		//******************************
		//** INPUT & OUTPUT VARIABLES **
		//******************************
		public String reference;
		public String company_name;
		public String website;
		public String email;
		public int type;
		public ContactDetails contact_details;
	
		public Profile() {}
		
		public Profile(JSONObject jsonObject) { super(jsonObject); }
	
		public Profile(String email, String company_name, int type) { 
			this.email = email;
			this.company_name = company_name;
			this.type = type;
		}
		
		public String getName() {
	  		return "profile";
	  	}
	
		public static class ContactDetails extends Resource {
			//******************************
			//** INPUT & OUTPUT VARIABLES **
			//******************************
			public String street;
			public String street2;
			public String city;
			public String state;
			public String zip;
			public String country;
			public String mobile;
			public String telephone;
			public String fax;
			
			public ContactDetails() {}
			
			public ContactDetails(JSONObject jsonObject) { super(jsonObject); }
			
			public String getName() {
		  		return "contact_details";
		  	}
		}
	}
}