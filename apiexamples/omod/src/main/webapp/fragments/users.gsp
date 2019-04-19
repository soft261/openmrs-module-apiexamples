<br>
<hr>
<h1>Users</h1>
<h2>Properties</h2>
<table>
  <tr>
   <th>userId</th>
   <th>PersonName</th>
   <th>systemId</th>
   <th>username</th>
   <th>roles</th>
  </tr>
  <% if (users) { %>
     <% users.each { %>
      <tr>
        <td>${ ui.format(it.userId) }</td>
        <td>${ ui.format(it.getNames().toString().replaceAll("\\[(.*?)\\]", '$1')) }</td>
        <td>${ ui.format(it.systemId) }</td>
        <td>${ ui.format(it.username) }</td>
        <td>${ ui.format(it.roles.toString().replaceAll("\\[(.*?)\\]", '$1')) }</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="2">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>