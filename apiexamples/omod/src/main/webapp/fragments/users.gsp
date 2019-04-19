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
        <td>${ ui.format(it.roles) }</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="2">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>

<h2>Set Your Own Properties!</h2>
  <div id="username">
    <h3>Update Current User's Password</h3>
    <div id="password">
      <input type="button"  onclick="location.href='openmrs/adminui/myaccount/changePassword.page'" value="Change Password" >
    </div>
  </div>