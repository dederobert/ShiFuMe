package fr.m2ihm.a1819.shi_fu_me.p2p.listeners;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;


import fr.m2ihm.a1819.shi_fu_me.activities.InGameActivity;
import fr.m2ihm.a1819.shi_fu_me.p2p.Client;
import fr.m2ihm.a1819.shi_fu_me.p2p.Server;

public final class ConnectionListener implements WifiP2pManager.ConnectionInfoListener {

    private final InGameActivity inGameActivity;

    public ConnectionListener(InGameActivity inGameActivity) {
        this.inGameActivity = inGameActivity;
    }


    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {

        if (info.groupFormed) {//Si il s'agit d'une connection
            if (!info.isGroupOwner) { //Si on est le client
                Log.d("[Client]", info.groupOwnerAddress.toString());
                inGameActivity.getGame().setClient(new Client(inGameActivity, info.groupOwnerAddress, new ClientListener(this.inGameActivity.getGame()), inGameActivity.getGame()));
            } else { //Si on est le serveur
                Log.d("[server]", info.groupOwnerAddress.toString());
                inGameActivity.getGame().setClient(new Client(inGameActivity, info.groupOwnerAddress, new ClientListener(this.inGameActivity.getGame()), inGameActivity.getGame()));
                inGameActivity.getGame().setServer(new Server(inGameActivity, inGameActivity.getGame()));
                if (inGameActivity.getGame().getServer() != null) {
                    inGameActivity.getGame().getServer().start();
                }
            }
            if (inGameActivity.getGame().getClient() != null) {
                inGameActivity.getGame().getClient().start();
            }
        }
    }
}
