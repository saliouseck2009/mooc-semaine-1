<%@ page language="java" contentType="text/html"
         pageEncoding="UTF-8" %>
<%@ page import="controller.Bag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<h1>Sac</h1>
<c:out value="${ requestScope.getAttribute(\"myBag\") }" />
<%--<%--%>
<%--    Bag attribut = (Bag) request.getAttribute("myBag");--%>
<%--    attribut.print(out);--%>
<%--%>--%>

    <form method='POST' action='home'>
        <label for='ref'>Ref</label>
        <input type='text' id='ref' name='ref'/><br/>
        <label for='qty'>Qty</label>
        <input type='text' id='qty' name='qty'/><br/>
        <input type='submit' value='send'>
    </form>

</body>
</html>