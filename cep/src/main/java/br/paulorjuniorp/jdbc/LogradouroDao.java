package br.paulorjuniorp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.paulorjuniorp.entidades.Logradouro;
import br.paulorjuniorp.entidades.Pessoa;

public class LogradouroDao {
	
	private Connection connection;

	public LogradouroDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Logradouro log) {
		String sql = "insert into logradouros "
				+ "(cep,nome,id_tipo_logradouro,id_bairro)" + " values (?,?,?,?,?)";

		try {

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);


			stmt.setString(1, log.getCep());
			stmt.setString(2, log.getNome());
			stmt.setLong(3, log.getTipoLogradouro().getId());
			stmt.setLong(4, log.getBairro().getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Logradouro> ordenadosPeloNome() {
		try {
			List<Logradouro> logs = new ArrayList<Logradouro>();
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("select * from logradouros order By nome");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Logradouro log = new Logradouro();
				log.setCep(rs.getString("cep"));
				log.setNome(rs.getString("nome"));
				log.getTipoLogradouro().setId(rs.getLong("id_tipo_logradouro"));
				log.getBairro().setId(rs.getLong("id_bairro"));
				
				logs.add(log);
			}
			rs.close();
			stmt.close();
			return logs;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void altera(Logradouro log) {
		String sql = "update logradouros set cep=?, nome=?";
		try {
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			
			stmt.setString(1, log.getCep());
			stmt.setString(2, log.getNome());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Logradouro log) {
		try {
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("delete from logradouros where id=?");
			stmt.setLong(1, log.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Logradouro> buscaPorCep(String log){
		String sql = "select l.cep, l.nome, t.nome, b.nome, c.nome from logradouros l inner join "
				+ "tipos_logradouros t on l.id_tipo_logradouro=t.id inner join bairros b  on l.id_bairro=b.id  inner join cidades c  on b.id_cidade=c.id  where cep like ? or l.nome like ?";

		List<Logradouro> logradouros = new ArrayList<Logradouro>();

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, "%" + log + "%");
			stmt.setString(2, "%" + log+"%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Logradouro logradouro = new Logradouro();
				logradouro.setCep(rs.getString("l.cep"));
				logradouro.setNome(rs.getString("l.nome"));
				logradouro.getTipoLogradouro().setNome(rs.getString("t.nome"));
				logradouro.getBairro().setNome(rs.getString("b.nome"));
				logradouro.getBairro().getCidade(). setNome(rs.getString("c.nome"));

				logradouros.add(logradouro);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return logradouros;
	}
	
	public List<Logradouro> pegaTodos() {
		try {
			List<Logradouro> logradouros = new ArrayList<Logradouro>();
			PreparedStatement stmt = this.connection.prepareStatement(
					"select l.cep, l.nome,t.nome, b.nome from logradouros l inner join tipos_logradouros t inner join bairros b on l.id_tipo_logradouro=t.id AND l.id_bairro=b.id limit 100");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Logradouro logradouro = new Logradouro();
				logradouro.setCep(rs.getString("l.cep"));
				logradouro.setNome(rs.getString("l.nome"));
				logradouro.getTipoLogradouro().setNome(rs.getString("t.nome"));
				logradouro.getBairro().setNome(rs.getString("b.nome"));
				logradouros.add(logradouro);
			}
			rs.close();
			stmt.close();
			return logradouros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}