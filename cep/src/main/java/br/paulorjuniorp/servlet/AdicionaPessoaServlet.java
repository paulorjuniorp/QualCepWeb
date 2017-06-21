package br.paulorjuniorp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.paulorjuniorp.entidades.Pessoa;
import br.paulorjuniorp.jdbc.PessoaDao;

@WebServlet("/adicionaPessoa")

public class AdicionaPessoaServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		
		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String celular = request.getParameter("celular");
		String logradouro = request.getParameter("logradouro");
		String numero = request.getParameter("numero");
		String complemento = request.getParameter("complemento");
		
		Pessoa pessoa = new Pessoa();
		
		pessoa.setCpf(cpf);
		pessoa.setNome(nome);
		pessoa.setEmail(email);
		pessoa.setCelular(celular);
		pessoa.getLogradouro().setId(Long.parseLong( logradouro));
		pessoa.setNumero(numero);
		pessoa.setComplemento(complemento);
		
		PessoaDao dao = new PessoaDao();
		
		dao.adiciona(pessoa);
		
		RequestDispatcher rd = request.getRequestDispatcher("/contato-adicionado.jsp");
		rd.forward(request, response);
		
		
	}
}
