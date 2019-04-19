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

import org.openmrs.api.UserService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;

/**
 *  * Controller for a fragment that shows all users  
 */
public class UsersFragmentController {

	// This method is intended to update the password of the current user, but some
	// bugs still remain. A similar approach could be used to implement a change
	// password feature, but would need more attention/testing.
	// protected FragmentActionResult updatePassword(UserService service, String
	// oldPassword, String newPassword) {
	// if (oldPassword.isEmpty() || newPassword.isEmpty()) {
	// return null;
	// }
	// service.changePassword(oldPassword, newPassword);
	// return new SuccessResult("Comment Updated!");
	// }

	public void controller(FragmentModel model, @SpringBean("userService") UserService service) {
		model.addAttribute("users", service.getAllUsers());
	}

}
