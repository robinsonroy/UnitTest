package mediathequeTest.clientTest;

import mediatheque.client.CategorieClient;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
//import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
    public void constructor(){
        assertEquals("categorieName", categorieClient.getNom());
        assertEquals(2, categorieClient.getNbEmpruntMax());
        assertEquals(250.0, categorieClient.getCotisation());
        assertEquals(0.5, categorieClient.getCoefDuree());
        assertEquals(0.5, categorieClient.getCoefTarif());
        assertEquals(true, categorieClient.getCodeReducUtilise());

        CategorieClient catClient = new CategorieClient("test");
        assertEquals("test", catClient.getNom());
        assertEquals(0, catClient.getNbEmpruntMax());
        assertEquals(0.0, catClient.getCotisation());
        assertEquals(0.0, catClient.getCoefDuree());
        assertEquals(0.0, catClient.getCoefTarif());
        assertEquals(false, catClient.getCodeReducUtilise());
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
        categorieClient.modifierCotisation(200);
        assertEquals(200.0, categorieClient.getCotisation());
    }

    @Test
    public void testModifierCoefDuree() {
        categorieClient.modifierCoefDuree(2);
        assertEquals(2.0, categorieClient.getCoefDuree());

    }

    @Test
    public void testModifierCoefTarif() {
        categorieClient.modifierCoefTarif(2);
        assertEquals(2.0, categorieClient.getCoefTarif());
    }

    @Test
    public void testModifierCodeReducActif() {
        categorieClient.modifierCodeReducActif(false);
        assertFalse(categorieClient.getCodeReducUtilise());

        categorieClient.modifierCodeReducActif(true);
        assertTrue(categorieClient.getCodeReducUtilise());
    }

    @Test
    public void testGetNbEmpruntMax() {
        assertEquals(2, categorieClient.getNbEmpruntMax());
    }

    @Test
    public void testGetCotisation() {
        assertEquals(250.0, categorieClient.getCotisation());
    }

    @Test
    public void getCoefDuree() throws Exception {
        assertEquals(0.5, categorieClient.getCoefDuree());
    }

    @Test
    public void getCoefTarif() throws Exception {
        assertEquals(0.5, categorieClient.getCoefTarif());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("categorieName", categorieClient.toString());
    }

    @Test
    public void getNom() throws Exception {
        assertEquals("categorieName", categorieClient.getNom());
    }

    @Test
    public void getCodeReducUtilise() throws Exception {
        assertTrue(categorieClient.getCodeReducUtilise());
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(categorieClient.hashCode(), categorieClient.hashCode());
        CategorieClient c1 = new CategorieClient("Normal");
        assertNotEquals(categorieClient.hashCode(), c1.hashCode());
    }

    @Test
    public void equals() throws Exception {
        assertFalse(categorieClient.equals(null));
        assertFalse(categorieClient.equals(new Object()));
        assertTrue(categorieClient.equals(categorieClient));

        CategorieClient c1 = new CategorieClient("categorieName", 2, 250, 0.5, 0.5, true);
        assertTrue(categorieClient.equals(c1));
        assertTrue(c1.equals(categorieClient));

        c1.modifierNom(null);
        assertFalse(c1.equals(categorieClient));

        CategorieClient c2 = new CategorieClient("test");
        assertFalse(categorieClient.equals(c2));
        c2.modifierNom("categorieName");
        c2.modifierCodeReducActif(true);
        assertTrue(categorieClient.equals(c2));

    }

}