package mediathequeTest.clientTest;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import mediatheque.OperationImpossible;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.text.DateFormat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;


/**
 * Created by Robinson on 31/10/2016.
 */
@RunWith(Parameterized.class)
public class ClientTest {

    private String nom;
    private String prenom;
    private String adresse;
    private int nbEmpruntsEnCours = 0;
    private int nbEmpruntsDepasses = 0;
    private int nbEmpruntsEffectues = 0;
    private CategorieClient catClient = null;
    private static int nbEmpruntsTotal = 0;
    private Date dateRenouvellement;
    private Date dateInscription;
    private int codeReduction = 0;

    private int nbWeekBorrowingAuthorize;
    private Date rentFrom = new Date(2016,11,4);
    private Date dateRetour;

    private Client c1;
    private Client c2;

    public ClientTest(String nom, String prenom, String adresse, CategorieClient catClient, int codeReduction, int nbWeekBorrowingAuthorize, Date dateRetour){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.catClient = catClient;
        this.codeReduction = codeReduction;

        this.nbWeekBorrowingAuthorize = nbWeekBorrowingAuthorize;
        this.dateRetour = dateRetour;

        if(catClient.getCodeReducUtilise()){
            try {

                this.c1 = new Client(nom, prenom, adresse, catClient, codeReduction);
            }catch (OperationImpossible e){
                fail("Construct 1 Error");
            }
        }else{
            try{
                this.c1 = new Client(nom, prenom, adresse, catClient);
            }catch (OperationImpossible e){
                fail("Construct 2 Error");
            }
        }

        this.c2 = new Client(nom, prenom);
    }

    @Parameters
    public static Collection<Object[]> parameters() throws IOException{
        CategorieClient cat1 = new CategorieClient("discounted",2,0,0.5,0.5,true);
        CategorieClient cat2 = new CategorieClient("normal",5,0,1,1,false);
        CategorieClient cat3 = new CategorieClient("subscriber",10,20,2,0,false);

        return Arrays.asList(
                new Object[] {"Damien", "Francois", "QG 75015 Paris", cat1, 1, 4, new Date(2016,11,17)},
                new Object[] {"Damien", "Francois", "QG 75015 Paris", cat1, 12, 6, new Date(2016,11,24)},
                new Object[] {"Damien", "Francois", "QG 75015 Paris", cat1, 5, 8, new Date(2016,11,31)},
                new Object[] {"Debouze", "Jamail", "JCC 63100 Vannes", cat2, 0, 4, new Date(2016,11,31)},
                new Object[] {"Debouze", "Jamail", "JCC 63100 Vannes", cat2, 0, 6, new Date(2016,12,14)},
                new Object[] {"Debouze", "Jamail", "JCC 63100 Vannes", cat2, 0, 8, new Date(2016,12,28)},
                new Object[] {"Redouane", "Arjane", "RG 75006 Paris", cat3, 0, 4, new Date(2016,12,28)},
                new Object[] {"Redouane", "Arjane", "RG 75006 Paris", cat3, 0, 6, new Date(2017,01,25)},
                new Object[] {"Redouane", "Arjane", "RG 75006 Paris", cat3, 0, 8, new Date(2017,02,25)}
        );
    }

    @Test
    public void testGetNom() {
        assertEquals(nom, c1.getNom());
    }

    @Test
    public void testGetPrenom() {
        assertEquals(prenom, c1.getPrenom());

    }

    @Test
    public void testGetAdresse() {
        assertEquals(adresse, c1.getAdresse());


    }

    @Test
    public void testGetNbEmpruntsEnCours() {
        assertEquals(nbEmpruntsEnCours,c1.getNbEmpruntsEnCours());
    }

    @Test
    public void testGetNbEmpruntsEffectues() {
        assertEquals(nbEmpruntsEffectues, c1.getNbEmpruntsEffectues());
    }

    @Test
    public void testGetNbEmpruntsEnRetard() {
        assertEquals(nbEmpruntsDepasses, c1.getNbEmpruntsEnRetard());
    }

    @Test
    public void testGetCoefTarif() {
        assertEquals(catClient.getCoefTarif(), c1.getCoefTarif());
    }

    @Test
    public void testGetCoefDuree() {
        assertEquals(catClient.getCoefDuree(), c1.getCoefDuree());
    }

    @Test
    public void testEquals() {
        assertTrue(c1.equals(c1));
        assertTrue(c1.equals(c2));

        assertFalse(c1.equals(null));
        assertFalse(c1.equals(1));

        Client c4;

        try {
            c4 = new Client(c1.getNom(), c1.getPrenom(), "21 pas rue du test", new CategorieClient("normal",5,0,1,1,false));
            assertTrue(c1.equals(c4));
        }catch (OperationImpossible e){
            fail("test not totaly done because of the OperationImpossible exception");
        }
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(c1.hashCode(), c1.hashCode());
        assertEquals(c2.hashCode(), c1.hashCode());
        Client c4 = new Client("Bernard", "Laporte", "21 pas rue du test", new CategorieClient("normal",5,0,1,1,false));
        assertNotEquals(c1.hashCode(), c4.hashCode());
    }

    @Test
    public void testADesEmpruntsEnCours(){
        assertFalse(c1.aDesEmpruntsEnCours());
        assertFalse(c2.aDesEmpruntsEnCours());

            c1.emprunter();
            assertTrue(c1.aDesEmpruntsEnCours());
    }

    @Test
    public void testPeutEmprunter() {
        for (int i = 0; i < (catClient.getNbEmpruntMax()-1); i++){
            c1.emprunter();
            assertTrue(c1.peutEmprunter());
        }
        c1.emprunter();
        assertFalse(c1.peutEmprunter());
    }

    @Test
    public void testEmprunter() {
        c1.emprunter();
        assertEquals(1, c1.getNbEmpruntsEnCours());
        c1.emprunter();
        assertEquals(2, c1.getNbEmpruntsEnCours());
        assertNotEquals(0, Client.getnbEmpruntsTotal());
    }

    @Test
    public void testEmprunter1() throws Exception {
        //fail("Not Yet Implemented");
    }

    @Test
    public void marquer() throws Exception {
        try {
            c1.marquer();
            fail();
        }catch (OperationImpossible e){}

        c1.emprunter();
        try {
            c1.marquer();

        }catch (OperationImpossible e){
            fail();
        }

        try {
            c1.marquer();
            fail();
        }catch (OperationImpossible e){

        }
    }

    @Test
    public void TestRestituer(){
        //fail("not yet Implemeted");


    }

    @Test
    public void restituer1() {
        //fail("not yet Implemeted");
    }

    @Test
    public void testToString() {
        assertEquals(String.class, c1.toString().getClass());

    }

    @Test
    public void testDateRetour() {
        assertEquals(dateRetour, c1.dateRetour(rentFrom, nbWeekBorrowingAuthorize*7));
    }

    @Test
    public void testSommeDue() {
        assertEquals(0.5*catClient.getCoefTarif(), c1.sommeDue(0.5));
        assertEquals(1*catClient.getCoefTarif(), c1.sommeDue(1));
        assertEquals(1.5*catClient.getCoefTarif(), c1.sommeDue(1.5));
    }

    @Test
    public void testNbMaxEmprunt() {
        assertEquals(catClient.getNbEmpruntMax(), c1.nbMaxEmprunt());
    }

    @Test
    public void testGetDateCotisation() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.DATE, -1);
        Date nextYear = cal.getTime();

        String dateCotisationExpected = DateFormat.getTimeInstance().format(nextYear);
        String dateCotisationActual = DateFormat.getTimeInstance().format(c1.getDateCotisation());

        assertEquals(dateCotisationExpected, dateCotisationActual);
    }

    @Test
    public void getDateInscription() {
        Calendar cal = Calendar.getInstance();
        String dateInscriptionExpected = DateFormat.getTimeInstance().format(cal.getTime());
        String dateInscriptionActual = DateFormat.getTimeInstance().format(c1.getDateInscription());

        assertEquals(dateInscriptionExpected, dateInscriptionActual);
    }

    @Test
    public void testGetCategorie() {
        assertEquals(catClient, c1.getCategorie());
    }

    @Test
    public void testSetCategorie() {
        CategorieClient cc1 = new CategorieClient("VIP",10,0,4,0,false);
        try {
            c1.setCategorie(cc1);
        } catch (OperationImpossible e) {
            fail("1");
        }

        assertEquals(cc1, c1.getCategorie());

        cc1 = new CategorieClient("personal",15,0,2,0,true);
        try {
            c1.setCategorie(cc1);
            fail("3");
        }catch (OperationImpossible e){}
    }

    @Test
    public void testSetCategorie1() {

        CategorieClient cc1 = new CategorieClient("VIP",10,0,4,0,true);

        try {
                c1.setCategorie(cc1, 5);
                assertEquals(cc1, c1.getCategorie());

        } catch (OperationImpossible e) {
            fail();
        }

        cc1 = new CategorieClient("VIP",10,0,4,0,false);

        try {
            c1.setCategorie(cc1, 5);
            fail();
        } catch (OperationImpossible e) {

        }
    }

    @Test
    public void testSetReduc() throws Exception {
        c1.setReduc(2134);
        assertEquals(2134, c1.getReduc());
        c1.setReduc(0);
        assertEquals(0, c1.getReduc());
    }

    @Test
    public void testSetNom() throws Exception {
        c1.setNom("test");
        assertEquals("test", c1.getNom());
        c1.setNom(null);
        assertEquals(null, c1.getNom());
    }

    @Test
    public void testSetPrenom() throws Exception {
        c1.setPrenom("test");
        assertEquals("test", c1.getPrenom());
        c1.setPrenom(null);
        assertEquals(null, c1.getPrenom());
    }

    @Test
    public void testSetAddresse() throws Exception {
        c1.setAddresse("test");
        assertEquals("test", c1.getAdresse());
        c1.setAddresse(null);
        assertEquals(null, c1.getAdresse());
    }

    @Test
    public void testGetReduc() throws Exception {
        c1.setReduc(5);
        assertEquals(5, c1.getReduc());
        c1.setReduc(0);
        assertEquals(0, c1.getReduc());
    }

}