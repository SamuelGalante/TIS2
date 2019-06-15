package modelo;

import org.json.JSONObject;

public class ProdutoCardapio {
	private int idProdutoCardapio;
	private int idProdutos;
	private int idCardapio;
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
	public int getIdProduto_cardapio() {
		return idProdutoCardapio;
	}
	public void setIdProduto_cardapio(int idProdutoCardapio) {
		this.idProdutoCardapio = idProdutoCardapio;
	}
	public int getIdProdutos() {
		return idProdutos;
	}
	public void setIdProdutos(int idProdutos) {
		this.idProdutos = idProdutos;
	}
	public int getIdCardapio() {
		return idCardapio;
	}
	public void setIdCardapio(int idCardapio) {
		this.idCardapio = idCardapio;
	}
	
	public ProdutoCardapio(int idProdutos, int idCardapio, String nomeProduto, float valorProduto) {
		super();
		this.idProdutos = idProdutos;
		this.idCardapio = idCardapio;
		this.nomeProduto = nomeProduto;
		this.valorProduto = valorProduto;
	}
	
	public ProdutoCardapio() {
		idProdutoCardapio = 0;
		idProdutos = 0;
		idCardapio = 0;
		nomeProduto = "";
		valorProduto = 0;
	}
	
	
	@Override
	public String toString() {
		return "ProdutoCardapio [idProdutoCardapio=" + idProdutoCardapio + ", idProdutos=" + idProdutos
				+ ", idCardapio=" + idCardapio + ", nomeProduto=" + nomeProduto + ", valorProduto=" + valorProduto
				+ "]";
	}
	public JSONObject Produto_CardapioToJson(){
		
		JSONObject obj = new JSONObject();
		obj.put("idProdutos", this.getIdProdutos());
		obj.put("idCardapio", this.getIdCardapio());
		obj.put("nomeProduto", this.getNomeProduto());
		obj.put("valorProduto", this.getValorProduto());
		return obj;
	}
}
