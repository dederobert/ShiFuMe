package fr.m2ihm.a1819.shi_fu_me.models;

import android.support.annotation.NonNull;

/**
 * Représente les données d'une parties
 * @version 1.0.0
 */
public class Game {

    public enum GameType{
        SINGLE_PLAYER,
        MULTI_PLAYER
    }

    /**
     * Choix de l'utilisateur
     */
    @NonNull
    private Choice playerChoice = Choice.UNSET;

    /**
     * Choix de l'adversaire
     */
    @NonNull
    private Choice advChoice = Choice.UNSET;

    /**
     * Score du joueur
     */
    private int playerScore = 0;

    /**
     * Score de l'adversaire
     */
    private int advScore = 0;

    /**
     * Créer un jeu
     */
    public Game() {}

    /**
     * Met à jours les score en fonction des choix
     */
    public void updateScore() {
        if (this.playerChoice.win(this.advChoice))
            this.playerScore++;
        else if(this.advChoice.win(this.playerChoice))
            this.advScore++;
    }

    /**
     * Obtiens le choix du joueur
     * @return Choix du joueur
     */
    public @NonNull Choice getPlayerChoice() {
        return playerChoice;
    }

    /**
     * Définie le choix du joueur
     * @param playerChoice Choix du joueur
     */
    public void setPlayerChoice(@NonNull Choice playerChoice) {
        this.playerChoice = playerChoice;
    }

    /**
     * Obtiens le choix de l'adversaire
     * @return Choix de l'adversaire
     */
    public @NonNull Choice getAdvChoice() {
        return advChoice;
    }

    /**
     * Définie le choix du joueur
     * @param advChoice Choix de l'adversaire
     */
    public void setAdvChoice(@NonNull Choice advChoice) {
        this.advChoice = advChoice;
    }

    /**
     * Obtiens le score du joueur
     * @return Score du joueur
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Obtiens le score de l'adversaire
     * @return Le score de l'adversaire
     */
    public int getAdvScore() {
        return advScore;
    }


    public void resetScore() {
        this.playerScore = 0;
        this.advScore = 0;
    }
}
