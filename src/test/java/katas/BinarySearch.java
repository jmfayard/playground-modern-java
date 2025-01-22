package katas;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Listes {
    public static boolean contientBruteforce(List<Integer> entiers, int valeurRecherchee) {
        if (entiers == null) return false;
        for (int value : entiers) {
            if (value == valeurRecherchee) return true;
        }
        return false;
    }

    public static boolean contient(List<Integer> entiers, int valeurRecherchee) {
        if (entiers == null || entiers.isEmpty()) return false;
        return contientRecursif(entiers, valeurRecherchee, 0, entiers.size());
    }

    /**
     * Recherche un élément dans une liste TRIÉE suivant l'algorithme binary search.
     */
    public static boolean contientRecursif(
            List<Integer> entiers,
            int valeurRecherchee,
            int depart,
            int fin) {
        if (depart  == fin) {
            return entiers.get(depart) == valeurRecherchee;
        }

        int indiceMilieu = (depart + fin) / 2;
        int valeurMilieu = entiers.get(indiceMilieu);
        if (valeurRecherchee == valeurMilieu) {
            return true;
        } else if (valeurRecherchee > valeurMilieu) {
            return contientRecursif(entiers, valeurRecherchee, indiceMilieu + 1, fin);
        } else {
            return contientRecursif(entiers, valeurRecherchee, depart, indiceMilieu - 1);
        }
    }
}

class BinarySearchTest {
    List<Integer> entiers = List.of(-12, -4, 1, 3, 4, 8, 12, 42);

    @Test
    void testNegatif() {
        assertThat(Listes.contient(entiers, 2)).isEqualTo(false);
    }

    @Test
    void testPositif() {
        assertThat(Listes.contient(entiers, 8)).isEqualTo(true);
    }

    @Test
    void testListeVide() {
        assertThat(Listes.contient(List.of(), -42)).isEqualTo(false);
    }

    @Test
    void testListeNulle() {
        assertThat(Listes.contient(null, -42)).isEqualTo(false);
    }

    @Test
    void testSingleton() {
        assertThat(Listes.contient(List.of(-42), -42)).isEqualTo(true);
    }
}