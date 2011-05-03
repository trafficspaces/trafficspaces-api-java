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
package com.trafficspaces.api.model;

import java.lang.reflect.*;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Resource {
	
	protected Resource() {}
	
	protected Resource(JSONObject jsonObject) {
		setJSONObject(jsonObject);
	}
	
	public abstract String getName(); 

	public JSONObject getJSONObject() {
		JSONObject jsonObject = new JSONObject(); 
		Field[] fields = this.getClass().getFields();
		for (int i = 0; i < fields.length; i++) {
			
			Object fieldValue = fields[i].get(this);
			int fieldModifiers = fields[i].getModifiers();
			if (filedValue != null &&& Modifier.isPublic(fieldModifiers) && !Modifier.isStatic(fieldModifiers) && 
					!Modifier.isNative(fieldModifiers)) {
				
				String name = fields[i].getName();
				Class type = fields[i].getType();
			
				if (type.isPrimitive()) {
					if (type.equals(Integer.class)) {
						jsonObject.put(name, fields[i].getInt(this));
					} else if (type.equals(Double.class)) {
						jsonObject.put(name, fields[i].getDouble(this));
					}
				} else if (type.isArray()) {
					Object[] values = (Object[]) fieldValue;
					for (int j = 0; j < values.length; j++) {
						if (values[j] != null && values[j] instanceof Resource) {
							jsonObject.put(name, ((Resource) values[j]).getJSONObject());
						}
					}
				} else if (type.equals(Resource.class)) {
					jsonObject.put(name, ((Resource) fieldValue).getJSONObject());
				} else if (type.equals(String.class)) {
					jsonObject.put(name, String.valueOf(fieldValue));
				}
			}
		}
		return jsonObject;
	}
	
	public void setJSONObject(JSONObject jsonObject) {
		Iterator itr = jsonObject.keys();
		while (itr.hasNext()) {
			String key = (String) itr.next();
			Object value = jsonObject.opt(key);
			try {
				Field field = this.getClass().getField(key);
				Class type = field.getType();
				
				if (type.isPrimitive()) {
					if (type.equals(Integer.class)) {
						field.setInt(this, jsonObject.getInt(key));
					} else if (type.equals(Double.class)) {
						field.setDouble(this, jsonObject.getDouble(key));
					}
				} else if (type.isArray() && value instanceof JSONArray) {
					JSONArray jsonArray = (JSONArray) value;
					Object[] values = (Object[]) Array.newInstance(type, jsonArray.length());
					for (int j = 0; j < jsonArray.length(); j++) {
						Resource resource = (Resource) type.newInstance();
						resource.setJSONObject(jsonArray.getJSONObject(j));
						values[i] = resource;
					}
					field.set(this, values);
				} else if (type.equals(Resource.class) && value instanceof JSONObject) {
					Resource resource = (Resource) type.newInstance();
					resource.setJSONObject((JSONObject) value);
					field.set(this, resource);
				} else if (type.equals(String.class) && value instanceof String) {
					field.set(this, (String) value);
				}	
			} catch (NoSuchFieldException nsfe) {
				//
			}
		}
	}
	
	public String getXML() {
		String name = getName();
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + ("<" + name + ">") + org.json.XML.toString(getJSONObject()) + ("</" + name + ">");
	}
	
	public String getJSON() {
		return getJSONObject().toString();
	}
}
?>