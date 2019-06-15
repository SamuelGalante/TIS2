package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.ConnectionFactory;
import modelo.Cardapio;


//analizar e editar toda a classe DAO
public class CardapioDAO {
	
	public boolean create(Cardapio c) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("INSERT INTO dbo.tb_cardapio (descricao, status) VALUES (" + "?,0" + ")");
			stmt.setString(1, c.getDescricao());
			
			stmt.executeUpdate();

			System.out.println("Cardapio criado");
			return true;
			
		} catch (SQLException e) {
			System.out.println("Erro ao criar o cardapio");
			e.printStackTrace();
			return false;
			
		} finally {
			ConnectionFactory.closeConnection(con);
		}
	}

	public List<Cardapio> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Cardapio> lista = new ArrayList<>();

		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_cardapio");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Cardapio c = new Cardapio();

				c.setIdCardapio(rs.getInt("idCardapio"));
				c.setDescricao(rs.getString("descricao"));
				c.setStatus(rs.getInt("status"));
				lista.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao exibir os cardapios" + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return lista;
	}

	public int getQuant() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int res = 0;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_cardapio");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				res++; 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao exibir a lista" + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return res;
		
	} 
	
	public boolean update(int idCardapio, Cardapio c) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("UPDATE dbo.tb_cardapio SET descricao = ?, status = ? WHERE idCardapio = ?");
			stmt.setString(1, c.getDescricao());
			stmt.setInt(2, c.getStatus());
			stmt.setInt(3, idCardapio);
			
			stmt.executeUpdate();
			
			System.out.println("Cardapio atualizado!");
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println("Erro ao atualizar" + e);
			return false;
			
		} finally {
			ConnectionFactory.closeConnection(con);
		}
	}

	public boolean delete(int cardapioId) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("DELETE FROM dbo.tb_cardapio WHERE idCardapio = ?");
			stmt.setInt(1, cardapioId);

			stmt.executeUpdate();
			System.out.println("Cardapio deletado!");
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao deletar o cardapio:" + e);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con);
		}
	}
}
