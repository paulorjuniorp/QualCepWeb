package br.paulorjuniorp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.paulorjuniorp.entidades.Pessoa;
import br.paulorjuniorp.jdbc.PessoaDao;

@WebServlet("/buscapessoa")

public class BuscaPessoaServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter(); 
		
		String nome = request.getParameter("nome");
		
		Pessoa pessoa = new Pessoa();
		
		pessoa.setNome(nome);
		
		PessoaDao dao = new PessoaDao();
		
		dao.buscaPorNome(pessoa);
		
		out.println("<html>");
		out.println("<body>");
		out.println("CEP: " + pessoa.getNome());
		out.println("<br/>");
		out.println("Logradouro: " + pessoa.getCpf());
		out.println("<br/>");
		out.println("</body>");
		out.println("</html>");
		
	}
	

}
