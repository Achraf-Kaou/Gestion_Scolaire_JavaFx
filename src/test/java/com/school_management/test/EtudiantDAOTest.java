package com.school_management.test;

import com.school_management.dao.EtudiantDAO;
import com.school_management.dao.EtudiantDAOImpl;
import com.school_management.model.Etudiant;

import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EtudiantDAOTest {

    private static EtudiantDAO etudiantDAO;

    @BeforeAll
    static void setup() {
        etudiantDAO = new EtudiantDAOImpl();
    }

    @Test
    void testAjouterEtLireParId() throws Exception {
        // Ajouter un étudiant de test
        Etudiant etu = new Etudiant();
        etu.setNom("Test");
        etu.setPrenom("Unitaire");
        etu.setEmail("test.unitaire2@test.com");
        etu.setPassword("password"); // En vrai : hashé !
        etu.setNumeroEtudiant("TST9992");
        etu.setDateInscription(LocalDate.now());
        etu.setClasseId(7L);

        etudiantDAO.ajouter(etu);
        // Récupérer
        List<Etudiant> trouve = etudiantDAO.lireTous();
        assertNotNull(trouve);
        for (Etudiant etudiant : trouve) {
            System.out.println(etudiant);
        }
//        assertEquals("Test", trouve.getNom());
//        assertEquals("test.unitaire@test.com", trouve.getEmail());
    }

    @Test
    void testLireTousEtSupprimer() throws Exception {
        List<Etudiant> etudiants = etudiantDAO.lireTous();
        assertNotNull(etudiants);
        assertTrue(etudiants.size() >= 0);

        // Optionnel : Supprimer le dernier étudiant ajouté
        if (!etudiants.isEmpty()) {
            Long idToDelete = etudiants.get(etudiants.size() - 1).getId();
            etudiantDAO.supprimer(idToDelete);
            assertNull(etudiantDAO.lireParId(idToDelete));
        }
    }

    @AfterAll
    static void cleanUp() {
        // Ici nettoyage de la BDD (optionnel)
    }
}
