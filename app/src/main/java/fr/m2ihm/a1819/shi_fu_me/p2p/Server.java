package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server extends Common {

    ServerSocket serverSocket;
    List<Socket> clients = new ArrayList<>();

    public Server(Context context) {
        super(context);
        Toast.makeText(context, "Je suis le serveur", Toast.LENGTH_LONG).show();
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            serverSocket = new ServerSocket(8888);
            clients.add(serverSocket.accept());
            new PrintWriter(clients.get(0).getOutputStream()).print("Bonjour client");
            clients.get(0).getInputStream().read(bytes);
            Log.d("Serveur", new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
