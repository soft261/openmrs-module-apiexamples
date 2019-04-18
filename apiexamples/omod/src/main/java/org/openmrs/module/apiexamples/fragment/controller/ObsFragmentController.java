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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.api.ObsService;
import org.openmrs.api.context.Context;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.openmrs.ui.framework.fragment.action.FailureResult;
import org.openmrs.ui.framework.fragment.action.FragmentActionResult;
import org.openmrs.ui.framework.fragment.action.SuccessResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  * Controller for a fragment that shows all users  
 */

// @Controller("${rootrootArtifactId}.ObsFragmentController")
// @RequestMapping(value = "openmrs/apiexamples/apiexamples.page")
public class ObsFragmentController {
	
	// Get person with personId 1
	Person person = Context.getPersonService().getPerson(1);
	// Get person with UUID of
	// Person person = Context.getPersonService().getPersonByUuid("6757d65f-e2c8-40eb-b9e0-75d16644e1e6");
	
	// Used to display the name of a Person in the title of the Obs table.
	protected String getPersonNameString(Person person) {
		Set<PersonName> personName = person.getNames();
		String nameString = personName.toString().replaceAll("\\[(.*?)\\]", "$1");
		return nameString;
	}
	
	protected FragmentActionResult updateComment(String obsId, String comment) {
		if (obsId.isEmpty()) {
			return null;
		}
		Obs obs = Context.getObsService().getObs(Integer.parseInt(obsId));
		System.out.println(obs);
		if (obs == null) {
			JFrame frame = new JFrame();
			frame.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(frame, "No such Obs was found in the database.", "Obs Not Found",
			    JOptionPane.ERROR_MESSAGE);
			return new FailureResult("Try Again");
		}
		obs.setComment(comment);
		Context.getObsService().saveObs(obs, "Date Updated");
		return new SuccessResult("Comment Updated!");
	}
	
	protected FragmentActionResult updateLocation(String obsId2, String location) {
		if (obsId2.isEmpty() || location.isEmpty()) {
			return null;
		}
		Obs obs = Context.getObsService().getObs(Integer.parseInt(obsId2));
		System.out.println(obs);
		Location locationName = Context.getLocationService().getLocation(Integer.parseInt(location));
		System.out.println(locationName);
		if (obs == null) {
			JFrame frame = new JFrame();
			frame.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(frame, "No such Obs was found in the database.", "Obs Not Found",
			    JOptionPane.ERROR_MESSAGE);
			return new FailureResult("Try Again");
		}
		obs.setLocation(locationName);
		Context.getObsService().saveObs(obs, "Date Updated");
		return new SuccessResult("Comment Updated!");
	}
	
	@RequestMapping(value = "/apiexamples.page", method = RequestMethod.GET)
	public void controller(FragmentModel model, @SpringBean("obsService") ObsService service,
	        @RequestParam(value = "obsId", required = false) String obsId,
	        @RequestParam(value = "comment", required = false) String comment,
	        @RequestParam(value = "obsId2", required = false) String obsId2,
	        @RequestParam(value = "location", required = false) String location) {
		System.out.println(obsId);
		System.out.println(comment);
		updateComment(obsId, comment);
		updateLocation(obsId2, location);
		model.addAttribute("name", getPersonNameString(person));
		model.addAttribute("obs", service.getObservationsByPerson(person));
	}
}
