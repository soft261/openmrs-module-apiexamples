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

import java.util.Set;

import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.api.PersonService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
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
	
	private void updatePersonName(Person person, String firstName, String lastName) {
		PersonName name = new PersonName(firstName, "", lastName);
		name.setPreferred(true);
		person.addName(name);
	}
	
	@RequestMapping(value = "/apiexamples.page", method = RequestMethod.GET)
	public void controller(FragmentModel model, @SpringBean("personService") PersonService service,
	        @RequestParam(value = "personId", required = false) String personIdString,
	        @RequestParam(value = "firstName", required = false) String firstName,
	        @RequestParam(value = "lastName", required = false) String lastName) {
		// Database has multiple patients with the last name "Smith"
		model.addAttribute("people", service.getPeople("Smith", null));
		
		if (!isNullOrEmpty(personIdString)) {
			Integer personId = Integer.parseInt(personIdString);
			Person person = service.getPerson(personId);
			if (!isNullOrEmpty(firstName) || !isNullOrEmpty(lastName)) {
				updatePersonName(person, firstName, lastName);
			}
			
			try {
				service.savePerson(person);
			}
			catch (Exception e) {
				System.out.println("Error saving person.");
				System.out.println(e);
			}
		}
	}
	
}
