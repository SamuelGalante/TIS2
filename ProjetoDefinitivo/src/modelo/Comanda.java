package modelo;
import org.json.JSONObject;

public class Comanda {
	private int idComanda;
	private String data;
	private int mesa;
	private int avaliacao;
	private int pagamento;
	
	public int getIdComanda() {
		return idComanda;
	}
	public void setNumero(int idComanda) {
		this.idComanda = idComanda;
	}
	public String getData() {
		return data;
	}
	public void setData(String date) {
		this.data = date;
	}
	public int getMesa() {
		return mesa;
	}
	public void setMesa(int mesa) {
		this.mesa = mesa;
	}
	public int getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	public int getPagamento() {
		return pagamento;
	}
	public void setPagamento(int pagamento) {
		this.pagamento = pagamento;
	}
	
	public Comanda(int idComanda, String data, int mesa, int avaliacao, int pagamento) {
		super();
		this.idComanda = idComanda;
		this.data = data;
		this.mesa = mesa;
		this.avaliacao = avaliacao;
		this.pagamento = pagamento;
	}
	
	public Comanda() {
		idComanda = 0;
		data = null;
		mesa = 0;
		avaliacao = 0;
		pagamento = 0;
	}
	@Override
	public String toString() {
		return "Comanda [idComanda=" + idComanda + ", data=" + data + ", mesa=" + mesa + ", avaliacao=" + avaliacao
				+ ", pagamento=" + pagamento + "]";
	}
	
	public JSONObject comandaToJson() {
		JSONObject obj = new JSONObject();
		obj.put("numero", this.getIdComanda());
		obj.put("data", this.getData());
		obj.put("mesa", this.getMesa());
		obj.put("avaliacao", this.getAvaliacao());
		obj.put("pagamento", this.getPagamento());
		return obj;
	}
}
