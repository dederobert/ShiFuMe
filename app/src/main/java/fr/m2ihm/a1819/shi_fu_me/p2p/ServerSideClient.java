package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSideClient  extends Common{
    private Socket socket;
    private boolean running = true;
    private boolean runningOnServerSide = false;

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

            response = this.getBufferedReader().readLine();
            runningOnServerSide = MessageHeader.HELLO_SERVERSIDE.checkResponse(response);
            Log.d("[Server]", "Hello reçut \"" + response + '"');


            do {
                response = this.getBufferedReader().readLine(); //On recoit le choix du client
                if (MessageHeader.END.checkResponse(response)) {running = false; continue;} //Check si on recoit fin de connection
                this.getPrintWriter().println(MessageHeader.INFOS + "[opp_choice: , your_score: , opp_score: ]"); //On envoie les infos
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
}