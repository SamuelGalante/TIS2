package modelDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.ConnectionFactory;
import modelo.Produto;

//DAO produtos ok
public class ProdutoDAO{
	
	public int getQuant() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int res = 0;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_produtos");
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
	
	public int create(Produto p) {
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO dbo.tb_produtos(tipo,descricao,nome,valor, imagem) VALUES(" + "?,?,?,?,?" +")");
			stmt.setString(1, p.getTipo());
			stmt.setString(2, p.getDescricao());
			stmt.setString(3, p.getNome());
			stmt.setDouble(4, p.getValor());
			stmt.setString(5, p.getImg());
			stmt.executeUpdate();
			
			System.out.println("Produto "+ p.getNome()+"  cadastrado!");
			
			stmt.close();
			
			stmt = con.prepareStatement("SELECT MAX(idProdutos) AS id FROM dbo.tb_produtos");
			rs = stmt.executeQuery();
			
			return rs.getInt("id");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao cadastrar!" + e);
			return 0;
			
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
	}
	
	public List<Produto> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Produto> lista = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_produtos");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Produto p = new Produto();
				
				p.setIdProdutos(rs.getInt("idProdutos"));
				p.setDescricao(rs.getString("descricao"));
				p.setNome(rs.getString("nome"));
				p.setTipo(rs.getString("tipo"));
				p.setValor(rs.getFloat("valor"));
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
	
	public List<Produto> readImage(Integer idProduto) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Produto> lista = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_produtos WHERE idProdutos = ?");
			stmt.setInt(1, idProduto);
			rs = stmt.executeQuery();
			Produto p = new Produto();
			if (rs.next())
			p.setIdProdutos(rs.getInt("idProdutos"));
			p.setDescricao(rs.getString("descricao"));
			p.setNome(rs.getString("nome"));
			p.setTipo(rs.getString("tipo"));
			p.setValor(rs.getFloat("valor"));
			p.setImg(rs.getString("imagem"));
			lista.add(p); 

			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public boolean update(Integer idProdutos, Produto p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE dbo.tb_produtos SET tipo = ?, descricao = ?, nome = ?, valor = ? WHERE idProdutos = ?");
			stmt.setString(1, p.getTipo());
			stmt.setString(2, p.getDescricao());
			stmt.setString(3, p.getNome());
			stmt.setDouble(4, p.getValor());
			stmt.setString(5, p.getImg());
			stmt.setInt(5, idProdutos);
			
			stmt.executeUpdate();
			
			System.out.println("Produto" + p.getNome() +" atualizado!");
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao atualizar" + p.getNome() + e);
			return false;
		}finally {
			ConnectionFactory.closeConnection(con);
		}
	}
	
	public boolean delete(Integer id) {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = null;
			
			try {
				stmt = con.prepareStatement("DELETE FROM dbo.tb_produtos WHERE idProdutos = ?");
				stmt.setInt(1, id);
				
				stmt.executeUpdate();
				
				System.out.println("Produto deletado!");
				
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block

				System.out.println("Erro ao deletar o produto:" + e);
				return false;
			}finally {
				ConnectionFactory.closeConnection(con);
			}
		}
		
}
