package br.paulorjuniorp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.paulorjuniorp.entidades.TiposLogradouro;

public class TipoLogDao {
	
	private Connection connection;

	public TipoLogDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(TiposLogradouro tipolog) {
		String sql = "insert into tipos_logradouros "
				+ "(id,nome)" + " values (?,?)";

		try {

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);


			stmt.setLong(1, tipolog.getId());
			stmt.setString(2, tipolog.getNome());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<TiposLogradouro> pegaTodos() {
		try {
			List<TiposLogradouro> tipologs = new ArrayList<TiposLogradouro>();
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("select * from tipos_logradouros");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				TiposLogradouro tipolog = new TiposLogradouro();
				tipolog.setId(rs.getLong("id"));
				tipolog.setNome(rs.getString("nome"));
				tipologs.add(tipolog);
			}
			rs.close();
			stmt.close();
			return tipologs;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public List<TiposLogradouro> ordenadosPeloNome() {
		try {
			List<TiposLogradouro> tipologs = new ArrayList<TiposLogradouro>();
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("select * from tipos_logradouros order By nome");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				TiposLogradouro tipolog = new TiposLogradouro();
				tipolog.setId(rs.getLong("id"));
				tipolog.setNome(rs.getString("nome"));
				tipologs.add(tipolog);
			}
			rs.close();
			stmt.close();
			return tipologs;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void altera(TiposLogradouro tipolog) {
		String sql = "update tipos_logradouros set nome=?";
		try {
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			stmt.setLong(1, tipolog.getId());
			stmt.setString(2, tipolog.getNome());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(TiposLogradouro tipolog) {
		try {
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement("delete from tipos_logradouros where id=?");
			stmt.setLong(1, tipolog.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public TiposLogradouro buscaPorId(Long id) {
		TiposLogradouro tipolog = new TiposLogradouro();
		
		 try {
	         PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("select * from tipos_logradouros where id=?");
	         stmt.setLong(1, id);
	         
	         ResultSet rs = stmt.executeQuery();
	         
				if (rs.next()) {
					tipolog.setId(rs.getLong("id"));
					tipolog.setNome(rs.getString("nome"));
				}
	         stmt.close();
	         return tipolog;
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }		
	}

}
