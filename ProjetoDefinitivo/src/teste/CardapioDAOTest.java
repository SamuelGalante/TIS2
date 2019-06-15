package teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import modelDAO.CardapioDAO;
import modelo.Cardapio;

public class CardapioDAOTest {
	public static CardapioDAO c = new CardapioDAO();

	@Test
	public void testCreate() {
		Cardapio card = new Cardapio("Cardápio de Bebidas", 1);
		assertEquals(true, c.create(card));
	}
	
	@Test
	public void testRead() {
		assertEquals(c.getQuant(), c.read().size());
	}
	
	@Test
	public void testUpdate() {
		List<Cardapio> lista = c.read();
		int validId = lista.get(lista.size() - 1).getIdCardapio();
		Cardapio card = new Cardapio("Cardápio de Bebidas2", 1);
		assertEquals(true, c.update(validId, card));
	}
	
	@Test
	public void testDelete() {
		List<Cardapio> lista = c.read();
		int validId = lista.get(lista.size() - 1).getIdCardapio();
		assertEquals(true, c.delete(validId));
	}

}
