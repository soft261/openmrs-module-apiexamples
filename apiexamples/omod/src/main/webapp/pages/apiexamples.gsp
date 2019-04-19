<% ui.decorateWith("appui", "standardEmrPage") %>
<head>
  <style>
  h1 { 
      text-align: center;
      margin-top: 30px;
    }
  #title {
    font-size: 40px;
    font-weight: bold;
  }
  h2 { margin-top: 20px; }
  h3 { margin-top: 20px; }
  td:empty::before { content: "N/A"; }
  hr {
    border: 1px solid black;
  }
  </style>
</head>

<h1 id="title">API Examples</h1>

Hello, world.

<% if (context.authenticated) { %>
    And a special hello to you, $context.authenticatedUser.personName.fullName.
    Your roles are:
    <% context.authenticatedUser.roles.findAll { !it.retired }.each { %>
        $it.role ($it.description)
    <% } %>
<% } else { %>
    You are not logged in.
<% } %>

${ ui.includeFragment("apiexamples", "users") }
${ ui.includeFragment("apiexamples", "obs") }
${ ui.includeFragment("apiexamples", "person") }