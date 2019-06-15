package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.ConnectionFactory;
import modelo.Pedidos;


//DAO pedidos ok!
public class PedidosDAO {
	
	public boolean create(Pedidos p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO dbo.tb_pedidos(idComanda, idProdutos, quantidade) values(" + "?,?,1" + ")");
			stmt.setInt(1, p.getIdComanda());
			stmt.setInt(2, p.getIdProdutos());
			stmt.executeUpdate();
			
			return true;
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("Erro ao fazer o pedido");
			return false;
			
		}finally {
		ConnectionFactory.closeConnection(con);
		}
	}
	
	public List<Pedidos> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Pedidos> lista = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("select dbo.tb_pedidos.idPedidos, dbo.tb_comanda.idComanda, dbo.tb_comanda.mesa, dbo.tb_produtos.nome, dbo.tb_produtos.valor, dbo.tb_pedidos.quantidade " + 
					"from tb_pedidos " + 
					"join tb_comanda on(tb_comanda.idComanda = tb_pedidos.idComanda) " + 
					"join tb_produtos on (tb_produtos.idProdutos = tb_pedidos.idProdutos) ");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Pedidos p = new Pedidos();
				p.setIdPedidos(rs.getInt("idPedidos"));
				p.setIdComanda(rs.getInt("idComanda"));
				p.setNomeProduto(rs.getString("nome"));
				p.setValorProduto(rs.getFloat("valor"));
				p.setQuantidade(rs.getInt("quantidade"));
				lista.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao exibir os itens da mesa"+ e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return lista;
	}
	
	public List<Pedidos> readPedidosComanda(Integer idComanda) {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Pedidos> lista = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("select dbo.tb_produtos.nome, dbo.tb_produtos.valor, dbo.tb_pedidos.quantidade "
				+ "	from tb_pedidos "
				+ "	join tb_comanda on(tb_comanda.idComanda = tb_pedidos.idComanda) "
				+ "	join tb_produtos on (tb_produtos.idProdutos = tb_pedidos.idProdutos) "
				+ " where tb_comanda.idComanda = ?"
			);
			stmt.setInt(1, idComanda);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Pedidos p = new Pedidos();
				p.setNomeProduto(rs.getString("nome"));
				p.setQuantidade(rs.getInt("quantidade"));
				p.setValorProduto(rs.getFloat("valor"));
				lista.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao exibir os itens da mesa"+ e);
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
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_pedidos");
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
	
	public boolean update(Integer idPedidos, Pedidos p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE dbo.tb_pedidos SET idComanda = ?, idProdutos = ?, quantidade = ? WHERE idPedidos = ?");
			stmt.setInt(1, p.getIdComanda());
			stmt.setInt(2, p.getIdProdutos());
			stmt.setInt(3, p.getQuantidade());
			stmt.setInt(4, idPedidos);
						
			stmt.executeUpdate();
			
			System.out.println("Pedido atualizado!");
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			return false;
		}finally {
			ConnectionFactory.closeConnection(con);
		}
	}
	
	public boolean delete(int idPedido) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM dbo.tb_pedidos WHERE idPedidos = ?");
			stmt.setInt(1, idPedido);
			
			stmt.executeUpdate();
			System.out.println("pedido deletado!");
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			System.out.println("Erro ao deletar o pedido:" + e);
			return false;
			
		}finally {
			ConnectionFactory.closeConnection(con);
		}
	}
}
