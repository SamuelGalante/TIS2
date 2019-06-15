package modelo;

import org.json.JSONObject;

public class Cardapio {
	private int idCardapio;
	private String descricao;
	private int status;

	public int getIdCardapio() {
		return idCardapio;
	}

	public void setIdCardapio(int idCardapio) {
		this.idCardapio = idCardapio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public Cardapio(String descricao, int status) {
		setDescricao(descricao);
		setStatus(status);
	}
	public Cardapio() {
		idCardapio = 0;
		descricao = "";
		status = 0;
	}

	@Override
	public String toString() {
		return "Cardapio [idCardapio=" + idCardapio + ", descricao=" + descricao
				+ ", status=" + status + "]";
	}

	public JSONObject cardapioToJson() {

		JSONObject obj = new JSONObject();
		obj.put("idCardapio", this.getIdCardapio());
		obj.put("descricao", this.getDescricao());
		obj.put("status", this.getStatus());
		return obj;
	}
}
