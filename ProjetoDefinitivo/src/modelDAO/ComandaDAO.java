package modelDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.ConnectionFactory;
import modelo.Comanda;


//Criar um update para a comanda
public class ComandaDAO {

	public boolean create(Comanda c) {
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		//verificar se ja existe a comanda.
		try {
			stmt = con.prepareStatement("INSERT INTO dbo.tb_comanda(data, mesa, avaliacao, pagamento) VALUES(" +"?,?,?,?" + ")");
			stmt.setString(1, c.getData());
			stmt.setInt(2, c.getMesa());
			stmt.setInt(3, c.getAvaliacao());
			stmt.setInt(4, c.getPagamento());
			
			stmt.executeUpdate();
			
			System.out.println("Comanda criada");
			return true;
			
		} catch (SQLException e) {
			System.out.println("Erro ao criar a comanda" + e);
			return false;
			
		}finally {
			ConnectionFactory.closeConnection(con);
		}
	}
	
	public Comanda get(Integer id) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Comanda c = new Comanda();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_comanda WHERE idComanda = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			c.setNumero(rs.getInt("idComanda"));
			c.setData(rs.getString("data"));
			c.setMesa(rs.getInt("mesa"));
			c.setAvaliacao(rs.getInt("avaliacao"));
			c.setPagamento(rs.getInt("pagamento"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println("Erro ao consutar" + e);
		}finally {
			ConnectionFactory.closeConnection(con);
		}
		
		return c;
	}
	
	public List<Comanda> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Comanda> lista = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_comanda"); //Aplicar o join comanda.
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Comanda c = new Comanda();

				c.setNumero(rs.getInt("idComanda"));
				c.setData(rs.getString("data"));
				c.setMesa(rs.getInt("mesa"));
				c.setAvaliacao(rs.getInt("avaliacao"));
				c.setPagamento(rs.getInt("pagamento"));
				lista.add(c); 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao exibir a lista" + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return lista;
	}

	public boolean update(Integer idComanda, Comanda c) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE dbo.tb_comanda SET data = ?, mesa = ?, avaliacao = ?, pagamento = ? WHERE idComanda = ?");
			stmt.setString(1, c.getData());
			stmt.setInt(2, c.getMesa());
			stmt.setInt(3, c.getAvaliacao());
			stmt.setInt(4, c.getPagamento());
			stmt.setInt(5, idComanda);
			
			stmt.executeUpdate();
			
			System.out.println("Comanda atualizada!");
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			return false;
		}finally {
			ConnectionFactory.closeConnection(con);
		}
	}
	
	public boolean delete(int idComanda) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM dbo.tb_comanda WHERE idComanda = ?");
			stmt.setInt(1, idComanda);
			
			stmt.executeUpdate();
			System.out.println("Comanda deletada!");
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println("Erro ao deletar a comanda:" + e);
			return false;
			
		}finally {
			ConnectionFactory.closeConnection(con);
		}
	}
	
	public int getQuant() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int res = 0;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_comanda");
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
}
