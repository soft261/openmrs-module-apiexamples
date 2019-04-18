<h2 style="margin-top:40px;">Users</h2>
<table>
  <tr>
   <th>userId</th>
   <th>systemId</th>
   <th>username</th>
  </tr>
  <% if (users) { %>
     <% users.each { %>
      <tr>
        <td>${ ui.format(it.userId) }</td>
        <td>${ ui.format(it.systemId) }</td>
        <td>${ ui.format(it.username) }</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="2">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>