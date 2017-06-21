<%@page import="java.util.List"%>
<%@page import="br.paulorjuniorp.servlet.BuscaCepServlet"%>
<%@ page import="br.paulorjuniorp.jdbc.LogradouroDao"%>
<%@ page import="br.paulorjuniorp.entidades.Logradouro;"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta por cep</title>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	    	<div class="container">
	        	<div class="navbar-header">
	          		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
			            <span class="sr-only">Toggle navigation</span>
			            <span class="icon-bar"></span>
			            <span class="icon-bar"></span>
			            <span class="icon-bar"></span>
	          		</button>
	          		<a class="navbar-brand hacked-font" href="#">CorreiosWeb</a>
	        	</div>
	        	<div id="navbar" class="navbar-collapse collapse">
	          		<ul class="nav navbar-nav">
	            		<li><a href="index.html">Inicio</a></li>
	            		<li><a href="consulta.jsp">Consulta</a></li> 
				         <li><a href="listapes.jsp">Pessoas</a></li> 
		              		</ul>
	            		</li>
	          		</ul>
	        	</div><!--/.nav-collapse -->
	      	</div>
    	</nav>	
    		<div class="container" style="padding: 93px; ">	
			<h2>Consulta</h2>
		<form method="get" action="consultacontroller.do">
			<div class="row">
				<div class="col-md-8">
					<div class="form-group">
						<label for="pesquisar">Pesquisar:</label>
						<input type="search" name="searchString" class="form-control" placeholder="Insira o Cep...">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<input type="submit" value="Pesquisar" class="btn btn-primary" style="margin-top: 25px;margin-left: -17px">
					</div>
				</div>
			</div>
		</form>
			<table class="table">
							<thead>

								<tr>
									<th>CEP</th>
									<th>Nome:</th>
									<th>Tipo</th>
									<th>Bairro</th>
								</tr>
							</thead>
							<tbody>
		<%
			LogradouroDao dao = new LogradouroDao();
			List<Logradouro> logradouros = dao.pegaTodos();
			for (Logradouro logradouro : logradouros) {
		%>
								<tr>
									<td><%=logradouro.getCep()%></td>
									<td><%=logradouro.getNome()%></td>
									<td><%=logradouro.getTipoLogradouro().getNome()%></td>
									<td><%=logradouro.getBairro().getNome()%></td>
								</tr>
		<%
			}
		%>
							</tbody>
						</table>
		</div>
</body>
</html>