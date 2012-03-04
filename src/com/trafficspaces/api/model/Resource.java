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
import org.json.XML;

import com.trafficspaces.api.controller.TrafficspacesAPIException;

public abstract class Resource {

	//******************************
	//****** OUTPUT VARIABLES ******
	//******************************
	public String id;
	
	protected Resource() {}
	
	protected Resource(JSONObject jsonObject) {
		setJSONObject(jsonObject);
	}
	
	public abstract String getName(); 

	public JSONObject getJSONObject() {
		JSONObject jsonObject = new JSONObject(); 
		Field[] fields = this.getClass().getFields();
		try {
			for (int i = 0; i < fields.length; i++) {
			 
				Object fieldValue = fields[i].get(this);
				int fieldModifiers = fields[i].getModifiers();
				//System.out.println("name=" + fields[i].getName() + ", value=" + fieldValue + ", type=" +fields[i].getType() + 
				//		", ispublic="+Modifier.isPublic(fieldModifiers) + ", isstatic="+Modifier.isStatic(fieldModifiers) + ", isnative="+Modifier.isNative(fieldModifiers));
				if (fieldValue != null && Modifier.isPublic(fieldModifiers) && !Modifier.isStatic(fieldModifiers) && 
						!Modifier.isNative(fieldModifiers)) {
					
					String name = fields[i].getName();
					Class type = fields[i].getType();
					
					if (type.isPrimitive()) {
						if (type.equals(int.class)) {
							jsonObject.put(name, fields[i].getInt(this));
						} else if (type.equals(double.class)) {
							jsonObject.put(name, fields[i].getDouble(this));
						}
					} else if (type.isArray()) {
						JSONObject jsonSubObject = new JSONObject();
						JSONArray jsonArray = new JSONArray();
						
						jsonObject.put(name, jsonSubObject);
						jsonSubObject.put(name.substring(0, name.length()-1), jsonArray);
						
						Object[] values = (Object[]) fieldValue;
						for (int j = 0; j < values.length; j++) {
							if (values[j] != null && values[j] instanceof Resource) {
								jsonArray.put(((Resource) values[j]).getJSONObject());
							}
						}
					} else if (Resource.class.isAssignableFrom(type)) {
						jsonObject.put(name, ((Resource) fieldValue).getJSONObject());
					} else if (type.equals(String.class) && fieldValue != null) {
						jsonObject.put(name, String.valueOf(fieldValue));
					}
				}
			}
		} catch (Exception nsfe) {
			//
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

				int fieldModifiers = field.getModifiers();
				//System.out.println("key=" + key + ", name=" + field.getName() + ", value=" + value + ", type=" +type + ", componentType=" +type.getComponentType() + 
				//		", ispublic="+Modifier.isPublic(fieldModifiers) + ", isstatic="+Modifier.isStatic(fieldModifiers) + ", isnative="+Modifier.isNative(fieldModifiers) +
				//		", isprimitive="+type.isPrimitive() + ", isarray="+type.isArray() + ", isResource="+Resource.class.isAssignableFrom(type));
				
				if (type.isPrimitive()) {
					if (type.equals(int.class)) {
						field.setInt(this, jsonObject.getInt(key));
					} else if (type.equals(double.class)) {
						field.setDouble(this, jsonObject.getDouble(key));
					}
				} else if (type.isArray()) {
					JSONArray jsonArray = null;
					if (value instanceof JSONArray) {
						jsonArray = (JSONArray) value;
					} else if (value instanceof JSONObject) {
						JSONObject jsonSubObject = (JSONObject) value;
						jsonArray = jsonSubObject.optJSONArray(key.substring(0, key.length()-1));
					}
					if (jsonArray != null && jsonArray.length() > 0) {
						Class componentType = type.getComponentType();
						Object[] values = (Object[]) Array.newInstance(componentType, jsonArray.length());
						for (int j = 0; j < jsonArray.length(); j++) {
							Resource resource = (Resource) componentType.newInstance();
							resource.setJSONObject(jsonArray.getJSONObject(j));
							values[j] = resource;
						}
						field.set(this, values);
					}
				} else if (Resource.class.isAssignableFrom(type) && value instanceof JSONObject) {
					Resource resource = (Resource) type.newInstance();
					resource.setJSONObject((JSONObject) value);
					field.set(this, resource);
				} else if (type.equals(String.class) && value instanceof String) {
					field.set(this, (String) value);
				}	
			} catch (NoSuchFieldException nsfe) {
				System.err.println("warning: field does not exist. key="+key+",value="+value);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("error: key="+key+",value="+value+", error="+e.getMessage());
			}
		}
	}
	
	public String getXML() throws TrafficspacesAPIException {
		String name = getName();
		try {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + ("<" + name + ">") + XML.toString(getJSONObject()) + ("</" + name + ">");
		} catch (Exception e) {
			throw new TrafficspacesAPIException();
		}
	}
	
	public String getJSON() {
		//System.out.println(getJSONObject().toString());
		return getJSONObject().toString();
	}
	
	public String toString() {
		return getJSON();
	}
}