package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;
import fr.m2ihm.a1819.shi_fu_me.models.Game;
import fr.m2ihm.a1819.shi_fu_me.p2p.listeners.callbacks.ServerSideClientCallBack;

public class ServerSideClient extends Common {

    private final int index;
    @NonNull
    private final Socket socket;
    @NonNull
    private final ServerSideClientCallBack serverSideClientCallBack;
    private boolean running = true;


    @NonNull
    private Choice ownChoice = Choice.UNSET;
    @NonNull
    private Choice opponentChoice = Choice.UNSET;

    private final Object lockOppChoice = new Object();
    private final Object lockOwnChoice = new Object();

    ServerSideClient(int index, @NonNull Context context, @NonNull Socket socket, @NonNull ServerSideClientCallBack serverSideClientCallBack, Game game) {
        super(context, game);
        this.index = index;
        this.socket = socket;
        this.serverSideClientCallBack = serverSideClientCallBack;
    }

    @Override
    public void run() {
        Log.d("[Server_F]", "running ...");
        super.run();
        try {
            this.setPrintWriter(new PrintWriter(socket.getOutputStream(), true));
            this.setBufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));

            String response;
            do {
                response = this.getBufferedReader().readLine(); //On recoit le choix du client

                if (MessageHeader.END.checkResponse(response)) { running = false; continue; } //Check si on recoit fin de connection
                if (MessageHeader.SND_PLAYER_CHOICE.checkResponse(response)) {
                    Log.d("[Server_F]", "Choix reçut");
                    ownChoice = Choice.valueOf(MessageHeader.SND_PLAYER_CHOICE.extractInfo(response));
                    serverSideClientCallBack.onReceivePlayerChoice(ownChoice, getIndex());
                }

                synchronized (lockOppChoice) {
                    while (getOpponentChoice().equals(Choice.UNSET)) { lockOppChoice.wait(); } // Tant que l'on n'a pas de choix on attend
                    this.getPrintWriter().println(MessageHeader.RCV_PLAYER_CHOICE + ":" + getOpponentChoice()); //On envoie le message
                    resetChoice();
                }
            } while (running);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void resetChoice() {
        setOpponentChoice(Choice.UNSET);
        setOwnChoice(Choice.UNSET);
    }

    @NonNull
    public Socket getSocket() {
        return socket;
    }

    @NonNull
    public Choice getOwnChoice() {
        return ownChoice;
    }

    @SuppressWarnings("WeakerAccess")
    public void setOwnChoice(@NonNull Choice ownChoice) {
        synchronized (lockOwnChoice) {
            this.ownChoice = ownChoice;
            lockOwnChoice.notify();
        }
    }

    @NonNull
    @SuppressWarnings("WeakerAccess")
    public Choice getOpponentChoice() {
        return opponentChoice;
    }

    public void setOpponentChoice(@NonNull Choice opponentChoice) {
        synchronized (lockOppChoice) {
            this.opponentChoice = opponentChoice;
            lockOppChoice.notify();
        }
    }

    @SuppressWarnings("WeakerAccess")
    public int getIndex() {
        return index;
    }
}
