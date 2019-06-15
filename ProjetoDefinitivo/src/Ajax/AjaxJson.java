package Ajax;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.json.JSONException;
import org.json.JSONObject;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import services.ServiceCardapio;
import services.ServiceComanda;
import services.ServiceGarcom;
import services.ServicePedidos;
import services.ServiceProduto;
import services.ServiceProduto_Cardapio;

public class AjaxJson implements Container {

	static ServiceProduto produtos;
	static ServiceCardapio cardapio;
	static ServiceComanda comanda;
	static ServicePedidos pedidos;
	static ServiceProduto_Cardapio produtoCardapio;
	static ServiceGarcom garcons;

	public void handle(Request request, Response response) {
		try {
			// Recupera a URL e o método utilizado.
			String path = request.getPath().getPath();
			String method = request.getMethod();
		 	String mensagem;

		 	
//		 	GARÇONS
		 	

			if (path.equals("/ReadGarcons") && "GET".equals(method)) {
				mensagem = garcons.read(request);
				this.enviaResposta(Status.OK, response, mensagem);
	
			} else if (path.equals("/CreateGarcom") && "POST".equals(method)) {
				mensagem = garcons.create(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
	
			} else  if (path.equals("/RemoveGarcom") && "GET".equals(method)) {
				//http://localhost:7200/RemoveGarcom?idGarcons=19281982
			}
			 	
	//		 	PRODUTOS
			 	
	
			else if (path.equals("/ReadProdutos") && "GET".equals(method)) {
				mensagem = produtos.read(request);
				this.enviaResposta(Status.OK, response, mensagem);
	
			} else if (path.equals("/CreateProduto") && "POST".equals(method)) {
				mensagem = produtos.create(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
			}else if (path.equals("/ReadImagem") && "GET".equals(method)) {
				mensagem = produtos.readImagem(request);
				System.out.println(mensagem);
				this.enviaResposta(Status.OK, response, mensagem);
			}
	//			CARDAPIOS
			
	
			 else if (path.equals("/ReadCardapios") && "GET".equals(method)) {
				mensagem = cardapio.read(request);
				this.enviaResposta(Status.OK, response, mensagem);
	
			} else if (path.equals("/CreateCardapio") && "POST".equals(method)) {
				mensagem = cardapio.create(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
	
			} else if (path.equals("/RemoveCardapio") && "GET".equals(method)) {
				//http://localhost:7200/RemoveCardapio?idCardapio=19281982
				
			} else if (path.equals("/UpdateCardapio") && "GET".equals(method)) {
				//http://localhost:7200/UpdateCardapio?idCardapio=19281982
			} 
	
	//			COMANDAS
	
			else if (path.equals("/ReadComandas") && "GET".equals(method)) {
				mensagem = comanda.read(request);
				this.enviaResposta(Status.OK, response, mensagem);
	
			} else if (path.equals("/CreateComanda") && "POST".equals(method)) {
				mensagem = comanda.create(request);
				this.enviaResposta(Status.CREATED, response, mensagem);
	
			} else if (path.equals("/UpdateComanda") && "GET".equals(method)) {
				//http://localhost:7200/UpdateComanda?idComanda=19281982
				mensagem = comanda.update(request);
				this.enviaResposta(Status.OK, response, mensagem);
			} 
	
	
	//			PEDIDOS
	
			else if (path.equals("/ReadPedidos") && "GET".equals(method)) {
				mensagem = pedidos.read(request);
				this.enviaResposta(Status.OK, response, mensagem);
	
			} else if (path.equals("/ReadPedidosComanda") && "GET".equals(method)) {
	//			http://localhost:7200/ReadPedidosComanda?idComanda=19281982
				mensagem = pedidos.readPedidosComanda(request);
				this.enviaResposta(Status.OK, response, mensagem);
	
			} else if (path.equals("/CreatePedido") && "GET".equals(method)) {
				mensagem = pedidos.create(request);
				this.enviaResposta(Status.CREATED, response, mensagem);	
	
			}
	
	//			PRODUTO CARDAPIO
	
			else if (path.equals("/ReadProdutosCardapio") && "GET".equals(method)) {
	//			http://localhost:7200/ReadProdutosCardapio?idCardapio=19281982
				mensagem = produtoCardapio.readProdutosCardapio(request);
				this.enviaResposta(Status.OK, response, mensagem);
	
			} else if (path.equals("/CreateProdutoCardapio") && "POST".equals(method)) {
				mensagem = produtoCardapio.create(request);
				this.enviaResposta(Status.CREATED, response, mensagem);	
	
			} else if (path.equals("/RemoveProdutoCardapio") && "GET".equals(method)) {
	//			http://localhost:7200/RemoveProdutosCardapio?idCardapio=19281982&idProduto=19821982
			}
	
	
	//			ERRO
			else {
				this.naoEncontrado(response, path);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		// json.put("id", id);
		// json.put("text", text);
		return json;
	}

	private void naoEncontrado(Response response, String path) throws Exception {
		JSONObject error = new JSONObject();
		error.put("error", "Não encontrado.");
		error.put("path", path);
		enviaResposta(Status.NOT_FOUND, response, error.toString());
	}

	private void enviaResposta(Status status, Response response, String str) throws Exception {

		PrintStream body = response.getPrintStream();
		long time = System.currentTimeMillis();

		response.setValue("Content-Type", "application/json");
		response.setValue("Server", "Controle de EstoqueService (1.0)");
		response.setValue("Access-Control-Allow-Origin", "null");
		response.setDate("Date", time);
		response.setDate("Last-Modified", time);
		response.setStatus(status);

		if (str != null)
			body.println(str);
		body.close();
	}

	public static void main(String args[]) throws IOException {

		// Instancia o Produto Service
		garcons = new ServiceGarcom();
		produtos = new ServiceProduto();
		cardapio = new ServiceCardapio();
		comanda = new ServiceComanda();
		pedidos = new ServicePedidos();
		produtoCardapio = new ServiceProduto_Cardapio();
		int porta = 7200;

		// Configura uma conexão soquete para o servidor HTTP.
		Container container = new AjaxJson();
		ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
		Connection conexao = new SocketConnection(servidor);
		SocketAddress endereco = new InetSocketAddress(porta);
		conexao.connect(endereco);

		System.out.println("Tecle ENTER para interromper o servidor...");
		System.in.read();
		System.out.println("Servidor finalizado.");
		conexao.close();
		servidor.stop();

	}

}
