# API Examples Module

## Purpose

This module was created to demonstrate the basic functionality of the [Obs](https://wiki.openmrs.org/display/docs/Obs), [Person](https://wiki.openmrs.org/display/docs/Person), and [User](https://wiki.openmrs.org/display/docs/User) objects in the OpenMRS API.

## Setup

Please make sure your server has at least one Person with the last name 'Smith' and that they have at lease one Obs attached to them (available by adding a visit note to the patient, or by clicking the `Manage Obs` link in Advanced System Settings).

## Deploying the Module

Open a new terminal. Enter the `openmrs-module-apiexamples` folder, then enter the `apiexamples` folder. Execute `mvn package`. This will create an OMOD file in the `apiexamples/omod/target` folder. Install the module in System Settings > Advanced System Settings > Manage Modules. Click `Add new module...`, choose the OMOD file, then click `Upload`.

## Architecture

The module uses Groovy / Java MVC. The pages (views) are located [here](/apiexamples/omod/src/main/webapp/fragments), and the controllers are located [here](/apiexamples/omod/src/main/java/org/openmrs/module/apiexamples/fragment/controller). The Java controller populates the Groovy views with data through a model, and the view displays the data as desired.