package services;



import modelo.Produto;
import modelDAO.ProdutoDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
import java.util.*;

public class ServiceProduto {
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	public String create(Request request) {
		Query query = request.getQuery();
				
		try {
		String url = query.get("imgFile");
		String[] data;
		data = url.split(",");
		Produto p = new Produto();
		p.setTipo(query.get("tipo"));
		p.setDescricao(query.get("descricao"));
		p.setNome(query.get("nome"));
		p.setValor(query.getFloat("valor"));
		p.setImg(data[1]);
		produtoDAO.create(p);
		System.out.println(p.toString());

		return query.get("url");
		}catch(Exception e){
			
			return "Erro ao criar o produto" + e;
		}
	}
	
	public String read(Request request) {
		try {
			return listaJson().toString();
		}
		catch (Exception e) {
			return "Erro ao listar os produtos" + e;
		}
	}
	
	public String readImagem(Request request) {
		Query query = request.getQuery();
		Integer idProduto = query.getInteger("idProduto");
		try {
			return imagemJson(idProduto).toString();
		}catch (Exception e) {
			return "Erro ao ler a imagem." + e;
		}
	}
	
	public String update(Integer idProdutos, Request request) {
		Query query = request.getQuery();
		Integer id = query.getInteger("idProduto");
		try {
			Produto p = new Produto();
			p.setTipo(query.get("tipo"));
			p.setDescricao(query.get("descricao"));
			p.setNome(query.get("nome"));
			p.setValor(query.getFloat("valor"));
			produtoDAO.update(id, p);
			
			return "Produto atualizado";
		}catch(Exception e){
			
			return "Erro ao atualizar o produto" + e;
		}
	}
	
	public String delete(Request request){
		Query query = request.getQuery();
		Integer id = query.getInteger("idPeoduto");
		try {
			
			produtoDAO.delete(id);
			return "Produto deletado";
		}
		catch(Exception e){
			return "Erro ao deletar o produto" + e;
		}
	}
	
	public JSONObject imagemJson(Integer idProduto) {
		List<Produto> listaProdutos = produtoDAO.readImage(idProduto);
		JSONArray array = new JSONArray();
		
		for (Produto p: listaProdutos) {
			array.put(p.produtoToJson());
		}
		
		JSONObject obj = new JSONObject();
		obj.put("imgProduto", new JSONArray(listaProdutos));

		return obj;		
	}
	public JSONObject listaJson() {
		List<Produto> listaProdutos = produtoDAO.read();
		
		JSONArray array = new JSONArray();
		
		for (Produto p: listaProdutos) {
			array.put(p.produtoToJson());
		}
		JSONObject obj = new JSONObject();
		obj.put("listaProdutos", new JSONArray(listaProdutos));
		
		return obj;
	}
	
}
