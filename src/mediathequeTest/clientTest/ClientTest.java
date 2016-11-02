package mediathequeTest.clientTest;

import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import org.junit.Before;
import org.junit.Test;
import sun.misc.Cleaner;

import static org.junit.Assert.*;

/**
 * Created by Robinson on 31/10/2016.
 */
public class ClientTest {

    private Client c1;
    private Client c2;
    private Client c3;

    private CategorieClient cat;



    @Before
    public void setUp(){
        c1 = new Client("Dupont", "Michel");

        cat = new CategorieClient("Discounted", 2, 25, 0.5, 0.5, false);
        //c2 = new Client("Damien", "Francois", "QG 75015 Paris", cat);


    }

    @Test
    public void getNom() throws Exception {

    }

    @Test
    public void getPrenom() throws Exception {

    }

    @Test
    public void getAdresse() throws Exception {

    }

    @Test
    public void getNbEmpruntsEnCours() throws Exception {

    }

    @Test
    public void getNbEmpruntsEffectues() throws Exception {

    }

    @Test
    public void getNbEmpruntsEnRetard() throws Exception {

    }

    @Test
    public void getCoefTarif() throws Exception {

    }

    @Test
    public void getCoefDuree() throws Exception {

    }

    @Test
    public void equals() throws Exception {

    }

    @Test
    public void testHashCode() throws Exception {

    }

    @Test
    public void aDesEmpruntsEnCours() throws Exception {

    }

    @Test
    public void peutEmprunter() throws Exception {

    }

    @Test
    public void emprunter() throws Exception {

    }

    @Test
    public void emprunter1() throws Exception {

    }

    @Test
    public void marquer() throws Exception {

    }

    @Test
    public void restituer() throws Exception {

    }

    @Test
    public void restituer1() throws Exception {

    }

    @Test
    public void afficherStatistiques() throws Exception {

    }

    @Test
    public void afficherStatCli() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }

    @Test
    public void dateRetour() throws Exception {

    }

    @Test
    public void sommeDue() throws Exception {

    }

    @Test
    public void nbMaxEmprunt() throws Exception {

    }

    @Test
    public void getDateCotisation() throws Exception {

    }

    @Test
    public void getDateInscription() throws Exception {

    }

    @Test
    public void getCategorie() throws Exception {

    }

    @Test
    public void setCategorie() throws Exception {

    }

    @Test
    public void setCategorie1() throws Exception {

    }

    @Test
    public void setReduc() throws Exception {

    }

    @Test
    public void setNom() throws Exception {

    }

    @Test
    public void setPrenom() throws Exception {

    }

    @Test
    public void setAddresse() throws Exception {

    }

    @Test
    public void getReduc() throws Exception {

    }

    @Test
    public void getnbEmpruntsTotal() throws Exception {

    }

    @Test
    public void getStat() throws Exception {

    }

}