<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>CREW - Log In</title>
    <style type="text/css" media="screen">@import "./style.css";</style>
</head>
<body>
<div id="headerContainer">

    <div id="headerLogo">
        <a href="./"><img
                src="./images/crew-logo.png" alt="CREW Logo"
                width="182" height="75"/></a>
    </div>


    <div id="headerLinks">
        <ul>
            <li><a href="./help.html">Help</a>&nbsp;|</li>
            <li><a href="./about.html">About</a></li>
        </ul>
    </div>

</div>


<div id="mainBody">
    <br/>
    <fieldset>
        <label>Login Details</label>

        <form method="POST" action="j_spring_security_check">

            <% if (request.getParameter("login_error") != null) { %>
            <p>Sorry, your login attempt failed. Please try again.</p>
            <% } %>

            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="j_username"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="j_password"></td>
                </tr>
            </table>

            <p><input type="submit" value="Log In"/></p>

            <p>If you don't have an account, please <a href="registration.do">register</a>.</p>

            <p><a href="forgottenPassword.do">Forgotten your password?</a></p>

        </form>
    </fieldset>
</div>

<div id="footer">
    <p id="copyright">&copy; 2007-2009 University of Bristol and University of Manchester.</p>
</div>

</body>
</html>
