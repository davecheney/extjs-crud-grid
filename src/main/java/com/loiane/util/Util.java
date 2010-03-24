package com.loiane.util;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loiane.model.Contact;

/**
 * Util class. Contains some common methods that can be used
 * for any class
 * 
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
public class Util {
	
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	private static DateFormat dfString = new SimpleDateFormat("MM/dd/yyyy");
	
	private static final Gson GSON = new Gson();

	/**
	 * Get list of Contacts from request.
	 * @param data - json data from request 
	 * @return list of Contacts
	 */
	public List<Contact> getContactsFromRequest(Object data){

		List<Contact> list;

		//it is an array - have to cast to array object
		if (data.toString().indexOf('[') > -1){

			list = getListContactsFromJSON(data);

		} else { //it is only one object - cast to object/bean

			Contact contact = getContactFromJSON(data);

			list = new ArrayList<Contact>();
			list.add(contact);
		}

		return list;
	}

	/**
	 * Transform json data format into Contact object
	 * @param data - json data from request
	 * @return 
	 */
	public Contact getContactFromJSON(Object data){
		Contact newContact = GSON.fromJson(data.toString(), Contact.class);
		return newContact;
	}

	/**
	 * Transform json data format into list of Contact objects
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Contact> getListContactsFromJSON(Object data){
		Type collectionType = new TypeToken<List<Contact>>(){}.getType();
		List<Contact> newContacts = GSON.fromJson(data.toString(), collectionType);
		return newContacts;
	}

	/**
	 * Tranform array of numbers in json data format into
	 * list of Integer
	 * @param data - json data from request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getListIdFromJSON(Object data){
		Type collectionType = new TypeToken<List<Integer>>(){}.getType();
		List<Integer> idContacts = GSON.fromJson(data.toString(), collectionType);
		return idContacts;
	}
	
	/**
	 * Format a yyyy-MM-dd'T'HH:mm:ss string to MM/dd/yyyy string
	 * JSON date has the following format: yyyy-MM-dd'T'HH:mm:ss
	 * @param jsonDate
	 * @return MM/dd/yyyy string date
	 * @throws ParseException
	 */
	public String getFormatedString(String jsonDate) throws ParseException{
		Date date = df.parse(jsonDate);
		return dfString.format(date);
	}
}
