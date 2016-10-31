package mediathequeTest.clientTest;

import mediatheque.client.CategorieClient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Robinson on 31/10/2016.
 */
public class CategorieClientTest {

    CategorieClient categorieClient;
    @Before
    public void setUp() throws Exception {
        categorieClient = new CategorieClient("categorieName", 2, 250, 0.5, 0.5, true);

    }

    @Test
    public void testModifierNom() {
        categorieClient.modifierNom("discounted");
        assertEquals("discounted", categorieClient.getNom());

        categorieClient.modifierNom(null);
        assertNull(categorieClient.getNom());
    }

    @Test
    public void testModifierMax() {
        categorieClient.modifierMax(4);
        assertEquals(4, categorieClient.getNbEmpruntMax());
    }

    @Test
    public void testModifierCotisation() {

    }

    @Test
    public void testModifierCoefDuree() {

    }

    @Test
    public void testModifierCoefTarif() {

    }

    @Test
    public void testModifierCodeReducActif() {

    }

    @Test
    public void getNbEmpruntMax() throws Exception {

    }

    @Test
    public void getCotisation() throws Exception {

    }

    @Test
    public void getCoefDuree() throws Exception {

    }

    @Test
    public void getCoefTarif() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void getNom() throws Exception {

    }

    @Test
    public void getCodeReducUtilise() throws Exception {

    }

    @Test
    public void testHashCode() throws Exception {

    }

    @Test
    public void equals() throws Exception {

    }

}