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
import java.util.Date;

import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.PersonName;
import org.openmrs.Relationship;
import org.openmrs.RelationshipType;
import org.openmrs.api.PersonService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.openmrs.api.context.Context;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *  * Controller for a fragment that shows all users  
 */
public class PersonFragmentController {
	
	public Person defaultPerson = Context.getPersonService().getPersonByUuid("6757d65f-e2c8-40eb-b9e0-75d16644e1e6");
	
	private static Boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	@RequestMapping(value = "/test.form", method = RequestMethod.POST)
	public ModelAndView Test() {
		defaultPerson.setBirthdate(new Date());
		return new ModelAndView("apiexamples.page");
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
	
	public void updateRelationship(String personA, String personB, String relationshipType) {
		PersonService service = Context.getPersonService();
		
		Integer personAId = Integer.parseInt(personA);
		Person _personA = service.getPerson(personAId);
		Integer personBId = Integer.parseInt(personB);
		Person _personB = service.getPerson(personBId);
		RelationshipType _relationshipType = service.getRelationshipType(Integer.parseInt(relationshipType));
		
		Relationship relationship = new Relationship(_personA, _personB, _relationshipType);
		service.saveRelationship(relationship);
	}
	
	public void updateAttribute(String personId, String attributeType, String attributeValue) {
		PersonService service = Context.getPersonService();
		
		Integer _personId = Integer.parseInt(personId);
		Person person = service.getPerson(_personId);
		
		Integer attributeTypeId = Integer.parseInt(attributeType);
		PersonAttributeType type = service.getPersonAttributeType(attributeTypeId);
		
		PersonAttribute attribute = new PersonAttribute(type, attributeValue);
		person.addAttribute(attribute);
		service.savePerson(person);
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
	        @RequestParam(value = "postalCode", required = false) String postalCode,
	        @RequestParam(value = "personA", required = false) String personA,
	        @RequestParam(value = "personB", required = false) String personB,
	        @RequestParam(value = "relationshipType", required = false) String relationshipType,
	        @RequestParam(value = "person", required = false) String attributePerson,
	        @RequestParam(value = "personAttributeType", required = false) String attributeType,
	        @RequestParam(value = "attributeValue", required = false) String attributeValue) {
		// Database has multiple patients with the last name "Smith"
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
		if (!isNullOrEmpty(personA)) {
			updateRelationship(personA, personB, relationshipType);
		}
		if (!isNullOrEmpty(attributeValue)) {
			updateAttribute(attributePerson, attributeType, attributeValue);
		}
		model.addAttribute("people", service.getPeople("Smith", null));
		model.addAttribute("defaultPerson", defaultPerson);
		model.addAttribute("relationships", service.getRelationshipsByPerson(defaultPerson));
		model.addAttribute("relationshipTypes", service.getRelationshipTypes("Sibling/Sibling", null));
		model.addAttribute("attributeTypes", service.getPersonAttributeTypes(null, null, null, null));
	}
	
}
