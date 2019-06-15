package modelo;
import org.json.JSONObject;

public class Produto {
	private int idProdutos;
	private String tipo;
	private String descricao;
	private String nome;
	private float valor;
	private String img;
	
	public int getIdProdutos() {
		return idProdutos;
	}
	public void setIdProdutos(int idProdutos) {
		this.idProdutos = idProdutos;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Produto(String tipo, String descricao, String nome, float valor, String img) {
		super();
		setTipo(tipo);
		setDescricao(descricao);
		setNome(nome);
		setValor(valor);
		setImg(img);
	}
	

	@Override
	public String toString() {
		return "Produto [idProdutos=" + idProdutos + ", tipo=" + tipo + ", descricao=" + descricao + ", nome=" + nome
				+ ", valor=" + valor + ", img=" + img + "]";
	}
	public Produto() {
		idProdutos = 0;
		tipo = "";
		descricao = "";
		nome = "";
		valor = 0;
		img="";
	}
	
	public JSONObject produtoToJson(){
		
		JSONObject obj = new JSONObject();
		obj.put("idProdutos", this.getIdProdutos());
		obj.put("tipo", this.getTipo());
		obj.put("descricao", this.getDescricao());
		obj.put("nome", this.getNome());
		obj.put("valor", this.getValor());
		return obj;
	}
	
	public JSONObject imagemToJson() {
		JSONObject obj = new JSONObject();
		obj.put("imagem", this.getImg());
		return obj;
	}
}