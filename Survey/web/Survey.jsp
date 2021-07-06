<%-- 
    Document   : Survey
    Created on : Jul 6, 2021, 11:35:03 AM
    Author     : azm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Survey</title>
    </head>
    <body>
        <form >
            <p>What is your favorite pet?</p>
            <p>
                <input type = "radio" name = "animal" value = "1" />Dog<br />

                <input type = "radio" name = "animal"value = "2" />Cat<br />

                <input type = "radio" name = "animal" value = "3" />Bird<br />

                <input type = "radio" name = "animal" value = "4" />Snake<br />
                
                <input type = "radio" name = "animal" value = "5" checked = "checked" /> None
            </p>
            <p><input type = "submit" value = "Submit" /></p>
        </form>
        
        <table>
            <thead>
                <tr class="col">Dog</tr>
                <tr class="col">Cat</tr>
                <tr class="col">Bird</tr>
                <tr class="col">Snake</tr>
                <tr class="col">None</tr>
            </thead>
            <tbody>
            <t>
                <th scope="row"></th>
            </t>
            </tbody>
        </table>
    </body>
</html>
