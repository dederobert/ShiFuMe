package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server extends Common {

    ServerSocket serverSocket;
    List<Socket> clients = new ArrayList<>();

    public Server(final Context context, InetAddress groupOwnerAddress) throws IOException {
        super(context, groupOwnerAddress);
        serverSocket = new ServerSocket(getPort());
        Toast.makeText(context, "Je suis le serveur", Toast.LENGTH_LONG).show();
        clients.add(serverSocket.accept());
        clients.get(0).getOutputStream().write("Bonjour client".getBytes());
    }

}
