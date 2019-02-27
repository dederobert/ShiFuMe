package fr.m2ihm.a1819.shi_fu_me.models;
import android.support.annotation.NonNull;

import fr.m2ihm.a1819.shi_fu_me.R;
import fr.m2ihm.a1819.shi_fu_me.utils.AndroidDrawableResource;
import fr.m2ihm.a1819.shi_fu_me.utils.DrawableResource;

import java.util.Random;

/**
 * Classe permettant de représenter les différents choix du jeu
 * @version 1.0.0
 */
public enum Choice {

    UNSET (new AndroidDrawableResource(R.drawable.ciseaux)),
    PIERRE(new AndroidDrawableResource(R.drawable.pierre)),
    FEUILLE(new AndroidDrawableResource(R.drawable.feuille)),
    CISEAUX(new AndroidDrawableResource(R.drawable.ciseaux));

    /**
     * Objet permetant de gérer l'aléatoir
     */
    private static Random random = new Random();

    /**
     * Effectue un choix aléatoir parmis les différents choix possible
     * @return Un choix aléatoir
     */
    public static @NonNull Choice randomChoice() {
        Choice choice;
        do {
            choice = Choice.values()[random.nextInt(Choice.values().length)];
        }while (choice.equals(UNSET));
        return choice;
    }

    /**
     * L"identifiant du drawable utilisé pour représenter le choix
     */

    private DrawableResource ressource;

    /**
     * Constructeur d'un choix
     * @param ressource Ressource utilisé pour être rendue
     */
    Choice(DrawableResource ressource) {
        this.ressource = ressource;
    }

    /**
     * Test si le choix passé en paramètre est battus par le choix actuel
     * @param adv Le choix à tester
     * @return Vraie si adv est battu par le le choix actuel, faux sinon ou si exéquo
     */
    public boolean win(@NonNull Choice adv) {
        switch (adv) {
            case PIERRE: return this.equals(FEUILLE);
            case CISEAUX: return this.equals(PIERRE);
            case FEUILLE: return this.equals(CISEAUX);
            default: return false;
        }
    }

    /**
     * Obtiens la ressource utilisé pour le rendu
     * @return La ressource représentant le choix
     */
    public @NonNull DrawableResource getRessource() {
        return ressource;
    }
}
