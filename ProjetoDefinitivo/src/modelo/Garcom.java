package modelo;

import org.json.JSONObject;

public class Garcom {
	
	private int idGarcom;
	private String nome;
	
	public int getIdGarcom() {
		return idGarcom;
	}
	public void setIdGarcom(int idGarcom) {
		this.idGarcom = idGarcom;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Garcom(int idGarcom, String nome) {
		super();
		this.idGarcom = idGarcom;
		this.nome = nome;
	}
	
	public Garcom() {
		idGarcom = 0;
		nome = "";
	}
	@Override
	public String toString() {
		return "Garcom [idGarcom=" + idGarcom + ", nome=" + nome + "]";
	}
	
	public JSONObject pedidosToJson() {
		JSONObject obj = new JSONObject();
		obj.put("idGarcom", this.getIdGarcom());
		obj.put("nome", this.getNome());
		return obj;
	}
}
