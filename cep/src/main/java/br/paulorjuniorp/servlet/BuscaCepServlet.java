package br.paulorjuniorp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.paulorjuniorp.entidades.Logradouro;
import br.paulorjuniorp.entidades.Pessoa;
import br.paulorjuniorp.jdbc.LogradouroDao;
import br.paulorjuniorp.jdbc.PessoaDao;

@WebServlet("/buscacep")
public class BuscaCepServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String cep = request.getParameter("cep");

		Logradouro logradouro = new Logradouro();

		logradouro.setCep(cep);

		LogradouroDao dao = new LogradouroDao();

		List<Logradouro> logradouros = new ArrayList<Logradouro>();

		try {
			logradouros = dao.buscaPorCep(cep);
		} catch (Exception e) {

			e.printStackTrace();
		}

		out.println("<html>");
		out.println("<head>");
		out.println("<link href='resources/css/bootstrap.min.css' rel='stylesheet'>");
		out.println("<link href='resources/css/style.css' rel='stylesheet'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='container' style='padding: 57px'>");
		out.println("<table class='table table-striped table-bordered'>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>"+"Cep"+"</th>");
		out.println("<th>" + "Rua"+"</th>");
		out.println("<th>"+"Tipo de Logradouro"+"</th>");
		out.println("<th>"+"Bairro"+"</th>");
		out.println("<th>"+"Cidade"+"</th>");
		out.println("</tr>");
		out.println("</thead>");
		
		out.println("<tbody>");
		
		for(Logradouro logr: logradouros){
			
			out.println("<tr>");
			out.println("<td>" + logr.getCep()+"</td>");
			out.println("<td>"+logr.getNome()+"</td>");
			out.println("<td>" + logr.getTipoLogradouro().getNome()+"</td>");
			out.println("<td>" + logr.getBairro().getNome()+"</td>");
			out.println("<td>"+ logr.getBairro().getCidade().getNome()+"</td>");
			out.println("</tr>");
		}
		

		out.println("</tbody>");
		out.println("</table>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

	}
}