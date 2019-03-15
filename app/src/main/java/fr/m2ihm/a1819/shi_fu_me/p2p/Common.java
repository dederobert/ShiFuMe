package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.PrintWriter;

import fr.m2ihm.a1819.shi_fu_me.models.Game;

/**
 * Classe commune au client et serveur
 */
public abstract class Common extends Thread{

    PrintWriter getPrintWriter() {
        return printWriter;
    }

    void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public enum MessageHeader {
        SND_PLAYER_CHOICE("snd_choice"), //choice [choice: CHOIX]
        RCV_PLAYER_CHOICE("rcv_choice"),
        END("end");

        @NonNull
        private final String message;
        MessageHeader(@NonNull String message) {
            this.message = message;
        }

        @Override
        @NonNull
        public String toString() {
            return this.message;
        }

        public boolean checkResponse(@NonNull String response) {
            return (response.startsWith(message));
        }

        @NonNull
        public String extractInfo(@NonNull String response) {
            return response.replace(message+":", "");
        }
    }

    /**
     * Le context de l'application
     */
    @NonNull
    private final Context context;
    private Game game;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;


    /**
     * Constructeur de la classe
     * @param context Le context de l'application
     * @param game
     */
    Common(@NonNull final Context context, Game game) {
        this.context = context;
        this.game = game;
    }

    /**
     * Obtiens le context
     * @return Le context de l'application
     */
    @NonNull
    @SuppressWarnings("WeakerAccess")
    public Context getContext() {
        return context;
    }

}
