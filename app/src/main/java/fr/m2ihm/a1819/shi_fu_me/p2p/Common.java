package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;

import java.io.BufferedReader;
import java.io.PrintWriter;

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

    public enum MessageHeader {
        INFOS("info "), //info [opp_choice: CHOIX, your_score: SCORE, opp_score: SCORE]
        PLAYER_CHOICE("choice "), //choice [choice: CHOIX]
        END("end");

        private String message;
        MessageHeader(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return this.message;
        }

        public boolean checkResponse(String response) {
            return (message.startsWith(response));
        }
        public String extractInfo(String response) {
            return response.replace(message+":", "");
        }
    }

    /**
     * Le context de l'application
     */
    private final Context context;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;


    /**
     * Constructeur de la classe
     * @param context Le context de l'application
     */
    Common(final Context context) {
        this.context = context;
    }

    /**
     * Obtiens le context
     * @return Le context de l'application
     */
    public Context getContext() {
        return context;
    }

}
