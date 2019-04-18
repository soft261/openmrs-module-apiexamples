<h2 style="margin-top:40px;">Person</h2>
<table>
  <tr>
   <th>Person Id</th>
   <th>Name</th>
   <th>Gender</th>
   <th>Birthdate</th>
   <th>Patient?</th>
  </tr>
  <% if (person) { %>
     <% person.each { %>
      <tr>
        <td>${ ui.format(it.personId) }</td>
        <td>${ it.getNames().toString().replaceAll("\\[(.*?)\\]", '$1') }</td>
        <td>${ ui.format(it.gender) }</td>
        <td>${ ui.format(it.birthdate) }</td>
        <td>${ ui.format(it.isPatient) }</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="5">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
  <label for="toModify">People</label>
  <select id="toModify">
  <% if(person) { %>
    <% person.each { %>
      <option value="${it.personId}">${it.getNames().toString().replaceAll("\\[(.*?)\\]", '$1')}</option>
    <% } %>
  <% } else { %>
    <option disabled>No patients found</option>
  <% } %>
  </select>
</table>