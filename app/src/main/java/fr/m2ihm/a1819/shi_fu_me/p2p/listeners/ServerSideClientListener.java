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

        if (server.allChoiceIsSet()){
            //Set opp choice for all other client
            server.getClient(0).setOpponentChoice(server.getChoice(1));
            server.getClient(1).setOpponentChoice(server.getChoice(0));
            server.resetChoice();
        }

    }
}
