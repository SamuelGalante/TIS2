package teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import modelDAO.CardapioDAO;
import modelDAO.ProdutoCardapioDAO;
import modelDAO.ProdutoDAO;
import modelo.ProdutoCardapio;

public class ProdutoCardapioDAOTest {
	public static ProdutoCardapioDAO p = new ProdutoCardapioDAO();

	@Test
	public void testCreate() {
		CardapioDAO com = new CardapioDAO();
		int idCardapio = com.read().get(0).getIdCardapio();
		
		ProdutoDAO prod = new ProdutoDAO();
		int idProdutos = prod.read().get(0).getIdProdutos();
		
		ProdutoCardapio prod_card = new ProdutoCardapio(idProdutos, idCardapio, "água", 2);
		assertEquals(true, p.create(prod_card));
	}
	
	@Test
	public void testRead() {
		System.out.println(p.getQuant());
		System.out.println(p.read().size());
		assertEquals(p.getQuant(), p.read().size());
	}
	
	@Test
	public void testUpdate() {
		CardapioDAO com = new CardapioDAO();
		int idCardapio = com.read().get(0).getIdCardapio();
		
		ProdutoDAO prod = new ProdutoDAO();
		int idProdutos = prod.read().get(0).getIdProdutos();
		
		ProdutoCardapio prod_card = new ProdutoCardapio(idProdutos, idCardapio, "água", 2);
		System.out.println(prod_card);
		
		List<ProdutoCardapio> lista = p.read();
		int validId = lista.get(lista.size() - 1).getIdProduto_cardapio();
		System.out.println(prod_card);
		assertEquals(true, p.update(validId, prod_card));
	}
	
	@Test
	public void testDelete() {
		List<ProdutoCardapio> lista = p.read();
		int validId = lista.get(lista.size() - 1).getIdProduto_cardapio();
		System.out.println(lista.get(lista.size() - 1));
		assertEquals(true, p.delete(validId));
	}

}
