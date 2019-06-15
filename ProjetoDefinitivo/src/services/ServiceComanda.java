package services;

import java.time.LocalDate;
import java.util.List;


import modelo.Comanda;
import modelDAO.ComandaDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public class ServiceComanda {
	
	private ComandaDAO comandaDAO = new ComandaDAO();
	
//	private String consultaComanda(Request request){ //private String consultaProduto(Integer id, Request request){
//		
//		Query query = request.getQuery();
//		int id = query.getInteger("id");
//		try {
//			Comanda comanda = comandaDAO.get(id);
//			return comanda.comandaToJson().toString();
//		}
//		catch(Exception e){
//			return "Erro ao consultar o produto" + e;
//		}
//	}
	
	public String create(Request request) {
		Query query = request.getQuery();
		
		try {
		Comanda c = new Comanda();
		c.setNumero(query.getInteger("id"));
		c.setData(LocalDate.now().toString());
		c.setMesa(query.getInteger("mesa"));
		c.setAvaliacao(query.getInteger("avaliacao"));
		c.setPagamento(query.getInteger("id"));
		comandaDAO.create(c);
		
		return "Comanda aberta";
		}catch(Exception e){
			return "Erro ao abrir a comanda" + e;
		}
	}
	
	
	public String read(Request request) {
				
		try {
			return listaJson().toString();
		}
		catch (Exception e) {
			return "Erro ao listar a comanda" + e;
		}
	}
	
	public String update(Request request) {
		ServicePedidos sp = new ServicePedidos();
		sp.verificar();
		
		Query query = request.getQuery();
		Integer idComanda = sp.idComanda;
		try {
			Comanda c = new Comanda();
//			c.setMesa(query.getInteger("mesa"));
//			c.setData(query.get("data"));
			c.setAvaliacao(query.getInteger("avaliacao"));
			c.setPagamento(1);
			comandaDAO.update(idComanda, c);
			
			return c.toString();
		}catch(Exception e){
			System.out.println(e);
			return "Erro ao atualizar a comanda";
		}
	}
	
	public String delete(Request request){
		Query query = request.getQuery();
		Integer idComanda = query.getInteger("id");
		try {
			comandaDAO.delete(idComanda);
			return "Cardapio deletado";
		}
		catch(Exception e){
			return "Erro ao deletar o cardapio" + e;
		}
	}
	

public JSONObject listaJson() {
		
		List<Comanda> listaComanda = comandaDAO.read();
		
		JSONArray array = new JSONArray();
		
		for (Comanda c: listaComanda) {
			array.put(c.comandaToJson());
		}
		JSONObject obj = new JSONObject();
		obj.put("listaComandas", new JSONArray(listaComanda));
		
		return obj;
	}
}
