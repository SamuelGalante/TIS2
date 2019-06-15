package modelo;

import org.json.JSONObject;

public class Pedidos {
	private int idPedidos;
	private int idComanda;
	private int idProdutos;
	private int quantidade;
	private String nomeProduto;
	private float valorProduto;
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public float getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(float valorProduto) {
		this.valorProduto = valorProduto;
	}
	
	public int getIdPedidos() {
		return idPedidos;
	}
	public void setIdPedidos(int idPedidos) {
		this.idPedidos = idPedidos;
	}
	public int getIdComanda() {
		return idComanda;
	}
	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}
	public int getIdProdutos() {
		return idProdutos;
	}
	public void setIdProdutos(int idProdutos) {
		this.idProdutos = idProdutos;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public Pedidos(int idComanda, int idProdutos, int quantidade, String nomeProduto, float valorProduto) {
		super();
		this.idComanda = idComanda;
		this.idProdutos = idProdutos;
		this.quantidade = quantidade;
		this.nomeProduto = nomeProduto;
		this.valorProduto = valorProduto;
	}
	
	public Pedidos() {
		idPedidos = 0;
		idComanda = 0;
		idProdutos = 0;
		quantidade = 0;
		nomeProduto = "";
		valorProduto = 0;
	}	
	

	@Override
	public String toString() {
		return "Pedidos [idPedidos=" + idPedidos + ", idComanda=" + idComanda + ", idProdutos=" + idProdutos
				+ ", quantidade=" + quantidade + ", nomeProduto=" + nomeProduto + ", valorProduto=" + valorProduto
				+ "]";
	}
	public JSONObject pedidosToJson() {
		JSONObject obj = new JSONObject();
		obj.put("idPedidos", this.getIdPedidos());
		obj.put("idComanda", this.getIdComanda());
		obj.put("idProdutos", this.getIdProdutos());
		obj.put("quantidade", this.getQuantidade());
		obj.put("nomeProduto", this.getNomeProduto());
		obj.put("valorProduto", this.getValorProduto());
		
		return obj;
	}
}
