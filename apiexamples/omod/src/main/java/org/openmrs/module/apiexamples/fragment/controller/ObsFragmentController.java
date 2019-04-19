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

import java.util.Date;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openmrs.Concept;
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
	
	// Get person with personId 1 (Default)
	//Person person = Context.getPersonService().getPerson(1);
	
	// Get person with specified UUID. Set this UUID to display a certain person in your database who is a patient and has obs.
	Person person = Context.getPersonService().getPersonByUuid("6757d65f-e2c8-40eb-b9e0-75d16644e1e6");
	
	// Used to display the name of a Person in the title of the Obs table.
	protected String getPersonNameString(Person person) {
		Set<PersonName> personName = person.getNames();
		String nameString = personName.toString().replaceAll("\\[(.*?)\\]", "$1");
		return nameString;
	}
	
	// Creates an obs for vitals given
	protected FragmentActionResult createObs(ObsService service, Person person, String conceptId, Date obsDateTime,
	        String locationId, String valueNumeric) {
		if (conceptId.isEmpty() || locationId.isEmpty()) {
			return null;
		}
		Concept concept = Context.getConceptService().getConcept(Integer.parseInt(conceptId));
		System.out.println(concept);
		Location location = Context.getLocationService().getLocation(Integer.parseInt(locationId));
		System.out.println(location);
		Obs obs = new Obs(person, concept, obsDateTime, location);
		obs.setValueNumeric(Double.parseDouble(valueNumeric));
		service.saveObs(obs, null);
		return new SuccessResult("New Obs Created!");
	}
	
	// Updates the comment of a given obs
	protected FragmentActionResult updateComment(ObsService service, String obsId, String comment) {
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
		Obs newObs = Obs.newInstance(obs);
		newObs.setPreviousVersion(obs);
		newObs.setComment(comment);
		Context.getObsService().saveObs(newObs, "Updated comment");
		Context.getObsService().voidObs(obs, "Updated comment");
		return new SuccessResult("Comment Updated!");
	}
	
	// Updates the location of a given obs
	protected FragmentActionResult updateLocation(ObsService service, String obsId2, String locationId2) {
		if (obsId2.isEmpty() || locationId2.isEmpty()) {
			return null;
		}
		Obs obs = service.getObs(Integer.parseInt(obsId2));
		System.out.println(obs);
		Location location = Context.getLocationService().getLocation(Integer.parseInt(locationId2));
		System.out.println(location);
		if (obs == null) {
			JFrame frame = new JFrame();
			frame.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(frame, "No such Obs was found in the database.", "Obs Not Found",
			    JOptionPane.ERROR_MESSAGE);
			return new FailureResult("Try Again");
		}
		Obs newObs = Obs.newInstance(obs);
		newObs.setPreviousVersion(obs);
		newObs.setLocation(location);
		Context.getObsService().saveObs(newObs, "Updated Location");
		Context.getObsService().voidObs(obs, "Updated Location");
		return new SuccessResult("Location Updated!");
	}
	
	// Allows the controller to receive values from the view (obs.gsp) using a POST request
	@RequestMapping(value = "/apiexamples.page", method = RequestMethod.POST)
	public void controller(FragmentModel model, @SpringBean("obsService") ObsService service,
	        @RequestParam(value = "conceptId", required = false) String conceptId,
	        @RequestParam(value = "locationId", required = false) String locationId,
	        @RequestParam(value = "valueNumeric", required = false) String valueNumeric,
	        @RequestParam(value = "obsId", required = false) String obsId,
	        @RequestParam(value = "comment", required = false) String comment,
	        @RequestParam(value = "obsId2", required = false) String obsId2,
	        @RequestParam(value = "locationId2", required = false) String locationId2) {
		createObs(service, person, conceptId, new Date(), locationId, valueNumeric);
		updateComment(service, obsId, comment);
		updateLocation(service, obsId2, locationId2);
		model.addAttribute("name", getPersonNameString(person));
		model.addAttribute("obs", service.getObservationsByPerson(person));
	}
}
