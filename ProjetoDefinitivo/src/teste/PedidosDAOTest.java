package teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import modelDAO.PedidosDAO;
import modelDAO.ComandaDAO;
import modelo.Pedidos;
import modelDAO.ProdutoDAO;

public class PedidosDAOTest {
	public static PedidosDAO p = new PedidosDAO();

	@Test
	public void testNovoPedido() {
		ComandaDAO com = new ComandaDAO();
		int idComanda = com.read().get(0).getIdComanda();
		
		ProdutoDAO prod = new ProdutoDAO();
		int idProdutos = prod.read().get(0).getIdProdutos();
		
		Pedidos ped = new Pedidos(idComanda, idProdutos, 1, "água", 2);
		assertEquals(true, p.create(ped));
	}
	
	@Test
	public void testRead() {
		assertEquals(p.getQuant(), p.read().size()); 
	}
	
	@Test
	public void testUpdate() {
		ComandaDAO com = new ComandaDAO();
		int idComanda = com.read().get(0).getIdComanda();
		
		ProdutoDAO prod = new ProdutoDAO();
		int idProdutos = prod.read().get(0).getIdProdutos();
		
		Pedidos ped = new Pedidos(idComanda, idProdutos, 2,  "água", 2); 
		
		List<Pedidos> lista = p.read();
		int validId = lista.get(lista.size() - 1).getIdPedidos();
		assertEquals(true, p.update(validId, ped));
	}
	
	@Test
	public void testDelete() {
		List<Pedidos> lista = p.read();
		int validId = lista.get(lista.size() - 1).getIdPedidos();
		assertEquals(true, p.delete(validId));
	}

}
