# API Examples Module

## Purpose

This module was created to demonstrate the basic functionality of the [Obs](https://wiki.openmrs.org/display/docs/Obs), [Person](https://wiki.openmrs.org/display/docs/Person), and [User](https://wiki.openmrs.org/display/docs/User) objects in the OpenMRS API.

## Setup

Please make sure your server has at least one Person with the last name 'Smith' and that they have at least one Obs attached to them (available by adding a visit note to the patient, or by clicking the `Manage Obs` link in Advanced System Settings).

## Deploying the Module

Open a new terminal. Enter the `openmrs-module-apiexamples` folder, then enter the `apiexamples` folder. Execute `mvn package`. This will create an OMOD file in the `apiexamples/omod/target` folder. Install the module as normal.
