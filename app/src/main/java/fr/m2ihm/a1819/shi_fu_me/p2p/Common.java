package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.support.annotation.NonNull;

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

    public void notifyChange() {}

    public enum MessageHeader {
        HELLO_CLIENTSIDE("hello_clt"),
        HELLO_SERVERSIDE("hello_srv"),
        SND_PLAYER_CHOICE("snd_choice"), //choice [choice: CHOIX]
        RCV_PLAYER_CHOICE("rcv_choice"),
        INFOS("info"), //info [opp_choice: CHOIX, your_score: SCORE, opp_score: SCORE]
        END("end");

        @NonNull
        private String message;
        MessageHeader(@NonNull String message) {
            this.message = message;
        }

        @Override
        @NonNull
        public String toString() {
            return this.message;
        }

        public boolean checkResponse(@NonNull String response) {
            return (message.startsWith(response));
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
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;


    /**
     * Constructeur de la classe
     * @param context Le context de l'application
     */
    Common(@NonNull final Context context) {
        this.context = context;
    }

    /**
     * Obtiens le context
     * @return Le context de l'application
     */
    @NonNull
    public Context getContext() {
        return context;
    }

}
