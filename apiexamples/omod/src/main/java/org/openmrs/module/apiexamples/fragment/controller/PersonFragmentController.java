/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.apiexamples.fragment.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;
import org.openmrs.api.PersonService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.openmrs.api.context.Context;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  * Controller for a fragment that shows all users  
 */
public class PersonFragmentController {
	
	private static Boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	private void updatePerson(Person person, String firstName, String lastName, String gender, String birthdate) {
		PersonService service = Context.getPersonService();
		
		if (!isNullOrEmpty(firstName) || !isNullOrEmpty(lastName)) {
			PersonName name = new PersonName(firstName, "", lastName);
			person.addName(name);
		}
		if (!isNullOrEmpty(gender)) {
			person.setGender(gender);
		}
		if (!isNullOrEmpty(birthdate)) {
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				person.setBirthdate(format.parse(birthdate));
			}
			catch (ParseException e) {
				System.out.println("Error parsing date.");
				System.out.println(e);
			}
		}
		
		try {
			service.savePerson(person);
		}
		catch (Exception e) {
			System.out.println("Error saving person.");
			System.out.println(e);
		}
	}
	
	private void updateAddress(Person person, String address1, String address2, String cityVillage, String stateProvince,
	        String country, String postalCode) {
		PersonService service = Context.getPersonService();
		
		if (!isNullOrEmpty(address1)) {
			PersonAddress addr = new PersonAddress();
			addr.setAddress1(address1);
			if (!isNullOrEmpty(address2)) {
				addr.setAddress2(address2);
			}
			addr.setCityVillage(cityVillage);
			addr.setStateProvince(stateProvince);
			addr.setCountry(country);
			addr.setPostalCode(postalCode);
			person.addAddress(addr);
			
			service.savePerson(person);
		}
	}
	
	@RequestMapping(value = "/apiexamples.page", method = RequestMethod.POST)
	public void controller(FragmentModel model, @SpringBean("personService") PersonService service,
	        @RequestParam(value = "personId", required = false) String personIdString,
	        @RequestParam(value = "firstName", required = false) String firstName,
	        @RequestParam(value = "lastName", required = false) String lastName,
	        @RequestParam(value = "gender", required = false) String gender,
	        @RequestParam(value = "birthdate", required = false) String birthdate,
	        @RequestParam(value = "address1", required = false) String address1,
	        @RequestParam(value = "address2", required = false) String address2,
	        @RequestParam(value = "cityVillage", required = false) String cityVillage,
	        @RequestParam(value = "stateProvince", required = false) String stateProvince,
	        @RequestParam(value = "country", required = false) String country,
	        @RequestParam(value = "postalCode", required = false) String postalCode) {
		// Database has multiple patients with the last name "Smith"
		model.addAttribute("people", service.getPeople("Smith", null));
		if (!isNullOrEmpty(personIdString)) {
			Integer personId = Integer.parseInt(personIdString);
			Person person;
			if (personId != 0) {
				person = service.getPerson(personId);
			} else {
				person = new Person();
			}
			
			updatePerson(person, firstName, lastName, gender, birthdate);
			updateAddress(person, address1, address2, cityVillage, stateProvince, country, postalCode);
		}
	}
	
}
