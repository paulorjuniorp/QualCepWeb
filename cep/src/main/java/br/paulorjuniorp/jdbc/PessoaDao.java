package br.paulorjuniorp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.paulorjuniorp.entidades.Pessoa;

public class PessoaDao {
	
	private Connection connection;

	public PessoaDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Pessoa pessoa) {
		String sql = "insert into pessoas"
				+ "(cpf,nome,email,celular,id_logradouro,numero,complemento)" + " values (?,?,?,?,?,?,?)";

		try {

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);


			stmt.setString(1, pessoa.getCpf());
			stmt.setString(2, pessoa.getNome());
			stmt.setString(3, pessoa.getEmail());
			stmt.setString(4, pessoa.getCelular());
			stmt.setLong(5, pessoa.getLogradouro().getId());
			stmt.setString(6, pessoa.getNumero());
			stmt.setString(7, pessoa.getComplemento());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Pessoa> pegaTodos() {
		try {
			List<Pessoa> pessoas = new ArrayList<Pessoa>();
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("select * from pessoas");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getLong("id"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setCelular(rs.getString("celular"));
				pessoa.getLogradouro().setId(rs.getLong("id_logradouro"));
				pessoa.setNumero(rs.getString("numero"));
				pessoa.setComplemento(rs.getString("complemento"));
				pessoas.add(pessoa);
			}
			rs.close();
			stmt.close();
			return pessoas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public List<Pessoa> ordenadosPeloNome() {
		try {
			List<Pessoa> pessoas = new ArrayList<Pessoa>();
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("select * from pessoas order By nome");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getLong("id"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setCelular(rs.getString("celular"));
				pessoa.getLogradouro().setId(rs.getLong("id_logradouro"));
				pessoa.setNumero(rs.getString("numero"));
				pessoa.setComplemento(rs.getString("complemento"));
				pessoas.add(pessoa);
			}
			rs.close();
			stmt.close();
			return pessoas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void altera(Pessoa pessoa) {
		String sql = "update pessoas set nome=?";
		try {
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			stmt.setLong(1, pessoa.getId());
			stmt.setString(2, pessoa.getCpf());
			stmt.setString(3, pessoa.getNome());
			stmt.setString(4, pessoa.getEmail());
			stmt.setString(5, pessoa.getCelular());
			stmt.setLong(6, pessoa.getLogradouro().getId());
			stmt.setString(7, pessoa.getNumero());
			stmt.setString(8, pessoa.getComplemento());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Pessoa pessoa) {
		try {
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("delete from pessoas where id=?");
			stmt.setLong(1, pessoa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Pessoa buscaPorCpf(String cpf) {
		Pessoa pessoa = new Pessoa();
		
		 try {
	         PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("select * from pessoas where cpf=?");
	         stmt.setString(1, cpf);
	         
	         ResultSet rs = stmt.executeQuery();
	         
				if (rs.next()) {
					pessoa.setId(rs.getLong("id"));
					pessoa.setCpf(rs.getString("cpf"));
					pessoa.setNome(rs.getString("nome"));
					pessoa.setEmail(rs.getString("email"));
					pessoa.getLogradouro().setId(rs.getLong("id_logradouro"));
					pessoa.setNumero(rs.getString("numero"));
					pessoa.setComplemento(rs.getString("complemento"));
				}
	         stmt.close();
	         return pessoa;
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }		
	}
	
	public Pessoa buscaPorNome(Pessoa nome){
		Pessoa pessoa = new Pessoa();
		
		try{
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("select * from pessoas where nome like ?");
			
			stmt.setString(1, "%" + nome + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				//pessoa.setId(rs.getLong("id"));
				//pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNome(rs.getString("nome"));
				//pessoa.setEmail(rs.getString("email"));
				//pessoa.getLogradouro().setId(rs.getLong("id_logradouro"));
				//pessoa.setNumero(rs.getString("numero"));
				//pessoa.setComplemento(rs.getString("complemento"));
			}
	         stmt.close();
	         return pessoa;
			
		} catch (SQLException e) {
	         throw new RuntimeException(e);
		}
	}
}
