package mediathequeTest;

import static org.junit.Assert.*;

import mediatheque.Genre;
import org.junit.Before;
import org.junit.Test;

public class GenreTest {

	public Genre genre;

	@Before
	public void setUp() {
		genre = new Genre("Action");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenre() {
		assertNotNull(genre);
		assertEquals("Action", genre.getNom());
		assertEquals(0, genre.getNbEmprunts());
	}

	@Test
	public void testEmprunter() {
		for(int i = 1; i <= 5; i++){
			genre.emprunter();
			assertEquals(i, genre.getNbEmprunts());
		}
	}

	@Test
	public void testGetNom() {
		assertEquals("Action", genre.getNom());
	}

	@Test
	public void testToString() {
		assertEquals("Genre: Action, nbemprunts:0", genre.toString());
		genre.emprunter();
		assertEquals("Genre: Action, nbemprunts:1", genre.toString());
	}

	@Test
	public void testGetNbEmprunts() {
		for(int i = 1; i <= 5; i++){
			genre.emprunter();
			assertEquals(i, genre.getNbEmprunts());
		}
	}

	@Test
	public void testEqualsObject() {
		assertTrue(genre.equals(genre));

		//If object diff of Genre
		assertFalse(genre.equals(1));
		assertFalse(genre.equals(1.1));
		assertFalse(genre.equals("test"));
		assertFalse(genre.equals(null));

		//is name dif on the two object
		Genre genre2 = new Genre("Cinema");
		assertFalse(genre.equals(genre2));
		Genre genre3 = new Genre(null);
		assertFalse(genre.equals(genre3));
		Genre genre4 = new Genre(null);
		assertTrue(genre3.equals(genre4));

		Genre genre5 = new Genre("Action");
		assertTrue(genre.equals(genre5));
	}

	@Test
	public void testAfficherStatistiques() {
		fail("la fonction affiche l'instance de l'objet et pas des statistique sur les emprunts");
	}
}
