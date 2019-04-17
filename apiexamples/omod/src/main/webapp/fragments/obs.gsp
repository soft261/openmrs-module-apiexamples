<h2>Obs for ${ui.format(name)}</h2>
<h3>Properties</h3>
<table>
  <tr>
   <th>obsId</th>
   <th>concept (Type of Obs)</th>
   <th>obsDatetime</th>
   <th>valueCodedName (Diagnoses)</th>
   <th>valueText (Note)</th>
   <th>comment</th>
  </tr>
  <% if (obs) { %>
     <% obs.each { %>
      <tr>
        <td>${ ui.format(it.obsId) }</td>
        <td>${ ui.format(it.concept) }</td>
        <td>${ ui.format(it.obsDatetime) }</td>
        <td>${ ui.format(it.valueCodedName) }</td>
        <td>${ ui.format(it.valueText) }</td>
        <td>${ ui.format(it.comment) }</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="2">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>
<h3>Set Your Own Properties!</h3>
<form method="get">
  Obs Id:<br>
  <input id="obsId" type="number" name="obsId" required><br>
  Comment:<br>
  <input id="comment" type="text" name="comment" required>
  <input type="submit" value="Update Comment">
</form>

