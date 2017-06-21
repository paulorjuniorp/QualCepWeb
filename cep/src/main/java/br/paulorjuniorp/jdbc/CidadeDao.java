package br.paulorjuniorp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.paulorjuniorp.entidades.Bairro;
import br.paulorjuniorp.entidades.Cidade;
import br.paulorjuniorp.entidades.Cidade;

public class CidadeDao {

	private Connection connection;

	public CidadeDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Cidade cidade) {
		String sql = "insert into cidades" + " (id,nome,id_estado)" + " values (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, cidade.getId());
			stmt.setString(2, cidade.getNome());
			stmt.setLong(3, cidade.getEstado().getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Cidade cidade) {
		String sql = "update cidades set id=?, nome=? where id_estado=?";

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, cidade.getId());
			stmt.setString(2, cidade.getNome());
			stmt.setLong(3, cidade.getEstado().getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Cidade cidade) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("delete from cidades where id=?");

			stmt.setLong(1, cidade.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cidade> pegaTodos() {
		try {
			List<Cidade> cidades = new ArrayList<Cidade>();
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("select * from cidades");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Cidade cidade = new Cidade();
				cidade.setId(rs.getLong("id"));
				cidade.setNome(rs.getString("nome"));
				cidade.getEstado().setId(rs.getLong("id_estado"));
				cidades.add(cidade);
			}
			rs.close();
			stmt.close();
			return cidades;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
