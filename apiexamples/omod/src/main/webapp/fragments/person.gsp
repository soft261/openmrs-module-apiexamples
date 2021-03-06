<br>
<hr>
<h1>Person</h1>
<h2>Properties</h2>
<table>
  <tr>
   <th>Person Id</th>
   <th>Name</th>
   <th>Gender</th>
   <th>Birthdate</th>
   <th>Is Dead? / Deathdate</th>
   <th>Patient?</th>
  </tr>
  <% if (people) { %>
     <% people.each { %>
      <tr>
        <td>${ ui.format(it.getPersonId()) }</td>
        <td>${ it.getNames().toString().replaceAll("\\[(.*?)\\]", '$1') }</td>
        <td>${ ui.format(it.getGender()) }</td>
        <td>${ ui.format(it.getBirthdate()) }</td>
        <td>${ ui.format(it.getDead() ? it.getDeathDate() : "false") }</td>
        <td>${ ui.format(it.isPatient()) }</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="6">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>

<h2>Modify Values</h2>

<form method="POST">
  Select a person to modify.<br>
  <select name="personId">
    <% if (people) { %>
      <% people.each { %>
        <option value="${it.getPersonId()}">${it.getNames().toString().replaceAll("\\[(.*?)\\]", '$1')}</option>
      <% } %>
    <% } else { %>
      <option disabled>No people found</option>
    <% } %>
    <option value="0">Add new...</option>
  </select>

  <label>Name</label>
  <input type="text" placeholder="Given Name" name="firstName" />
  <input type="text" placeholder="Family Name (surname)" name="lastName" />

  <label>Birthdate</label>
  <input type="date" name="birthdate" />

  <label>Gender</label>
  <select name="gender">
    <option value="M">Male</option>
    <option value="F">Female</option>
  </select>

  <input type="submit" value="Add Name" />
</form>

<br/><br/>

<table>
  <tr>
   <th>Person Id</th>
   <th>Name</th>
   <th>Address 1</th>
   <th>Address 2</th>
   <th>City / Village</th>
   <th>State / Province</th>
   <th>Postal Code</th>
  </tr>
  <% if (people) { %>
     <% people.each { %>
      <tr>
        <td>${ ui.format(it.getPersonId()) }</td>
        <td>${ it.getNames().toString().replaceAll("\\[(.*?)\\]", '$1') }</td>
      <% if(it.getAddresses().size() > 0) { %>
        <% it.getAddresses().each { %>
            <td>${ ui.format(it.address1) }</td>
            <td>${ ui.format(it.address2) }</td>
            <td>${ ui.format(it.cityVillage) }</td>
            <td>${ ui.format(it.stateProvince) }</td>
            <td>${ ui.format(it.postalCode) }</td>
        <% } %>
      <% } %>
      </tr>  
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="5">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>

<h3>Add an Address</h3>
<form method="POST">
  Select a person to modify.<br>
  <select name="personId">
    <% if (people) { %>
      <% people.each { %>
        <option value="${it.getPersonId()}">${it.getNames().toString().replaceAll("\\[(.*?)\\]", '$1')}</option>
      <% } %>
    <% } else { %>
      <option disabled selected>No people found</option>
    <% } %>
  </select>

  <label>Address 1</label>
  <input type="text" name="address1" placeholder="Address 1" required />
  <label>Address 2</label>
  <input type="text" name="address2" placeholder="Address 2" />
  <label>City / Village</label>
  <input type="text" name="cityVillage" placeholder="City / Village" required />
  <label>State / Province</label>
  <input type="text" name="stateProvince" placeholder="State / Province" required />
  <label>Country</label>
  <input type="text" name="country" placeholder="Country" required />
  <label>Postal Code</label>
  <input type="text" name="postalCode" placeholder="Postal Code" required />

  <input type="submit" value="Add Address" />
</form>

<br/><br/>

<h2>Relationships for ${defaultPerson.getNames().toString().replaceAll("\\[(.*?)\\]", '$1')}</h2>

<table>
  <tr>
    <th>Person A</th>
    <th>Relationship A &#8594; B</th>
    <th>Person B</th>
  </tr>
  <% if (relationships) { %>
    <% relationships.each { %>
      <tr>
        <td>${ it.getPersonA().getNames().toString().replaceAll("\\[(.*?)\\]", '$1') }</td>
        <td>${ ui.format(it.getRelationshipType().getaIsToB()) }</td>
        <td>${ it.getPersonB().getNames().toString().replaceAll("\\[(.*?)\\]", '$1') }</td>
      </tr>
    <% } %>
  <% } else { %>
    <tr>
      <td colspan="3">${ ui.message("general.none") }</td>
    </tr>
  <% } %>
</table>

<form method="POST">
  <select name="personA" style="display:inline">
    <option value="${defaultPerson.getPersonId()}">
    ${ defaultPerson.getNames().toString().replaceAll("\\[(.*?)\\]", '$1') }
    </option>
  </select>

  <select name="relationshipType" style="display:inline">
    <% if (relationshipTypes) { %>
      <% relationshipTypes.each { %>
        <option value="${it.getRelationshipTypeId()}">${it.getaIsToB()}</option>
      <% } %>
    <% } else { %>
      <option disabled selected>No relationship types found</option>
    <% } %>
  </select>

  <select name="personB" style="display:inline">
    <% if (people) { %>
      <% people.each { %>
        <option value="${it.getPersonId()}">${it.getNames().toString().replaceAll("\\[(.*?)\\]", '$1')}</option>
      <% } %>
    <% } else { %>
      <option disabled selected>No people found</option>
    <% } %>
  </select>

  <input type="submit" value="Add Relationship" />
</form>

<br/><br/>

<h2>Person Attributes</h2>

<table>
  <tr>
   <th>Person Id</th>
   <th>Name</th>
   <th>Attributes</th>
  </tr>
  <% if (people) { %>
     <% people.each { %>
      <tr>
        <td>${ ui.format(it.getPersonId()) }</td>
        <td>${ it.getNames().toString().replaceAll("\\[(.*?)\\]", '$1') }</td>
        <td>${ it.getAttributes() }</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="3">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>
<br>
<form>
  <select name="person" style="display:inline">
    <% if (people) { %>
      <% people.each { %>
        <option value="${it.getPersonId()}">${it.getNames().toString().replaceAll("\\[(.*?)\\]", '$1')}</option>
      <% } %>
    <% } else { %>
      <option disabled selected>No people found</option>
    <% } %>
  </select>

  <select name="personAttributeType" style="display:inline" required>
    <% if (attributeTypes) { %>
      <% attributeTypes.each { %>
        <option value="${it.getPersonAttributeTypeId()}">${it.toString()}</option>
      <% } %>
    <% } else { %>
      <option disabled selected>No attribute types found</option>
    <% } %>
  </select>
  <br><br>Attribute Value:<br>
  <input type="text" placeholder="Attribute Value" name="attributeValue" style="display:inline" required />

  <input type="submit" value="Add Attribute Type" />
</form>