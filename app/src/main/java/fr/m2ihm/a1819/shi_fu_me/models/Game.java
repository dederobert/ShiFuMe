package fr.m2ihm.a1819.shi_fu_me.models;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import fr.m2ihm.a1819.shi_fu_me.activities.InGameActivity;
import fr.m2ihm.a1819.shi_fu_me.p2p.Client;
import fr.m2ihm.a1819.shi_fu_me.p2p.Server;

/**
 * Représente les données d'une parties
 * @version 1.0.0
 */
public class Game {

    private boolean enable = true;

    private void toggleButton() {
        enable = !enable;
        inGameActivity.btn_ciseaux.setClickable(enable);
        inGameActivity.btn_ciseaux.setEnabled(enable);

        inGameActivity.btn_pierre.setClickable(enable);
        inGameActivity.btn_pierre.setEnabled(enable);

        inGameActivity.btn_feuille.setClickable(enable);
        inGameActivity.btn_feuille.setEnabled(enable);
    }

    public void resetChoice() {

       toggleButton();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playerChoice = Choice.UNSET;
                advChoice = Choice.UNSET;
                toggleButton();
            }
        }, 2000);
    }

    public enum GameType{
        SINGLE_PLAYER,
        MULTI_PLAYER
    }


    @NonNull
    private final InGameActivity inGameActivity;

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


    private @Nullable Server server;
    private @Nullable Client client;

    /**
     * Créer un jeu
     * @param inGameActivity Activité
     */
    public Game(@NonNull InGameActivity inGameActivity) {
        this.inGameActivity = inGameActivity;
    }

    /**
     * Met à jours les score en fonction des choix
     */
    public void updateScore() {
        if (this.playerChoice.win(this.advChoice))
            this.playerScore++;
        else if(this.advChoice.win(this.playerChoice))
            this.advScore++;
    }

    public void resetScore() {
        this.playerScore = 0;
        this.advScore = 0;
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
        if (client != null)
            client.setOwnChoice(playerChoice);
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

    @Nullable
    public Client getClient() {
        return client;
    }

    public void setClient(@NonNull Client client) {
        this.client = client;
    }

    @Nullable
    public Server getServer() {
        return server;
    }

    public void setServer(@NonNull Server server) {
        this.server = server;
    }

    public @NonNull InGameActivity getInGameActivity() {
        return inGameActivity;
    }
}
