package test;

import com.sun.source.tree.AssertTree;
import mediatheque.*;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Audio;
import mediatheque.document.Document;
import mediatheque.document.Livre;
import mediatheque.document.Video;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import util.Datutil;
import util.InvariantBroken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static util.Datutil.addDate;

/**
 * Created by Robinson on 05/11/2016.
 */
@RunWith(Parameterized.class)
public class FicheEmpruntTest {

    private Mediatheque mediatheque;
    private Client client;
    private Document document;

    private FicheEmprunt fichEmprunt;

    public FicheEmpruntTest(Mediatheque mediatheque, Client client, Document document) throws OperationImpossible, InvariantBroken {
        this.mediatheque = mediatheque;
        this.client = client;
        this.document = document;

        if (!document.estEmpruntable()){
            document.metEmpruntable();
        }

        if (fichEmprunt == null){
            fichEmprunt = new FicheEmprunt(mediatheque, client, document);
        }

    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() throws IOException, OperationImpossible, InvariantBroken {

        CategorieClient cat1 = new CategorieClient("discounted",2,0,0.5,0.5,true);
        CategorieClient cat2 = new CategorieClient("normal",5,0,1,1,false);
        CategorieClient cat3 = new CategorieClient("subscriber",10,20,2,0,false);

        Mediatheque mediatheque = new Mediatheque("Central");

        Client client1 = new Client("Damien", "Francois", "QG 75015 Paris", cat1, 1);
        Client client2 = new Client("Damien", "Francois", "QG 75015 Paris", cat2);
        Client client3 = new Client("Damien", "Francois", "QG 75015 Paris", cat3);

        Audio audio = new Audio("2132", new Localisation("1","5"), "Mon bijou", "Jul", "2016", new Genre("R&B"), "B7");
        Livre livre = new Livre("8132", new Localisation("2","5"), "Les Fourmis", "Bernad Werber", "2013", new Genre("Roman"), 731);
        Video video = new Video("5434", new Localisation("1","3"), "Le diner de con", "Francois Veber", "1998", new Genre("Comedie"), 110,"metion legal");

        return Arrays.asList(
                new Object[] {mediatheque, client1, audio},
                new Object[] {mediatheque, client1, livre},
                new Object[] {mediatheque, client1, video},
                new Object[] {mediatheque, client2, audio},
                new Object[] {mediatheque, client2, livre},
                new Object[] {mediatheque, client2, video},
                new Object[] {mediatheque, client3, audio},
                new Object[] {mediatheque, client3, livre},
                new Object[] {mediatheque, client3, video}
        );
    }


    @Test
    public void TestVerifier() throws OperationImpossible {
        Date d = fichEmprunt.getDateLimite();

        fichEmprunt.verifier();
        assertEquals(d, fichEmprunt.getDateLimite());

        fichEmprunt.getClient().setCategorie(new CategorieClient("test", 1, 0, 0, 0, false));
        assertNotEquals(d, fichEmprunt.getDateLimite());

        d = fichEmprunt.getDateLimite();

        fichEmprunt.verifier();
        assertEquals(d, fichEmprunt.getDateLimite());

    }

    @Test
    public void testModifierClient() throws OperationImpossible {
        Client c = new Client("Barnard", "Laporte");
        System.out.println(fichEmprunt);
        assertEquals(client, fichEmprunt.getClient());
        fichEmprunt.modifierClient(c);
        assertEquals(c, fichEmprunt.getClient());
        fichEmprunt.modifierClient(client);
    }

    @Test
    public void testCorrespond() throws OperationImpossible, InvariantBroken {
        assertTrue(fichEmprunt.correspond(client, document));
        Client c = new Client("Barnard", "Laporte");
        assertFalse(fichEmprunt.correspond(c, document));
        Video v = new Video("1", new Localisation("1","3"), "Le diner", "Francois Veber", "1998", new Genre("Comedie"), 110,"metion legal");
        assertFalse(fichEmprunt.correspond(client, v));
        assertFalse(fichEmprunt.correspond(c, v));
    }

    @Test
    public void restituer() throws OperationImpossible, InvariantBroken {
        assertEquals(1, client.getNbEmpruntsEnCours());
        fichEmprunt.restituer();
        assertEquals(0, client.getNbEmpruntsEnCours());
        assertFalse(document.estEmprunte());

        /*
         * Tester lever d'exception
         */

    }

    @Test
    public void getDocument() {
        assertEquals(document, fichEmprunt.getDocument());
    }

    @Test
    public void getDateEmprunt() {
        Date dateEmprunt;
        assertEquals(dateEmprunt = Datutil.dateDuJour(), fichEmprunt.getDateEmprunt());
    }

    @Test
    public void getDateLimite() {
        Date dateEmprunt = Datutil.dateDuJour();
        assertEquals( client.dateRetour(dateEmprunt, document.dureeEmprunt()), fichEmprunt.getDateLimite());
    }

    @Test
    public void getDepasse() {
        assertFalse(fichEmprunt.getDepasse());
    }

    @Test
    public void getDureeEmprunt() {
        double d = document.dureeEmprunt()*client.getCoefDuree();
        int expected = (int) d;
        assertEquals(expected, fichEmprunt.getDureeEmprunt());
    }

    @Test
    public void getTarifEmprunt() {
        double expected = document.tarifEmprunt()*client.getCoefTarif();
        assertEquals(expected, fichEmprunt.getTarifEmprunt());
    }

    @Test
    public void changementCategorie() {
        try {
            fichEmprunt.getClient().setCategorie(new CategorieClient("test", 1, 0, 0, 0, false));
        } catch (OperationImpossible operationImpossible) {
            operationImpossible.printStackTrace();
        }

        try {
            fichEmprunt.changementCategorie();
            fail();
        } catch (OperationImpossible e) {
            e.printStackTrace();
        }finally {
            assertTrue(fichEmprunt.getDepasse());
            assertEquals(1, fichEmprunt.getClient().getNbEmpruntsEnRetard());
        }

        try {
            fichEmprunt.getClient().setCategorie(new CategorieClient("test", 1, 0, 1, 0, false));
        } catch (OperationImpossible operationImpossible) {
            operationImpossible.printStackTrace();
        }
        try {
            assertFalse(fichEmprunt.changementCategorie());
            assertFalse(fichEmprunt.getDepasse());
        } catch (OperationImpossible operationImpossible) {
            fail();
        }
    }

    @Test
    public void TestToString() throws Exception {
        assertEquals(String.class, fichEmprunt.toString().getClass());

        try {
            fichEmprunt.getClient().setCategorie(new CategorieClient("test", 1, 0, 0, 0, false));
        } catch (OperationImpossible operationImpossible) {
            operationImpossible.printStackTrace();
        }
        assertEquals(String.class, fichEmprunt.toString().getClass());
    }

    @After
    public void after() throws OperationImpossible, InvariantBroken {
        if (document.estEmprunte())
        fichEmprunt.restituer();
    }

}