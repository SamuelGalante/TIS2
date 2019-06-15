package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import ConnectionFactory.ConnectionFactory;
import modelDAO.ComandaDAO;
import modelDAO.PedidosDAO;
import modelo.Comanda;
import modelo.Pedidos;


public class ServicePedidos {

	final static LocalDate localDate = LocalDate.now();
	final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd,MMMM/yyyy");
	final static String date = localDate.format(formatter);
	static Integer idComanda = 0;
	private ComandaDAO comandaDAO = new ComandaDAO();
	private PedidosDAO pedidosDAO = new PedidosDAO();
	
	public boolean verificar() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
//		Integer idComanda;
		try {
			stmt = con.prepareStatement("SELECT * FROM dbo.tb_comanda WHERE pagamento = 0");
			rs = stmt.executeQuery();
			
			System.out.println(rs);
			
			if(rs.next()) {
				idComanda = rs.getInt("idComanda");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String create(Request request) {
		Query query = request.getQuery();
		
		try {
			
			if (this.verificar() != true) {
				Comanda c = new Comanda();
				
				c.setMesa(query.getInteger("mesa"));
				c.setData(date);
				c.setPagamento(0);
				comandaDAO.create(c);
			}
			
			System.out.println(idComanda);
			
			
			Pedidos p = new Pedidos();
			
			p.setIdComanda(idComanda);
			p.setIdProdutos(query.getInteger("idProduto"));
			p.setQuantidade(query.getInteger("quantidade"));
			pedidosDAO.create(p);
			
			}catch(Exception e){
			
			return "Erro ao criar pedido" + e;
		}
		return date;
	}
	
	public String readPedidosComanda(Request request) {
		try {
			this.verificar();
			return listaJsonJoin(idComanda).toString();
		}
		catch (Exception e) {
			return "Erro ao listar a comanda" + e;
		}
	}
	
	public String read(Request request) {
		try {
			return listaJson().toString();
		}
		catch (Exception e) {
			return "Erro ao listar os pedidos" + e;
		}
	}
	
	public String update(Request request) {
		Query query = request.getQuery();
		Integer idPedido = query.getInteger("id");
		try {
			Pedidos p = new Pedidos();
			p.setIdComanda(query.getInteger("idComanda"));
			p.setIdProdutos(query.getInteger("idProdutos"));
			p.setQuantidade(query.getInteger("quantidade"));
			pedidosDAO.update(idPedido, p);
		
		return "Produto atualizado";
		}catch(Exception e){
			System.out.println(e);
			return "Pedido não atualizado" + e;
		}
	}
	
	public String delete(Request request){
		Query query = request.getQuery();
		Integer idPedido = query.getInteger("id");
		try {
			
			pedidosDAO.delete(idPedido);
			return "Pedido deletado";
		}
		catch(Exception e){
			return "Erro ao deletar o Pedido" + e;
		}
	}
	
	public JSONObject listaJson() {
		
		List<Pedidos> listaPedidos = pedidosDAO.read();
		
		JSONArray array = new JSONArray();
		
		for (Pedidos p: listaPedidos) {
			array.put(p.pedidosToJson());
		}
		JSONObject obj = new JSONObject();
		obj.put("listaPedidos", new JSONArray(listaPedidos));
		
		return obj;
	}
	
	public JSONObject listaJsonJoin(Integer comandaId) {
		
		List<Pedidos> listaPedidosComanda = pedidosDAO.readPedidosComanda(comandaId);
		
		JSONArray array = new JSONArray();
		
		for (Pedidos p: listaPedidosComanda) {
			array.put(p.pedidosToJson());
		}
		JSONObject obj = new JSONObject();
		obj.put("PedidosComanda", new JSONArray(listaPedidosComanda));
		
		return obj;
	}
	
}
