package services;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import modelDAO.ProdutoCardapioDAO;
import modelo.ProdutoCardapio;

public class ServiceProduto_Cardapio {

	private ProdutoCardapioDAO ProdutoCardapioDAO = new ProdutoCardapioDAO();
	
	public String create(Request request) {
		Query query = request.getQuery();
		
		try {
			ProdutoCardapio p = new ProdutoCardapio();

			p.setIdCardapio(query.getInteger("idCardapio"));
			p.setIdProdutos(query.getInteger("idProdutos"));
			ProdutoCardapioDAO.create(p);
			
			return "ProdutoCardapio criado"; //listaJson().toString();
		}catch(Exception e){

			return "Erro: ServiceProduto_Cardapio " + e;
		}
	}
	
	public String readProdutosCardapio(Request request) {
		Query query = request.getQuery();
		Integer idCardapio = query.getInteger("idCardapio");
		try {
			return listaJsonJoin(idCardapio).toString();
		}
		catch (Exception e) {
			return "Erro ao listar os ProdutoCardapio" + e;
		}
	}
	
	
	
	public String read(Request request) {
		
		try {
			return listaJson().toString();
		}
		catch (Exception e) {
			return "Erro ao listar os ProdutoCardapio" + e;
		}
	}

	public String update(Request request) {
		Query query = request.getQuery();
		Integer idProdutoCardapio = query.getInteger("id");
		try {
			ProdutoCardapio p = new ProdutoCardapio();
			p.setIdProduto_cardapio(query.getInteger("idCardapio"));
			p.setIdProdutos(query.getInteger("idCardapio"));
			ProdutoCardapioDAO.update(idProdutoCardapio, p);
		
			return "ProdutoCardapio atualizado"; //listaJson().toString();
		}catch(Exception e){

			return "Erro: ServiceProduto_Cardapio " + e;
		}
	}
	
	public String delete(Request request){
		Query query = request.getQuery();
		Integer idCardapio = query.getInteger("id");
		try {
			ProdutoCardapioDAO.delete(idCardapio);
			return "ProdutoCardapio deletado";
		}
		catch(Exception e){
			return "Erro ao deletar o ProdutoCardapio" + e;
		}
	}
	
	
//	
//	Lista gerada pelo SELECT com inner
	public JSONObject listaJsonJoin(Integer idCardapio) {
		List<ProdutoCardapio> listaProdutoCardapio = ProdutoCardapioDAO.readProdutosCardapio(idCardapio);
		
		JSONArray array = new JSONArray();
		
		for (ProdutoCardapio p: listaProdutoCardapio) {
			array.put(p.Produto_CardapioToJson());
		}
		JSONObject obj = new JSONObject();
		obj.put("listaProdutosCardapio", new JSONArray(listaProdutoCardapio));
		
		return obj;
	}
	
//	Lista gerada pelo select *
	public JSONObject listaJson() {
		List<ProdutoCardapio> listaProdutoCardapio = ProdutoCardapioDAO.read();
		
		JSONArray array = new JSONArray();
		
		for (ProdutoCardapio p: listaProdutoCardapio) {
			array.put(p.Produto_CardapioToJson());
		}
		JSONObject obj = new JSONObject();
		obj.put("listaProdutoCardapios", new JSONArray(listaProdutoCardapio));
		
		return obj;
	}
}
