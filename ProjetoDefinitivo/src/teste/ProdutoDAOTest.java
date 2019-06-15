package teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import modelDAO.ProdutoDAO;
import modelo.Produto;

public class ProdutoDAOTest {
	public static ProdutoDAO p = new ProdutoDAO();

	@Test
	public void testCreate() {
		Produto prod = new Produto("Bebida", "Coca-Cola", "Refrigerante", 6, null); 
		assertEquals(true, p.create(prod));
	}
	
	@Test
	public void testRead() {
		assertEquals(p.getQuant(), p.read().size());
	}
	
	@Test
	public void testUpdate() {
		List<Produto> lista = p.read();
		int validId = lista.get(lista.size() - 1).getIdProdutos();
		Produto prod = new Produto("Bebida3", "Coca-Cola3", "Refrigerante3", 6, null ); 
		assertEquals(true, p.update(validId, prod));
	}
	
	@Test
	public void testDelete() {
		List<Produto> lista = p.read();
		int validId = lista.get(lista.size() - 1).getIdProdutos();
		assertEquals(true, p.delete(validId));
	}

}
