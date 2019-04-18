<h2 style="margin-top:40px;">Person</h2>
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
    <td colspan="5">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>

<h3>Modify values</h3>

<form method="GET">
  <label>Select a person to modify.</label> 
  <br/>
  <select name="personId">
    <% if (people) { %>
      <% people.each { %>
        <option value="${it.getPersonId()}">${it.getNames().toString().replaceAll("\\[(.*?)\\]", '$1')}</option>
      <% } %>
    <% } else { %>
      <option disabled>No patients found</option>
    <% } %>
    <option value="0">Add new...</option>
  </select>

  <label>Name</label>
  <input type="text" placeholder="Given name" name="firstName" /> <input type="test" placeholder="Family name (surname)" name="lastName" />

  <label>Birthdate</label>
  <input type="date" name="birthdate" />

  <label>Gender</label>
  <select name="gender">
    <option value="M">Male</option>
    <option value="F">Female</option>
  </select>

  <input type="submit" value="Update" />
</form>
