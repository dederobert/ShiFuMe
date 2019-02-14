package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client extends Common {

    Socket socket;
    byte[] bytes = new byte[1024];

    public Client(Context context, InetAddress groupOwnerAddress) throws IOException {
        super(context, groupOwnerAddress);
        socket = new Socket();
        Toast.makeText(context, "Je suis le client", Toast.LENGTH_LONG).show();
        socket.connect(new InetSocketAddress(getOwnerAddress(), getPort()));
        socket.getInputStream().read(bytes);
        Log.d("Client", new String(bytes));
    }
}
