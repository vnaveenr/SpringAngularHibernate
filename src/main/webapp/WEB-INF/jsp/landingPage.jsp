<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<html>
<body>
<h2>Welcome to Spring Greetings!</h2>
 
<p><a href="/SpringAngularJs/home/addgreeting.html">Add greeting</a><br/>
<a href="/SpringAngularJs/home/greetings.html">Show all greetings</a><br/>
<sec:authorize access="hasRole('ROLE_MANAGER')">
<a href="/SpringAngularJs/home/restrictedArea.html">Restricted area...</a><br/>
</sec:authorize>
<a href="/SpringAngularJs/logout">Logout</a>
 
</body>
</html>