package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.ConnectionFactory;
import modelo.ProdutoCardapio;


//Criar idProduto_cardapio na dbo.produto_cardapio
public class ProdutoCardapioDAO {

	public boolean create(ProdutoCardapio p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("INSERT INTO dbo.tb_produto_cardapio (idProdutos, idCardapio) values(" + "?,?" +")");
			stmt.setInt(1, p.getIdProdutos());
			stmt.setInt(2, p.getIdCardapio());
			stmt.executeUpdate();
			
			System.out.println("Produto_CardapioDAO cadastrado");
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<ProdutoCardapio> readProdutosCardapio(Integer idCardapio) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<ProdutoCardapio> lista = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_produto_cardapio tb1"
					+ " JOIN dbo.tb_produtos tb2 ON (tb1.idProdutos = tb2.idProdutos)"
					+ " WHERE idCardapio = ?");//Pegar o join do pedidos cardapio
			stmt.setInt(1, idCardapio);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				ProdutoCardapio p = new ProdutoCardapio();
				p.setIdProduto_cardapio(rs.getInt("idProdutoCardapio"));
				p.setIdCardapio(rs.getInt("idCardapio"));
				p.setIdProdutos(rs.getInt("idProdutos"));
				p.setNomeProduto(rs.getString("nome"));
				p.setValorProduto(rs.getFloat("valor"));
				lista.add(p); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao exibir a lista" + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return lista;
	}
	
	public List<ProdutoCardapio> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<ProdutoCardapio> lista = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_produto_cardapio");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				ProdutoCardapio p = new ProdutoCardapio();
				p.setIdProduto_cardapio(rs.getInt("idProdutoCardapio"));
				p.setIdCardapio(rs.getInt("idCardapio"));
				p.setIdProdutos(rs.getInt("idProdutos"));
				lista.add(p); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao exibir a lista" + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return lista;
	}
	
	public boolean update(Integer idProdutoCardapio, ProdutoCardapio p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE dbo.tb_produto_cardapio SET idCardapio = ?, idProdutos = ? WHERE idProdutoCardapio = ?");
			stmt.setInt(1, p.getIdCardapio());
			stmt.setInt(2, p.getIdProdutos());
			stmt.setInt(3, idProdutoCardapio);
			
			stmt.executeUpdate();
			
			System.out.println("ProdutoCardapio atualizado!");
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao atualizar" + e);
			return false;
		}finally {
			ConnectionFactory.closeConnection(con);
		}
	}
	
	public boolean delete(Integer id) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM dbo.tb_produto_cardapio WHERE idProdutoCardapio = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
			System.out.println("Produto_CardapioDAO deletado");
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public int getQuant() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int res = 0;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_produto_cardapio");
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
