package fr.m2ihm.a1819.shi_fu_me.p2p.listeners;

import fr.m2ihm.a1819.shi_fu_me.models.Choice;
import fr.m2ihm.a1819.shi_fu_me.p2p.Server;
import fr.m2ihm.a1819.shi_fu_me.p2p.ServerSideClient;
import fr.m2ihm.a1819.shi_fu_me.p2p.listeners.callbacks.ServerSideClientCallBack;

public final class ServerSideClientListener implements ServerSideClientCallBack {

    private final Server server;

    public ServerSideClientListener(Server server) {
        this.server = server;
    }

    @Override
    public void onReceivePlayerChoice(Choice choice, int clientIndex) {
       //Set the choice on server
        server.setChoice(choice, clientIndex);

        //Set opp choice for all other client
        int i = 0;
        for (ServerSideClient client: server.getClients()) {
            if (i++ != clientIndex)
                client.setOpponentChoice(choice);
        }
    }
}
