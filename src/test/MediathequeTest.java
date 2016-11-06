package test;

import mediatheque.FicheEmprunt;
import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.client.HashClient;
import mediatheque.document.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;
import java.util.Vector;

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

    @Before
    public void setUp(){

    }

    @Test
    public void empty() throws Exception {

    }

    @Test
    public void chercherGenre() throws Exception {

    }

    @Test
    public void supprimerGenre() throws Exception {

    }

    @Test
    public void ajouterGenre() throws Exception {

    }

    @Test
    public void modifierGenre() throws Exception {

    }

    @Test
    public void listerGenres() throws Exception {

    }

    @Test
    public void getGenreAt() throws Exception {

    }

    @Test
    public void getGenresSize() throws Exception {

    }

    @Test
    public void supprimerLocalisation() throws Exception {

    }

    @Test
    public void chercherLocalisation() throws Exception {

    }

    @Test
    public void ajouterLocalisation() throws Exception {

    }

    @Test
    public void modifierLocalisation() throws Exception {

    }

    @Test
    public void listerLocalisations() throws Exception {

    }

    @Test
    public void getLocalisationAt() throws Exception {

    }

    @Test
    public void getLocalisationsSize() throws Exception {

    }

    @Test
    public void chercherCatClient() throws Exception {

    }

    @Test
    public void supprimerCatClient() throws Exception {

    }

    @Test
    public void ajouterCatClient() throws Exception {

    }

    @Test
    public void modifierCatClient() throws Exception {

    }

    @Test
    public void listerCatsClient() throws Exception {

    }

    @Test
    public void getCategorieAt() throws Exception {

    }

    @Test
    public void getCategoriesSize() throws Exception {

    }

    @Test
    public void chercherDocument() throws Exception {

    }

    @Test
    public void ajouterDocument() throws Exception {

    }

    @Test
    public void retirerDocument() throws Exception {

    }

    @Test
    public void metEmpruntable() throws Exception {

    }

    @Test
    public void metConsultable() throws Exception {

    }

    @Test
    public void listerDocuments() throws Exception {

    }

    @Test
    public void getDocumentAt() throws Exception {

    }

    @Test
    public void getDocumentsSize() throws Exception {

    }

    @Test
    public void emprunter() throws Exception {

    }

    @Test
    public void restituer() throws Exception {

    }

    @Test
    public void verifier() throws Exception {

    }

    @Test
    public void listerFicheEmprunts() throws Exception {

    }

    @Test
    public void getFicheEmpruntAt() throws Exception {

    }

    @Test
    public void getFicheEmpruntsSize() throws Exception {

    }

    @Test
    public void inscrire() throws Exception {

    }

    @Test
    public void inscrire1() throws Exception {

    }

    @Test
    public void resilier() throws Exception {

    }

    @Test
    public void modifierClient() throws Exception {

    }

    @Test
    public void changerCategorie() throws Exception {

    }

    @Test
    public void changerCodeReduction() throws Exception {

    }

    @Test
    public void chercherClient() throws Exception {

    }

    @Test
    public void listerClients() throws Exception {

    }

    @Test
    public void existeClient() throws Exception {

    }

    @Test
    public void getClientAt() throws Exception {

    }

    @Test
    public void getClientsSize() throws Exception {

    }

    @Test
    public void findClient() throws Exception {

    }

    @Test
    public void afficherStatistiques() throws Exception {

    }

    @Test
    public void getNom() throws Exception {

    }

    @Test
    public void initFromFile() throws Exception {

    }

    @Test
    public void saveToFile() throws Exception {

    }

}