package test;

import mediatheque.OperationImpossible;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import mediatheque.document.*;
import mediatheque.Localisation;
import mediatheque.Genre;
import util.InvariantBroken;


import static org.junit.Assert.*;
/**
 * Created by tosnos on 05/11/16.
 */

@RunWith(value = BlockJUnit4ClassRunner.class)
public class LivreTest {

    private Livre livretest;

    @Before
    public void setUp() throws Exception {

        livretest = new Livre("codeTest", new Localisation("l1","l2"), "titreTest", "auteurTest", "anneeTest", new Genre("genreTest"), 40);
        livretest.metEmpruntable();
        livretest.emprunter();

    }

    @After
    public void tearDown() throws Exception {

    }


    //Problème ne retourne pas l'exception car considere toujours l'integer positif
    @Test
    public void testNegativePages() {
           try {
            Livre livrefail = new Livre("codeTest", new Localisation("l1", "l2"), "titreTest", "auteurTest", "anneTest", new Genre("genreTest"), -40);
            fail("Book without pages created");
        } catch (OperationImpossible e) {}
        catch (InvariantBroken e) {}
    }

    @Test
    public void testDureeEmprunt(){
        assertEquals(6*7, livretest.dureeEmprunt());
    }

    @Test
    public void testTarifEmprunt(){
        assertEquals(0.5, livretest.tarifEmprunt(), 0.0);

    }

    /***
     * Test class Document
     */

    @Test
    public void testGetAnnee(){
        assertEquals("anneeTest", livretest.getAnnee());
    }

    @Test
    public void testGetGenre() throws OperationImpossible, InvariantBroken {
        Livre l = new Livre("8132", new Localisation("2","5"), "Les Fourmis", "Bernad Werber", "2013", new Genre("genreTest"), 731);
        l.metEmpruntable();
        l.emprunter();

        assertEquals(l.getGenre(), livretest.getGenre());
    }

    @Test
    public void testGetAuteur(){
        assertEquals("auteurTest", livretest.getAuteur());
    }

    @Test
    public void testGetCode(){
        assertEquals("codeTest", livretest.getCode());
    }

    @Test
    public void testEquals() throws OperationImpossible, InvariantBroken{
        Object objNull = null;
        Object objSame = livretest;
        Object notInstanceOfClass = new String("test");
        Livre livrebis = new Livre("codeTest", new Localisation("l1","l2"), "titreTest", "auteurTest", "anneeTest", new Genre("genreTest"), 40);
        assertFalse(livretest.equals(objNull));
        assertTrue(livretest.equals(objSame));
        assertFalse(livretest.equals(notInstanceOfClass));
        assertTrue(livretest.equals(livrebis));
    }

    /***
     * Ne fonctionne pas
     */
    @Test
    public void testEmpruntImpossible(){
        try{
           livretest.emprunter();
        } catch(OperationImpossible e) {}
        catch (InvariantBroken e){}
    }
}