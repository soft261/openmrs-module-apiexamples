<h1>Obs for ${ui.format(name)}</h1>
<h2>Properties</h2>
<table>
  <tr>
   <th>obsId</th>
   <th>concept (Type of Obs)</th>
   <th>obsDatetime</th>
   <th>valueNumeric</th>
   <th>comment</th>
   <th>location</th>
   <th>previousVersion(obsId)</th>
  </tr>
  <% if (obs) { %>
     <% obs.each { %>
      <tr>
        <td>${ ui.format(it.obsId) }</td>
        <td>${ ui.format(it.concept) }</td>
        <td>${ ui.format(it.obsDatetime) }</td>
        <td>${ ui.format(it.valueNumeric) }</td>
        <td>${ ui.format(it.comment) }</td>
        <td>${ ui.format(it.getLocation().toString().replaceAll("\\[(.*?)\\]", '$1')) }</td>
        <td>${ ui.format(it.getPreviousVersion().obsId)}</td>
      </tr>
    <% } %>
  <% } else { %>
  <tr>
    <td colspan="2">${ ui.message("general.none") }</td>
  </tr>
  <% } %>
</table>

<div id="setProperties">
  <h2>Set Your Own Properties!</h2>

  <div id="createObs">
  <h3>Create a New Vitals Obs for ${ui.format(name)}</h3>
  <form method="post">
      Choose Vital Concept:<br>
      <select name="conceptId" required>
        <option value = "" selected disabled hidden>Choose Vital...</option>
        <option value="5090">Height (cm)</option>
        <option value="5089">Weight (kg)</option>
        <option value="5088">Temperature (C)</option>
        <option value="5087">Pulse</option>
        <option value="5244">Respiratory Rate</option>
        <option value="5085">Systolic Blood Pressure</option>
        <option value="5086">Diastolic Blood Pressure</option>
        <option value="5092">Blood Oxygen Saturation</option>
      </select><br>
      Value Numeric:<br>
      <input id="valueNumeric" type="number" step="0.1" name="valueNumeric" required><br>
      Choose Location:<br>
      <select name="locationId" required>
        <option value = "" selected disabled hidden>Choose Location...</option>
        <option value="1">Unknown Location</option>
        <option value="2">Pharmacy</option>
        <option value="3">Labratory</option>
        <option value="4">Insolation Ward</option>
        <option value="5">Registration Desk</option>
        <option value="6">Inpatient Ward</option>
        <option value="7">Outpatient Clinic</option>
        <option value="8">Amani Hospital</option>
      </select><br>
      <input type="submit" value="Create Obs">
    </form>
  </div>

  <div id="comment">
    <h3>Update Comment</h3>
    <form method="post">
      Obs Id:<br>
      <input id="obsId" type="number" name="obsId" required><br>
      Comment:<br>
      <input id="comment" type="text" name="comment" required>
      <input type="submit" value="Update Comment">
    </form>
  </div>

  <div id="location">
    <h3>Update Location</h3>
    <form method="post">
      Obs Id:<br>
      <input id="obsId2" type="number" name="obsId2" required><br>
      Choose Location:<br>
      <select name="locationId2" required>
        <option value = "" selected disabled hidden>Choose Location...</option>
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


