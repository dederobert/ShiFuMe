package fr.m2ihm.a1819.shi_fu_me.p2p;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client extends Common {

    Socket socket;
    byte[] bytes = new byte[1024];
    private InetAddress ownerAddress;

    public Client(Context context, InetAddress groupOwnerAddress) {
        super(context);
        this.ownerAddress = groupOwnerAddress;
        Toast.makeText(context, "Je suis le client", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(getOwnerAddress(), 8888));
            socket.getInputStream().read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Client", new String(bytes));
        return null;
    }

    public InetAddress getOwnerAddress() {
        return ownerAddress;
    }
}
