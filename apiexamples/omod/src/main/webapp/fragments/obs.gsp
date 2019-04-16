<h2 style="margin-top:40px;">Obs</h2>
<table>
  <tr>
   <th>Obs Id</th>
   <th>Coded Name</th>
   <th>Text</th>
   <th>Date/Time</th>
  </tr>
  <% if (obs) { %>
     <% obs.each { %>
      <tr>
        <td>${ ui.format(it.obsId) }</td>
        <td>${ ui.format(it.valueCodedName) }</td>
        <td>${ ui.format(it.valueText) }</td>
        <td>${ ui.format(it.obsDatetime) }</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="2">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>