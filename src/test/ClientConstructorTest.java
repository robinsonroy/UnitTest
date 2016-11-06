package test;

import mediatheque.OperationImpossible;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by Robinson on 05/11/2016.
 */
@RunWith(Parameterized.class)
public class ClientConstructorTest {

    private String nom;
    private String prenom;
    private String adresse;
    private CategorieClient catClient;
    private int codeReduction = 0;

    private Client c1;

    public ClientConstructorTest(String nom, String prenom, String adresse, CategorieClient catClient, int codeReduction){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.catClient = catClient;
        this.codeReduction = codeReduction;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() throws IOException {
        CategorieClient cat1 = new CategorieClient("discounted",2,0,0.5,0.5,true);
        CategorieClient cat2 = new CategorieClient("normal",5,0,1,1,false);
        CategorieClient cat3 = new CategorieClient("subscriber",10,20,2,0,false);

        return Arrays.asList(
                new Object[] {"Damien", "Francois", "QG 75015 Paris", cat1, 1},
                new Object[] {"Damien", "Francois", "QG 75015 Paris", cat2, 12},
                new Object[] {null, "Francois", "QG 75015 Paris", cat2, 12},
                new Object[] {"Damien",null, "QG 75015 Paris", cat2, 12},
                new Object[] {null, null, "QG 75015 Paris", cat2, 12}
        );
    }

    @Test
    public void testConstructor() throws OperationImpossible {

        if (prenom==null || nom==null || adresse==null || catClient==null){
            try {
                c1 = new Client(nom, prenom);
                fail();
            } catch (OperationImpossible operationImpossible) {}

            try {
                c1 = new Client(nom, prenom, adresse, catClient);
                fail();
            } catch (OperationImpossible operationImpossible) {}

            try {
                c1 = new Client(nom, prenom, adresse, catClient, codeReduction);
                fail();
            } catch (OperationImpossible operationImpossible) {}
        }else {
            try {
                c1 = new Client(nom, prenom);
                assertNotNull(c1);
                assertEquals(nom, c1.getNom());
                assertEquals(prenom, c1.getPrenom());
            }catch (OperationImpossible e){ fail(); }

            if (catClient.getCodeReducUtilise()) {
                try {
                    c1 = new Client(nom, prenom, adresse, catClient);
                    fail();
                } catch (OperationImpossible e) {
                }
                try {
                    c1 = new Client(nom, prenom, adresse, catClient, codeReduction);
                }catch (OperationImpossible e){
                    fail();
                }
            }else {
                try {
                    c1 = new Client(nom, prenom, adresse, catClient);
                    assertNotNull(c1);
                    assertEquals(nom, c1.getNom());
                    assertEquals(prenom, c1.getPrenom());
                    assertEquals(adresse, c1.getAdresse());
                    assertEquals(catClient, c1.getCategorie());
                } catch (OperationImpossible e) {
                    fail();
                }
                try {
                    c1 = new Client(nom, prenom, adresse, catClient, codeReduction);
                    assertNotNull(c1);
                    assertEquals(nom, c1.getNom());
                    assertEquals(prenom, c1.getPrenom());
                    assertEquals(adresse, c1.getAdresse());
                    assertEquals(catClient, c1.getCategorie());
                    fail();
                }catch (OperationImpossible e){
                }
            }
        }



    }
}
