package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.ConnectionFactory;
import modelo.Garcom;

public class GarcomDAO {

	public boolean create(Garcom g) {
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO dbo.tb_garcons(nome) VALUES(" + "?" + ")");
			stmt.setString(1, g.getNome());
			
			stmt.executeUpdate();
			
			System.out.println("Garcom criada");
			return true;
			
		} catch (SQLException e) {
			System.out.println("Erro ao criar o Garcom" + e);
			return false;
			
		}finally {
			ConnectionFactory.closeConnection(con);
		}
	}

	public List<Garcom> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Garcom> lista = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_garcons");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Garcom g = new Garcom();
				g.setIdGarcom(rs.getInt("idGarcom"));
				g.setNome(rs.getString("nome"));
				lista.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao exibir os garcons" + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return lista;
	}
	
	public boolean update(Integer idGarcom, Garcom g) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE dbo.tb_garcons SET nome = ? WHERE idGarcom = ?");
			stmt.setString(1, g.getNome());
			stmt.setInt(2, idGarcom);
						
			stmt.executeUpdate();
			
			System.out.println("Garcom atualizado!");
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			return false;
		}finally {
			ConnectionFactory.closeConnection(con);
		}
	}
	
	public boolean delete(int idGarcom) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM dbo.tb_garcons WHERE idGarcom = ?");
			stmt.setInt(1, idGarcom);
			
			stmt.executeUpdate();
			System.out.println("Garcom deletado!");
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println("Erro ao deletar o Garcom:" + e);
			return false;
			
		}finally {
			ConnectionFactory.closeConnection(con);
		}
	}
	
	
}
