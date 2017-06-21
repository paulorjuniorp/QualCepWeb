package br.paulorjuniorp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.paulorjuniorp.entidades.Pessoa;
import br.paulorjuniorp.jdbc.PessoaDao;

@WebServlet("/listarPessoas")

public class ListarPessoaServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		PessoaDao dao = new PessoaDao();
		
		List<Pessoa> pessoas = dao.pegaTodos();
		
		for (Pessoa pessoa : pessoas) {
			
			out.println("<html>");
			out.println("<body>");
			out.println("Nome: " + pessoa.getNome());
			out.println("<br/>");
			out.println("Cpf: " + pessoa.getCpf());
			out.println("<br/>");
			out.println("Celular: " + pessoa.getCelular());
			out.println("<br/>");
			out.println("Logradouro: " + pessoa.getLogradouro().getNome());
			out.println("<br/>");
			out.println("Numero: " + pessoa.getNumero());
			out.println("<br/>");
			out.println("Complemento: " + pessoa.getComplemento());
			out.println("<br/>");
			out.println("<br/>");
			out.println("</body>");
			out.println("</html>");
		}
		
	}

}
