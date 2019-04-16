<% ui.decorateWith("appui", "standardEmrPage") %>
<h1 style="text-align:center;">API Examples</h1>

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