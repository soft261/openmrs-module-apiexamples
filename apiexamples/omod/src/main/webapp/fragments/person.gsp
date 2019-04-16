<h2 style="margin-top:40px;">Person</h2>
<table>
  <tr>
   <th>Person Id</th>
   <th>Gender</th>
   <th>Birthdate</th>
   <th>Patient?</th>
  </tr>
  <% if (person) { %>
     <% person.each { %>
      <tr>
        <td>${ ui.format(it.personId) }</td>
        <td>${ ui.format(it.gender) }</td>
        <td>${ ui.format(it.birthdate) }</td>
        <td>${ ui.format(it.isPatient) }</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="2">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>