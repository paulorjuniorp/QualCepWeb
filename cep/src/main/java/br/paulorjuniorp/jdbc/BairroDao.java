package br.paulorjuniorp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.paulorjuniorp.entidades.Bairro;

public class BairroDao {
	
	private Connection connection;

	public BairroDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Bairro bairro) {
		String sql = "insert into bairros "
				+ "(id,nome,id_cidade)" + " values (?,?,?)";

		try {

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);


			stmt.setLong(1, bairro.getId());
			stmt.setString(2, bairro.getNome());
			stmt.setLong(3, bairro.getCidade().getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Bairro> pegaTodos() {
		try {
			List<Bairro> bairros = new ArrayList<Bairro>();
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("select * from bairros");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Bairro bairro = new Bairro();
				bairro.setId(rs.getLong("id"));
				bairro.setNome(rs.getString("nome"));
				bairro.getCidade().setId(rs.getLong("id_cidade"));
				bairros.add(bairro);
			}
			rs.close();
			stmt.close();
			return bairros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public List<Bairro> ordenadosPeloNome() {
		try {
			List<Bairro> bairros = new ArrayList<Bairro>();
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("select * from bairros order By nome");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Bairro bairro = new Bairro();
				bairro.setId(rs.getLong("id"));
				bairro.setNome(rs.getString("nome"));
				bairro.getCidade().setId(rs.getLong("id_cidade"));
				bairros.add(bairro);
			}
			rs.close();
			stmt.close();
			return bairros;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void altera(Bairro bairro) {
		String sql = "update bairros set nome=?";
		try {
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			stmt.setLong(1, bairro.getId());
			stmt.setString(2, bairro.getNome());
			stmt.setLong(3, bairro.getCidade().getId());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Bairro bairro) {
		try {
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("delete from bairros where id=?");
			stmt.setLong(1, bairro.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Bairro buscaPorId(Long id) {
		Bairro bairro = new Bairro();
		
		 try {
	         PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("select * from bairros where id=?");
	         stmt.setLong(1, id);
	         
	         ResultSet rs = stmt.executeQuery();
	         
				if (rs.next()) {
					bairro.setId(rs.getLong("id"));
					bairro.setNome(rs.getString("nome"));
					bairro.getCidade().setId(rs.getLong("id_cidade"));
				}
	         stmt.close();
	         return bairro;
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }		
	}

}
