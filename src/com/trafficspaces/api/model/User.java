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
 ** Reference Documentation: http://support.trafficspaces.com/kb/api/api-users
 **/
package com.trafficspaces.api.model;

import org.json.JSONObject;

public class User extends Resource {
	//******************************
	//** INPUT & OUTPUT VARIABLES **
	//******************************
	public String name;
	public String role;
	public Profile profile;

	//******************************
	//*** OUTPUT ONLY VARIABLES ****
	//******************************
	public String id;
	public String creation_date;
	public String last_modified_date;
	public String expiration_date;

	public User() {}
	
	public User(JSONObject jsonObject) { super(jsonObject); }
	
	public String getName() {
  		return "user";
  	}

	static class Profile extends Resource {
		//******************************
		//** INPUT & OUTPUT VARIABLES **
		//******************************
		public String title;
		public String first_name;
		public String last_name;
		public String gender;
		public String date_of_birth;
		public String company_name;
		public String website;
		public String email;
		public String photo_url;
		public String locale;
		public ContactDetails contact_details;
	
		public Profile() {}
		
		public Profile(JSONObject jsonObject) { super(jsonObject); }
		
		public String getName() {
	  		return "profile";
	  	}
	
		static class ContactDetails extends Resource {
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
