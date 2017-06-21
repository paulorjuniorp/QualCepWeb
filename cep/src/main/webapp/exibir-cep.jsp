<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<jsp:useBean id="dao" class="br.paulorjuniorp.jdbc.LogradouroDao"/>
<table>
<!-- for -->
<c:forEach var="cep" items="${dao.cep}">
<tr>
<td>${cep.cep}</td>
<td>${cep.nome}</td>
<td>${cep.logradouro}</td>
</tr>
</c:forEach>
</table>
</body>
</html>