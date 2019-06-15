package services;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import modelDAO.GarcomDAO;
import modelo.Garcom;



public class ServiceGarcom {
	private GarcomDAO garcomDAO = new GarcomDAO();
	
	public String create(Request request) {
		Query query = request.getQuery();
		
		try {
			Garcom g = new Garcom();
			g.setNome(query.get("nome"));
			garcomDAO.create(g);
			
			return "Garcom Cadastrado"; //listaJson().toString();
		}catch(Exception e){
			
			return "Erro ao cadastrar Garcom" + e;
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
		Integer idGarcom = query.getInteger("id");
		try {
			Garcom g = new Garcom();
			g.setNome(query.get("nome"));
			garcomDAO.update(idGarcom, g);
			
		return "Garcom atualizado";
		}catch(Exception e){
			System.out.println(e);
			return "Garcom não atualizado" + e;
		}
	}
	
	public String delete(Request request){
		Query query = request.getQuery();
		Integer idGarcom = query.getInteger("id");
		try {
			
			garcomDAO.delete(idGarcom);
			return "Garcom deletado";
		}
		catch(Exception e){
			return "Erro ao deletar o Garcom" + e;
		}
	}
	
	public JSONObject listaJson() {
		
		List<Garcom> listaGarcom = garcomDAO.read();
		JSONArray array = new JSONArray();
		
		for (Garcom g: listaGarcom) {
			array.put(g.pedidosToJson());
		}
		JSONObject obj = new JSONObject();
		obj.put("listaGarcons", new JSONArray(listaGarcom));
		
		return obj;
	}
}
