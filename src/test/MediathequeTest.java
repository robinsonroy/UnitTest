package test;

import mediatheque.*;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.client.HashClient;
import mediatheque.document.Audio;
import mediatheque.document.Document;
import mediatheque.document.Livre;
import mediatheque.document.Video;
import org.junit.Before;
import org.junit.Test;
import util.InvariantBroken;

import javax.print.Doc;
import java.util.Hashtable;
import java.util.Vector;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import static org.junit.Assert.*;

/**
 * Created by Robinson on 06/11/2016.
 */
public class MediathequeTest {

    private String nom;
    /** Objets geres par la mediatheque : Genre, Localisation, Client
     * Documents, FicheEmprunt
     */
    private Vector<Genre> lesGenres;
    private Vector<Localisation> lesLocalisations;
    private Vector<CategorieClient> lesCatsClient;
    private Hashtable<String, Document> lesDocuments;
    private Hashtable<HashClient,Client> lesClients;
    private Vector<FicheEmprunt> lesEmprunts;

    private Mediatheque mediatheque;


    @Before
    public void setUp() throws OperationImpossible, InvariantBroken {
        mediatheque = new Mediatheque("MediaTest");

        mediatheque.ajouterGenre("Action");
        mediatheque.ajouterGenre("Roman");

        mediatheque.ajouterLocalisation("2","5");

        mediatheque.ajouterCatClient("discounted",2,0,0.5,0.5,true);
        mediatheque.ajouterCatClient("normal",5,0,1,1,false);

        mediatheque.inscrire("Canteloup", "Nicolas", "hisaddress", "discounted", 1);


        mediatheque.ajouterDocument(new Livre("8132", new Localisation("2","5"), "Les Fourmis", "Bernad Werber", "2013", new Genre("Roman"), 731));

    }

    @Test
    public void tesTConstructor(){
        mediatheque = new Mediatheque("MediaTest");
        assertEquals("MediaTest", mediatheque.getNom());
        assertEquals(0, mediatheque.getGenresSize());
        assertEquals(0, mediatheque.getLocalisationsSize());
        assertEquals(0, mediatheque.getCategoriesSize());
        assertEquals(0, mediatheque.getDocumentsSize());
        assertEquals(0, mediatheque.getFicheEmpruntsSize());
        assertEquals(0, mediatheque.getClientsSize());
        assertFalse(mediatheque.initFromFile());
    }

    @Test
    public void chercherGenre() {
        Genre test = mediatheque.chercherGenre("Action");
        assertNotNull(test);
        test = mediatheque.chercherGenre("NotExisting");
        assertNull(test);
    }

    @Test
    public void supprimerGenre() throws OperationImpossible {
        int size = mediatheque.getGenresSize();
        mediatheque.supprimerGenre("Action");
        assertEquals(size - 1, mediatheque.getGenresSize());

        try {
            mediatheque.supprimerGenre("Action");
            fail("delete a none existing genre");
        } catch (OperationImpossible operationImpossible) {
        }

        try {
            mediatheque.supprimerGenre("Roman");
            fail("Supprime un genre relier a des documents");
        } catch (OperationImpossible operationImpossible) {
        }
    }

    @Test
    public void ajouterGenre() {
        try {
            mediatheque.ajouterGenre("Roman");
            fail();
        } catch (OperationImpossible operationImpossible) {
        }

    }

    @Test
    public void modifierGenre() {
        try {
            mediatheque.modifierGenre("Action", "test");
        } catch (OperationImpossible e) {
            fail(e.getMessage());
        }
        assertNotNull(mediatheque.chercherGenre("test"));
        assertNull(mediatheque.chercherGenre("Action"));

        try {
            mediatheque.modifierGenre("Action", "test");
            fail();
        } catch (OperationImpossible operationImpossible) {
        }


    }

    //Not a junit test
    @Test
    public void listerGenres()  {
        mediatheque.listerGenres();
    }

    @Test
    public void getGenreAt()  {
        Genre g = mediatheque.chercherGenre("Action");
        assertEquals(g, mediatheque.getGenreAt(0));
    }

    @Test
    public void getGenresSize()  {
        assertEquals(2, mediatheque.getGenresSize());
    }

    @Test
    public void supprimerLocalisation() throws OperationImpossible {
        int size = mediatheque.getLocalisationsSize();

        try {
            mediatheque.supprimerLocalisation("Not existing", "Not existing");
            fail();
        } catch (OperationImpossible operationImpossible) {
        }

        try {
            mediatheque.supprimerLocalisation("2", "5");
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Suppression de localisation impossible. Il existe au moins un document � la localisation Salle/Rayon : 2/5"
                    , e.getMessage());
        }

        mediatheque.retirerDocument("8132");

        try {
            mediatheque.supprimerLocalisation("2", "5");
        } catch (OperationImpossible operationImpossible) {
            fail();
        }
        assertEquals(size-1, mediatheque.getLocalisationsSize());

    }

    @Test
    public void chercherLocalisation()  {
        assertNotNull(mediatheque.chercherLocalisation("2", "5"));
        assertNull(mediatheque.chercherLocalisation("Not existing", "Not existing"));
    }

    @Test
    public void ajouterLocalisation()  {
        try {
            mediatheque.ajouterLocalisation("2","5");
            fail("add a existing location");
        } catch (OperationImpossible operationImpossible) {
        }

        try {
            mediatheque.ajouterLocalisation("salle","rayon");
        } catch (OperationImpossible operationImpossible) {
            fail("location not add");
        }

        assertNotNull(mediatheque.chercherLocalisation("salle","rayon"));

    }

    @Test
    public void modifierLocalisation()  {
        Localisation l = new Localisation("1","5");
        try {
            mediatheque.modifierLocalisation(l, "4", "8");
            fail("modif a not existing location");
        } catch (OperationImpossible operationImpossible) {
        }
        l = new Localisation("2","5");

        try {
            mediatheque.modifierLocalisation(l, "4", "8");
        } catch (OperationImpossible operationImpossible) {
            fail();
        }

        assertNotNull(mediatheque.chercherLocalisation("4", "8"));
    }

    @Test
    public void getLocalisationAt() throws OperationImpossible {
        Localisation l = new Localisation("2", "5");
        assertEquals(l, mediatheque.getLocalisationAt(0));
        mediatheque.ajouterLocalisation("salle","rayon");
        l = new Localisation("salle", "rayon");
        assertEquals(l, mediatheque.getLocalisationAt(1));
    }

    @Test
    public void getLocalisationsSize() throws OperationImpossible {
        assertEquals(1, mediatheque.getLocalisationsSize());
        mediatheque.ajouterLocalisation("salle","rayon");
        assertEquals(2, mediatheque.getLocalisationsSize());
    }

    @Test
    public void chercherCatClient()  {
        CategorieClient cat1 = new CategorieClient("discounted",2,0,0.5,0.5,true);

        assertEquals(cat1, mediatheque.chercherCatClient("discounted"));
        assertNull(mediatheque.chercherCatClient("Not existing"));
    }

    @Test
    public void supprimerCatClient()  {
        int size = mediatheque.getCategoriesSize();

        try {
            mediatheque.supprimerCatClient("Not existing");
            fail();
        } catch (OperationImpossible operationImpossible) {
        }

        try {
            mediatheque.supprimerCatClient("discounted");
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Il existe un client dans la categorie discounted"
                    , e.getMessage());
        }

        try {
            mediatheque.supprimerCatClient("normal");
        } catch (OperationImpossible operationImpossible) {
            fail();
        }
        assertEquals(size-1, mediatheque.getClientsSize());

    }

    @Test
    public void ajouterCatClient() {
        try {
            mediatheque.ajouterCatClient("discounted",10,0,0,0,false);
            fail("add a existing location");
        } catch (OperationImpossible operationImpossible) {
        }

        try {
            mediatheque.ajouterCatClient("Vip",10,0,0,0,false);
        } catch (OperationImpossible operationImpossible) {
            fail("location not add");
        }

        assertNotNull(mediatheque.chercherCatClient("Vip"));
    }

    @Test
    public void modifierCatClient()  {
        CategorieClient cc = new CategorieClient("test",10,0,0,0,false);
        try {
            mediatheque.modifierCatClient(cc,"Vip",10,0,0,0,false);
            fail("modif a not existing location");
        } catch (OperationImpossible operationImpossible) {
        }
        cc = mediatheque.chercherCatClient("discounted");

        try {
            mediatheque.modifierCatClient(cc,"VIP",10,1,0.4,0.2,false);
        } catch (OperationImpossible operationImpossible) {
            fail();
        }

        assertNotNull(mediatheque.chercherCatClient("VIP"));
        cc = mediatheque.chercherCatClient("VIP");
        assertEquals(10, cc.getNbEmpruntMax());
        assertEquals(1.0, cc.getCotisation());
        assertEquals(0.4, cc.getCoefDuree());
        assertEquals(0.2, cc.getCoefTarif());
        assertFalse(cc.getCodeReducUtilise());
    }

    @Test
    public void getCategorieAt() throws OperationImpossible {
        CategorieClient cc = new CategorieClient("discounted",2,0,0.5,0.5,true);
        assertEquals(cc, mediatheque.getCategorieAt(0));

        int size = mediatheque.getCategoriesSize();

        mediatheque.ajouterCatClient("VIP",10,1,0.4,0.2,false);
        cc = new CategorieClient("VIP",10,1,0.4,0.2,false);
        assertEquals(cc, mediatheque.getCategorieAt(size));
    }

    @Test
    public void getCategoriesSize() throws OperationImpossible {
        assertEquals(2, mediatheque.getCategoriesSize());
        mediatheque.ajouterCatClient("VIP",10,1,0.4,0.2,false);
        assertEquals(3, mediatheque.getCategoriesSize());
    }

    @Test
    public void chercherDocument()  {
        Document d = mediatheque.chercherDocument("8132");
        assertEquals("Les Fourmis", d.getTitre());
    }

    @Test
    public void ajouterDocument() throws InvariantBroken {
        try {
            mediatheque.ajouterDocument(new Livre("8132", new Localisation("0","5"), "un bouquin", "un auteur", "2013", new Genre("Roman"), 340));
            fail("Add a existing document");
        } catch (OperationImpossible e) {
            assertEquals("Document \"8132\" deja existant", e.getMessage());
        }

        try {
            mediatheque.ajouterDocument(new Livre("1", new Localisation("4","5"), "Les", "Werber", "2013", new Genre("Action"), 731));
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Ajout d'un document avec une localisation inexistante", e.getMessage());
        }

        try {
            mediatheque.ajouterDocument(new Livre("1", new Localisation("2","5"), "Les Fourmis", "Bernad Werber", "2013", new Genre("Not exisiting"), 731));
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Ajout d'un document avec un genre non inclus dans la mediatheque",e.getMessage());
        }

        try {
            mediatheque.ajouterDocument(new Livre("1", new Localisation("2","5"), "Au Bonheur des dames", "Emile Zola", "1930", new Genre("Roman"), 1540));
            mediatheque.ajouterDocument(new Audio("2", new Localisation("2","5"), "Clandestino", "Manu Ciao", "1996", new Genre("Action"), "bla"));
            mediatheque.ajouterDocument(new Video("3", new Localisation("2","5"), "Le dinner de con", "Francois verber", "1998", new Genre("Action"), 130,"bla"));
        }catch (OperationImpossible e){
            fail();
        }
        assertNotNull(mediatheque.chercherDocument("1"));
        assertNotNull(mediatheque.chercherDocument("2"));
        assertNotNull(mediatheque.chercherDocument("3"));


    }

    @Test
    public void retirerDocument() throws OperationImpossible, InvariantBroken {
        try {
            mediatheque.retirerDocument("2");
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Document 2 inexistant", e.getMessage());
        }

        try {
            mediatheque.retirerDocument("8132");
        } catch (OperationImpossible e) {
            fail();
        }

        mediatheque.ajouterDocument(new Livre("1", new Localisation("2","5"), "Au Bonheur des dames", "Emile Zola", "1930", new Genre("Roman"), 1540));
        mediatheque.metEmpruntable("1");
        mediatheque.chercherDocument("1").emprunter();

        try {
            mediatheque.retirerDocument("1");
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Document \"1\" emprunte", e.getMessage());
        }
    }

    @Test
    public void metEmpruntable() throws InvariantBroken {
        try {
            mediatheque.metEmpruntable("2");
            fail();
        } catch (OperationImpossible operationImpossible) {
        }
        try {
            mediatheque.metEmpruntable("8132");
            assertTrue(mediatheque.chercherDocument("8132").estEmpruntable());
        } catch (OperationImpossible operationImpossible) {
            fail();
        }
    }

    @Test
    public void metConsultable() throws InvariantBroken, OperationImpossible {

        try {
            mediatheque.metConsultable("2");
            fail();
        } catch (OperationImpossible operationImpossible) {
        }
        mediatheque.metEmpruntable("8132");
        try {
            mediatheque.metConsultable("8132");
            assertFalse(mediatheque.chercherDocument("8132").estEmpruntable());
        } catch (OperationImpossible operationImpossible) {
            fail();
        }

    }

    @Test
    public void getDocumentAt() throws OperationImpossible, InvariantBroken {
        Document document = new Livre("8132", new Localisation("2","5"), "Les Fourmis", "Bernad Werber", "2013", new Genre("Roman"), 731);

        assertEquals(document, mediatheque.getDocumentAt(0));

        int size = mediatheque.getDocumentsSize();

        mediatheque.ajouterDocument(new Livre("1", new Localisation("2","5"), "Au Bonheur des dames", "Emile Zola", "1930", new Genre("Roman"), 1540));
        assertEquals(document, mediatheque.getDocumentAt(size));
    }

    @Test
    public void getDocumentsSize() throws OperationImpossible, InvariantBroken {
        assertEquals(1, mediatheque.getDocumentsSize());
        mediatheque.ajouterDocument(new Livre("1", new Localisation("2","5"), "Au Bonheur des dames", "Emile Zola", "1930", new Genre("Roman"), 1540));
        assertEquals(2, mediatheque.getDocumentsSize());
    }

    @Test
    public void emprunter() throws InvariantBroken, OperationImpossible {


        try {
            mediatheque.emprunter("not existing","Nicolas","8132");
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Client not existing Nicolas inexistant", e.getMessage());
        }

        try {
            mediatheque.emprunter("Canteloup","Nicolas","3");
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Document 3 inexistant", e.getMessage());
        }

        try {
            mediatheque.emprunter("Canteloup","Nicolas","8132");
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Document 8132 non empruntable", e.getMessage());
        }
        mediatheque.metEmpruntable("8132");

        try {
            mediatheque.emprunter("Canteloup","Nicolas","8132");
        } catch (OperationImpossible operationImpossible) {
            fail();
        }
        assertEquals(mediatheque.chercherClient("Canteloup", "Nicolas"),mediatheque.getFicheEmpruntAt(0).getClient());
        assertEquals(mediatheque.chercherDocument("8132"),mediatheque.getFicheEmpruntAt(0).getDocument());

        try {
            mediatheque.emprunter("Canteloup","Nicolas","8132");
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Document 8132 deja emprunte", e.getMessage());
        }

    }

    @Test
    public void restituer() throws InvariantBroken, OperationImpossible {
        try {
            mediatheque.restituer("not existing", "Nicolas", "8132");
            fail();
        } catch (OperationImpossible e) {
        }

        try {
            mediatheque.restituer("Canteloup", "Nicolas", "4");
            fail();
        } catch (OperationImpossible e) {
        }


        try {
            mediatheque.restituer("Canteloup", "Nicolas", "8132");
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Emprunt par \"Canteloup\" de \"8132\" non trouve", e.getMessage());
        }


        mediatheque.metEmpruntable("8132");
        mediatheque.emprunter("Canteloup","Nicolas","8132");

        try {
            mediatheque.restituer("Canteloup", "Nicolas", "8132");
        } catch (OperationImpossible e) {
            fail();
        }

        assertTrue(mediatheque.chercherDocument("8132").estEmpruntable());
        assertEquals(0, mediatheque.getFicheEmpruntsSize());

    }

    @Test
    public void getFicheEmpruntAt() throws OperationImpossible, InvariantBroken {
        mediatheque.metEmpruntable("8132");
        mediatheque.emprunter("Canteloup","Nicolas","8132");

        FicheEmprunt f = mediatheque.getFicheEmpruntAt(0);

        assertEquals("8132", f.getDocument().getCode());


    }

    @Test
    public void getFicheEmpruntsSize() throws OperationImpossible, InvariantBroken {
        assertEquals(0, mediatheque.getFicheEmpruntsSize());
        mediatheque.metEmpruntable("8132");
        mediatheque.emprunter("Canteloup","Nicolas","8132");
        assertEquals(1, mediatheque.getFicheEmpruntsSize());
    }

    @Test
    public void inscrire()  {
        try {
            mediatheque.inscrire("a", "v", "hisaddress", "not existing");
            fail();
        } catch (OperationImpossible operationImpossible) {
        }

        try {
            mediatheque.inscrire("a", "b", "hisaddress", "normal", 1);
            assertEquals(2, mediatheque.getClientsSize());
        } catch (OperationImpossible operationImpossible) {
            fail();
        }

        try {
            mediatheque.inscrire("a", "v", "hisaddress", "not existing", 1);
            fail();
        } catch (OperationImpossible operationImpossible) {
        }

        try {
            mediatheque.inscrire("Canteloup", "Nicolas", "hisaddress", "discounted", 1);
            fail();
        } catch (OperationImpossible operationImpossible) {
        }

        try {
            mediatheque.inscrire("w", "e", "hisaddress", "discounted", 1);
            assertEquals(3, mediatheque.getClientsSize());
        } catch (OperationImpossible operationImpossible) {
            fail();
        }

        try {
            mediatheque.inscrire("Canteloup", "Nicolas", "hisaddress", "normal");
            fail();
        } catch (OperationImpossible e) {
        }

    }

    @Test
    public void resilier() throws OperationImpossible, InvariantBroken {
        try {
            mediatheque.resilier("not existing", "Nicolas");
            fail();
        } catch (OperationImpossible operationImpossible) {
        }

        mediatheque.metEmpruntable("8132");
        mediatheque.emprunter("Canteloup","Nicolas","8132");

        try {
            mediatheque.resilier("Canteloup", "Nicolas");
            fail();
        } catch (OperationImpossible operationImpossible) {
        }

        mediatheque.restituer("Canteloup","Nicolas","8132");

        try {
            mediatheque.resilier("Canteloup", "Nicolas");
        } catch (OperationImpossible e) {
            fail();
        }
        assertEquals(0, mediatheque.getClientsSize());
    }

    @Test
    public void modifierClient() throws OperationImpossible {

        Client c = new Client("Not existing", "prenom", "add", new CategorieClient("normal",0,0,1,1,false));

        try {
            mediatheque.modifierClient(c, "test", "Nicolas", "hisaddress", "discounted", 1);
        } catch (OperationImpossible e) {
           assertEquals("Client Not existing prenom inexistant",e.getMessage());
        }

        try {
            mediatheque.modifierClient(mediatheque.getClientAt(0), "Kev", "Adams", "Paris", "normal", 0);
        } catch (OperationImpossible e) {
            e.printStackTrace();
            fail();
        }

        c = mediatheque.getClientAt(0);
        assertEquals("Kev", c.getNom());
        assertEquals("Adams", c.getPrenom());
        assertEquals("Paris", c.getAdresse());
        assertEquals("normal", c.getCategorie().getNom());

        try {
            mediatheque.modifierClient(mediatheque.getClientAt(1), "Canteloup", "Nicolas", "Panam", "discounted", 1);
        } catch (OperationImpossible e) {
            e.printStackTrace();
            fail();
        }

        c = mediatheque.getClientAt(0);
        assertEquals("Canteloup", c.getNom());
        assertEquals("Nicolas", c.getPrenom());
        assertEquals("Panam", c.getAdresse());
        assertEquals("discounted", c.getCategorie().getNom());


    }

    @Test
    public void changerCategorie()  {
        try {
            mediatheque.changerCategorie("a", "b", "normal", 0);
            fail();
        } catch (OperationImpossible operationImpossible) {

        }

        try {
            mediatheque.changerCategorie("Canteloup", "Nicolas", "not existing", 0);
            fail();
        } catch (OperationImpossible e) {
            assertEquals("Categorie client not existing non trouvee", e.getMessage());

        }

        try {
            mediatheque.changerCategorie("Canteloup", "Nicolas", "normal", 0);
        } catch (OperationImpossible operationImpossible) {
            fail();
        }
        assertEquals("normal", mediatheque.chercherClient("Canteloup", "Nicolas").getCategorie().getNom());

        try {
            mediatheque.changerCategorie("Canteloup", "Nicolas", "discounted", 0);
        } catch (OperationImpossible operationImpossible) {
            fail();
        }
        assertEquals("discounted", mediatheque.chercherClient("Canteloup", "Nicolas").getCategorie().getNom());
    }

    @Test
    public void changerCodeReduction() throws OperationImpossible {
        try {
            mediatheque.changerCodeReduction("a", "v", 1);
            fail();
        } catch (OperationImpossible operationImpossible) {
        }

        try {
            mediatheque.changerCodeReduction("Canteloup", "Nicolas", 5);
        } catch (OperationImpossible operationImpossible) {
            fail();
        }

        assertEquals(5, mediatheque.chercherClient("Canteloup", "Nicolas").getReduc());


        mediatheque.changerCategorie("Canteloup", "Nicolas", "normal", 0);

        try {
            mediatheque.changerCodeReduction("Canteloup", "Nicolas", 5);
            fail();
        } catch (OperationImpossible operationImpossible) {

        }
    }

    @Test
    public void existeClient()  {
        CategorieClient cat1 = new CategorieClient("discounted",2,0,0.5,0.5,true);
        CategorieClient cat2 = new CategorieClient("normal",2,0,0.5,0.5,true);
        CategorieClient cat3 = new CategorieClient("as",2,0,0.5,0.5,true);
        assertTrue(mediatheque.existeClient(cat1));
        assertFalse(mediatheque.existeClient(cat2));
        assertFalse(mediatheque.existeClient(cat3));

    }

    @Test
    public void initFromFile()  {

    }

    @Test
    public void saveToFile()  {

    }

}