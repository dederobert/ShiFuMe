package fr.m2ihm.a1819.shi_fu_me.p2p.listeners;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.widget.Toast;

public final class PeersListener implements WifiP2pManager.PeerListListener {

    private Context context;

    public PeersListener(Context context) {
        this.context = context;
    }


    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers) {
        Toast.makeText(context, "Pair disponible", Toast.LENGTH_LONG).show();
        Log.d("p2p", peers.getDeviceList().toString());
    }
}
