package services;


import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;


import modelDAO.CardapioDAO;
import modelo.Cardapio;



public class ServiceCardapio {

	private CardapioDAO cardapioDAO = new CardapioDAO();
	
	public String create(Request request) {
		Query query = request.getQuery();
		
		try {
		Cardapio c = new Cardapio();
		c.setDescricao(query.get("descricao"));
		c.setStatus(query.getInteger("status"));
		cardapioDAO.create(c);
		
		return c.cardapioToJson().toString(); //listaJson().toString();
		}catch(Exception e){
			return "Erro ao adcionar o cardapio" + e;
		}
	}
	
	public String read(Request request) {
		try {
			return listaJson().toString();
		}
		catch (Exception e) {
			return "Erro ao listar o cardapio" + e;
		}
	}
	
	
	public String update(Request request) {
		Query query = request.getQuery();
		Integer idCardapio = query.getInteger("id");
		try {
		Cardapio c = new Cardapio();
		c.setDescricao(query.get("descricao"));
		c.setStatus(query.getInteger("status"));
		cardapioDAO.update(idCardapio, c);
		
		return c.toString();
		}catch(Exception e){
			return "Erro ao atualizar o cardapio" + e;
		}
	}
	
	public String delete(Request request){
		Query query = request.getQuery();
		Integer idCardapio = query.getInteger("id");
		try {
			
			cardapioDAO.delete(idCardapio);
			return "Cardapio deletado";
		}
		catch(Exception e){
			return "Erro ao deletar o cardapio" + e;
		}
	}
	
	public JSONObject listaJson() {
		List<Cardapio> listaCardapio = cardapioDAO.read();
		
		JSONArray array = new JSONArray();
		
		for (Cardapio c: listaCardapio) {
			array.put(c.cardapioToJson());
		}
		JSONObject obj = new JSONObject();
		obj.put("listaCardapio", new JSONArray(listaCardapio));
		
		return obj;
	}
}
