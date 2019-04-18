<h1>Obs for ${ui.format(name)}</h1>
<h2>Properties</h2>
<table>
  <tr>
   <th>obsId</th>
   <th>concept (Type of Obs)</th>
   <th>obsDatetime</th>
   <th>valueCodedName (Diagnoses)</th>
   <th>valueText (Note)</th>
   <th>comment</th>
   <th>location</th>
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
        <td>${ ui.format(it.getLocation().toString().replaceAll("\\[(.*?)\\]", '$1')) }</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="2">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>
<div id="set-properties">
  <h2>Set Your Own Properties!</h2>
  <div id="comment">
    <h3>Update Comment</h3>
    <form method="get">
      Obs Id:<br>
      <input id="obsId" type="number" name="obsId" required><br>
      Comment:<br>
      <input id="comment" type="text" name="comment" required>
      <input type="submit" value="Update Comment">
    </form>
  </div>
  <div id="location">
    <h3>Update Location</h3>
    <form method="get">
      Obs Id:<br>
      <input id="obsId2" type="number" name="obsId2" required><br>
      Choose Location:<br>
      <select name="location" required>
        <option value="1">Unknown Location</option>
        <option value="2">Pharmacy</option>
        <option value="3">Labratory</option>
        <option value="4">Insolation Ward</option>
        <option value="5">Registration Desk</option>
        <option value="6">Inpatient Ward</option>
        <option value="7">Outpatient Clinic</option>
        <option value="8">Amani Hospital</option>
      </select><br>
      <input type="submit" value="Update Location">
    </form>
  </div>
</div>


