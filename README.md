![OpenMRS Logo](https://camo.githubusercontent.com/93680c923c12178e9fa6b523b1bbb644d32f4039/68747470733a2f2f74616c6b2e6f70656e6d72732e6f72672f75706c6f6164732f64656661756c742f6f726967696e616c2f32582f662f663165633537396230333938636230346338306135346335366461323139623234343066653234392e6a7067)

# API Examples Module

## Purpose

This module was created to demonstrate the basic functionality of the [Obs](https://wiki.openmrs.org/display/docs/Obs), [Person](https://wiki.openmrs.org/display/docs/Person), and [User](https://wiki.openmrs.org/display/docs/User) objects in the OpenMRS Core API.

## Setup

Please make sure your server has at least one Person with the last name 'Smith' and that they have at least one Obs attached to them (available by adding a visit note to the patient, or by clicking the `Manage Obs` link in Advanced System Settings).

To use the Obs section, navigate [here](https://github.com/soft261/openmrs-module-apiexamples/blob/f5a80c563b153ccfbe01f19b7d1701786db0505d/apiexamples/omod/src/main/java/org/openmrs/module/apiexamples/fragment/controller/ObsFragmentController.java#L42-L46) and get your person by either finding them by personId, or use the second (commented out) line to find a person within your server by UUID. This person should be a patient, and should have at least one Obs taken on them, as described above.

## Deploying the Module

Open a new terminal. Enter into the `openmrs-module-apiexamples` folder, then enter into the `apiexamples` folder. Execute `mvn package`. This will create an OMOD file in the `apiexamples/omod/target` folder. Install the module in System Settings > Advanced System Settings > Manage Modules. Click `Add new module...`, choose the OMOD file, then click `Upload`.

## Architecture

The module uses Groovy / Java files and Spring MVC. The pages (views) are located [here](/apiexamples/omod/src/main/webapp/fragments), and the controllers are located [here](/apiexamples/omod/src/main/java/org/openmrs/module/apiexamples/fragment/controller). The Java controller populates the Groovy views with data through a model, and the view displays the data as desired. More information regarding the architecture of this module can be found [here](https://wiki.openmrs.org/display/docs/UI+Framework+Step+By+Step+Tutorial).
