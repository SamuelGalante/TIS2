package teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import modelDAO.ComandaDAO;
import modelo.Comanda;

public class ComandaDAOTest {
	public static ComandaDAO c = new ComandaDAO();

	@Test
	public void testCreate() {
		Comanda com = new Comanda(1, "17/05/2019", 1, 10, 100);
		assertEquals(true, c.create(com));
	}
	
	@Test
	public void testRead() {
		assertEquals(c.getQuant(), c.read().size());
	}
	
	@Test
	public void testUpdate() {
		List<Comanda> lista = c.read();
		int validId = lista.get(lista.size() - 1).getIdComanda();
		Comanda com = new Comanda(1, "17/05/2019", 1, 11, 101);
		assertEquals(true, c.update(validId, com));
	}
	
	@Test
	public void testDelete() {
		List<Comanda> lista = c.read();
		int validId = lista.get(lista.size() - 1).getIdComanda();
		assertEquals(true, c.delete(validId));
	}

}
