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
import org.openmrs.api.ObsService;
import org.openmrs.api.context.Context;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;

/**
 *  * Controller for a fragment that shows all users  
 */
public class ObsFragmentController {
	
	// Create a person and insert their UUID here
	Person person = Context.getPersonService().getPersonByUuid("6757d65f-e2c8-40eb-b9e0-75d16644e1e6");
	
	protected String getPersonNameString(Person person) {
		Set<PersonName> personName = person.getNames();
		String nameString = personName.toString().replaceAll("\\[(.*?)\\]", "$1");
		return nameString;
	}
	
	public void controller(FragmentModel model, @SpringBean("obsService") ObsService service) {
		model.addAttribute("name", getPersonNameString(person));
		model.addAttribute("obs", service.getObservationsByPerson(person));
	}
	
}
