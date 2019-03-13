package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;

public class ServerSideClient extends Common{
    private Socket socket;
    private boolean running = true;
    private boolean runningOnServerSide = false;
    private Choice choice;

    ServerSideClient(Context context, Socket socket) {
        super(context);
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            this.setPrintWriter(new PrintWriter(socket.getOutputStream(), true));
            this.setBufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));

            String response;
            do {
                response = this.getBufferedReader().readLine(); //On recoit le choix du client
                if (MessageHeader.END.checkResponse(response)) {running = false; continue;} //Check si on recoit fin de connection


                if (MessageHeader.PLAYER_CHOICE.checkResponse(response)) {
                    choice = Choice.valueOf(MessageHeader.PLAYER_CHOICE.extractInfo(response));
                }

            } while (running);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }
}
